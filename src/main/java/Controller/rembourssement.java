package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.Rembourssement;
import services.RembourssementService;

import java.io.IOException;
import java.sql.SQLException;

public class rembourssement {
    @FXML
    private TextField moisCol;

    @FXML
    private TextField payeeCol;

    @FXML
    private TextField retardCol;

    @FXML
    void afficheRmb(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/afficheRmb.fxml"));
        try {
            moisCol.getScene().setRoot(loader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void ajoutRmb(ActionEvent event) {
        RembourssementService rmbservice=new RembourssementService();
        Rembourssement rmb=new Rembourssement(Integer.parseInt(payeeCol.getText()),0,moisCol.getText(),retardCol.getText());
        try {
            rmbservice.ajouter(rmb);
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("rmb ajouter ");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
      }
    }

}
