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

public class OrganismeModel implements Model {

    private LongProperty id = new SimpleLongProperty();

    private StringProperty name = new SimpleStringProperty();

    private StringProperty comment = new SimpleStringProperty();

    private IntegerProperty firstRow = new SimpleIntegerProperty();

    private IntegerProperty columnCompteFrom = new SimpleIntegerProperty();

    private IntegerProperty columnCompteTo = new SimpleIntegerProperty();

    private IntegerProperty columnDateTime = new SimpleIntegerProperty();

    private IntegerProperty columnAmount = new SimpleIntegerProperty();

    private IntegerProperty columnType = new SimpleIntegerProperty();

    private IntegerProperty columnComment = new SimpleIntegerProperty();

    private static OrganismeModel organismeModelSelected;


    public static OrganismeModel getOrganismeModelSelected() {
        return organismeModelSelected;
    }

    public static void setOrganismeModelSelected(OrganismeModel organismeModelSelected) {
        OrganismeModel.organismeModelSelected = organismeModelSelected;
    }

    public int getFirstRow() {
        return firstRow.get();
    }

    public IntegerProperty firstRowProperty() {
        return firstRow;
    }

    public void setFirstRow(int firstRow) {
        this.firstRow.set(firstRow);
    }

    public int getColumnCompteFrom() {
        return columnCompteFrom.get();
    }

    public IntegerProperty columnCompteFromProperty() {
        return columnCompteFrom;
    }

    public void setColumnCompteFrom(int columnCompteFrom) {
        this.columnCompteFrom.set(columnCompteFrom);
    }

    public int getColumnCompteTo() {
        return columnCompteTo.get();
    }

    public IntegerProperty columnCompteToProperty() {
        return columnCompteTo;
    }

    public void setColumnCompteTo(int columnCompteTo) {
        this.columnCompteTo.set(columnCompteTo);
    }



    public int getColumnAmount() {
        return columnAmount.get();
    }

    public IntegerProperty columnAmountProperty() {
        return columnAmount;
    }

    public void setColumnAmount(int columnAmount) {
        this.columnAmount.set(columnAmount);
    }

    public int getColumnDateTime() {
        return columnDateTime.get();
    }

    public IntegerProperty columnDateTimeProperty() {
        return columnDateTime;
    }

    public void setColumnDateTime(int columnDateTime) {
        this.columnDateTime.set(columnDateTime);
    }

    public int getColumnType() {
        return columnType.get();
    }

    public IntegerProperty columnTypeProperty() {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType.set(columnType);
    }

    public int getColumnComment() {
        return columnComment.get();
    }

    public IntegerProperty columnCommentProperty() {
        return columnComment;
    }

    public void setColumnComment(int columnComment) {
        this.columnComment.set(columnComment);
    }

    @Override
    public void Insert() throws SQLException {

    }

    @Override
    public void Update() throws SQLException {

    }

    @Override
    public void Delete() throws SQLException {

    }

    @Override
    public List<OrganismeModel> Select() throws SQLException, ToLongStringException {
        String sql = "SELECT * FROM t_organisme";
        Statement st = SingletonConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = st.executeQuery(sql);
        List<OrganismeModel> list = new ArrayList<>();
        while(resultSet.next()){
            OrganismeModel organismeModel = new OrganismeModel();
            organismeModel.setId(resultSet.getLong("id"));
            organismeModel.setName(resultSet.getString("name"));
            organismeModel.setComment(resultSet.getString("comment"));
            organismeModel.setFirstRow(resultSet.getInt("first_row"));
            organismeModel.setColumnCompteFrom(resultSet.getInt("column_compte_from"));
            organismeModel.setColumnCompteTo(resultSet.getInt("column_compte_to"));
            organismeModel.setColumnDateTime(resultSet.getInt("column_datetime"));
            organismeModel.setColumnAmount(resultSet.getInt("column_amount"));
            organismeModel.setColumnType(resultSet.getInt("column_type"));
            organismeModel.setColumnComment(resultSet.getInt("column_comment"));
            list.add(organismeModel);
        }

        return list;
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
}
