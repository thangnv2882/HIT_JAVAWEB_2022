package com.hit.btvn_b3;

import java.util.ArrayList;
import java.util.List;


public class Store {

    public static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1L, "user1", "123", "Nguyen Van Thang 1"));
        users.add(new User(2L, "user2", "234", "Nguyen Van Thang 2"));
        users.add(new User(3L, "user3", "345", "Nguyen Van Thang 3"));
        users.add(new User(4L, "user4", "456", "Nguyen Van Thang 4"));
        users.add(new User(5L, "user5", "567", "Nguyen Van Thang 5"));
    }

}
