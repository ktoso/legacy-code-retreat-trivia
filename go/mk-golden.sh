#!/bin/sh
go build -o gengolden golden.go trivia.go

mkdir -p golden
for i in `seq 1 10000`; do
  ./gengolden -seed=$i > "golden/$i.out"
done
