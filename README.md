Javadoc doclet to transform javadoc comments into Atlassian Confluence pages. The output is in wiki markup syntax.

# Building
`mvn package`

# Testing
Sample classes for testing (to see the output being generated) are in the `org.aniskop.doclet.test` package in `src/test/java` directory.
```
javadoc -sourcepath ./src/test/java -doclet org.aniskop.doclet.ConfluenceDoclet -docletpath ./target/confluence-doclet-1.0-jar-with-dependencies.jar -cp $JAVA_HOME/lib/tools.jar  org.aniskop.doclet.test
```

# Supported Javadoc syntax elements
* First sentence as a method summary.
* `@param`, `@return`.
* `<code>`.
