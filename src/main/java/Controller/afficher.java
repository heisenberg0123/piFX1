package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Pret;
import services.PretService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class afficher {



    @FXML
    private TableColumn<Pret, String> descriptionCol;
    @FXML
    private TableColumn<Pret, Integer> idCol;

    @FXML
    private TableColumn<Pret, Integer> montantCol;
    @FXML
    private TableColumn<Pret, String> interetCol;
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
            interetCol.setCellValueFactory(new PropertyValueFactory<>("interet"));




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
        try {
            idTF.getScene().setRoot(loader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


    }

    @FXML
    void statistique(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Chart.fxml"));
            Parent root = loader.load();
            statistique statistiqueController = loader.getController();

            // Passer les données de la table au contrôleur de statistiques
            statistiqueController.initialiserDonnees(tableView.getItems());

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void pdf(ActionEvent event) {


            Document document = new Document(PageSize.A4);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName("tableau.pdf");
            File selectedFile = fileChooser.showSaveDialog(new Stage());

            if (selectedFile != null) {
                try {
                    PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                    document.open();

                    // Création de la table iText PDF
                    PdfPTable pdfTable = new PdfPTable(tableView.getColumns().size());
                    // Ajout des en-têtes de colonnes
                    for (TableColumn<Pret, ?> column : tableView.getColumns()) {
                        pdfTable.addCell(column.getText());
                    }
                    // Ajout des données de la table
                    ObservableList<Pret> items = tableView.getItems();
                    for (Pret item : items) {
                        pdfTable.addCell(String.valueOf(item.getId()));
                        pdfTable.addCell(String.valueOf(item.getMontant()));
                        pdfTable.addCell(item.getType());
                        pdfTable.addCell(String.valueOf(item.getPeriode()));
                        pdfTable.addCell(item.getDescription());
                        pdfTable.addCell(item.getInteret());
                    }
                    document.add(pdfTable);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Fichier PDF créé avec succès !");
                    alert.show();

                } catch (DocumentException | FileNotFoundException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("Une erreur est survenue lors de la création du fichier PDF !");
                    alert.show();
                    e.printStackTrace();
                } finally {
                    if (document != null) {
                        document.close();
                    }
                }
            }
        }

    }





