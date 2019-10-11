package org.aniskop.doclet;

public class DocletOptions {

    private String[][] opt;

    public DocletOptions(String[][] options) {
        opt = options;
    }

    public String get(String optionName) {
        String value = "";
        if (opt != null) {
            for (int i = 0; i < opt.length; i++) {
                if (opt[i][0].equals(optionName)) {
                    value = opt[i][1];
                }
            }
        }
        return value;
    }

    public String getOutputDir() {
        return get("-d");
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < opt.length; i++) {
            for (int j = 0; j < opt[i].length; j++) {
                s.append(opt[i][j]);
                s.append(' ');
            }
            s.append('\n');
        }
        return s.toString();
    }

}
