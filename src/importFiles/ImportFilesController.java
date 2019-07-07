package importFiles;



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
import transaction.TransactionModel;

import java.io.*;

import java.net.URL;


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
    private TableColumn<OrganismeModel,String> tableColumNameOrganisme;

    @FXML
    private TableColumn<OrganismeModel,String> tableColumComment;

    private Node backnode;
    private File BaseUtilities;

    @FXML
    public void OnClicButtonOk(){

        if(tableViewOrganisme.getSelectionModel().getSelectedItem() == null ) {
            HelperAlert.getInstance().showError("Veuillez sélectionner un organisme avant de poursuivre la procédure");
            return;
        }
        // on spécifie un organisme
        OrganismeModel.setOrganismeModelSelected((OrganismeModel) tableViewOrganisme.getSelectionModel().getSelectedItem());
        // sélection du fichier à importer
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(borderPane.getScene().getWindow());
        if(file != null) {
            // LECTURE DU FICHIER
            OrganismeModel currentModel = OrganismeModel.getOrganismeModelSelected();
            // reception du JAR parserContext
            String[] list = HelperLoadParser.getInstance().getListParser();

            List<TransactionModel> ret = HelperLoadParser.getInstance().InvokeMethod(list[0], "parse");

        }
        else
            HelperAlert.getInstance().showError("Fichier inconnu");

        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./AnchorPaneImport.fxml"));
        backnode = borderPane.getCenter();
        borderPane.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        tableColumNameOrganisme.setCellValueFactory(new PropertyValueFactory<OrganismeModel,String>("name"));
        tableColumComment.setCellValueFactory(new PropertyValueFactory<OrganismeModel,String>("comment"));
    }
}
