package org.grails.markdown;

import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import java.util.Collections;

public class MarkdownUtil {
    final static DataHolder OPTIONS = new MutableDataSet()
            .set(Parser.EXTENSIONS, Collections.singletonList(TablesExtension.create()))
            .toImmutable();

    final static Parser PARSER = Parser.builder(OPTIONS).build();
    final static HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).build();

    public static String htmlFromMarkdown(String input) {
        Node document = PARSER.parse(input);
        return RENDERER.render(document);
    }
}
