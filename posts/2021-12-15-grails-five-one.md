---
title: Grails 5.1 Released
date: Dec 15, 2021
description: Grails framework 5.1.0 improves Gradle 7.2 support and upgrades to Spring Boot 2.6.1, GORM 7.2 and Micronaut 3.2.0
author: Puneet Behl
image: grails-blog-index-2.png
---

# [%title]

[%author]

[%date]

The [Grails Foundation™](https://grails.org/foundation/index.html) is pleased to announce a new minor release: [**Grails framework 5.1**](https://github.com/grails/grails-core/releases/tag/v5.1.0)!

This release of the Grails framework includes a number of bug fixes and improvements for Gradle 7.2 support, plus some updates to our plugins and dependencies.

The dependencies that have been updated include:

- Spring Boot 2.6.1
- Micronaut 3.2
- GORM for Hibernate 7.2, which updates to Hibernate 5.6
- GORM for MongoDB 7.2, which updates to underline Mongo driver 4.4

Check-out the release notes from these libraries to learn about the new features that are included with those releases.

A major change that accompanies Spring Boot 2.6 is that circular references are prohibited. In order to maintain backwards compatibility for Grails users, Grails framework 5.1 explicitly enables circular references by default. This will no longer be the case once Grails framework 6 is released.  

## A Note About Semantic Versioning

Grails framework 4 is the first version of the Framework that follows semantic versioning. The release of Grails framework 5.1 is not expected to break Grails 5 applications, and we recommend that you upgrade quickly to get the latest updates into your Grails 5 applications.

## Upgrading to Grails Framework 5.1

As this is a minor release, updating to Grails framework 5.1 should be pretty straightforward. Here are step-by step instructions:

In your `gradle.properties` file,

- Update property `grailsVersion` to 5.1.0
- Update property `gorm.version` to 7.2.0

In your `build.gradle` file

- Update Gradle `hibernate5` plugin to 7.2.0
- Under dependencies, update `hibernate-core` to 5.6.2.Final

## The Road Ahead

### Spring Security Core Plugin

Currently, the Spring Security Core Grails plugin still uses Spring Security Core 5.1.13.RELEASE which is EOL. We are planning to update the plugin to use the latest version of Spring Security. See [Spring Security Versions · spring-projects/spring-security Wiki · GitHub](https://github.com/spring-projects/spring-security/wiki/Spring-Security-Versions#released-versions) for more information.

### Leveling up the Grails CLI!

The current implementation of Grails CLI is difficult to test and unadaptable. For example, configuring automation tools to update dependencies is impossible. Also, the CLI does not work offline and it is very difficult to customize it. We are planning to work on implementing a CLI which is super flexible, easy to customize, and basically solves the above limitations.

### Consolidating Grails Plugin

Currently, the Grails plugin is distributed over in the multiple Github organization such as [gpc - Grails Plugin Collective · GitHub](https://github.com/gpc), [Grails Plugins · GitHub](https://github.com/grails-plugins), and [grails3-plugins · GitHub](https://github.com/grails3-plugins). We are planning to consolidate a list of all active plugins into one single place. Please stay tuned to our blogs, we will soon share more information around the same.


