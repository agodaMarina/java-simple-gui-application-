package Models;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnexionDB {
    public Connection databaseLink;

    public Connection getConnection(){
        String dbName="ProjetCsc301";
        String user="root";
        String password="Marina1234";
        String url="jdbc:mysql://localhost/"+dbName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink=DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }

}
