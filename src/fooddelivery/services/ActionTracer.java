package fooddelivery.services;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionTracer {

    public static void traceAction(String action){

        String currentDate = new SimpleDateFormat("dd-MM-yyyy k:m:s").format(new Date()).toString();

        CsvReader r = CsvReader.getInstance();

        String text = r.readFile("csvFiles/ActionsTrace.csv");
        text += action + ", " + currentDate + "\n";

        CsvWriter w = CsvWriter.getInstance();
        w.writeToFile(text, "csvFiles/ActionsTrace.csv");
    }
}
