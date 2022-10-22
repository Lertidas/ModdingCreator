package com.example.moddingcreator.util;

import com.example.moddingcreator.data.InstanceData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;

public class XmlUtil {
    public static void addSave(String modName, String modid) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            // Build file
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(InstanceData.xmlSavedModsPath));
            Element root = document.getDocumentElement();

            Element savedModNode = document.createElement("saved-node");

            Element modNameNode = document.createElement("mod-name");
            modNameNode.setTextContent(modName);

            Element modidNode = document.createElement("mod-id");
            modidNode.setTextContent(modid);

            savedModNode.appendChild(modNameNode);
            savedModNode.appendChild(modidNode);
            root.appendChild(savedModNode);

            try (FileOutputStream outputStream = new FileOutputStream(InstanceData.xmlSavedModsPath)) {
                writeXml(document, outputStream);
            }
            catch (IOException | TransformerException e) {
                throw new RuntimeException(e);
            }
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeSave(String modName) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            // Build file
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(InstanceData.xmlSavedModsPath));

            NodeList nodes = document.getElementsByTagName("saved-node");

            for (int i = 0; i < nodes.getLength(); i++) {
                if (nodes.item(i).getFirstChild().getTextContent().equals(modName)) {
                    nodes.item(i).getParentNode().removeChild(nodes.item(i));
                    break;
                }
            }

            try (FileOutputStream outputStream = new FileOutputStream(InstanceData.xmlSavedModsPath)) {
                writeXml(document, outputStream);
            }
            catch (IOException | TransformerException e) {
                throw new RuntimeException(e);
            }
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeXml(Document document, FileOutputStream outputStream) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(outputStream);

        transformer.transform(source, result);
    }

    public static HashSet<String> getSaveNames() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            // Build file
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(InstanceData.xmlSavedModsPath));
            NodeList nodes = document.getElementsByTagName("mod-name");
            HashSet<String> saveNames = new HashSet<>();
            for (int i = 0; i < nodes.getLength(); i++) {
                saveNames.add(nodes.item(i).getTextContent());
            }
            return saveNames;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getModid(String modName) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            // Build file
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(InstanceData.xmlSavedModsPath));
            // Xpath
            XPath xPath = XPathFactory.newInstance().newXPath();
            String xPathExpression = "/Saves/saved-node[mod-name = '" + modName + "']";
            // xPathExpression = "Saves/saved-node";

            XPathEvaluationResult<?> nodeList = xPath.evaluateExpression(xPathExpression, document);
            XPathNodes xPathNodes = (XPathNodes) nodeList.value();
            return xPathNodes.get(0).getChildNodes().item(1).getTextContent();
        } catch (ParserConfigurationException | IOException | SAXException | XPathException e) {
            throw new RuntimeException(e);
        }
    }
}
