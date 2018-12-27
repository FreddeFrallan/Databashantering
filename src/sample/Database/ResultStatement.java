package sample.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ResultStatement {
    public void apply(ResultSet dbResult, ArrayList results) throws SQLException;
}
