package javacode;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Folders_X {
	public static Document loadXML(String xml) throws Exception {
		final DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		return builder.parse(new InputSource(new StringReader(xml)));
	}
	
	public static Collection<String> folderNames(String xml, char startingLetter) throws Exception {
		List<String> names = new ArrayList<>();
		Document doc = loadXML(xml);
		doc.getDocumentElement().normalize();
		
		NodeList nl = doc.getElementsByTagName("folder");
		for (int i = 0; i < nl.getLength() ; i++) {
			Node n = nl.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) n;
				String name = e.getAttribute("name");
				if (name.length() > 0 && name.charAt(0) == startingLetter)
					names.add(name);
			}
		}
		
		return names;
	}
	
//    public static Collection<String> folderNames(String xml, char startingLetter) { //throws Exception {
//    	List<String> list = new ArrayList<>();
//    	
//    	int len = xml.length();
//    	String target = "<folder name";
//    	for (int i = 0 ; i < len - target.length() ; i++) {
//    		if (target.equals(xml.substring(i, i + target.length()))) {
//    			int bi = i + target.length() + 2;
//    			int ei = xml.indexOf('"', bi);
//    			//System.out.println(xml.substring(bi, ei));
//    			String substr = xml.substring(bi, ei); 
//    			if (substr.length() > 0 && substr.startsWith(""+startingLetter))
//    				list.add(substr);
//    		}
//    	}
//    	return list;
//    }
    
    public static void main(String[] args) throws Exception {
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<folder name=\"c\">" +
                    "<folder name=\"program files\">" +
                        "<folder name=\"uninstall information\" />" +
                    "</folder>" +
                    "<folder name=\"users\" />" +
                "</folder>";

        Collection<String> names = folderNames(xml, 'u');
        for(String name: names)
            System.out.println(name);
    }
}
