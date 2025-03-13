package com.project.backend.factory;

import com.project.backend.model.Librarian;
import com.project.backend.model.Member;
import com.project.backend.model.User;

public class UserFactory {

    public static User createUser(String type) {
        if (type.equalsIgnoreCase("LIBRARIAN")) {
            return new Librarian();
        } else if (type.equalsIgnoreCase("MEMBER")) {
            return new Member();
        }
        return null;
    }

}
