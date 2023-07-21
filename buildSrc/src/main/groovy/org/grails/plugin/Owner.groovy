package org.grails.plugin

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Owner {
    String name;
    List<Plugin> plugins;
}
