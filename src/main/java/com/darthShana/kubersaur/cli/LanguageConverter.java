package com.darthShana.kubersaur.cli;

import com.beust.jcommander.IStringConverter;

public class LanguageConverter implements IStringConverter<Language> {
    @Override
    public Language convert(String s) {
        return Language.valueOf(s);
    }
}
