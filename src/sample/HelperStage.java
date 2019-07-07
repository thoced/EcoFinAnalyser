package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.Charset;

public class HelperStage {
    private static HelperStage ourInstance = new HelperStage();

    public static HelperStage getInstance() {
        return ourInstance;
    }

    private HelperStage() {
    }

    public Object createStageModal(String viewPath,String title, int width,int height) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
        Parent parent = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent,width,height));
      //  stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.showAndWait();
        Object controller = loader.getController();
        return controller;
    }
}
