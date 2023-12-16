package uk.matvey.pink.freezer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public sealed interface QueryParam {

    void setValue(PreparedStatement statement, int index);

    final class TextParam implements QueryParam {

        private final String value;

        public TextParam(String value) {
            this.value = value;
        }

        public static TextParam textParam(String value) {
            return new TextParam(value);
        }

        @Override
        public void setValue(PreparedStatement statement, int index) {
            try {
                statement.setString(index, this.value);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    final class UuidParam implements QueryParam {

        private final UUID value;

        public UuidParam(UUID value) {
            this.value = value;
        }

        public static UuidParam uuidParam(UUID value) {
            return new UuidParam(value);
        }

        @Override
        public void setValue(PreparedStatement statement, int index) {
            try {
                statement.setObject(index, this.value);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    final class TimestampParam implements QueryParam {

        private final Instant value;

        public TimestampParam(Instant value) {
            this.value = value;
        }

        public static TimestampParam timestampParam(Instant value) {
            return new TimestampParam(value);
        }

        @Override
        public void setValue(PreparedStatement statement, int index) {
            try {
                statement.setTimestamp(index, Timestamp.from(this.value));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
