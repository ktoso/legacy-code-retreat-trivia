#!/bin/sh
mvn compile
mkdir -p golden

java -cp target/classes:$HOME/.m2/repository/com/google/guava/guava/14.0.1/guava-14.0.1.jar \
  com.sckrk.legacycoderetreat.golden.GoldenGenerator \
  1 10000 golden
