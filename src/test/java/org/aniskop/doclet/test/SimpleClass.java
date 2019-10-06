package org.aniskop.doclet.test;

import java.util.Map;

/**
 * Just a simple standalone class for testing doclet output.
 */
public class SimpleClass {
    /**
     * Creates and instance of <code>SimpleClass</code>.
     */
    public SimpleClass() {

    }

    /**
     * Method with no arguments.
     * <p>Testing second line.</p>
     */
    public void noArgumentsMethod() {

    }

    /**
     * Method which returns <code>String</code> and has 2 arguments.
     * @param k Integer number
     * @param s Some text
     * @return  Just nothing.
     */
    public String returningMethodWithArguments(int k, String s) {
        return "";
    }

    /**
     * For testing output of the generic arguments.
     * @param theMap    String of Strings map.
     */
    public void methodWithGenericArgument(Map<String, String> theMap) {

    }

    /**
     * For testing array argument.
     * @param theArray
     */
    public void methodWithArrayArgument(String[] theArray) {

    }

    /**
     * For testing method which returns something, but has no @return tag.
     */
    public int methodWithoutReturnTag() {
        return 0;
    }
}
