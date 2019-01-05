package utils.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


import lombok.Getter;
import utils.database.ormclasses.Address;
import utils.database.ormclasses.Owner;

import java.io.IOException;
import java.sql.SQLException;

public class Connection {

    private static final String URL = "jdbc:sqlite:MyDB.db";

    @Getter
    private static ConnectionSource connectionSource;

    @Getter
    private static Dao<Address, Integer> addressDao;

    @Getter
    private static Dao<Owner, Integer> ownerDao;


    public static void initDatabase(){
        initConnection();
        initDao();
        initTable();
    }

    private static void initDao() {
        try {
            addressDao = DaoManager.createDao(connectionSource, Address.class);
            ownerDao = DaoManager.createDao(connectionSource, Owner.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ConnectionSource initConnection(){
        try {
            connectionSource =  new JdbcConnectionSource(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connectionSource;
    }

    private static void initTable()  {
        try {
            TableUtils.createTableIfNotExists(Connection.initConnection(), Address.class);
            TableUtils.createTableIfNotExists(Connection.initConnection(), Owner.class);
            Connection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(){
        if(connectionSource != null){
            try {
                connectionSource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}