package transaction;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;

public class TransactionModel {

    private StringProperty compte = new SimpleStringProperty();

    private StringProperty compteContrepartie = new SimpleStringProperty();

    private LocalDateTime localDateTime = LocalDateTime.now();

    private LongProperty amount = new SimpleLongProperty();

    private StringProperty type = new SimpleStringProperty();

    private StringProperty comment = new SimpleStringProperty();

    public String getCompte() {
        return compte.get();

    }

    public StringProperty compteProperty() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte.set(compte);
    }

    public String getCompteContrepartie() {
        return compteContrepartie.get();
    }

    public StringProperty compteContrepartieProperty() {
        return compteContrepartie;
    }

    public void setCompteContrepartie(String compteContrepartie) {
        this.compteContrepartie.set(compteContrepartie);
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public long getAmount() {
        return amount.get();
    }

    public LongProperty amountProperty() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount.set(amount);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
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
