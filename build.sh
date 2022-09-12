#!/bin/sh

echo "build start"

javac -d ./bin -cp ./src ./src/**/*.java
cp -r src/server/resources bin/server/resources

echo "build finish"
