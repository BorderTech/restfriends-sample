# RestFriends Sample

## Status

[![Build Status](https://travis-ci.com/BorderTech/restfriends-sample.svg?branch=master)](https://travis-ci.com/BorderTech/restfriends-sample)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=restfriends-sample&metric=alert_status)](https://sonarcloud.io/dashboard?id=restfriends-sample)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=restfriends-sample&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=restfriends-sample)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=restfriends-sample&metric=coverage)](https://sonarcloud.io/dashboard?id=restfriends-sample)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/8c2da2359ee348259906c5b76936651e)](https://www.codacy.com/app/BorderTech/restfriends-sample?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=BorderTech/restfriends-sample&amp;utm_campaign=Badge_Grade)

## Content

- [What is RestFriends Sample](#what-is-restfriends-sample)
- [Hosted Demo](#hosted-demo)
- [Build and run locally](#build-and-run-locally)
- [Contributing](#contributing)

## What is RestFriends Sample

A sample REST API with automated tests using JAX-RS, Jersey and [RestFriends](https://github.com/BorderTech/restfriends).

An [overview](https://github.com/BorderTech/restfriends/wiki/Automated-API-Testing---DRAFT) of the automated testing tools is also available.

## Hosted Demo

A demo of the sample REST API is available with its [Swagger UI](http://restfriends-sample-api.herokuapp.com/launchswagger) and [API Endpoint](http://restfriends-sample-api.herokuapp.com/api).

Example GET: http://restfriends-sample-api.herokuapp.com/api/v1/app/MYID

Note: The sample API allows any "id" to be entered. Using an id of "error" simulates an error condition.

## Build and Run Locally

If you wish to build the sample you will need [Apache Maven](https://maven.apache.org/) installed.

Minimum requirements are `Maven 3.3.9` and `Java 8`.

Follow these commands to fetch the sample source, build and run:

```
git clone https://github.com/bordertech/restfriends-sample.git

cd restfriends-sample

mvn install

cd sample-app-lde

mvn lde-exec:run
```

Access swagger ui with http://localhost:8082/lde/launchswagger

Perform a simple GET with http://localhost:8082/lde/api/v1/app/MYID

## Contributing

Refer to these guidelines for [Workflow](https://github.com/BorderTech/java-common/wiki/Workflow) and [Releasing](https://github.com/BorderTech/java-common/wiki/Releasing).
