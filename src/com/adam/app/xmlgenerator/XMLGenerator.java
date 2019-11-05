/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: XMLGenerator.java
 * Brief: 
 * 
 * Author: AdamChen
 * Create Date: 2019/11/5
 */

package com.adam.app.xmlgenerator;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * <h1>XMLGenerator</h1>
 * <p>
 * 
 * @author AdamChen
 * @version
 * @since 2019/11/5
 */
public class XMLGenerator {

    /**
     * @param
     * @return
     */
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // create root
            Element mainRoot = doc.createElementNS(
                    "https://adam.com/createXMLDOM", "Header");
            doc.appendChild(mainRoot);

            // append child node to root node
            mainRoot.appendChild(newNode(doc, "1", "Adam", "12", "988"));
            mainRoot.appendChild(newNode(doc, "2", "Snoopy", "21", "99988"));
            mainRoot.appendChild(newNode(doc, "3", "Bill", "123", "1988"));

            // output child elements to root
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            // create source
            DOMSource source = new DOMSource(doc);
            // Save to file
            StreamResult console = new StreamResult(new File("adam_test.xml"));
            // StreamResult console = new StreamResult(System.out); //output console
            transformer.transform(source, console);

            System.out.println("\nXML DOM Created Successfully..");

        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Create new node
     * 
     * @param
     * @return
     */
    private static Node newNode(Document doc, String id, String item1,
            String item2, String item3) {
        Element company = doc.createElement("Summary");
        // add new attribute and element for new node
        company.setAttribute("id", id);
        company.appendChild(getNewElements(doc, "item1", item1));
        company.appendChild(getNewElements(doc, "item2", item2));
        company.appendChild(getNewElements(doc, "item3", item3));
        company.appendChild(getNewElements(doc, "Note", "This is sample"));
        return company;
    }

    /**
     * Create node element
     * 
     * @param
     * @return
     */
    private static Node getNewElements(Document doc, String name,
            String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

}

/*
 * ===========================================================================
 * Revision history
 * ===========================================================================
 */
