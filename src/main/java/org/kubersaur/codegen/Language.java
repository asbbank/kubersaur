package org.kubersaur.codegen;

public enum Language {

    JAVA{
        @Override
        public String getTemplateLocation() {
            return "java/";
        }
    },
    CSHARP{
        @Override
        public String getTemplateLocation() {
            return "csharp/";
        }
    };

    public abstract String getTemplateLocation();
}
