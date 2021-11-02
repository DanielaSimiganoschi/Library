package org.internship.library.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserLibrary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;
    private String userName;
    private String password;
    private String email;

    public UserLibrary() {
    }

    public UserLibrary(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
