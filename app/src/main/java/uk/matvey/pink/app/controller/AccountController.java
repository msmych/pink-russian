package uk.matvey.pink.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.matvey.pink.app.account.Account;
import uk.matvey.pink.app.account.AccountRepo;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@RestController
public class AccountController {
    private final AccountRepo accountRepo;

    public AccountController(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @GetMapping("/accounts")
    public Collection<Account> getAccounts() {
        return accountRepo.selectAll();
    }


    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable UUID id) {
        return accountRepo.selectById(id);
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createAccount(@RequestBody AccountController.CreateAccountRequest request) {
        var id = randomUUID();
        final var now = Instant.now();
        accountRepo.insert(new Account(id, request.name, request.login, request.email, now, now));
        return id;
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable UUID id) {
        accountRepo.delete(id);
    }

    public static class CreateAccountRequest {

        public String name, email, login;


    }
}
