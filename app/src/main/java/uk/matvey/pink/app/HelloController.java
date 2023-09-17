package uk.matvey.pink.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.beans.BeanProperty;

@RestController
public class HelloController {

    private final String username;

    public HelloController(@Value("${username}") String username) {
        this.username = username;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello " + username;
    }

    @PostMapping ("/ciao")
    public String ciao() {
        return "Ciao";
    }
}
