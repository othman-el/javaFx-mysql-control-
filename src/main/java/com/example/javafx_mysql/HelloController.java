package com.example.javafx_mysql;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelloController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField marqueField;

    @FXML
    private DatePicker anneeField;  // Changed from TextField to DatePicker

    private final String DB_URL = "jdbc:mysql://localhost:3307/efcf";
    private final String USER = "root";
    private final String PASS = "";

    @FXML
    void SaveClick(ActionEvent event) {
        String nom = nomField.getText();
        String marque = marqueField.getText();
        LocalDate date = anneeField.getValue();  // Get LocalDate from DatePicker

        if (nom.isEmpty() || marque.isEmpty() || date == null) {
            showErrorAlert("Erreur de validation", "Tous les champs sont obligatoires!");
            return;
        }

        // Format the date as string (you can change the format as needed)
        String annee = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String query = "INSERT INTO equipments (nom, marque, annee) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nom);
            stmt.setString(2, marque);
            stmt.setString(3, annee);
            stmt.executeUpdate();

            clearFields();
            showSuccessAlert("Succès", "Données enregistrées avec succès!");

        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Erreur de base de données", "Erreur lors de l'enregistrement des données: " + e.getMessage());
        }
    }

    private void clearFields() {
        nomField.clear();
        marqueField.clear();
        anneeField.setValue(null);  // Clear DatePicker
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}