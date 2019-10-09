package org.aniskop.doclet;

import com.sun.javadoc.ClassDoc;

public class InterfaceAdapter extends ClassAdapter {

    private InterfaceAdapter(ClassDoc c) {
        super(c);
    }

    public static InterfaceAdapter[] toArray(ClassDoc[] interfaces) {
        InterfaceAdapter[] interfaceAdapters = new InterfaceAdapter[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            interfaceAdapters[i] = new InterfaceAdapter(interfaces[i]);
        }
        return interfaceAdapters;
    }

}
