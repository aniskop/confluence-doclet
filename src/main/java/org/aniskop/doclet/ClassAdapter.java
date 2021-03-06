package org.aniskop.doclet;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.ParameterizedType;
import com.sun.javadoc.TypeVariable;

/**
 * Represents a Java class.
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

    public boolean isGeneric() {
        TypeVariable[] vars = doc.typeParameters();
        return (vars == null && vars.length > 0) ? false : true;
    }

    /**
     * Gets type variables (or type parameters) of a generic class.
     * @return  Empty array for non-generic classes.
     */
    public TypeVariableAdapter[] getTypeParams() {
        TypeVariable[] vars = doc.typeParameters();
        if (vars != null) {
            return TypeVariableAdapter.toArray(vars);
        } else {
            return new TypeVariableAdapter[0];
        }
    }
}
