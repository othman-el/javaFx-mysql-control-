package com.example.javafx_mysql;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StageData {
    private final StringProperty nom;
    private final StringProperty prenom;
    private final StringProperty email;

    public StageData(String nom, String prenom, String email) {
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.email = new SimpleStringProperty(email);
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public StringProperty prenomProperty() {
        return prenom;
    }

    public StringProperty sujetProperty() {
        return email;
    }
}
