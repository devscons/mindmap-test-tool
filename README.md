# Mindmap command-line test tool
cli tests tool that eats test definitions in [mindmap] (src/main/resources/calc_tests.mm) format

And and executes tests against a remote REST service.

# How to run:

checkout repo
```
mvn clean install
```
```
mvn exec:java - executes tests using default mindmap from resources
```
```
mvn exec:java -Dmindmap=<path to mindmap> - using your own mindmap
```
```
mvn exec:java -Dhost=<url of rest endpoint to test> -Dmindmap=<path to mindmap> - using your own mindmap
```

