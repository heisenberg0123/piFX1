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
import javafx.scene.input.MouseEvent;
import models.Pret;
import models.Rembourssement;
import services.PretService;
import services.RembourssementService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class afficheRmb {


    @FXML
    private TableColumn<Rembourssement, Integer> idCol;

    @FXML
    private TableColumn<Rembourssement, String> moisCol;

    @FXML
    private TableColumn<rembourssement, Integer> payeeCol;

    @FXML
    private TableColumn<Rembourssement, String> retardCol;

    @FXML
    private TableView<Rembourssement> rmb;


    @FXML
    private TextField idFT;
    @FXML
    private TextField moisFT;
    @FXML
    private TextField payeeFT;
    @FXML
    private TextField retardFT;



    @FXML
    void initialize() {

        RembourssementService rmbservice=new RembourssementService();
        try {
            List<Rembourssement> list =rmbservice.recuperer();
            ObservableList<Rembourssement> observableList = FXCollections.observableList(list);


            rmb.setItems(observableList);
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            moisCol.setCellValueFactory(new PropertyValueFactory<>("mois"));
            payeeCol.setCellValueFactory(new PropertyValueFactory<>("montant_paye"));
            retardCol.setCellValueFactory(new PropertyValueFactory<>("periode_retard"));




        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void addRmb(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Rembourssement.fxml"));
        try {
            moisFT.getScene().setRoot(loader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
    @FXML
    void modifRmb(ActionEvent event) {
        RembourssementService pretservice =new RembourssementService();
        Rembourssement p = new Rembourssement(Integer.parseInt(idFT.getText()), Integer.parseInt(payeeFT.getText()),0, moisFT.getText(), retardFT.getText());
        try {
            pretservice.modifier(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("Rmb modifier avec succés!");
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
    void deleteRmb(ActionEvent event) {
        RembourssementService pr =new RembourssementService();
        int id= Integer.parseInt(idFT.getText());
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
            throw new RuntimeException(e);}


    }



}
