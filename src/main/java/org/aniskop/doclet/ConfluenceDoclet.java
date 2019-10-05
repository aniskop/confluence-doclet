package org.aniskop.doclet;

import com.sun.javadoc.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/*
cd ~/development/confluence-doclet
javadoc -sourcepath ./src/main/java -doclet org.aniskop.doclet.ConfluenceDoclet -docletpath ./target/confluence-doclet-1.0-jar-with-dependencies.jar -cp $JAVA_HOME/lib/tools.jar  org.aniskop.doclet
*/


/*Confluence rest body
* {
    "type": "page",
    "title": "new page from REST wiki 23",
    "space": {
        "key": "~pavans"
    },
    "body": {
        "storage": {
            "value": "{toc}\nh1. miau",
            "representation": "wiki"  <--- wiki markuup format
        }
    }
}*/

/**
 * Hello world!
 */
public class ConfluenceDoclet {

    public static boolean start(RootDoc root) {

        System.out.println("=== Confluence doclet ===");
        ConfluenceDoclet doclet = new ConfluenceDoclet();
        doclet.generateClassPages(root.classes());

        return true;
    }

    private void generateClassPages(ClassDoc[] classes) {
        if (classes != null && classes.length > 0) {
            for (ClassDoc c : classes) {
                generateClassPage(c);

                break; //TODO just for test
                //savePage(content);
            }
        }
    }

    private void savePage(String content) {
        if (content != null && content.length() > 0) {
            System.out.println(content);
        }
    }

    private void generateClassPage(ClassDoc theClass) {
        Configuration cfg = new Configuration(new Version("2.3.23"));
        cfg.setClassForTemplateLoading(this.getClass(), "/wiki-templates/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, Object> input = new HashMap<String, Object>();

        //input.put("title", "Vogella example");

        /*input.put("exampleObject", new ValueExampleObject("Java object", "me"));

        List<ValueExampleObject> systems = new ArrayList<ValueExampleObject>();
        systems.add(new ValueExampleObject("Android", "Google"));
        systems.add(new ValueExampleObject("iOS States", "Apple"));
        systems.add(new ValueExampleObject("Ubuntu", "Canonical"));
        systems.add(new ValueExampleObject("Windows7", "Microsoft"));
        input.put("systems", systems);*/
        input.put("class", new ClassAdapter(theClass));

        Template template = null;
        try {
            template = cfg.getTemplate("class-page.ftl");
            if (template != null) {
                template.process(input, new OutputStreamWriter(System.out));
            }
        } catch (IOException e) {
            //TODO exception handling
            e.printStackTrace();
        } catch (Exception ee) {
            //TODO exception handling
            ee.printStackTrace();
        }

    }

    private String generateMethodsSummary(MethodDoc[] methods) {
        StringBuilder summary = new StringBuilder();
        if (methods != null && methods.length > 0) {
            for (MethodDoc m : methods) {
                summary.append(generateMethodSummary(m));

            }
        }
        return summary.toString();
    }

    private String generateMethodSummary(MethodDoc method) {
        final String TEMPLATE = "<tr><td>%s</td><td>%s</td></tr>";
        return String.format(TEMPLATE, method.returnType().typeName(), method.name());
    }

    private void generatePackagePages() {

    }

    private void generatePackagePage() {

    }



}