# Commons Lang

Commons Lang provides a host of helper utilities for the java.lang API.
String, Date manipulation methods, scheduling helper classes, methods,
thread, future classes, methods, pagination, pattern matching and more

# Compatibility Matrix

| Commons Lang Version | Java Version |
|----------------------|------------|
| 1.X.X                | 1.8 >=     |
| 2.X.X                | 17         |


# Commons Lang

![Build](https://github.com/inomera/commons-lang/workflows/Build/badge.svg)


| Artifact                   | Version                                                                                                                                                                                                                                    |
|----------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| lock-provider-api          | [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.inomera.telco.commons/commons-lang/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.inomera.telco.commons/commons-lang)                   |

# Usage

## With Maven

```xml
<dependency>
  <groupId>com.inomera.telco.commons</groupId>
  <artifactId>commons-lang</artifactId>
  <version>2.0.2</version>
</dependency>

<dependency>
  <groupId>com.inomera.telco.commons</groupId>
  <artifactId>commons-lang</artifactId>
  <version>1.1.1</version>
</dependency>

```

## With Gradle

```groovy
implementation 'com.inomera.telco.commons:commons-lang:2.0.2'
implementation 'com.inomera.telco.commons:commons-lang:1.1.1'
```
 

# Publishing
To publish a version to maven repository, 
you should edit your local gradle.properties file.

The file is: `/path-to-user-home/.gradle/gradle.properties`

For example: `~/.gradle/gradle.properties`

Add credentials for nexus repository to `gradle.properties` file.

Example `gradle.properties` file:

```
telcoTeamUsername=********
telcoTeamPassword=************************
```

Then execute `gradle` `publish` task on the project.

For example, to publish the project `lock-provider`, 
you need to execute the following command in project root:

```
gradle :lock-provider:publish
``` 

The repository will not allow you to publish the same version twice.
You need to change version of the artifact every time you want to publish.

You can change version in `build.gradle` file of the sub-project.

```
build.gradle > publishing > publications > mavenJava > version
```

Please change the version wisely.
