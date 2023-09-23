package uk.matvey.pink.app.account;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account {

    @Id
    public UUID id;
    public String name;
    public String login;
    public String email;


    public Account(UUID id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
    }

    public Account() {
    }


    public String getName() {return name;}

    public String getLogin() {return login;}

    public String getEmail() {return email;}

    //public String getInformationAccount() {return name + " " + login + " " + email;}
}
