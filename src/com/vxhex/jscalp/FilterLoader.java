/**
 * Load a PHP-IDS formatted XML filter using SAX.
 */

package com.vxhex.jscalp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FilterLoader extends DefaultHandler {
	
	private String xmlDoc = "default_filter.xml";
	private Filter tmpFilter;	
	private String tmpValue;
	private List<Filter> filters = new ArrayList<Filter>();

	/**
	 * If a filter file other than the default is specified, use it.
	 * @param file Filter file to load
	 */
	public FilterLoader(String file) {
		if(!file.trim().equals("")) {
			xmlDoc = file;
		}
	}
	
	/**
	 * Open the filter file and begin parsing.
	 * @return List of filter objects built from our file
	 */
	public List<Filter> parseXML() {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try{
			SAXParser sp = spf.newSAXParser();
			sp.parse(xmlDoc, this);
		} catch(ParserConfigurationException e) {
			System.out.println("Exception thrown while creating SAX parser");
		} catch(IllegalArgumentException e) {
			System.out.println("Filter XML file is null");
		} catch(IOException e) {
			System.out.println("IOException opening filter XML file: " + xmlDoc);
		} catch(SAXException e) {
			System.out.println("SAXException parsing filter XML file: " + xmlDoc);
		}
		return filters;
	}
	
	/**
	 * This method is called when we hit an opening tag.
	 * We'll use it to begin construction on a new filter object.
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) 
		throws SAXException {
		if(qName.equalsIgnoreCase("filter")) {
			tmpFilter = new Filter();
		}
	}
	
	/**
	 * This method is called when we hit a closing tag.
	 * We'll use it to add filter attributes and add the filter to our list.
	 */
	public void endElement(String uri, String localName, String qName)
		throws SAXException {
		
		if(qName.equalsIgnoreCase("id")) {
			tmpFilter.setID(tmpValue);
		}
		
		if(qName.equalsIgnoreCase("rule")) {
			tmpFilter.setRule(tmpValue);
		}
		
		if(qName.equalsIgnoreCase("description")) {
			tmpFilter.setDescription(tmpValue);
		}
		
		if(qName.equalsIgnoreCase("tag")) {
			tmpFilter.setTags(tmpFilter.getTags() + tmpValue);
		}
		
		if(qName.equalsIgnoreCase("impact")) {
			tmpFilter.setImpact(tmpValue);
		}
		
		if(qName.equalsIgnoreCase("filter")) {
			filters.add(tmpFilter);
		}
			
	}
	
	/**
	 * This method is called when we hit a tag value.
	 * Store the value to be added as a filter attribute.
	 */
	public void characters(char[] ch, int start, int length) 
		throws SAXException {
		tmpValue = new String(ch,start,length);
	}
}
