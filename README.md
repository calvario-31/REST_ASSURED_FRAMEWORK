To run all:

    mvn clean test

To run with tags:

    mvn clean test -DsuiteName=${suiteName}

Example:

    mvn clean test -DsuiteName=regression
