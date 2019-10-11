package org.aniskop.doclet;

import com.sun.javadoc.*;
import com.sun.tools.doclets.standard.Standard;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/*
cd ~/development/confluence-doclet
javadoc -sourcepath ./src/main/java -doclet ConfluenceDoclet -docletpath
./target/confluence-doclet-1.0-jar-with-dependencies.jar -cp $JAVA_HOME/lib/tools.jar  org.aniskop.doclet
*/
/* QUICK COMPILE AND TEST
mvn package && javadoc -sourcepath ./src/test/java -doclet org.aniskop.doclet.ConfluenceDoclet -docletpath
./target/confluence-doclet-1.0-jar-with-dependencies.jar -cp $JAVA_HOME/lib/tools.jar  org.aniskop.doclet.test
*/



/*Confluence rest body
* {
    "type": "page",
    "title": "new page from REST wiki 23",
    "space": {
        "key": "~demospace"
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
// Extend Standard doclet class to make sure its command line arguments
// are available to the doclet.
public class ConfluenceDoclet extends Standard {

    private static Writer consoleWriter;
    private boolean useConsoleOut = true;

    public static boolean start(RootDoc root) {
        try {
            System.out.println("=== Confluence doclet ===");
            ConfluenceDoclet doclet = new ConfluenceDoclet();

            DocletOptions options = new DocletOptions(root.options());
            doclet.init(options);

            System.out.println(options.toString());
            String outDir = options.getOutputDir();
            System.out.println("Out dir " + outDir);

            Configuration templateConfig = doclet.createTemplateConfiguration();

            doclet.createPackageDirectories(root.specifiedPackages(), outDir);
            doclet.generatePackagePages(root.specifiedPackages(), templateConfig, outDir);
            //doclet.generateClassPages(root.classes(), templateConfig);

            return true;
        } catch (Exception e) {
            //TODO implement
            e.printStackTrace();
            return false;
        }
    }

    private void init(DocletOptions options) {
        useConsoleOut = "".equals(options.getOutputDir());
        if (useConsoleOut) {
            consoleWriter = new OutputStreamWriter(System.out);

        }
    }

    private boolean isConsoleOut() {
        return useConsoleOut;
    }

    private Writer createOutputWriter(String outDir, String name) throws IOException {
        if (isConsoleOut()) {
            return consoleWriter;
        } else {
            return new FileWriter(outDir + "/" + name);
        }
    }

    private Configuration createTemplateConfiguration() {
        Configuration cfg = new Configuration(new Version("2.3.23"));
        cfg.setClassForTemplateLoading(this.getClass(), "/wiki-templates/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        return cfg;
    }

    /**
     * NOTE: Without this method present and returning LanguageVersion.JAVA_1_5,
     * Javadoc will not process generics because it assumes LanguageVersion.JAVA_1_1
     *
     * @return language version (hard coded to LanguageVersion.JAVA_1_5)
     */
    public static LanguageVersion languageVersion() {
        return LanguageVersion.JAVA_1_5;
    }

    private void generateClassPages(ClassDoc[] classes, Configuration templateConfig) {
        if (classes != null && classes.length > 0) {
            for (ClassDoc c : classes) {
                if (!c.name().equals("AppTest")) { //TODO just for testing
                    generateClassPage(c, templateConfig);
                }
            }
        }
    }

    private void generateClassPage(ClassDoc theClass, Configuration templateConfig) {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("class", new ClassAdapter(theClass));

        //generatePage(templateConfig, input, "class-page.ftl");
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
        final String TEMPLATE = "<tr><td>%s</td><td>%s</td></ltr>";
        return String.format(TEMPLATE, method.returnType().typeName(), method.name());
    }

    private void generatePackagePages(PackageDoc[] packages, Configuration templateConfig, String outDir)
            throws IOException {
        if (packages != null && packages.length > 0) {
            for (PackageDoc p : packages) {
                generatePackagePage(p, templateConfig, createOutputWriter(outDir, p.name() + ".wiki"));
            }
        }
    }

    private File createDirectory(String parentDir, String name) {
        if (!isConsoleOut()) {
            File dir = new File(parentDir + "/" + name);
            //TODO process creation error, boolean return value
            System.out.println("Creating dir " + parentDir + "/" + name);
            dir.mkdir();
            return dir;
        } else {
            //TODO throw exception??
            return null;
        }
    }

    private void generatePage(Configuration templateConfig, Map<String, Object> input, String templateName,
                              Writer writer) {
        Template template = null;
        try {
            template = templateConfig.getTemplate(templateName);
            if (template != null) {
                template.process(input, writer);
            }
        } catch (IOException e) {
            //TODO exception handling
            e.printStackTrace();
        } catch (Exception ee) {
            //TODO exception handling
            ee.printStackTrace();
        }
    }

    private void generatePackagePage(PackageDoc p, Configuration templateConfig, Writer writer) {
        /*Configuration cfg = new Configuration(new Version("2.3.23"));
        cfg.setClassForTemplateLoading(this.getClass(), "/wiki-templates/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);*/

        //input.put("title", "Vogella example");

        /*input.put("exampleObject", new ValueExampleObject("Java object", "me"));

        List<ValueExampleObject> systems = new ArrayList<ValueExampleObject>();
        systems.add(new ValueExampleObject("Android", "Google"));
        systems.add(new ValueExampleObject("iOS States", "Apple"));
        systems.add(new ValueExampleObject("Ubuntu", "Canonical"));
        systems.add(new ValueExampleObject("Windows7", "Microsoft"));
        input.put("systems", systems);*/
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("package", new PackageAdapter(p));

        generatePage(templateConfig, input, "package.ftl", writer);
    }

    private void createPackageDirectories(PackageDoc[] packages, String parentDir) {
        for (PackageDoc p : packages) {
            createDirectory(parentDir, p.name());
        }
    }

}