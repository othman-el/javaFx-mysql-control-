package com.example.javafx_mysql;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StageData {
    private final StringProperty nom;
    private final StringProperty marque;
    private final StringProperty annee;

    public StageData(String nom, String marque, String annee) {
        this.nom = new SimpleStringProperty(nom);
        this.marque = new SimpleStringProperty(marque);
        this.annee = new SimpleStringProperty(annee);
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public StringProperty marqueProperty() {
        return marque;
    }

    public StringProperty anneeProperty() {
        return annee;
    }
}
