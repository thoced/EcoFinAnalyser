package sample;

import newDossier.DossierModel;
import newDossier.ToLongStringException;

import java.sql.SQLException;
import java.util.List;

public interface Model<T> {
    public  void Insert() throws SQLException;
    public  void Update() throws SQLException;
    public  void Delete() throws SQLException;
    public  List<T> Select() throws SQLException, ToLongStringException;
}
