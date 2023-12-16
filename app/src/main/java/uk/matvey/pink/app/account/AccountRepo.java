package uk.matvey.pink.app.account;

import org.springframework.stereotype.Repository;
import uk.matvey.pink.freezer.QueryParams;
import uk.matvey.pink.freezer.Repo;
import uk.matvey.pink.freezer.ResultExtractor;

import java.util.List;
import java.util.UUID;

import static uk.matvey.pink.freezer.QueryParam.TextParam.textParam;
import static uk.matvey.pink.freezer.QueryParam.TimestampParam.timestampParam;
import static uk.matvey.pink.freezer.QueryParam.UuidParam.uuidParam;

@Repository
public class AccountRepo {

    private final Repo repo;

    public AccountRepo(Repo repo) {
        this.repo = repo;
    }

    public void insert(Account account) {
        repo.insert("accounts", new QueryParams()
                .add("id", uuidParam(account.id))
                .add("name", textParam(account.name))
                .add("login", textParam(account.login))
                .add("email", textParam(account.email))
                .add("createdAt", timestampParam(account.createdAt))
                .add("email", timestampParam(account.updatedAt))
        );
    }

    public void delete(UUID id) {
        repo.delete("accounts", "id = ?", new QueryParams().add("id", uuidParam(id)));
    }
    public List<Account> selectAll() {
        return repo.select("select * from accounts",
                QueryParams.EMPTY,
                AccountRepo::extractAccount
        );
    }

    public Account selectById(UUID id) {
        return repo.select("select * from accounts where id = ?",
                new QueryParams().add("id", uuidParam(id)),
                AccountRepo::extractAccount).getFirst();
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
