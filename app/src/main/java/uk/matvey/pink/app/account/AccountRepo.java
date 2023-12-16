package uk.matvey.pink.app.account;

import uk.matvey.pink.freezer.QueryParams;
import uk.matvey.pink.freezer.Repo;
import uk.matvey.pink.freezer.ResultExtractor;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import static uk.matvey.pink.freezer.QueryParam.TextParam.textParam;
import static uk.matvey.pink.freezer.QueryParam.TimestampParam.timestampParam;
import static uk.matvey.pink.freezer.QueryParam.UuidParam.uuidParam;

public class AccountRepo {

    private final Repo repo;
    private final Executor executor;

    public AccountRepo(Repo repo, ThreadPoolExecutor executor) {
        this.repo = repo;
        this.executor = executor;
    }

    public CompletableFuture<Void> insert(Account account) {
        return CompletableFuture.runAsync(() -> repo.insert("accounts", new QueryParams()
                .add("id", uuidParam(account.id))
                .add("name", textParam(account.name))
                .add("login", textParam(account.login))
                .add("email", textParam(account.email))
                .add("createdAt", timestampParam(account.createdAt))
                .add("email", timestampParam(account.updatedAt))
        ), executor);
    }

    public CompletableFuture<List<Account>> selectAll() {
        return CompletableFuture.supplyAsync(() -> repo.select("select * from accounts",
                QueryParams.EMPTY,
                AccountRepo::extractAccount
        ), executor);
    }

    public CompletableFuture<Account> selectById(UUID id) {
        return CompletableFuture.supplyAsync(() -> repo.select("select * from accounts where id = ?",
                        new QueryParams().add("id", uuidParam(id)),
                        AccountRepo::extractAccount), executor)
                .thenApply(List::getFirst);
    }

    private static Account extractAccount(ResultExtractor ex) {
        return new Account(
                ex.uuid("id"),
                ex.text("name"),
                ex.text("login"),
                ex.text("email"),
                ex.instant("createdAt"),
                ex.instant("updatedAt")
        );
    }
}
