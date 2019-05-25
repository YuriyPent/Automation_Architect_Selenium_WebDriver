package utilities;

import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.io.IOException;

public class JsonReader {


    public String fileName;
    private File jsonFile;

    public JsonReader(String fileName) {

        this.fileName = fileName;
    }


    public String getLocator(String locator) throws IOException {

        jsonFile = new File(fileName);
        return JsonPath.read(jsonFile, "$." + locator);


    }

}
