package newDossier;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import sample.HelperAlert;

import java.sql.SQLException;

public class NewDossierController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button buttonOk;

    @FXML
    private Button buttonCancel;

    @FXML
    private TextField textFieldNewNameDossier;

    @FXML
    private void OnClicButtonOk(){
        try {

            if(textFieldNewNameDossier.getText().length() < 6) {
                HelperAlert.getInstance().showError("Veuillez indiquer un nom de dossier d'un minimum de 6 caractères");
                textFieldNewNameDossier.clear();
                return;
            }

            DossierModel model = new DossierModel(textFieldNewNameDossier.getText());

            if(model.IsNameDossierExist()){
                HelperAlert.getInstance().showError("Un dossier porte déja ce nom, veuillez modifier ce nom");
                textFieldNewNameDossier.clear();
                return;
            }

            // ajout du model
            model.Insert();
            DossierModel.setCurrentDossier(model);

            HelperAlert.getInstance().showMessage("Nouveau dossier ajouté avec succes !");

            borderPane.getScene().getWindow().hide();

        } catch (ToLongStringException e) {
            HelperAlert.getInstance().showError(e.getMessage());
        } catch (SQLException e) {
            HelperAlert.getInstance().showError(e.getMessage());
        }

    }

    @FXML
    private void OnClicButtonCancel(){
        borderPane.getScene().getWindow().hide();
    }
}
