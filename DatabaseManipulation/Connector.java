package DatabaseManipulation;


import java.sql.*;
import java.util.Properties;

public class Connector {
    Connection conn = null;
    Statement stat;
    static String url, database, username, password, hostname, port, driver;

    public Connector(Properties props,String pass){
        database = props.getProperty("Database");
        username = props.getProperty("Username");
        password = pass;
        hostname = props.getProperty("Host");
        port = props.getProperty("Port");
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://"+hostname+":"+port+"/"+database;
    }

    public boolean open(){
        try {
            DriverManager.registerDriver((java.sql.Driver) Class.forName(driver).newInstance());
            conn = DriverManager.getConnection(url,username,password);
            stat = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(conn !=null ){
            System.out.println("Connected successfuly!");
            return true;
        }
        else {
            System.out.println("Failed to  connect!");
            return false;
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
            return stat.executeQuery(query);
    }
    public void executeUpdate(String query) throws SQLException {
            stat.executeUpdate(query);
    }
}
