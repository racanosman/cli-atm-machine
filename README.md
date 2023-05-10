ATM CLI Application
===

This application reads user operations from an input.txt file and processes this information to simulate
an interaction with an ATM machine.

A racan-atm.log file is located in the root directory of the project and will by default display 
some debugging logs. The log level can be changed using the log4j.properties file.

Prerequisites
---
Java 8+
Maven 3+

How to run the application
---
The application can be run with the run.sh script located in the root directory of the project

Future improvements
---
- Use property file to read input.txt
- More unit test coverage
- Error checking for input file
