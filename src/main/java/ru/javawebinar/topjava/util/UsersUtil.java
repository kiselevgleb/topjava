package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.*;

import static ru.javawebinar.topjava.model.Role.ROLE_ADMIN;
import static ru.javawebinar.topjava.model.Role.ROLE_USER;

public class UsersUtil {
    public static Set<Role> roleUser = null;
    public static Set<Role> roleAdmin = null;
    public static Set<Role> roles = null;

    {
        roleUser.add(ROLE_USER);
        roleAdmin.add(ROLE_ADMIN);
        roles.add(ROLE_USER);
        roles.add(ROLE_ADMIN);
    }

    public static final List<User> USERS = Arrays.asList(
            new User(2, "user", "1@1.ru", "123", 2000, true, roleUser),
            new User(1, "admin", "2@2.ru", "123", 2500, true, roleAdmin)
    );

    public static List<User> getSortListUser(List<User> users) {
        Comparator<User> userInitialsComparator = Comparator.comparing(User::getName);
        Collections.sort(users, userInitialsComparator);
        return users;
    }

}