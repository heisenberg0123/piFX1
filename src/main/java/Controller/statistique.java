package Controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;

import javafx.animation.RotateTransition;
import javafx.util.Duration;
import models.Pret;

import java.util.HashMap;
import java.util.Map;

public class statistique {
    @FXML
    private VBox vboxStatistiques;

    @FXML
    private PieChart pieChartStatistiques;

    public void initialiserDonnees(ObservableList<Pret> donnees) {
        // Calculer les statistiques pour chaque type de réclamation
        Map<String, Integer> statistiques = new HashMap<>();
        int totalReclamations = donnees.size();

        for (Pret reclamation : donnees) {
            String type = reclamation.getType();
            statistiques.put(type, statistiques.getOrDefault(type, 0) + 1);
        }

        pieChartStatistiques.getData().clear();

        // Ajouter les données au PieChart
        for (Map.Entry<String, Integer> entry : statistiques.entrySet()) {
            String type = entry.getKey();
            int nombreReclamations = entry.getValue();
            double pourcentage = (double) nombreReclamations / totalReclamations * 100;

            PieChart.Data data = new PieChart.Data(type + " (" + String.format("%.2f", pourcentage) + "%)", nombreReclamations);
            pieChartStatistiques.getData().add(data);
        }


    }


}