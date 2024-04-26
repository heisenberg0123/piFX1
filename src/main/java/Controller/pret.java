package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import models.Pret;
import services.PretService;

import java.io.IOException;
import java.sql.SQLException;

public class pret {
    @FXML
    private TextArea descriptionTF;

    @FXML
    private TextArea montantTF;

    @FXML
    private TextArea periodeTF;

    @FXML
    private TextArea typeTF;

    @FXML
    void affichePret(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherPret.fxml"));
        try {
            montantTF.getScene().setRoot(loader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void ajoutPret(ActionEvent event) {

        PretService pretservice= new PretService();
        Pret pret1=new Pret(Integer.parseInt(montantTF.getText()), Integer.parseInt(periodeTF.getText()), "", typeTF.getText(), descriptionTF.getText(), 0, 0, 0);


        try {
            pretservice.ajouter(pret1);
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("demande ajouter ");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
