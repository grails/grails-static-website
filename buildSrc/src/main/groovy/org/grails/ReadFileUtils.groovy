package org.grails

class ReadFileUtils {

    static String readFileContent(ClassLoader classLoader, String filename) {
        File file = getFileFromURL(classLoader, filename)
        if ( !file || !file.exists() ) {
            file = new File("buildSrc/build/resources/main/$filename")
            if ( !file || !file.exists() ) {
                return null
            }
        }
        file.text
    }

    static File getFileFromURL(ClassLoader classLoader, String filename) {
        URL url = classLoader.getResource(filename)
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
