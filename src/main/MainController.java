package main;

import importFiles.ImportFilesController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.tools.ant.taskdefs.SQLExec;
import sample.HelperAlert;
import sample.HelperStage;
import transaction.TransactionModel;

import javax.script.*;
import java.io.*;
import java.net.URL;
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
    private MenuItem menuItemScript;

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
            stage.setScene(new Scene(parent, 320, 480));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Charger un dossier");
            stage.showAndWait();
        } catch (IOException e) {
            HelperAlert.getInstance().showError(e.getLocalizedMessage());
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
            stage.showAndWait();

        }
        catch(IOException e){
            HelperAlert.getInstance().showError(e.getMessage());
        }
    }

    @FXML
    public void OnClicMenuItemScript() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> factories = manager.getEngineFactories();

        for(ScriptEngineFactory factory : factories){
            System.out.println("Name:" + factory.getEngineName());
        }

        ScriptEngine engine = manager.getEngineByName("Groovy");
        if(engine == null)
            System.out.println("engine inconnu");

        // chargement du script
        String dir = System.getProperty("user.dir");
        String path = dir + "/" + "script.groovy";

        File file = new File(path);

        String script = null;
        try {

            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] scriptBytes = new byte[fileInputStream.available()];
            fileInputStream.read(scriptBytes);
            fileInputStream.close();
            script = new String(scriptBytes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{

            // appel du fichier de selection
            FileChooser fileChooser = new FileChooser();
            File fileParser = fileChooser.showOpenDialog(borderPane.getScene().getWindow());
            // evaluation du script
            engine.eval(script);

            if(engine  instanceof Invocable){
                Invocable moteurInvocable = (Invocable) engine;
                List<TransactionModel> resultat = (List) moteurInvocable.invokeFunction("parse",fileParser);
            }

        }catch(ScriptException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Label getLabelCurrentDossier() {
        return labelCurrentDossier;
    }

}
