#!/bin/bash

javac *.java
rmic Server
rmic Writer
rmiregistry 5000
