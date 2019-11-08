package org.grails.model

interface Guide {
    String getVersionNumber()
    List<String> getAuthors()
    String getCategory()
    String getName()
    String getTitle()
    String getSubtitle()
    String getGithubBranch()
    String getGithubSlug()
    List<String> getTags()
    Date getPublicationDate()
}