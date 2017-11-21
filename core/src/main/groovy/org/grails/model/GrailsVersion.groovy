package org.grails.model

import groovy.transform.CompileStatic

@CompileStatic
class GrailsVersion implements Comparable<GrailsVersion> {

    int major
    int minor
    int patch

    Snapshot snapshot

    String versionText

    static GrailsVersion build(String version) {
        String[] parts = version.split("\\.")
        GrailsVersion grailsVersion
        if (parts.length >= 3) {
            grailsVersion = new GrailsVersion()
            grailsVersion.versionText = version
            if (parts.length > 3) {
                grailsVersion.snapshot = new Snapshot(parts[3])
            }
            grailsVersion.major = parts[0].toInteger()
            grailsVersion.minor = parts[1].toInteger()
            grailsVersion.patch = parts[2].toInteger()
        }
        grailsVersion
    }

    boolean isSnapshot() {
        snapshot != null
    }

    @Override
    int compareTo(GrailsVersion o) {
        int majorCompare = this.major <=> o.major
        if (majorCompare != 0) {
            return majorCompare
        }

        int minorCompare = this.minor <=> o.minor
        if (minorCompare != 0) {
            return minorCompare
        }

        int patchCompare = this.patch <=> o.patch
        if (patchCompare != 0) {
            return patchCompare
        }

        if (this.isSnapshot() && !o.isSnapshot()) {
            return -1
        } else if (!this.isSnapshot() && o.isSnapshot()) {
            return 1
        } else if (this.isSnapshot() && o.isSnapshot()) {
            return this.getSnapshot() <=> o.getSnapshot()
        } else {
            return 0
        }
    }
}

