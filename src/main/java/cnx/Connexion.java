package cnx;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
public class Connexion {
    private final String URL= "jdbc:mysql://localhost:3306/client";
    private final String USER = "root";
    private final String PASS="";
    private Connection connection;

    private static Connexion instance;

    private Connexion(){
        try{
            connection = DriverManager.getConnection(URL,USER,PASS);
            System.out.println("Connection established");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static Connexion getInstance(){
        if(instance ==null){
            instance = new Connexion();

        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }



}
