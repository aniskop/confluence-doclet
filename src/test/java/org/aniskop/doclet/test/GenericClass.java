package org.aniskop.doclet.test;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Gets list of type T.
     * <p>Used to test generic method.</p>
     */
    public List<T> getList() {
        return new ArrayList<>();
    }

    /**
     * Sets list of type T.
     * <p>Used to test generic arguments.</p>
     * @param list
     */
    public void setList(List<T> list) {

    }
}
