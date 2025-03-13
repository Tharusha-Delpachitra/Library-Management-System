package com.project.backend.singleton;

import lombok.Getter;

@Getter
public class LibraryConfig {
    private static LibraryConfig instance;
    private final int maxBooksPerPerson;

    private LibraryConfig() {
        this.maxBooksPerPerson = 5;
    }

    public static LibraryConfig getInstance() {
        if (instance == null) {
            instance = new LibraryConfig();
        }
        return instance;
    }

}
