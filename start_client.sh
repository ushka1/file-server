#!/bin/sh

# run java
#   --enable-preview = activate preview features
#   -cp = classpath, specify where to find user class files, acts location of default package
#   arg[0] = full class name of file to run
java --enable-preview -cp ./bin client.Main
