package org.aniskop.doclet;

import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Tag;

/**
 * Represents a method. Used by templating engine.
 */
public class MethodAdapter extends BaseDocAdapter<MethodDoc> {

    public MethodAdapter(MethodDoc m) {
        super(m);
    }

    /**
     * @return Non-qualified type name. Includes <code>[]</code> for arrays.
     */
    public String getReturnTypeName() {
        return doc.returnType().typeName();
    }

    public String getReturnTypeDimension() {
        return doc.returnType().dimension();
    }

    public static MethodAdapter[] toArray(MethodDoc[] methods) {
        MethodAdapter[] methodAdapters = new MethodAdapter[methods.length];
        for (int i = 0; i < methods.length; i++) {
            methodAdapters[i] = new MethodAdapter(methods[i]);
        }
        return methodAdapters;
    }

    public ParameterAdapter[] getParameters() {
        return ParameterAdapter.toArray(doc.parameters(), doc.paramTags());
    }

    public String getModifiers() {
        return doc.modifiers();
    }

    public ParamTagAdapter[] getParamTags() {
        return ParamTagAdapter.toArray(doc.paramTags());
    }

    /**
     * Gets <code>@return</code> comment.
     */
    public String getReturnComment() {
        String comment = "";
        Tag[] returnTags = doc.tags("@return");
        if (returnTags.length > 0) {
            comment = returnTags[0].text();
        }
        return comment;
    }

}
