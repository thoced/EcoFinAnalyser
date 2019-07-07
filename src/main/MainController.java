package main;

import importFiles.ImportFilesController;
import importFiles.OrganismeModel;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import loadDossier.LoadDossierController;
import newDossier.DossierModel;
import newDossier.ToLongStringException;
import sample.HelperAlert;
import sample.HelperStage;

import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    @FXML
    private BorderPane borderPane;

    @FXML
   private MenuItem menuItemNewDossier;

    @FXML
    private MenuItem menuItemLoadDossier;

    @FXML
    private MenuItem menuItemImportFile;

    @FXML
    private Label labelCurrentDossier;

    @FXML
    private void OnClicMenuItemNewDossier(){
        try {
            HelperStage.getInstance().createStageModal("../newDossier/NewDossierView.fxml","Nouveau dossier",320,240);
        } catch (IOException e) {
            HelperAlert.getInstance().showError(e.getMessage());
        }

    }

    @FXML
    private void OnClicMenuItemLoadDossier(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../loadDossier/LoadDossierView.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent,320,480));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Charger un dossier");
            LoadDossierController controller = loader.getController();
            DossierModel model = new DossierModel();
            List<DossierModel> list = model.Select();

            if(list != null){
                ObservableList<DossierModel> observableList = FXCollections.observableArrayList(list);
                controller.getTableView().setItems(observableList);
            }

            stage.showAndWait();

            } catch (IOException e) {
            HelperAlert.getInstance().showError(e.getMessage());
            } catch (SQLException e) {
            HelperAlert.getInstance().showError(e.getMessage());
            } catch (ToLongStringException e) {
            HelperAlert.getInstance().showError(e.getMessage());
            }

    }

    @FXML
    public void OnClicMenuItemImportFile(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../importFiles/ImportFilesView.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent, 480,480));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Import des fichiers organisme");
            ImportFilesController controller = loader.getController();
            // selection de la liste des organismes
            OrganismeModel organismeModel = new OrganismeModel();
            List<OrganismeModel> list = organismeModel.Select();
            // appel de la vue avec la liste
            controller.getTableViewOrganisme().setItems(FXCollections.observableArrayList(list));
            stage.showAndWait();

        }
        catch(IOException e){
            HelperAlert.getInstance().showError(e.getMessage());
        } catch (SQLException e) {
            HelperAlert.getInstance().showError(e.getMessage());
        } catch (ToLongStringException e) {
            HelperAlert.getInstance().showError(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Label getLabelCurrentDossier() {
        return labelCurrentDossier;
    }

}
