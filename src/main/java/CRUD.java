
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

/*
CREATE
READ - 1st one
UPDATE
DELETE
 */

public class CRUD {
    protected String host;
    protected String port;
    protected String schema;
    protected String MYSQL_DB_URL;

    //creds
    protected String MYSQL_DB_USERNAME;
    protected String MYSQL_DB_PASSWORD;
    protected MysqlDataSource mysqlDS;

    protected Connection con = null;
    protected Statement stmt = null;

    CRUD(String host, String port, String schema, String username, String password) {
        this.host = host;
        this.port = port;
        this.schema = schema;
        this.MYSQL_DB_USERNAME = username;
        this.MYSQL_DB_PASSWORD = password;
        this.MYSQL_DB_URL = String.format("jdbc:mysql://%s:%s/%s", host, port, schema);
        init();
    }

    protected void init() {
        mysqlDS = new MysqlDataSource();
        mysqlDS.setURL(MYSQL_DB_URL);
        mysqlDS.setUser(MYSQL_DB_USERNAME);
        mysqlDS.setPassword(MYSQL_DB_PASSWORD);
        try {
            con = mysqlDS.getConnection();
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected String quoteAll(String[] list, String quote) {
        return Arrays.stream(list).map(s -> String.format("%s%s%s", quote, s, quote)).collect(Collectors.joining(","));
    }

    //CREATE
    public Integer create(String tableName, String[] fields, String[] values) {

        String quotedFields = quoteAll(fields, "`");
        String quotedValues = quoteAll(values, "'");

        String sqlInsert = String.format("INSERT INTO %s (%s) VALUES (%s);", tableName, quotedFields, quotedValues);

        Integer affectedRowCount = null;
        try {
            affectedRowCount = stmt.executeUpdate(sqlInsert);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return affectedRowCount;
    }

    //READ
    public ResultSet read(String sql) {
        ResultSet rs = null;

        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return rs;
    }

    //UPDATE
    /*
    UPDATE table_name
SET column1 = value1, column2 = value2, ...
WHERE condition;
     */
    public Integer update(String tableName, HashMap<String, String> pairs, Integer id, String idField) {

        //create key value pairs for update
        ArrayList<String> list = new ArrayList<>();
        for (String k : pairs.keySet()) {
            String v = pairs.get(k);
            list.add(String.format("`%s` = '%s'", k, v));
        }
        String both = String.join(", ", list);

        String sqlUpdate = String.format(
                "UPDATE %s SET %s WHERE `%s` = %d;"
                , tableName, both, idField, id);
        System.out.println(sqlUpdate);
        Integer affectedRowCount = null;
        try {
            affectedRowCount = stmt.executeUpdate(sqlUpdate);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return affectedRowCount;
    }

    public Integer delete(String tableName, Integer id, String idField) {

        String sqlDelete = String.format("DELETE FROM %s WHERE `%s` = %d;", tableName, idField, id);
        System.out.println(sqlDelete);
        Integer affectedRowCount = null;
        try {
            affectedRowCount = stmt.executeUpdate(sqlDelete);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return affectedRowCount;
    }

    public ResultSet search(String sql, String needleSought) {
        ResultSet rs = null;
        try {
            var ps = con.prepareStatement(sql);
            ps.setString(1, needleSought);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return rs;
    }


    protected void printAll(ResultSet rs, String[] fields) {
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                for (String f : fields) {
                    System.out.println(f + ": " + rs.getString(f));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<HashMap<String, String>> convert(ResultSet rs, String[] fields) {
        var list = new ArrayList<HashMap<String, String>>();

        if (null == rs) {
            System.out.println("NO RESULTS");
            return list;
        }

        while (true) {

            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            var item = new HashMap<String, String>();

            try {
                for (String f : fields) {
                    item.put(f, rs.getString(f));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            list.add(item);
        }

        try {
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public ArrayList<HashMap<String, String>> searchData(String sql, String[] fields, String needleSought) {
        return convert(search(sql, needleSought), fields);
    }

    public ArrayList<HashMap<String, String>> getData(String sql, String[] fields) {
        return convert(read(sql), fields);
    }


    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
