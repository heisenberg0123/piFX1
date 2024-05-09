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
            idCol.setCellValueFactory(new PropertyValueFactory<>("rmb_id"));
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
        Rembourssement p = new Rembourssement(Integer.parseInt(idFT.getText()), Integer.parseInt(payeeFT.getText()),0, moisFT.getText(), retardFT.getText(),0);
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
                PdfPTable pdfTable = new PdfPTable(rmb.getColumns().size());
                // Ajout des en-têtes de colonnes
                for (TableColumn<Rembourssement, ?> column : rmb.getColumns()) {
                    pdfTable.addCell(column.getText());
                }
                // Ajout des données de la table
                ObservableList<Rembourssement> items = rmb.getItems();
                for (Rembourssement item : items) {
                    pdfTable.addCell(String.valueOf(item.getId()));
                    pdfTable.addCell(String.valueOf(item.getMontant_paye()));
                    pdfTable.addCell(item.getMois());
                    pdfTable.addCell(String.valueOf(item.getPeriode_retard()));

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




