package com.exist.service;

import com.exist.storage.User;
import com.exist.utility.UserList;

import java.util.ArrayList;
import java.util.List;

public class CreateUserList implements UserList {
    private List<User> users;

    public CreateUserList() {
        users = new ArrayList<User>();
    }

    @Override
    public List<User> getUsers() {
        return this.users;
    }

    public void populate() {

        User user = new User();
        user.setId(3);
        user.setLastName("crispo");
        user.setEmail("jcrispo");
        this.users.add(user);

        user = new User();
        user.setId(4);
        user.setLastName("mohr");
        user.setEmail("lmohr");
        this.users.add(user);

        user = new User();
        user.setId(5);
        user.setLastName("sy");
        user.setEmail("ksy");

        this.users.add(user);

    }
}
