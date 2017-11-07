package org.grails

trait ReadFileUtils {

    String readFileContent(String filename) {
        File file = getFileFromURL(filename)
        if ( !file || !file.exists() ) {
            file = new File("build/resources/main/$filename")
            if ( !file || !file.exists() ) {
                return null
            }
        }
        file.text
    }

    File getFileFromURL(String filename) {
        URL url = this.getClass().getClassLoader().getResource(filename)
        File file = null
        try {
            file = new File(url.toURI())
        } catch (URISyntaxException e) {
            file = new File(url.getPath())
        } finally {
            return file
        }
    }
}
