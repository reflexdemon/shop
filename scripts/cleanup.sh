#!/usr/bin/env bash

rm -rf src/dist

cd src/app

find . "-name" "*.map" -exec rm {} \;
find . "-name" "*.css" -exec rm {} \;
find . "-name" "*.js" -exec rm {} \;
