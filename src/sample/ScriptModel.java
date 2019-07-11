package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.FileInputStream;

public class ScriptModel {

    private static ScriptModel scriptModelSelected = null;

    private StringProperty nameFileScript = new SimpleStringProperty();

    private StringProperty contentScript = new SimpleStringProperty();

    private StringProperty organismeName = new SimpleStringProperty();

    private StringProperty organismeComment = new SimpleStringProperty();

    public String getNameFileScript() {
        return nameFileScript.get();
    }

    public StringProperty nameFileScriptProperty() {
        return nameFileScript;
    }

    public void setNameFileScript(String nameFileScript) {
        this.nameFileScript.set(nameFileScript);
    }

    public String getContentScript() {
        return contentScript.get();
    }

    public StringProperty contentScriptProperty() {
        return contentScript;
    }

    public void setContentScript(String contentScript) {
        this.contentScript.set(contentScript);
    }

    public String getOrganismeName() {
        return organismeName.get();
    }

    public StringProperty organismeNameProperty() {
        return organismeName;
    }

    public void setOrganismeName(String organismeName) {
        this.organismeName.set(organismeName);
    }

    public String getOrganismeComment() {
        return organismeComment.get();
    }

    public StringProperty organismeCommentProperty() {
        return organismeComment;
    }

    public void setOrganismeComment(String organismeComment) {
        this.organismeComment.set(organismeComment);
    }

    public static ScriptModel getScriptModelSelected() {
        return scriptModelSelected;
    }

    public static void setScriptModelSelected(ScriptModel scriptModelSelected) {
        ScriptModel.scriptModelSelected = scriptModelSelected;
    }
}
