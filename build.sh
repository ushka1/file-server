#!/bin/sh

# compile java files
#   --enable-preview = activate preview features
#   --release = specify Java SE release that defines the feature
#   -d = output directory
#   -cp = classpath, specify where to find user class files, acts location of default package
#   arg[0] = sourcefiles glob pattern
javac --enable-preview --release 18 -d ./bin -cp ./src ./src/**/*.java

# copy resources (.properties)
cp -r src/server/resources bin/server/resources
