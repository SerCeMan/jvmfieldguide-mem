#!/usr/bin/env bash

set -eu -o pipefail

log() {
  local prog
  prog=$(basename "$0")
  echo "$(date '+[%Y-%m-%d %H:%M:%S]') ${prog}: INFO: $@"
}

error() {
  local prog
  prog=$(basename "$0")
  echo "$(date '+[%Y-%m-%d %H:%M:%S]') ${prog}: ERROR: $@" >&2
  exit 1
}

run() {
  local names
  set +e
  names=$(docker ps --format "{{.Names}} {{.Image}}" | grep jvmmemfieldguide)
  set -e
  if [[ "${names}" =~ .*jvmmemfieldguide.* ]]; then
    docker exec -it "${names% *}" $@
  else
    docker run --privileged -it \
        --memory 2g \
        -v "$(pwd)":/root/proj/ \
        -p 8888:8888 \
        -p 9898:9898 \
        jvmmemfieldguide:latest $@
  fi
}

gcgc() {
  cd /root/GCGC/src/notebooks/
  jupyter notebook --allow-root --ip 0.0.0.0
  cd -
}

mainalloc() {
  echo "$@"
  java_opts="$1"
  shift
  echo "$@"
#  ./gradlew --no-daemon build
#  JAVA_OPTS=(
#    -XX:+HeapDumpOnOutOfMemoryError
#    -XX:HeapDumpPath=heapdump.hprof
#    # optionally
#    -XX:+ExitOnOutOfMemoryError
#  )
#  JAVA_OPTS=(
#    -Xlog:safepoint*,gc*:file=gc/gc.log:time,level,tags:filecount=5,filesize=5000k
#  )
#  java "${JAVA_OPTS[@]}" -cp "build/classes/java/main/" me.serce.LongRunningAlloc
  java "${JAVA_OPTS[@]}" -cp "build/classes/java/main/" me.serce.MainAlloc -- $@
  JAVA_OPTS=(-Xmx6G) && java "${JAVA_OPTS[@]}" -cp "build/classes/java/main/" me.serce.MainAlloc -- $@
}

build() {
  docker build -t jvmmemfieldguide .
}

main() {
  if [[ $# -eq 0 ]]; then
    error "provide params"
  fi
  java_opts=""
  while [[ $# -gt 0 ]]; do
    case $1 in
      run)
        shift
        run "$@"
        break
        ;;
      gcgc)
        shift
        gcgc
        break
        ;;
      --java-opts)
        shift
        java_opts="$1"
        shift
        ;;
      mainalloc)
        shift
        mainalloc "${java_opts}" "$@"
        break
        ;;
      build)
        shift
        build "$@"
        break
        ;;
      *)
        error "unknown option"
        ;;
    esac
  done
}

main "$@"
