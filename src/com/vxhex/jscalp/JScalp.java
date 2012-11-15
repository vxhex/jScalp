/**
 * jScalp Server Log Auditor
 * @author Andy G (vxhex)
 * @version 0.1
 * Reads Apache Combined Log Format access logs and looks for PHPIDS-formatted filter matches.
 * 
 * TODO multiple input filters / multiple log files
 * TODO filter by date range, ip range
 * TODO exhaustive matching or break on first filter match
 * TODO tor / proxy filter
 * TODO flood detect with hashmap collision of ip+agent / minute
 * TODO specify output method (xml, html, text)
 * TODO flag ip addresses on hit
 * TODO support other log formats
 */

package com.vxhex.jscalp;

public class JScalp {
	
	/**
	 * Entry method for executable jar
	 * @param args Array of command line arguments
	 */
	public static void main(String[] args) {
		
		if(args.length != 2) {
			System.out.println("Usage: java -jar jscalp.jar [FILTER_FILE] [LOG_FILE]");
			return;
		}
		
		Scalper jscalp = new Scalper();
		//jscalp.setOptions();
		jscalp.loadFilters(args[0]);
		jscalp.scalpLog(args[1]);
	}
	
}
