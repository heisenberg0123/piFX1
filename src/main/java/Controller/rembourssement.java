package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import models.Pret;
import models.Rembourssement;
import services.PretService;
import services.RembourssementService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class rembourssement {
    @FXML
    private TextField moisCol;

    @FXML
    private TextField payeeCol;

    @FXML
    private TextField retardCol;
    @FXML
    private ChoiceBox<Integer> idCol;

    PretService pretService = new PretService();

    public void initialize() throws SQLException {
        List ls= this.pretService.recuperer();

        for(int i = 0; i < ls.size(); i++) {
            Pret pret = (Pret) ls.get(i);
            idCol.getItems().add(pret.getId());
        }

    }


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
        if (payeeCol.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez saisir un montant payeé.");
            alert.showAndWait();
            return;
        }
        RembourssementService rmbservice=new RembourssementService();
        Rembourssement rmb=new Rembourssement(Integer.parseInt(payeeCol.getText()),0,moisCol.getText(),retardCol.getText(), 0);
        try {
            rmbservice.ajouter(rmb,Integer.parseInt(String.valueOf(idCol.getValue())));
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
