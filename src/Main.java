import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        File file = new File("processen10000.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);
        document.getDocumentElement().normalize();

        NodeList processesXml = document.getElementsByTagName("process");
        Process[] processes = new Process[processesXml.getLength()];
        Process[] terminated = new Process[processesXml.getLength()];

        for(int i = 0; i < processesXml.getLength(); i++){
            Node processXml = processesXml.item(i);
            Element pElement = (Element)processXml;
            int a = Integer.parseInt(pElement.getElementsByTagName("arrivaltime").item(0).getTextContent());
            int s = Integer.parseInt(pElement.getElementsByTagName("servicetime").item(0).getTextContent());
            Process process = new Process(a,s);
            processes[i] = process;
        }

        for (Process process : processes) {
            System.out.println(process.arrivaltime + "/" + process.servicetime);
        }
    }
}
