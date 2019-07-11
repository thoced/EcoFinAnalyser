package importFiles;



import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import sample.HelperAlert;
import sample.HelperLoadParser;
import sample.HelperScript;
import sample.ScriptModel;
import transaction.TransactionModel;

import javax.script.ScriptException;
import java.io.*;

import java.net.URL;


import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ImportFilesController  implements Initializable{

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button buttonOk;

    @FXML
    private Button buttonCancel;

    @FXML
    private TableView tableViewOrganisme;

    @FXML
    private TableColumn<ScriptModel,String> tableColumNameOrganisme;

    @FXML
    private TableColumn<ScriptModel,String> tableColumComment;

    private Node backnode;
    private File BaseUtilities;

    @FXML
    public void OnClicButtonOk(){

        if(tableViewOrganisme.getSelectionModel().getSelectedItem() == null ) {
            HelperAlert.getInstance().showError("Veuillez sélectionner un organisme avant de poursuivre la procédure");
            return;
        }
        // on spécifie un organisme
        ScriptModel.setScriptModelSelected((ScriptModel) tableViewOrganisme.getSelectionModel().getSelectedItem());
        // sélection du fichier à importer
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(borderPane.getScene().getWindow());
        if(file != null) {
            // LECTURE DU FICHIER
            ScriptModel currentModel = ScriptModel.getScriptModelSelected();
            // Appel de la méthode parse du script
            try {
                List<TransactionModel> listTransaction = HelperScript.getInstance().parse(currentModel,file);
                for(TransactionModel transactionModel : listTransaction){
                    System.out.println(transactionModel);
                }
            } catch (ScriptException e) {
                HelperAlert.getInstance().showError(e.getLocalizedMessage());
            } catch (NoSuchMethodException e) {
                HelperAlert.getInstance().showError(e.getLocalizedMessage());
            }
        }
        else
            HelperAlert.getInstance().showError("Fichier inconnu");

        // fermeture de la fenetre
        borderPane.getScene().getWindow().hide();
    }

    @FXML
    public void OnClicButtonCancel(){
        borderPane.getScene().getWindow().hide();
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public Button getButtonOk() {
        return buttonOk;
    }

    public Button getButtonCancel() {
        return buttonCancel;
    }

    public TableView getTableViewOrganisme() {
        return tableViewOrganisme;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumNameOrganisme.setCellValueFactory(new PropertyValueFactory<ScriptModel,String>("organismeName"));
        tableColumComment.setCellValueFactory(new PropertyValueFactory<ScriptModel,String>("organismeComment"));

        // reception de la liste des scripts
        List<ScriptModel> list =  HelperScript.getInstance().getListScript();

            // appel de la vue avec la liste
         this.getTableViewOrganisme().setItems(FXCollections.observableArrayList(list));

    }
}
