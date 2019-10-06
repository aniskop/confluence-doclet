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

    /**
     *
     * @return Non-qualified type name. Includes <code>[]</code> for arrays.
     */
    public String getReturnTypeName() {
        return method.returnType().typeName();
    }

    public String getReturnTypeDimension() {
        return  method.returnType().dimension();
    }

    public static MethodAdapter[] toArray(MethodDoc[] methods) {
        MethodAdapter[] methodAdapters = new MethodAdapter[methods.length];
        for (int i = 0; i < methods.length; i++) {
            methodAdapters[i] = new MethodAdapter(methods[i]);
        }
        return methodAdapters;
    }

    public String getSummary() {
        return method.commentText();//TODO extract only first sentence??
    }

    public ParameterAdapter[] getParameters() {
        return ParameterAdapter.toArray(method.parameters());
    }

    public String getModifiers() {
        return method.modifiers();
    }

    public String getDescription() {
        return method.commentText();
    }

}
