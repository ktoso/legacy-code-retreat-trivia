#!/bin/bash -e

g++ *.cpp -o game

rm test/*
while read seed; do
    echo seed: $seed
    ./game $seed > test/run.$seed 
    diff test/run.$seed reference/game_reference.$seed 
done < seeds.txt
