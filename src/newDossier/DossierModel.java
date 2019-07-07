package newDossier;

import javafx.beans.property.*;
import sample.Model;
import sample.SingletonConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DossierModel implements Model {

    private static DossierModel currentDossier;

    private LongProperty id = new SimpleLongProperty();

    private StringProperty name = new SimpleStringProperty();

    private static StringProperty nameCurrentDossier = new SimpleStringProperty();

    public DossierModel() {
    }


    public DossierModel(String name) throws ToLongStringException {
        setName(name);
    }

    public static void setCurrentDossier(DossierModel currentDossier) {
        DossierModel.currentDossier = currentDossier;
        if(currentDossier != null)
            nameCurrentDossier.set(currentDossier.getName());
    }

    public static DossierModel getCurrentDossier() {
        return currentDossier;
    }

    public static StringProperty nameCurrentDossierProperty() {
        return nameCurrentDossier;
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

    public void setName(String name) throws ToLongStringException {
        if(name.length() > 32)
            throw new ToLongStringException("Nom de dossier trop grand (veuillez inscrire un nom de 32 caractères maximum");
        this.name.set(name);
    }

    @Override
    public void Insert() throws SQLException {
        String sql = "INSERT into t_dossier (name) VALUES (?)";
        PreparedStatement ps = SingletonConnection.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,getName());
        ps.executeUpdate();
        ResultSet resultSet = ps.getGeneratedKeys();
        if(resultSet.next()){
            setId(resultSet.getLong(1));
        }
    }

    @Override
    public void Update() throws SQLException {
        String sql = "UPDATE t_dossier SET name = ? WHERE id = ?";
        PreparedStatement ps = SingletonConnection.getInstance().getConnection().prepareStatement(sql);
        ps.setString(1,getName());
        ps.setLong(2,getId());
        ps.executeUpdate();
    }

    @Override
    public void Delete() throws SQLException {
        String sql = "DELETE FROM t_dossier WHERE id = ?";
        PreparedStatement ps = SingletonConnection.getInstance().getConnection().prepareStatement(sql);
        ps.setLong(1,getId());
        ps.executeUpdate();
    }

    @Override
    public List<DossierModel> Select() throws SQLException, ToLongStringException {
        String sql = "SELECT * FROM t_dossier";
        List<DossierModel> list = new ArrayList<>();
        Statement st = SingletonConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = st.executeQuery(sql);
        while(resultSet.next()){
            DossierModel model = new DossierModel();
            model.setId(resultSet.getLong("id"));
            model.setName(resultSet.getString("name"));
            list.add(model);
        }
        return list;
    }

    public boolean IsNameDossierExist() throws SQLException {
        String sql = "SELECT * FROM t_dossier WHERE name = ?";
        PreparedStatement ps = SingletonConnection.getInstance().getConnection().prepareStatement(sql);
        ps.setString(1,getName());
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
            return true;
        }
        return false;
    }
}
