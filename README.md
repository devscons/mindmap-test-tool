# Mindmap command-line test tool
cli tests tool that eats test definitions in [mindmap] (src/main/resources/calc_tests.mm) format

And and executes tests against a remote REST service.

Default host is http://calculator.neueda.lv

# How to run:

checkout repo
```
mvn clean install
```
```
mvn exec:java - executes tests using default mindmap from resources and default host
```
```
mvn exec:java -Dmindmap=<path to mindmap> - using your own mindmap
```
```
mvn exec:java -Dmindmap=<path to mindmap> - using your own mindmap and default host
```
```
mvn exec:java -Dhost=<url of rest endpoint to test> -Dmindmap=<path to mindmap> - using your own mindmap and host
```

