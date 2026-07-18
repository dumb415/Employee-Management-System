package com.ems.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {

    /** Replace the whole window scene (e.g. login -> dashboard, logout -> login). */
    public static void switchScene(ActionEvent event, String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(SceneSwitcher.class.getResource(fxmlPath));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + fxmlPath, e);
        }
    }

    /** Load an FXML as a node (for embedding inside a dashboard's center pane). */
    public static Parent load(String fxmlPath) {
        try {
            return FXMLLoader.load(SceneSwitcher.class.getResource(fxmlPath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + fxmlPath, e);
        }
    }
}
