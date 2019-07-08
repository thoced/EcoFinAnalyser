package loadDossier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import newDossier.DossierModel;
import newDossier.ToLongStringException;
import sample.HelperAlert;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class LoadDossierController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn<DossierModel,Long> indexColumn;

    @FXML
    private TableColumn<DossierModel,String> nameColumn;

    @FXML
    private Button buttonOk;

    @FXML
    private Button buttonCancel;

    @FXML
    public void OnClicButtonOk() throws NotDossierSelectedException {

        if(tableView.getSelectionModel().getSelectedItem() != null){
            DossierModel model = (DossierModel) tableView.getSelectionModel().getSelectedItem();
            if(model != null){
                DossierModel.setCurrentDossier(model);
            }
        }else
            HelperAlert.getInstance().showError("Aucun dossier n'a été sélectionné");

        borderPane.getScene().getWindow().hide();
    }

    @FXML
    public void OnClicButtonCancel(){
        borderPane.getScene().getWindow().hide();
    }

    public TableView getTableView() {
        return tableView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        indexColumn.setCellValueFactory(new PropertyValueFactory<DossierModel, Long>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<DossierModel,String>("name"));

        try {
        DossierModel model = new DossierModel();
        List<DossierModel> list = null;
        list = model.Select();
        if(list != null){
           ObservableList<DossierModel> observableList = FXCollections.observableArrayList(list);
           tableView.setItems(observableList);
        }

        } catch (SQLException e) {
           HelperAlert.getInstance().showError(e.getMessage());
        } catch (ToLongStringException e) {
            HelperAlert.getInstance().showError(e.getMessage());
        }




    }
}
