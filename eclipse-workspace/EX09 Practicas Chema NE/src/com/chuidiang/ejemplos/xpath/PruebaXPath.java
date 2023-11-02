package com.chuidiang.ejemplos.xpath;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class PruebaXPath {
	public static void main(String[] args) throws Exception {
		// La expresion xpath de busqueda
		String xPathExpression = "//satelite[@nombre!='Luna']";
		String xPathExpression2 = "//espacio//galaxia[@nombre]";
		// Carga del documento xml
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document documento = builder.parse(new File("prueba.xml"));
// Preparación de xpath
		XPath xpath = XPathFactory.newInstance().newXPath();
// Consultas
		NodeList nodos = (NodeList) xpath.evaluate(xPathExpression, documento, XPathConstants.NODESET);
		NodeList nodos2 = (NodeList) xpath.evaluate(xPathExpression2, documento, XPathConstants.NODESET);
		for (int i = 0; i < nodos.getLength(); i++) {
			System.out.println(nodos.item(i).getNodeName() + " : " + nodos.item(i).getAttributes().getNamedItem("nombre"));
			System.out.println(nodos2.item(i).getNodeName() + " : " + nodos2.item(i).getAttributes().getNamedItem("nombre"));
		}
		
	}
}