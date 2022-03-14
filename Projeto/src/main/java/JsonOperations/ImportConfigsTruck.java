package JsonOperations;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class imports truck capacity
 */
public class ImportConfigsTruck {
    public ImportConfigsTruck() {

    }

    /**
     * This method import truck capacity
     *
     * @param path path to file
     * @return capacity present in the file, 63 if the file is not found
     */
    public static double importConfigCapacity(String path) {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Path should not be null or empty");
        }
        JSONParser jsonParser = new JSONParser();

        File file = new File(path);
        boolean exists = file.exists();

        if (exists) {

            JSONObject obj = null;
            try {
                obj = (JSONObject) jsonParser.parse(new FileReader(file.getPath()));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            double volume = 0;
            String volumeTemp = obj.get("capacity").toString();
            volume = Double.valueOf(volumeTemp);
            return volume;
        }

        return 63.0;
    }
}
