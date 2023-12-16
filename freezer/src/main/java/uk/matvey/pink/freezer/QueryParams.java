package uk.matvey.pink.freezer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QueryParams {

    public static final QueryParams EMPTY = new QueryParams();

    private final Map<String, QueryParam> params = new LinkedHashMap<>();

    public QueryParams add(String name, QueryParam param) {
        this.params.put(name, param);
        return this;
    }

    public List<String> paramNames() {
        return List.copyOf(this.params.keySet());
    }

    public void setValues(PreparedStatement statement) throws SQLException {
        int index = 0;
        for (final var param : this.params.values()) {
            param.setValue(statement, ++index);
        }
    }
}
