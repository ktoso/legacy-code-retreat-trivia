#!/bin/sh
go build -o under_test golden.go trivia.go

mkdir -p out
rm out/*
for i in `seq 1 1000`; do
  ./under_test -seed=$i > out/$i.out

  failed=`diff "out/$i.out" "golden/$i.out" | wc -l`

  if [ "$failed" != "0" ] ; then
    echo "FAIL for seed: $i"
  fi

done
