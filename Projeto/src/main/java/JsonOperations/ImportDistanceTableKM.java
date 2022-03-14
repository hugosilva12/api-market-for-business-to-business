package JsonOperations;

import ModuleCost.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class that imports a json file with the distances between districts
 */
public class ImportDistanceTableKM {

    private IDistanceTable basicIDistanceTable = new DistanceTable();

    public ImportDistanceTableKM() {

    }

    /**
     * Returns a ArrayList of districtsCosts imported by a JSON file.
     *
     * @param path   the path of the JSON file to import the costsTable' data.
     * @throws IllegalArgumentException If the argument path is null or empty.
     *
     * @return  a ArrayList of districtsCosts imported by a JSON file.
     */
    public IDistanceTable importCostsTableKM(String path) {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Path should not be null or empty");
        }

        this.basicIDistanceTable = new DistanceTable();

        File file = new File(path);

        JSONParser jsonParser = new JSONParser();
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

            JSONArray listDistrictsDistances = (JSONArray) obj.get("districts");
            for (int i = 0; i < listDistrictsDistances.size(); i++) {
                JSONObject objDistrictsDistances = (JSONObject) listDistrictsDistances.get(i);

                IDistrictDistance listIDistrictDistance = new DistrictDistance((String) objDistrictsDistances.get("name"));

                JSONArray listDistances = (JSONArray) objDistrictsDistances.get("distances");
                for (int j = 0; j < listDistances.size(); j++) {
                    JSONObject objDistance = (JSONObject) listDistances.get(j);

                    int distance = Integer.parseInt(objDistance.get("distance").toString());

                    IDistanceLine basicDistance = new DistanceLine((String) objDistance.get("id"), distance);

                    listIDistrictDistance.addDistance(basicDistance);
                }
                basicIDistanceTable.addDistrictDistance(listIDistrictDistance);
            }

            return this.basicIDistanceTable;

        } else {
            return null;
        }

    }
}
