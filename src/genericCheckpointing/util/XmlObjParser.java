package genericCheckpointing.util;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlObjParser implements ParserI {

	@Override
	public SerializableObject parser(String objStr) {
		
		SerializableObject retObj = null;
		
		try {
			// http://www.javacodegeeks.com/2013/05/parsing-xml-using-dom-sax-and-stax-parser-in-java.html
			// get DOM Builder Factory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			// get DOM Builder
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			// load and parse xml string
			Document document = builder.parse(new ByteArrayInputStream(objStr.getBytes("UTF-8")));
			
			
			// Iterating through the nodes and extracting the data
			NodeList nodeList = document.getDocumentElement().getChildNodes();
			String clsType = null;
			for (int i = 0; i < nodeList.getLength(); i++) {
				
				Node node = nodeList.item(i);
				clsType = node.getAttributes().getNamedItem("clsType").getNodeValue();
				NodeList childList = node.getChildNodes();
				Object obj = ReflectUtil.reflectObj(childList, clsType);
				
				if (clsType.equals("genericCheckpointing.util.EmployeeRecord")) {
					retObj = ((EmployeeRecord)obj);
				} else if (clsType.equals("genericCheckpointing.util.StudentRecord")) {
					retObj = ((StudentRecord)obj);
				} else {
					throw new RuntimeException("Person type not valid!");
				}
			}
			
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			//e.printStackTrace();
			System.exit(1);
		} 		
		
		return retObj;
	}

}
