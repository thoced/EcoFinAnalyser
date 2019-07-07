package transaction;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class TransactionModel {

    private StringProperty compteFrom = new SimpleStringProperty();

    private StringProperty compteTo = new SimpleStringProperty();

    private ObjectProperty<LocalDateTime> datetime = new SimpleObjectProperty<LocalDateTime>();

    private LongProperty amount = new SimpleLongProperty();

    private StringProperty type = new SimpleStringProperty();

    private StringProperty comment = new SimpleStringProperty();

    public String getCompteFrom() {
        return compteFrom.get();
    }

    public StringProperty compteFromProperty() {
        return compteFrom;
    }

    public void setCompteFrom(String compteFrom) {
        this.compteFrom.set(compteFrom);
    }

    public String getCompteTo() {
        return compteTo.get();
    }

    public StringProperty compteToProperty() {
        return compteTo;
    }

    public void setCompteTo(String compteTo) {
        this.compteTo.set(compteTo);
    }

    public LocalDateTime getDatetime() {
        return datetime.get();
    }

    public ObjectProperty<LocalDateTime> datetimeProperty() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime.set(datetime);
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
