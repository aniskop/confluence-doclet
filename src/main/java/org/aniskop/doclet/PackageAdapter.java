package org.aniskop.doclet;

import com.sun.javadoc.PackageDoc;

public class PackageAdapter extends BaseDocAdapter<PackageDoc> {

    public PackageAdapter(PackageDoc p) {
        super(p);
    }

    public ClassAdapter[] getClasses() {
        return ClassAdapter.toArray(doc.allClasses());
    }

    public InterfaceAdapter[] getInterfaces() {
        return InterfaceAdapter.toArray(doc.interfaces());
    }
}
