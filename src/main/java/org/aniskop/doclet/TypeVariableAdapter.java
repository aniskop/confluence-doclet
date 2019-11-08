package org.aniskop.doclet;

import com.sun.javadoc.TypeVariable;

public class TypeVariableAdapter {

    private TypeVariable typeVariable;

    public TypeVariableAdapter(TypeVariable t) {
        this.typeVariable = t;
    }

    public String getName() {
        return typeVariable.simpleTypeName();
    }

    public static TypeVariableAdapter[] toArray(TypeVariable[] types) {
        TypeVariableAdapter[] typeAdapters = new TypeVariableAdapter[types.length];
        for (int i = 0; i < types.length; i++) {
            typeAdapters[i] = new TypeVariableAdapter(types[i]);
        }
        return typeAdapters;
    }
}
