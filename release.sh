#!/bin/bash

mvn release:clean release:prepare
if [ $? -eq 0 ]; then
  mvn release:perform
fi
