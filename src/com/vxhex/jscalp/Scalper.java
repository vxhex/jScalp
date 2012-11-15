/**
 * This class contains most of the logic for auditing log files.
 */

package com.vxhex.jscalp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jregex.Matcher;
import jregex.Pattern;
import jregex.PatternSyntaxException;


public class Scalper {
	
	private Pattern regexIp = new Pattern("^(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})");
	private Pattern regexDate = new Pattern("\\[(\\d{2}.\\w{3}.\\d{4})\\:");
	private Pattern regexTime = new Pattern("\\:(\\d{2}\\:\\d{2}\\:\\d{2})\\s-");
	private Pattern regexMethod = new Pattern("\\] \"(\\w{3,5})");
	private Pattern regexUrl = new Pattern("\"\\w{3,5} (.*) HTTP.\\d.\\d");
	private Pattern regexAgent = new Pattern("\" \"(.*)\" \\d+");
	
	private List<Filter> filters = new ArrayList<Filter>();
	
	private long logsTotal;
	private long logsBad;
	
	/**
	 * Load XML filter file and compile rules into patterns.
	 * @param file
	 */
	public void loadFilters(String file) {
		FilterLoader loader = new FilterLoader(file);
		List<Filter> newFilters = loader.parseXML();
		
		for(Filter f : newFilters) {
			Pattern p = null;
			try { 
				p = new Pattern(f.getRule()); 
			} catch(PatternSyntaxException e) {
				System.out.println("Failed to compile pattern ID: " + f.getID());
			}
			if(p != null) {
				f.setPattern(p);
			}
		}
		filters.addAll(newFilters);
	}
	
	/**
	 * Sequentially reads lines from a log file, builds a log entry, and passes to analysis method
	 * @param file The log file to read
	 */
	public void scalpLog(String file) {
		logsTotal = 0;
		logsBad = 0;
		System.out.println("Log file: " + file);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) != null) {
				analyzeLog(parseLog(line));
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file);
		} catch (IOException e) {
			System.out.println("IOException on file: " + file);
		}
		
		System.out.println(logsBad + " / " + logsTotal + " matched from a total of " + filters.size() + " filters");
	}
	
	/**
	 * Builds a log entry object from a string
	 * @param input A log entry in Common Log Format
	 */
	private Log parseLog(String logEntry) {
		Log log = new Log();
		log.setIp(regexFirst(regexIp, logEntry));
		log.setDate(regexFirst(regexDate, logEntry));
		log.setTime(regexFirst(regexTime, logEntry));
		log.setMethod(regexFirst(regexMethod, logEntry));
		log.setUrl(regexFirst(regexUrl, logEntry));
		log.setAgent(regexFirst(regexAgent, logEntry));
		log.setRaw(logEntry);
		return log;
	}
	
	/**
	 * Analyze our logs for anything weird
	 * @param l Log file to analyze
	 */
	private void analyzeLog(Log l) {
		logsTotal++;
		
		for(Filter f : filters) {
			if(f.getPattern() != null) {
				Matcher m = f.getPattern().matcher(l.getUrl());
				if(m.find()) {
					System.out.println(f.getDescription());
					System.out.println(l);
					System.out.println("");
					logsBad++;
					break;
				}
			}
		}
	}
	
	/**
	 * Given a regex pattern and string, returns the first match in the string
	 * @param p Regular expression pattern
	 * @param in String to find matches in
	 * @return The first match
	 */
	private String regexFirst(Pattern p, String in) {
		Matcher m = p.matcher(in);
		String out = "";
		while (m.find()){
			out = m.group(1);
		}
		return out.trim();
	}
}
