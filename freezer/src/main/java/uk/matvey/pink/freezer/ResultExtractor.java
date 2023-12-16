package uk.matvey.pink.freezer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class ResultExtractor {

    private final ResultSet resultSet;

    public ResultExtractor(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public Optional<UUID> uuidOpt(String name) {
        try {
            return Optional.ofNullable(resultSet.getObject(name))
                    .filter(value -> {
                        try {
                            return !resultSet.wasNull();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .map(value -> (UUID) value);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UUID uuid(String name) {
        return uuidOpt(name).orElseThrow();
    }

    public Optional<String> textOpt(String name) {
        try {
            return Optional.ofNullable(resultSet.getString(name))
                    .filter(value -> {
                        try {
                            return !resultSet.wasNull();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String text(String name) {
        return textOpt(name).orElseThrow();
    }

    public Optional<Instant> instantOpt(String name) {
        try {
            final var timestamp = resultSet.getTimestamp(name);
            return resultSet.wasNull() ? Optional.empty() : Optional.of(timestamp.toInstant());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Instant instant(String name) {
        return instantOpt(name).orElseThrow();
    }
}
