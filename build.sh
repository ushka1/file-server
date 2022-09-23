#!/bin/sh

echo "Build started."

# compile java files
#   --enable-preview = activate preview features
#   --release = specify Java SE release that defines the feature
#   -d = output directory
#   -cp = classpath, specify where to find user class files, acts location of default package
#   arg[0] = sourcefiles glob pattern
echo "Compiling project."
javac --enable-preview --release 18 -d ./bin -cp ./src ./src/**/*.java
echo "Project compiled successfully."

# copy resources (.properties)
echo "Copying resources."
cp -r src/server/resources bin/server/resources
echo "Resources copied successfully."

echo "Build finished."
