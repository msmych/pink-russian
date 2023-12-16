package uk.matvey.pink.app.account;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.util.UUID;

@JsonIgnoreProperties
public class Account {

    public UUID id;
    public String name;
    public String login;
    public String email;
    public Instant createdAt;
    public Instant updatedAt;


    public Account(UUID id, String name, String login, String email, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Account() {
    }


    public String getName() {return name;}

    public String getLogin() {return login;}

    public String getEmail() {return email;}
}
