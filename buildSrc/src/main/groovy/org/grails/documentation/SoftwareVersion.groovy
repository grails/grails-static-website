package org.grails.documentation

import groovy.transform.CompileStatic

@CompileStatic
class SoftwareVersion implements Comparable<SoftwareVersion> {

    int major
    int minor
    int patch

    Snapshot snapshot

    String versionText

    static SoftwareVersion build(String version) {
        String[] parts = version ? version.split("\\.") : null
        SoftwareVersion softVersion
        if (parts && parts.length >= 3) {
            softVersion = new SoftwareVersion()
            softVersion.versionText = version
            softVersion.major = parts[0].toInteger()
            softVersion.minor = parts[1].toInteger()
            if (parts.length > 3) {
                softVersion.snapshot = new Snapshot(parts[3])
            } else if (parts[2].contains('-')) {
                String[] subParts = parts[2].split("-")
                softVersion.patch = subParts.first() as int
                softVersion.snapshot = new Snapshot(subParts[1..-1].join("-"))
            } else {
                softVersion.patch = parts[2].toInteger()
            }
        }
        softVersion
    }

    boolean isSnapshot() {
        snapshot != null
    }

    @Override
    int compareTo(SoftwareVersion o) {
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

