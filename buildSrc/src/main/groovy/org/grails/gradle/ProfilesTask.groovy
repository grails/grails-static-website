package org.grails.gradle

import groovy.transform.CompileStatic
import org.grails.documentation.ProfilesPage
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

@CompileStatic
class ProfilesTask extends DefaultTask {

    static final String PAGE_NAME_PROFILES = "profiles.html"
    public static final String TEMP = "temp"

    @Input
    final Property<File> profiles = project.objects.property(File)

    @OutputDirectory
    final Property<File> output = project.objects.property(File)

    @TaskAction
    void renderDocsPage() {
        File build = output.get()
        File temp = new File(build.absolutePath + "/" + TEMP)
        temp.mkdir()

        File output = new File(temp.getAbsolutePath() + "/" + PAGE_NAME_PROFILES)
        output.createNewFile()
        output.text = "title: Profiles | Grails Framework\nbody: docs\n---\n" +
                ProfilesPage.mainContent(profiles.get())
    }
}
