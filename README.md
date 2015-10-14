# Mindmap command-line test tool
cli tests tool that eats test definitions in [mindmap] src/main/resources/calc_tests.mm format

# How to run:

checkout repo

mvn clean install

mvn exec:java - executes tests using default mindmap from resources

mvn exec: java -Dmindmap=<path to mindmap> - using your own mindmap

