package uk.matvey.pink.app.account;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.Instant;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account {

    @Id
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
