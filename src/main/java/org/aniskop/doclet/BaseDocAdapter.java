package org.aniskop.doclet;

import com.sun.javadoc.Doc;
import com.sun.javadoc.Tag;

//TODO implement support of generic classes and their params in the output

/**
 * Base class for all Javadoc adapters, containing common API.
 * All specific API is implemented in child classes.
 *
 * @param <T> Original Javadoc object.
 */
public class BaseDocAdapter<T extends Doc> {
    /**
     * Javadoc structure like ClassDoc, PackageDoc etc.
     */
    protected T doc;

    /**
     * Must be called by all child classes for proper general initiation.
     *
     * @param doc Javadoc structure like ClassDoc, PackageDoc etc.
     */
    protected BaseDocAdapter(T doc) {
        this.doc = doc;
    }

    /**
     * Gets non-qualified name.
     */
    public String getName() {
        return doc.name();
    }

    /**
     * Gets summary from Javadoc comment.
     */
    public String getSummary() {
        Tag[] tags = doc.firstSentenceTags();
        StringBuilder s = new StringBuilder();
        for (Tag t : tags) {
            s.append(t.text());
            s.append(" ");
        }

        return s.toString();
    }

    /**
     * Gets entire Javadoc comment.
     */
    public String getComment() {
        return doc.commentText();
    }

}
