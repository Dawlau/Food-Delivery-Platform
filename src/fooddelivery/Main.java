package fooddelivery;


import fooddelivery.config.DataSetup;
import fooddelivery.config.DatabaseConfiguration;

public class Main {

    public static void main(String[] args) {
//        App.run();
        DatabaseConfiguration.getDatabaseConnection();

        DataSetup setUpData = new DataSetup();
        setUpData.setUp();
    }
}
