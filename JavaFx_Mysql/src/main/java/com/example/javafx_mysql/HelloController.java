package com.example.javafx_mysql;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;

public class HelloController {

    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField emailField;


    private final String DB_URL = "jdbc:mysql://localhost:3306/gestion_stages";
    private final String USER = "root";
    private final String PASS = "";

    @FXML
    void SaveClick(ActionEvent event) {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();


        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty()) {
            showErrorAlert("Erreur de validation", "Tous les champs sont obligatoires!");
            return;}

        String query = "INSERT INTO stages (nom, prenom ,email ) VALUES (?, ?, ? )";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, email );
            stmt.executeUpdate();

            clearFields();
            System.out.println("Données enregistrées avec succès.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'enregistrement des données.");
        }
    }

    private void clearFields() {
        nomField.clear();
        prenomField.clear();
        emailField.clear();
    }


    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);    alert.setHeaderText(null);
        alert.setContentText(message);    alert.showAndWait();
    }
    private void showSuccessAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);    alert.setHeaderText(null);
        alert.setContentText(message);    alert.showAndWait();
    }

}


