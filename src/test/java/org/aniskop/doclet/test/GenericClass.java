package org.aniskop.doclet.test;

/**
 * A simple generic class to test support of generics.
 *
 * @param <T> Some type.
 */
public class GenericClass<T> {

    private T member;

    public GenericClass(T arg) {
        member = arg;
    }

    /**
     * Gets member of type T.
     */
    public T getMember() {
        return member;
    }

    /**
     * Sets value of internal generic member.
     * @param newValue      New value for the class member.
     */
    public void setMember(T newValue) {
        member = newValue;
    }
}
