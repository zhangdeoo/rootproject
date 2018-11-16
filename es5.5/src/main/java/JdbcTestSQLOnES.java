package myproject;

import org.junit.Test;

import java.sql.*;
import java.util.Properties;

/**
 */

public class JdbcTestSQLOnES {

    String driver = "org.elasticsearch.xpack.sql.jdbc.jdbc.JdbcDriver";
    String elasticsearchAddress = "10.33.80.109:9200";
//    String elasticsearchAddress = "192.168.40.130:9200";

    public Properties connectionProperties(){
        Properties properties = new Properties();
//        properties.put("user", "test_admin");
//        properties.put("password", "x-pack-test-password");
        return properties;
    }

    @Test
    public void test(){
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String address = "jdbc:es://http://" + elasticsearchAddress;
        Properties connectionProperties = connectionProperties();

        try (Connection connection = DriverManager.getConnection(address, connectionProperties);
            Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(
                     "SELECT name, page_count FROM library ORDER BY page_count DESC LIMIT 5")) {

            while(results.next()){
                System.out.println(results.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}