package uk.matvey.pink.freezer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.Collections.nCopies;

public class Repo {

    private final DataSource dataSource;

    public Repo(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(String tableName, QueryParams params) {
        withConnection(conn -> {
            final var paramNames = params.paramNames();
            final var columnNames = String.join(",", paramNames);
            final var questions = String.join(",", nCopies(paramNames.size(), "?"));
            final var query = String.format("insert into %s (%s) values (%s)", tableName, columnNames, questions);
            try (var statement = conn.prepareStatement(query)) {
                params.setValues(statement);
                statement.executeUpdate();
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
    
    public void update(String tableName, QueryParams updateParams, String condition, QueryParams conditionParams) {
        withConnection(conn -> {
            final var setValues = String.join(",", updateParams.paramNames().stream().map(paramName -> String.format("%s=?", paramName)).toList());
            final var query = String.format("update %s set %s where %s", tableName, setValues, condition);
            try (final var statement = conn.prepareStatement(query)) {
                updateParams.setValues(statement);
                conditionParams.setValues(statement);
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public <T> List<T> select(String query, QueryParams queryParams, Function<ResultExtractor, T> mapper) {
        return withConnection(conn -> {
            try (var statement = conn.prepareStatement(query)) {
                queryParams.setValues(statement);
                final var resultSet = statement.executeQuery();
                final var list = new ArrayList<T>();
                while (resultSet.next()) {
                    list.add(mapper.apply(new ResultExtractor(resultSet)));
                }
                return list;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public <T> T withConnection(Function<Connection, T> block) {
        try (var conn = dataSource.getConnection()) {
            return block.apply(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
