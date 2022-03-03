package com.hit.btvn_b3;

public class User {
    private Long id;
    private String username;
    private String password;
    private String fullName;

    public User() {
    }

    public User(Long id, String username, String password, String fullName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User))
            return false;
        User other = (User) o;
        if (this.username.equals(other.username) && this.password.equals(other.password))
            return true;
        else
            return false;
    }

    public boolean Duyet(User user) {
        Store store = new Store();
        for (User o : store.users) {
            if (o.equals(user)) {
                return true;
            }
        }
        return false;
    }

}
