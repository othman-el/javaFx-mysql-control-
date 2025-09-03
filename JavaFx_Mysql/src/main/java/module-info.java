module com.example.javafx_mysql {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    opens com.example.javafx_mysql to javafx.fxml;
    exports com.example.javafx_mysql;
}