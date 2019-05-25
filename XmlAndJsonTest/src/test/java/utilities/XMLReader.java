package utilities;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XMLReader {

    private Document doc;
    private String fileName;

    public XMLReader(String fileName) {

        this.fileName = fileName;

    }


    public String getLocator(String locator) throws DocumentException {
        SAXReader reader = new SAXReader();
        doc = reader.read(fileName);

        String data = doc.selectSingleNode("//" + locator.replace(".", "/")).getText().trim();
        return data;
    }

}
