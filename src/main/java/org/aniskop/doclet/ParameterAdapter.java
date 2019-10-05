package org.aniskop.doclet;

import com.sun.javadoc.Parameter;

public class ParameterAdapter {
    private Parameter p;

    public ParameterAdapter(Parameter p) {
        this.p = p;
    }

    public static ParameterAdapter[] toArray(Parameter[] parameters) {
        ParameterAdapter[] paramAdapters = new ParameterAdapter[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            paramAdapters[i] = new ParameterAdapter(parameters[i]);
        }
        return paramAdapters;
    }

    public String getName() {
        return p.name();
    }

    public String getTypeName() {
        return p.type().typeName();
    }

    public String getDimension() {
        return  p.type().dimension();
    }

    //TODO REMOVE THIS AS IT IS ONLY FOR TESTING
    public void miau(int i, int k) {

    }
}
