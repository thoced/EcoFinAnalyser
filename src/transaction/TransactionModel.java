package transaction;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class TransactionModel {

    public static enum TYPE_TRANSACTION {FRAIS,VIREMENT,ACHAT,RETRAIT,DEPOT,AUTRE,NONE};

    private StringProperty compte = new SimpleStringProperty();

    private StringProperty compteContrepartie = new SimpleStringProperty();

    private LocalDateTime localDateTime = LocalDateTime.now();

    private DoubleProperty amount = new SimpleDoubleProperty();

    private ObjectProperty<TYPE_TRANSACTION> type = new SimpleObjectProperty<TYPE_TRANSACTION>();

    private StringProperty comment = new SimpleStringProperty();

    public String getCompte() {return compte.get();  }

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

    public double getAmount() {return amount.get(); }

    public DoubleProperty amountProperty() { return amount; }

    public void setAmount(double amount) { this.amount.set(amount); }

    public void setAmount(long amount) {
        this.amount.set(amount);
    }

    public TYPE_TRANSACTION getType() { return type.get(); }

    public ObjectProperty<TYPE_TRANSACTION> typeProperty() { return type; }

    public void setType(TYPE_TRANSACTION type) { this.type.set(type); }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "compte=" + compte +
                ", compteContrepartie=" + compteContrepartie +
                ", localDateTime=" + localDateTime +
                ", amount=" + amount +
                ", type=" + type +
                ", comment=" + comment +
                '}';
    }
}
