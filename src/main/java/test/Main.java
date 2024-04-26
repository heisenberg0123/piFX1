package test;

import cnx.Connexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.PretService;
import models.Pret;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    public static void main(String[] args) {
        /*UserService us = new UserService();
        try {
//            us.create(new Person(23,"insert into ","Ben Foulen"));
//            us.update(new Person(1,25, "Skander","Ben Foulen"));
            us.delete(16);
            System.out.println(us.read());
            us.create(new User(7000,"badis@gmail.com","admdin","badis2008","badis","gombra","admin"));
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(us.read());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }*/
        launch();
    }



    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/afficheRmb.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            stage.setTitle("FinancialHub");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
