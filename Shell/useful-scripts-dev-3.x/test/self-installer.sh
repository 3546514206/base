#!/usr/bin/env bash

if [ ! -d "/tmp/useful-scripts-$USER" ]; then
  if type -P git &>/dev/null; then
    git clone https://github.com/oldratlee/useful-scripts.git "/tmp/useful-scripts-$USER"
  elif type -P svn &>/dev/null; then
    svn checkout https://github.com/oldratlee/useful-scripts/branches/release-3.x "/tmp/useful-scripts-$USER"
  else
    echo "fail to find command git/svn"
    return 1
  fi
fi

export PATH="$PATH:/tmp/useful-scripts-$USER/bin"
