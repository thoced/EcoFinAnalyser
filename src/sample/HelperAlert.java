package sample;

import javafx.scene.control.Alert;

public class HelperAlert {
    private static HelperAlert ourInstance = new HelperAlert();

    public static HelperAlert getInstance() {
        return ourInstance;
    }

    private HelperAlert() {
    }

    public void showError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Erreur");
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void showMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
