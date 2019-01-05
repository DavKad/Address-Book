package utils.database.fx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
@Getter
public class AddressFx {

    //Fields to get data from database.
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty street = new SimpleStringProperty();
    private StringProperty local = new SimpleStringProperty();
    private StringProperty city = new SimpleStringProperty();
    private StringProperty ownerName = new SimpleStringProperty();

    public AddressFx(Integer id, String street, String local, String city, String ownerName){
        this.id.setValue(id);
        this.street.setValue(street);
        this.local.setValue(local);
        this.city.setValue(city);
        this.ownerName.setValue(ownerName);
    }
}
