package importFiles;

import javafx.beans.property.*;
import javafx.scene.control.cell.PropertyValueFactory;
import newDossier.DossierModel;
import newDossier.ToLongStringException;
import sample.Model;
import sample.SingletonConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrganismeModel {

    private LongProperty id = new SimpleLongProperty();

    private StringProperty name = new SimpleStringProperty();

    private StringProperty comment = new SimpleStringProperty();

    private StringProperty nameParser = new SimpleStringProperty();


    private static OrganismeModel organismeModelSelected;


    public static OrganismeModel getOrganismeModelSelected() {
        return organismeModelSelected;
    }

    public static void setOrganismeModelSelected(OrganismeModel organismeModelSelected) {
        OrganismeModel.organismeModelSelected = organismeModelSelected;
    }


    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public String getNameParser() {
        return nameParser.get();
    }

    public StringProperty nameParserProperty() {
        return nameParser;
    }

    public void setNameParser(String nameParser) {
        this.nameParser.set(nameParser);
    }
}
