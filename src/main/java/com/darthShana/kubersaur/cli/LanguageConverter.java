package com.darthShana.kubersaur.cli;

import com.beust.jcommander.IStringConverter;
import org.kubersaur.codegen.Language;

public class LanguageConverter implements IStringConverter<Language> {
    @Override
    public Language convert(String s) {
        return Language.valueOf(s);
    }
}
