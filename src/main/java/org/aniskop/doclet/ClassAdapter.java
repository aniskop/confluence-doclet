package org.aniskop.doclet;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;

/**
 * Represents a class. Used by templating engine.
 */
public class ClassAdapter {

    private ClassDoc theClass;

    public ClassAdapter(ClassDoc method) {
        this.theClass = method;
    }

    public String getName() {
        return theClass.name();
    }

    public String getPackageName() {
        return theClass.containingPackage().name();
    }

    public static ClassAdapter[] toArray(ClassDoc[] classes) {
        ClassAdapter[] methodAdapters = new ClassAdapter[classes.length];
        for (int i = 0; i < classes.length; i++) {
            methodAdapters[i] = new ClassAdapter(classes[i]);
        }
        return methodAdapters;
    }

    public String getComment() {
        return theClass.commentText()+"//"+theClass.modifiers();
    }

    public MethodAdapter[] getMethods() {
        return MethodAdapter.toArray(theClass.methods());
    }

    public String getModifiers() {
        return theClass.modifiers();
    }

    public String getSuperclass() {
        return theClass.superclass().name();
    }

    public String getQualifiedSuperclass() {
        return theClass.superclass().qualifiedName();
    }
}
