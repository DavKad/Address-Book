package utils.database.mvc;

import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import utils.database.Connection;
import utils.database.fx.AddressFx;
import utils.database.ormclasses.Address;
import utils.database.ormclasses.Owner;


import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AddressMVC {

    private static final Logger LOGGER = LoggerFactory.getLogger(Address.class);

    @Getter
    private ObservableList<AddressFx> oList = FXCollections.observableArrayList();

    //Creating a ObservableList<AddressFX> instances.
    private ObservableList<AddressFx> addToList(List<Address> list) {
        list.forEach(c -> {
            if (c.getOwner() != null) {
                oList.add(new AddressFx(c.getId(), c.getStreet(), c.getLocal(), c.getCity(), c.getOwner().getName()));
            } else {
                oList.add(new AddressFx(c.getId(), c.getStreet(), c.getLocal(), c.getCity(), "not assigned"));
            }
        });
        return oList;
    }

    //Finding row when given street exist.
    public ObservableList<AddressFx> findAddress(String address, String owner, String local, String city) throws SQLException {
        List<Address> addresses = Connection.getAddressDao().queryForAll();
        addresses.forEach(c -> {
            if (c.getOwner() == null) {
                c.setOwner(new Owner());
                c.getOwner().setName("not assigned");
            }
        });
        if (owner.equals("")) {
            return this.addToList(addresses.stream()
                    .filter(s -> s.getStreet().contains(address) && s.getLocal().contains(local) && s.getCity().contains(city))
                    .collect(Collectors.toList()));
        } else {
            return this.addToList(addresses.stream()
                    .filter(s -> s.getOwner().getName().contains(owner))
                    .collect(Collectors.toList()));
        }
    }

    //Adding street when Owner is not defined
    public void addAddress(Address address) {
        try {
            Connection.getAddressDao().createOrUpdate(address);
            Connection.closeConnection();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    //Adding street when Owner is defined
    public void addAddressWithOwner(Address address, Owner owner) {
        try {
            Connection.getOwnerDao().createOrUpdate(owner);
            address.setOwner(owner);
            Connection.getAddressDao().createOrUpdate(address);
            Connection.closeConnection();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    //List table content
    public ObservableList<AddressFx> initTable() throws SQLException {
        List<Address> addresses = Connection.getAddressDao().queryForAll();
        this.oList.clear();
        Connection.closeConnection();
        return this.addToList(addresses);
    }
}
