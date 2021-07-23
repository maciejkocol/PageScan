#!/bin/bash
set -e

code-server --install-extension alexkrechik.cucumberautocomplete

DIR="/home/seluser/page-scan/"
if [ -z "$(ls -A $DIR)" ]; then
  git clone https://github.com/maciejkocol/pagescan.git "$DIR"
fi

exec "$@"