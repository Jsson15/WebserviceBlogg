package com.example.webservice.DTO;





public class UserDTO {
    private String userName;



    public UserDTO(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

