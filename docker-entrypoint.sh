#!/bin/bash
set -e

DIR="/home/seluser/page-scan/"
if [ -z "$(ls -A $DIR)" ]; then
  git clone https://github.com/maciejkocol/pagescan.git "$DIR"
fi

exec "$@"