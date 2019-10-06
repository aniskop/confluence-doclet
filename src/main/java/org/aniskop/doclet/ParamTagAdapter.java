package org.aniskop.doclet;

import com.sun.javadoc.ParamTag;

public class ParamTagAdapter {

    private ParamTag t;

    public ParamTagAdapter(ParamTag tag) {
        t = tag;
    }

    public String getName() {
        return t.parameterName();
    }

    public String getComment() {
        return t.parameterComment();
    }

    public static ParamTagAdapter[] toArray(ParamTag[] tags) {
        ParamTagAdapter[] tagAdapters = new ParamTagAdapter[tags.length];
        for (int i = 0; i < tags.length; i++) {
            tagAdapters[i] = new ParamTagAdapter(tags[i]);
        }
        return tagAdapters;
    }
}
