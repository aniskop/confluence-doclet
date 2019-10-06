package org.aniskop.doclet;

import com.sun.javadoc.Type;

public class TypeAdapter {

    Type t;

    public TypeAdapter(Type t) {
        this.t = t;
    }

    public String getName() {
        return t.simpleTypeName();
    }

    public static TypeAdapter[] toArray(Type[] types) {
        TypeAdapter[] typeAdapters = new TypeAdapter[types.length];
        for (int i = 0; i < types.length; i++) {
            typeAdapters[i] = new TypeAdapter(types[i]);
        }
        return typeAdapters;
    }
}
