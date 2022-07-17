package ProjectLevel;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
//import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateXMLFile {

	public static final String xmlFilePath = "input\\xmlfile.xml";
	private static double out;
	private static String rl;
	
	public CreateXMLFile(){
		out = new Predict().result;
		Analysis();
		Create();
	}
	
	private static void Analysis() {
		if(out==0) {
			rl="Medium";
		}else if(out==1) {
			rl="Low";
		}else if(out==2) {
			rl="High";
		}else {
			rl="Null";
		}
	}
	
	private static void Create() {
		 
        try {
 
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance(); 
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder(); 
            Document document = documentBuilder.newDocument();
 
            // root element
            Element root = document.createElement("src");
            document.appendChild(root);
 
            // reusabilityLevel element
            Element reusabilityLevel = document.createElement("reusabilityLevel");
            reusabilityLevel.appendChild(document.createTextNode(rl));
            root.appendChild(reusabilityLevel);
 
            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
 
            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging 
 
            transformer.transform(domSource, streamResult);
 
            System.out.println("Done creating XML File");
 
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
