package org.grails.documentation

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import org.yaml.snakeyaml.Yaml

@CompileStatic
class SiteMap {

    static List<SoftwareVersion> versions(File releases) {
        Yaml yaml = new Yaml()
        assert releases.exists()
        Map model = yaml.load(releases.newDataInputStream())
        model['releases'].collect { Map release ->
            SoftwareVersion.build(release['version'] as String)
        }.sort()
    }

    static SoftwareVersion latestVersion(File releases) {
        List<SoftwareVersion> versions = stableVersions(releases)
        versions ? versions.get(0) : null
    }

    static List<String> olderVersions(File releases) {
        stableVersions(releases).reverse().drop(1).collect { it.versionText }
    }

    static List<SoftwareVersion> stableVersions(File releases) {
        (versions(releases).findAll { SoftwareVersion softwareVersion ->
            !softwareVersion.isSnapshot()
        } as List<SoftwareVersion>).sort { a, b -> b <=> a }
    }

    @CompileDynamic
    static List<SoftwareVersion> preReleaseVersions(File releases) {
        versions(releases).findAll { SoftwareVersion softwareVersion ->
            Snapshot snapshot = softwareVersion.snapshot
            snapshot?.isMilestone() || snapshot?.isReleaseCandidate()
        }.sort { a, b -> b <=> a }
    }

    static SoftwareVersion latestPreReleaseVersion(File releases) {
        List<SoftwareVersion> versions = preReleaseVersions(releases)
        versions ? versions.get(0) : null
    }
}
