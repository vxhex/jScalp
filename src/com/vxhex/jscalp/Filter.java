/**
 * Storage object for a filter.
 */

package com.vxhex.jscalp;

import jregex.Pattern;

public class Filter {
	
	private String ID = "";
	private String rule = "";
	private String description = "";
	private String tags = "";
	private String impact = "";
	private Pattern pattern = null;
	
	public void setID(String iD) {
		this.ID = iD;
	}
	public String getID() {
		return ID;
	}
	
	public void setRule(String rule) {
		this.rule = rule;
	}
	public String getRule() {
		return rule;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getTags() {
		return tags;
	}
	
	public void setImpact(String impact) {
		this.impact = impact;
	}
	public String getImpact() {
		return impact;
	}
	
	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
	public Pattern getPattern() {
		return pattern;
	}
	
}
