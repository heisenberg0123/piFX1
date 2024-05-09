package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import models.Mailing;
import models.Pret;
import services.PretService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
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
    private double interet;

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
    void ajoutPret(ActionEvent event) throws MessagingException, GeneralSecurityException, IOException {
        if (montantTF.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez saisir un montant.");
            alert.showAndWait();
            return;
        }
        this.interet=(Integer.parseInt(montantTF.getText())*0.05*Integer.parseInt(periodeTF.getText()))/100;
        PretService pretservice= new PretService();
        Pret pret1=new Pret(Integer.parseInt(montantTF.getText()), Integer.parseInt(periodeTF.getText()), ""+this.interet, typeTF.getText(), descriptionTF.getText(), 0, 0, 0);

        Mailing mailing=new Mailing();

        try {
            pretservice.ajouter(pret1);
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("demande ajouter ");
            alert.showAndWait();
            mailing.sendMail("demande pret ","demande de pret est ajouteé","koukikarim507@gmail.com");




        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


}
