package org.grails.documentation

import spock.lang.Specification

class SoftwareVersionSpec extends Specification {

    void "test build snapshot SoftwareVersion from String(#versionText)"() {

        when:
        SoftwareVersion softwareVersion = SoftwareVersion.build(versionText)

        then:
        noExceptionThrown()
        softwareVersion.snapshot
        softwareVersion.snapshot.buildSnapshot == isSnapshot
        softwareVersion.snapshot.releaseCandidate == isReleaseCandidate
        softwareVersion.snapshot.milestone == isMilestone

        where:
        versionText            || isSnapshot | isReleaseCandidate | isMilestone
        '5.0.0-SNAPSHOT'       || true       | false              | false
        '5.0.0-BUILD-SNAPSHOT' || true       | false              | false
        '5.0.0.BUILD-SNAPSHOT' || true       | false              | false
    }

    void "test build release-candidate SoftwareVersion from String(#versionText)"() {

        when:
        SoftwareVersion softwareVersion = SoftwareVersion.build(versionText)

        then:
        noExceptionThrown()
        softwareVersion.snapshot
        softwareVersion.snapshot.buildSnapshot == isSnapshot
        softwareVersion.snapshot.releaseCandidate == isReleaseCandidate
        softwareVersion.snapshot.milestone == isMilestone
        softwareVersion.snapshot.releaseCandidateVersion == rcVersion

        where:
        versionText || isSnapshot | isReleaseCandidate | isMilestone | rcVersion
        '5.0.0-RC1' || false      | true               | false       | 1
        '5.0.0-RC2' || false      | true               | false       | 2
        '5.0.0.RC1' || false      | true               | false       | 1
    }

    void "test build milestone SoftwareVersion from String(#versionText)"() {

        when:
        SoftwareVersion softwareVersion = SoftwareVersion.build(versionText)

        then:
        noExceptionThrown()
        softwareVersion.snapshot
        softwareVersion.snapshot.buildSnapshot == isSnapshot
        softwareVersion.snapshot.releaseCandidate == isReleaseCandidate
        softwareVersion.snapshot.milestone == isMilestone
        softwareVersion.snapshot.milestoneVersion == milestoneVersion

        where:
        versionText || isSnapshot | isReleaseCandidate | isMilestone | milestoneVersion
        '5.0.0-M1'  || false      | false              | true        | 1
        '5.0.0-M2'  || false      | false              | true        | 2
        '5.0.0.M2'  || false      | false              | true        | 2
    }

    void "test that SoftwareVersion is null when build from String(#versionText)"() {

        when:
        SoftwareVersion softwareVersion = SoftwareVersion.build(versionText)

        then:
        noExceptionThrown()
        softwareVersion == null

        where:
        versionText << ['', null]
    }
}
