package com.project.backend.singleton;

import lombok.Getter;

@Getter
public class LibraryConfig {
    private static LibraryConfig instance;
    private final int maxBooksPerPerson;
    private final double finePerDay;
    private final boolean reservationEnabled;

    private LibraryConfig() {
        this.maxBooksPerPerson = 5;
        this.finePerDay = 1.5;
        this.reservationEnabled = true;
    }

    public static LibraryConfig getInstance() {
        if (instance == null) {
            instance = new LibraryConfig();
        }
        return instance;
    }

}
