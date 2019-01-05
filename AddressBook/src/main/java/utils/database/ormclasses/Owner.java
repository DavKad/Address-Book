package utils.database.ormclasses;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

@Data
@DatabaseTable(tableName = "OWNER")
public class Owner {
    public Owner() {

    }

    public Owner(String name, int age, long pesel) {
        this.name = name;
        this.age = age;
        this.pesel = pesel;
    }

    @DatabaseField(columnName = "ID",generatedId = true)
    private int id;

    @DatabaseField(columnName = "NAME",canBeNull = false)
    private String name;

    @DatabaseField(columnName = "AGE", canBeNull = false)
    private int age;

    @DatabaseField(columnName = "PESEL", canBeNull = false)
    private long pesel;

    @ForeignCollectionField
    private ForeignCollection<Address> ownerAddresses;


}
