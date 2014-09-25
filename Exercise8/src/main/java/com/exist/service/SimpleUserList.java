package com.exist.service;

import com.exist.storage.User;
import com.exist.utility.UserList;

import java.util.ArrayList;
import java.util.List;

public class SimpleUserList implements UserList {
    private List<User> users;

    public void setUsers(User user){
        if (users == null) {
            users = new ArrayList<User>();
        } else {
            users.add(user);
        }
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addEmailDomain() {
        if (users != null) {
            for (User user : users) {
                String email = user.getLastName() + "@gmail.com";
                user.setEmail(email);
            }
        }
    }

    public void clear() {
        if (users != null) {
            users.clear();
        }
    }

}
