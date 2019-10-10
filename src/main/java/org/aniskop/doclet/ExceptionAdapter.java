package org.aniskop.doclet;

import com.sun.javadoc.ClassDoc;

public class ExceptionAdapter {

    private ClassDoc e;

    public ExceptionAdapter(ClassDoc e) {
        this.e = e;
    }

    public String getName() {
        return e.name();
    }

    public String getSummary() {
        return "";
    }

    public String getComment() {
        return e.commentText();
    }

    public static ExceptionAdapter[] toArray(ClassDoc[] exceptions) {
        ExceptionAdapter[] exceptionAdapters = new ExceptionAdapter[exceptions.length];
        for (int i = 0; i < exceptions.length; i++) {
            exceptionAdapters[i] = new ExceptionAdapter(exceptions[i]);
        }
        return exceptionAdapters;
    }

}
