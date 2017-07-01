package earthquake;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Kevin Yang
 *
 */

public class Location {
   private Document locationXML;
   
   public Document getLocationXML() {
      return this.locationXML;
   }
   
   public Location(String address) throws Exception{
      String url = "https://maps.googleapis.com/maps/api/geocode/xml?address=" 
         + address +
         "&key=AIzaSyDC6yrVEAoHs0cvf2nx4nCaUipMoL0F5Rc";
      System.out.println(url);
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true); // parse uses XML namespaces
      this.locationXML = factory.newDocumentBuilder().parse(new URL(url).openStream());
   }
   
   private void xmlParse(Node node) {
    NodeList nodeList = node.getChildNodes();
    for (int i = 0; i < nodeList.getLength(); i++) {
        Node currentNode = nodeList.item(i);
        String value = currentNode.getNodeName();
        System.out.print(value + " "); // prints Employee

        NodeList nodes = currentNode.getChildNodes();
        for (int j = 0; j < nodes.getLength(); j++) {
            Node cnode = nodes.item(j);
            System.out.print(cnode.getNodeName() + " ");//prints:name, id, age
        }
        System.out.println();
    }
   }
   
   public void printDocument(Document doc, OutputStream out) throws IOException, TransformerException {
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer transformer = tf.newTransformer();
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

    transformer.transform(new DOMSource(doc), 
         new StreamResult(new OutputStreamWriter(out, "UTF-8")));
    
    xmlParse(doc.getElementsByTagName("bounds").item(0));
   }
}
