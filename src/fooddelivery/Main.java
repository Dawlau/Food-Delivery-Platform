package fooddelivery;


import fooddelivery.config.DataSetup;
import fooddelivery.config.DatabaseConfiguration;
import fooddelivery.enums.ProductType;
import fooddelivery.models.*;
import fooddelivery.repository.*;

import javax.sound.sampled.Port;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        DataSetup dataSetup = new DataSetup();
        dataSetup.setUp();
        App.run();
    }
}
