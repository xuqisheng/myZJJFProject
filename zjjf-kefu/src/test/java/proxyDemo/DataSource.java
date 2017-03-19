package proxyDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by hwdd1 on 2017/2/18 0018.
 */
public class DataSource {
    private static LinkedList<Connection> connections = new LinkedList<>();

    static {
        try {
            Class.forName("com.mydql.jdbc.Drive");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection("url","username","password");
    }

    private DataSource(){
        if(connections==null||connections.size()==0){
            for (int i = 0; i < 10; i++) {
                try {
                    connections.add(createConnection());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Connection getConnection(){
        if (connections.size()>0) {
            return  new ConnectionProxy(connections.remove());
        }
        return null;
    }

    public void recoveryConnection(Connection connection){
        connections.add(connection);
    }


    private static class DataSourceInstance{
        private static DataSource dataSource = new DataSource();
    }

    public static DataSource getInstance(){
        return DataSourceInstance.dataSource;
    }
}
