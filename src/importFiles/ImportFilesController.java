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
import transaction.TransactionModel;

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
            // Appel de la méthode parse du parser JAR
            List<TransactionModel> listTransaction  = HelperLoadParser.getInstance().parse(currentModel.getNameParser(),file);
            if(listTransaction != null){
                for(TransactionModel transactionModel : listTransaction){
                    System.out.println(transactionModel.getCompte());
                    System.out.println(transactionModel.getLocalDateTime().toString());
                }
            }

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


            // selection de la liste des organismes
          String[] listParser = HelperLoadParser.getInstance().getListParser();
          List<OrganismeModel> listOrganisme = new ArrayList<>();
          for(String parser : listParser){

              // pour chaque parser trouvé, on appel la methode getNameOrganisme
              String nameOrganisme = HelperLoadParser.getInstance().getNameOrganisme(parser);
              String nameOrganismeComment = HelperLoadParser.getInstance().getNameOrganismeComment(parser);

              OrganismeModel organismeModel = new OrganismeModel();
              organismeModel.setNameParser(parser); // ajout du nom du parser JAR
              organismeModel.setName(nameOrganisme);
              organismeModel.setComment(nameOrganismeComment);
              listOrganisme.add(organismeModel);
          }


            // appel de la vue avec la liste
            this.getTableViewOrganisme().setItems(FXCollections.observableArrayList(listOrganisme));

    }
}
