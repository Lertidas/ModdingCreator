package com.example.moddingcreator.util;

import com.example.moddingcreator.data.InstanceData;
import com.example.moddingcreator.data.LoadedModData;
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

    // General

    public static void createXML(String rootElementName, String fileName) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement(rootElementName);
            document.appendChild(rootElement);

            try (FileOutputStream outputStream = new FileOutputStream(LoadedModData.modDataPath + fileName)) {
                writeXml(document, outputStream);
            }
            catch (IOException | TransformerException e) {
                throw new RuntimeException(e);
            }
        }
        catch (ParserConfigurationException e) {
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

    // XML Mod Saves

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

    // XML Items

    public static void saveItem(String itemName, String className, String variableName, String jsonName) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            // Build file
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(LoadedModData.modDataPath + "itemData.xml"));
            Element root = document.getDocumentElement();

            Element itemNode = document.createElement("item-node");

            Element itemNameNode = document.createElement("item-name");
            itemNameNode.setTextContent(itemName);

            Element itemClassName = document.createElement("class-name");
            itemClassName.setTextContent(className);

            Element itemVariableName = document.createElement("variable-name");
            itemVariableName.setTextContent(variableName);

            Element itemJSONName = document.createElement("json-name");
            itemJSONName.setTextContent(jsonName);

            itemNode.appendChild(itemNameNode);
            itemNode.appendChild(itemClassName);
            itemNode.appendChild(itemVariableName);
            itemNode.appendChild(itemJSONName);
            root.appendChild(itemNode);

            try (FileOutputStream outputStream = new FileOutputStream(LoadedModData.modDataPath + "itemData.xml")) {
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

    public static boolean containsItem(String itemName, String className, String variableName, String jsonName) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            // Build file
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(LoadedModData.modDataPath + "itemData.xml"));
            // Xpath
            XPath xPath = XPathFactory.newInstance().newXPath();
            String xPathExpression = "item-data/item-node[item-name='" + itemName + "'" +
                    " or class-name='" + className + "'" +
                    " or variable-name='" + variableName + "'" +
                    " or json-name='" + jsonName + "']";

            XPathEvaluationResult<?> nodeList = xPath.evaluateExpression(xPathExpression, document);
            XPathNodes xPathNodes = (XPathNodes) nodeList.value();
            return xPathNodes.size() > 0;
        } catch (ParserConfigurationException | IOException | SAXException | XPathException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteItem() {
        // TODO: Add once option to delete is created
    }

    // XML Blocks

    public static void saveBlock(String blockName, String className, String variableName, String jsonName) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            // Build file
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(LoadedModData.modDataPath + "blockData.xml"));
            Element root = document.getDocumentElement();

            Element blockNode = document.createElement("block-node");

            Element blockNameNode = document.createElement("block-name");
            blockNameNode.setTextContent(blockName);

            Element blockClassName = document.createElement("class-name");
            blockClassName.setTextContent(className);

            Element blockVariableName = document.createElement("variable-name");
            blockVariableName.setTextContent(variableName);

            Element blockJSONName = document.createElement("json-name");
            blockJSONName.setTextContent(jsonName);

            blockNode.appendChild(blockNameNode);
            blockNode.appendChild(blockClassName);
            blockNode.appendChild(blockVariableName);
            blockNode.appendChild(blockJSONName);
            root.appendChild(blockNode);

            try (FileOutputStream outputStream = new FileOutputStream(LoadedModData.modDataPath + "blockData.xml")) {
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

    public static boolean containsBlock(String blockName, String className, String variableName, String jsonName) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            // Build file
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(LoadedModData.modDataPath + "blockData.xml"));
            // Xpath
            XPath xPath = XPathFactory.newInstance().newXPath();
            String xPathExpression = "block-data/block-node[block-name='" + blockName + "'" +
                    " or class-name='" + className + "'" +
                    " or variable-name='" + variableName + "'" +
                    " or json-name='" + jsonName + "']";

            XPathEvaluationResult<?> nodeList = xPath.evaluateExpression(xPathExpression, document);
            XPathNodes xPathNodes = (XPathNodes) nodeList.value();
            return xPathNodes.size() > 0;
        } catch (ParserConfigurationException | IOException | SAXException | XPathException e) {
            throw new RuntimeException(e);
        }
    }
}
