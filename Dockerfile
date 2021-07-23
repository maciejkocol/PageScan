FROM selenium/standalone-chrome:latest

# Variables
ENV MAVEN_VERSION=3.8.1 \
  MAVEN_HOME=/usr/lib/mvn \
  NODE_VERSION=16.5.0 \
  SHELL=/bin/bash \
  LANG=en_US.UTF-8 \
  CSVER=3.11.0 \
  PATH=$MAVEN_HOME/bin:/usr/local/bin:$PATH

COPY docker-entrypoint.sh /usr/local/bin/

# Packages
USER root
RUN apt-get update \
  && apt-get install -y openjdk-11-jdk \
  curl \
  dumb-init \
  && curl -sL https://deb.nodesource.com/setup_12.x | sudo -E bash - \
  && apt-get install -y \
  nodejs \
  maven \
  net-tools \
  git \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/* \
  && rm -rf /var/cache/oracle-jdk11-installer \
  && npm install -g npmlog

# https://wiki.debian.org/Locale#Manually
RUN sed -i "s/# en_US.UTF-8/en_US.UTF-8/" /etc/locale.gen \
  && locale-gen \
  && chsh -s /bin/bash \
  && echo "seluser ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers.d/nopasswd

# Install fixuid
RUN curl -SsL https://github.com/boxboat/fixuid/releases/download/v0.4/fixuid-0.4-linux-amd64.tar.gz | tar -C /usr/local/bin -xzf - \
  && chown root:root /usr/local/bin/fixuid \
  && chmod 4755 /usr/local/bin/fixuid \
  && mkdir -p /etc/fixuid \
  && printf "user: seluser\ngroup: seluser\n" > /etc/fixuid/config.yml

# Install Code-Server
RUN cd /tmp && \
  curl -SsL https://github.com/cdr/code-server/releases/download/v${CSVER}/code-server-${CSVER}-linux-amd64.tar.gz | tar -xzf - \
  && mv code-server* /usr/local/lib/code-server \
  && ln -s /usr/local/lib/code-server/code-server /usr/local/bin/code-server

USER seluser
EXPOSE 8080
WORKDIR /home/seluser/page-scan

ENTRYPOINT ["docker-entrypoint.sh"]
CMD ["dumb-init", "fixuid", "-q", "/usr/local/bin/code-server", "--host", "0.0.0.0", "/home/seluser/page-scan"]