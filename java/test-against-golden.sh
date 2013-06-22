#!/bin/sh
mvn compile

mkdir -p out
rm out/*
for i in `seq 1 1000`; do
  java -cp target/classes:$HOME/.m2/repository/com/google/guava/guava/14.0.1/guava-14.0.1.jar \
    com.sckrk.legacycoderetreat.golden.GoldenGenerator \
    $i $i out

  failed=`diff "out/$i.out" "golden/$i.out" | wc -l`

  if [ "$failed" != "0" ] ; then
    echo "FAIL for seed: $i"
  fi

done
