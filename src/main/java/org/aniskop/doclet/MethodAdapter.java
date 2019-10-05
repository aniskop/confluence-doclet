package org.aniskop.doclet;

import com.sun.javadoc.MethodDoc;

/**
 * Represents a method. Used by templating engine.
 */
public class MethodAdapter {

    private MethodDoc method;

    public MethodAdapter(MethodDoc method) {
        this.method = method;
    }

    public String getName() {
        return method.name();
    }

    public String getReturnType() {
        return method.returnType().typeName();
    }

    public static MethodAdapter[] toArray(MethodDoc[] methods) {
        MethodAdapter[] methodAdapters = new MethodAdapter[methods.length];
        for (int i = 0; i < methods.length; i++) {
            methodAdapters[i] = new MethodAdapter(methods[i]);
        }
        return methodAdapters;
    }

    public String getSummary() {
        return method.commentText();
    }

    public String getParameters() {
        return "parameters";
    }

}
