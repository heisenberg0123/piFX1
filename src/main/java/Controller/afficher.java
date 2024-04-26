package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Pret;
import services.PretService;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class afficher {



    @FXML
    private TableColumn<Pret, String> descriptionCol;
    @FXML
    private TableColumn<Pret, Integer> idCol;

    @FXML
    private TableColumn<Pret, Integer> montantCol;

    @FXML
    private TableColumn<Pret, Integer> periodeCol;

    @FXML
    private TableView<Pret> tableView;

    @FXML
    private TableColumn<Pret, String> typeCol;

    @FXML
    private TextField descriptionTF;

    @FXML
    private TextField montantTF;

    @FXML
    private TextField idTF;

    @FXML
    private TextField periodeTF;


    @FXML
    private TextField typeTF;


    @FXML
    void initialize() {
        PretService pretservice =new PretService();


        try {
            List<Pret> list =pretservice.recuperer();
            ObservableList<Pret> observableList = FXCollections.observableList(list);


            tableView.setItems(observableList);
      idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            montantCol.setCellValueFactory(new PropertyValueFactory<>("montant"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            periodeCol.setCellValueFactory(new PropertyValueFactory<>("periode"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));




        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }



    }


    @FXML
    void modifPret(ActionEvent event) {
          PretService pretservice =new PretService();
          Pret p = new Pret(Integer.parseInt(idTF.getText()),Integer.parseInt(montantTF.getText()),Integer.parseInt(periodeTF.getText()),"",typeTF.getText(),descriptionTF.getText(),0,0,0);
        try {
            pretservice.modifier(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("User modifier avec succés!");
            alert.show();
            initialize();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText(e.getMessage());
            alert.show();
            throw new RuntimeException(e);


        }
    }


    @FXML
    void deletePret(ActionEvent event) {

PretService pr =new PretService();
int id= Integer.parseInt(idTF.getText());
        try {
            pr.supprimer(id);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("Rmb supprimer avec succés!");
            alert.show();

            initialize();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText(e.getMessage());
            alert.show();
            throw new RuntimeException(e);

        }

        ;

    }
    @FXML
    void addPret(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Pret.fxml"));
    }



}
