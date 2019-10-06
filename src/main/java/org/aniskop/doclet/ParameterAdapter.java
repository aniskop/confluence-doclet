package org.aniskop.doclet;

import com.sun.javadoc.ParamTag;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.ParameterizedType;

public class ParameterAdapter {

    private Parameter p;
    private String comment;

    /**
     * @param p
     * @param comment Corresponding javadoc comment.
     */
    public ParameterAdapter(Parameter p, String comment) {
        this.p = p;
        this.comment = comment;
    }

    public static ParameterAdapter[] toArray(Parameter[] parameters, ParamTag[] tags) {
        ParameterAdapter[] paramAdapters = new ParameterAdapter[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            ParamTag paramTag = FindParamTag(parameters[i], tags);
            if (paramTag == null) {
                paramAdapters[i] = new ParameterAdapter(parameters[i], "");
            } else {
                paramAdapters[i] = new ParameterAdapter(parameters[i], paramTag.parameterComment());
            }
        }
        return paramAdapters;
    }

    private static ParamTag FindParamTag(Parameter param, ParamTag[] tags) {
        ParamTag result = null;
        if (tags != null && tags.length > 0) {
            for (ParamTag tag : tags) {
                if (param.name().equals(tag.parameterName())) {
                    result = tag;
                    break;
                }
            }
        }
        return result;
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
     *
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

    public String getComment() {
        return comment;
    }

}
