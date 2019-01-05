package utils.database.ormclasses;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

@Data
@DatabaseTable(tableName = "ADDRESS")
public class Address {
    public Address() {
    }

    public Address(String street, String local, String city) {
        this.street = street;
        this.local = local;
        this.city = city;
    }

    @DatabaseField(columnName = "ID",generatedId = true)
    private int id;

    @DatabaseField(columnName = "STREET", canBeNull = false)
    private String street;

    @DatabaseField(columnName = "LOCAL", canBeNull = false)
    private String local;

    @DatabaseField(columnName = "CITY", canBeNull = false)
    private String city;

    @DatabaseField(columnName = "OWNER_ID", foreign = true, foreignAutoRefresh = true)
    private Owner owner;
}
