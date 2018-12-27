package sample.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PrepStatements{
    public void apply(PreparedStatement s) throws SQLException;
}
