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

Grails framework 4 was the first version of the Framework that follows semantic versioning. Grails framework 5.1.0 is a minor release. Because of that, it should not contain breaking changes. It contains new features, the patches introduced in Grails 5.0.x releases and minor upgrades to Grails dependencies such as SpringBoot, Micronaut or GORM. Please, upgrade quickly to get the latest updates into your Grails 5 applications.

## Upgrading to Grails Framework 5.1

As this is a minor release, updating to Grails framework 5.1 should be pretty straightforward. Here are step-by step instructions:

In your `gradle.properties` file,

- Update property `grailsVersion` to 5.1.0
- Update property `gorm.version` to 7.2.0

In your `build.gradle` file

- Update Gradle `hibernate5` plugin to 7.2.0
- Under dependencies, update `hibernate-core` to 5.6.2.Final

## The Road Ahead

- Spring Security Core Plugin
- Major updates to the Grails CLI

## Need help upgrading to Grails® framework 5.1?

We are excited with the release of Grails 5.1 and looking forward to hearing about your experience updating the applications. Please feel free to reach out to us if you have any questions or need any help updating your applications. We are here to [support](https://grails.org/support.html) you.
