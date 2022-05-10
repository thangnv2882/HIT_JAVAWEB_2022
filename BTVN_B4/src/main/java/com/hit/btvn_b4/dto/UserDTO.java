package com.hit.btvn_b4.dto;

public class UserDTO {
    private String username;
    private String password;
    private String fullname;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String fullname) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public static boolean checkNull(UserDTO userDTO) {
        if (userDTO.getUsername().compareTo("") == 0
                || userDTO.getPassword().compareTo("") == 0
                || userDTO.getFullname().compareTo("") == 0) {
            return false;
        }
        return true;
    }
}

