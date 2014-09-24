package com.exist.utility;

import com.exist.storage.User;

import java.io.Serializable;
import java.util.List;

public interface UserList extends Serializable {

    public List<User> getUsers();
}
