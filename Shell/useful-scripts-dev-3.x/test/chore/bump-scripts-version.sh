#!/usr/bin/env bash
set -eEuo pipefail

################################################################################
# util functions
################################################################################

# NOTE: $'foo' is the escape sequence syntax of bash
readonly NL=$'\n' # new line

colorPrint() {
  local color=$1
  shift
  # if stdout is a terminal, turn on color output.
  #   '-t' check: is a terminal?
  #   check isatty in bash https://stackoverflow.com/questions/10022323
  if [ -t 1 ]; then
    printf '\e[1;%sm%s\e[0m\n' "$color" "$*"
  else
    printf '%s\n' "$*"
  fi
}

redPrint() {
  colorPrint 31 "$@"
}

yellowPrint() {
  colorPrint 33 "$@"
}

bluePrint() {
  colorPrint 36 "$@"
}

logAndRun() {
  local simple_mode=false
  [ "$1" = "-s" ] && {
    simple_mode=true
    shift
  }

  if $simple_mode; then
    echo "Run under work directory $PWD : $*"
    "$@"
  else
    bluePrint "Run under work directory $PWD :$NL$*"
    time "$@"
  fi
}

die() {
  redPrint "Error: $*" >&2
  exit 1
}

################################################################################
# biz logic
################################################################################

(($# != 1)) && die "need only 1 argument for version!$NL${NL}usage:$NL  $0 3.x.y"
readonly bump_version=$1

# adjust current dir to project dir
#
# Bash Pitfalls#5
#  http://mywiki.wooledge.org/BashPitfalls#cd_.24.28dirname_.22.24f.22.29
cd -P -- "$(dirname -- "$0")"/..

# Bash Pitfalls#1
#  http://mywiki.wooledge.org/BashPitfalls#for_f_in_.24.28ls_.2A.mp3.29
logAndRun find -D exec bin lib -type f -exec \
  sed -ri "s/^(.*\bPROG_VERSION\s*=\s*')\S*('.*)$/\1$bump_version\2/" -- \
  {} +
