package org.aniskop.doclet;

import com.sun.javadoc.Parameter;
import com.sun.javadoc.ParameterizedType;

import java.util.HashMap;

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
        return p.type().dimension();
    }

    public boolean isPrimitive() {
        return p.type().isPrimitive();
    }

    public boolean isArray() {
        return ("".equals(p.type().dimension())) ? false : true;
    }

    public boolean isGeneric() {
        ParameterizedType pp = p.type().asParameterizedType();
        return (pp == null) ? false : true;
    }

    /**
     * Used for generic types.
     * @return
     */
    public TypeAdapter[] getParamTypes() {
        ParameterizedType pp = p.type().asParameterizedType();
        if (pp != null) {
            return TypeAdapter.toArray(pp.typeArguments());
        } else {
            return new TypeAdapter[0];
        }
    }

    //TODO REMOVE THIS AS IT IS ONLY FOR TESTING
    public void miau(int i, int k, HashMap<String, String> j) {

    }
}
