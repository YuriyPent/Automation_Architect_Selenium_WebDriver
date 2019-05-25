package rough;

import org.dom4j.DocumentException;
import utilities.XMLReader;

public class TestXML {

    public static void main(String[] args) throws DocumentException {

        XMLReader xml = new XMLReader(System.getProperty("user.dir") +
                "\\XmlAndJsonTest\\src\\test\\resources\\locators\\OR.xml");
        System.out.println(xml.getLocator("header.username.xpath"));

        //driver.findElement(By.xpath(xml.getLocator("homepage.header.username.css"))).sendKeys();
    }

}
