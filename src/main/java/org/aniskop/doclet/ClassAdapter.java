package org.aniskop.doclet;

import com.sun.javadoc.ClassDoc;

/**
 * Represents a class. Used by templating engine.
 */
public class ClassAdapter extends BaseDocAdapter<ClassDoc> {

    public ClassAdapter(ClassDoc c) {
        super(c);
    }

    public String getPackageName() {
        return doc.containingPackage().name();
    }

    public static ClassAdapter[] toArray(ClassDoc[] classes) {
        ClassAdapter[] methodAdapters = new ClassAdapter[classes.length];
        for (int i = 0; i < classes.length; i++) {
            methodAdapters[i] = new ClassAdapter(classes[i]);
        }
        return methodAdapters;
    }

    public MethodAdapter[] getMethods() {
        return MethodAdapter.toArray(doc.methods());
    }

    public String getModifiers() {
        return doc.modifiers();
    }

    public String getSuperclass() {
        return doc.superclass().name();
    }

    public String getQualifiedSuperclass() {
        return doc.superclass().qualifiedName();
    }
}
