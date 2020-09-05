package org.grails.guides

import groovy.transform.CompileStatic

@CompileStatic
class MultiLanguageGuide implements Guide {
    List<String> authors
    String category
    Map<ProgrammingLanguage, String> githubSlugs
    Map<ProgrammingLanguage, List<String>> programmingLanguageTags
    String name
    String title
    String subtitle
    Date publicationDate
    String versionNumber

    @Override
    List<String> getTags() {
        List<List<String>> tags = programmingLanguageTags.collect { ProgrammingLanguage k, List<String> v -> v}
        tags << programmingLanguageTags.collect {ProgrammingLanguage k, List<String> v -> k.toString().toLowerCase()} as List<String>
        tags.flatten() as List<String>
    }
}
