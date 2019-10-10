package org.aniskop.doclet;

import com.sun.javadoc.PackageDoc;

public class PackageAdapter {

    private PackageDoc p;

    public PackageAdapter(PackageDoc p) {
        this.p = p;
    }

    public String getName() {
        return p.name();
    }

    public String getSummary() {
        return "";
    }

    public String getComment() {
        return p.commentText();
    }

    public ClassAdapter[] getClasses() {
        return ClassAdapter.toArray(p.allClasses());
    }

    public InterfaceAdapter[] getInterfaces() {
        return InterfaceAdapter.toArray(p.interfaces());
    }

    public ExceptionAdapter[] getExceptions() {
        return ExceptionAdapter.toArray(p.exceptions());
    }
}
