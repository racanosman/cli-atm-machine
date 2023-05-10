#!/usr/bin/env bash

echo "========================================================================"
echo "                          Building project                              "
echo "========================================================================"
mvn clean dependency:copy-dependencies package

echo "========================================================================"
echo "                               STARTED                                  "
echo "========================================================================"
java -jar target/racan-atm-coding-test-1.0-SNAPSHOT.jar

echo "========================================================================"
echo "                               FINISHED                                 "
echo "========================================================================"
