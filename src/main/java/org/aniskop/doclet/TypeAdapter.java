package org.aniskop.doclet;

import com.sun.javadoc.ParameterizedType;
import com.sun.javadoc.Type;
import com.sun.javadoc.TypeVariable;

public class TypeAdapter {

    private Type t;

    public TypeAdapter(Type t) {
        this.t = t;
    }

    public String getName() {
        return t.simpleTypeName();
    }

    public boolean isGeneric() {
        ParameterizedType genericType = t.asParameterizedType();
        if (genericType != null) {
            Type[] typeVars = genericType.typeArguments();
            return (typeVars != null && typeVars.length > 0);
        } else {
            return false;
        }
    }

    public String getDimension() {
        return t.dimension();
    }

    public static TypeAdapter[] toArray(Type[] types) {
        TypeAdapter[] typeAdapters = new TypeAdapter[types.length];
        for (int i = 0; i < types.length; i++) {
            typeAdapters[i] = new TypeAdapter(types[i]);
        }
        return typeAdapters;
    }

    public TypeAdapter[] getTypeParams() {
        ParameterizedType paramType = t.asParameterizedType();
        Type[] vars = paramType.typeArguments();
        if (vars != null) {
            return TypeAdapter.toArray(vars);
        } else {
            return new TypeAdapter[0];
        }
    }
}
