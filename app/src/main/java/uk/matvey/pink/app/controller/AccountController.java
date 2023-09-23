package uk.matvey.pink.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.matvey.pink.app.account.Account;
import uk.matvey.pink.app.account.AccountRepository;

import java.util.Collection;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@RestController
public class AccountController {
    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/accounts")
    public Collection<Account> getAccounts() { return accountRepository.findAll();}


    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable UUID id) {
        return accountRepository.getReferenceById(id);
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createAccount(@RequestBody AccountController.CreateAccountRequest request) {
        var id = randomUUID();
        accountRepository.save(new Account(id, request.name, request.login, request.email));
        return id;
    }
    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable UUID id){
        accountRepository.deleteById(id);
    }

    public static class CreateAccountRequest {

        public String name,email,login;


    }
}
