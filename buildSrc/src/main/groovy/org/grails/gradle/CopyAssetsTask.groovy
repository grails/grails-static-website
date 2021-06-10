package org.grails.gradle

import groovy.transform.CompileStatic
import org.gradle.api.Action
import org.gradle.api.DefaultTask
import org.gradle.api.file.CopySpec
import org.gradle.api.provider.Property
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

@CompileStatic
class CopyAssetsTask extends DefaultTask {

    static final String[] FONT_EXTENSIONS = ["*.eot", "*.ttf", "*.woff", "*.woff2"] as String[]
    static final String[] JAVASCRIPT_EXTENSIONS = ["*.js"] as String[]
    static final String[] CSS_EXTENSIONS = ["*.css"] as String[]
    static final String[] IMAGE_EXTENSIONS = ["*.ico", "*.png", "*.svg", "*.jpg", "*.jpeg", "*.gif"]
    static final String[] FILE_EXTENSIONS = ["*.jar", "*.md5"]

    @InputDirectory
    final Property<File> assets = project.objects.property(File)

    @OutputDirectory
    final Property<File> output = project.objects.property(File)

    @TaskAction
    void copyAssets() {
        copyImages()
        copyCss()
        copyJavascripts()
        copyFonts()
        copyFiles()
    }

    File dist() {
        new File(output.get().absolutePath + "/" + RenderSiteTask.DIST)
    }

    List<String> recursiveIncludes(String[] extensions) {
        extensions.collect { ["$it", "**/$it"] }.flatten() as List<String>
    }

    void copyImages() {
        File srcDir = new File(assets.get().absolutePath + '/images')
        File destDir = new File(dist().absolutePath + '/images')
        destDir.mkdir()

        project.copy(new Action<CopySpec>() {
            @Override
            void execute(CopySpec copySpec) {
                copySpec.from(srcDir)
                copySpec.into(destDir)
                copySpec.include(recursiveIncludes(IMAGE_EXTENSIONS))
                copySpec.setIncludeEmptyDirs(false)
            }
        })
    }

    void copyCss() {
        File stylesheets = new File(assets.get().absolutePath + '/stylesheets')
        File outputStylesheets = new File(dist().absolutePath + '/stylesheets')
        outputStylesheets.mkdir()
        project.copy(new Action<CopySpec>() {
            @Override
            void execute(CopySpec copySpec) {
                copySpec.from(stylesheets)
                copySpec.into(outputStylesheets)
                copySpec.include(CSS_EXTENSIONS)
            }
        })
    }

    void copyFonts() {
        File outputFonts = new File(dist().absolutePath + '/fonts')
        outputFonts.mkdir()
        File fonts = new File(assets.get().absolutePath + '/fonts')
        project.copy(new Action<CopySpec>() {
            @Override
            void execute(CopySpec copySpec) {
                copySpec.from(fonts)
                copySpec.into(outputFonts)
                copySpec.include(FONT_EXTENSIONS)
            }
        })
    }

    void copyJavascripts() {
        File outputJavascripts = new File(dist().absolutePath + '/javascripts')
        outputJavascripts.mkdir()
        File javascripts = new File(assets.get().absolutePath + '/javascripts')
        project.copy(new Action<CopySpec>() {
            @Override
            void execute(CopySpec copySpec) {
                copySpec.from(javascripts)
                copySpec.into(outputJavascripts)
                copySpec.include(JAVASCRIPT_EXTENSIONS)
            }
        })
    }

    void copyFiles() {
        File outputFiles = new File(dist().absolutePath + '/files')
        outputFiles.mkdir()
        File files = new File(assets.get().absolutePath + '/files')
        project.copy(new Action<CopySpec>() {
            @Override
            void execute(CopySpec copySpec) {
                copySpec.from(files)
                copySpec.into(outputFiles)
                copySpec.include(recursiveIncludes(FILE_EXTENSIONS))
            }
        })
    }
}
