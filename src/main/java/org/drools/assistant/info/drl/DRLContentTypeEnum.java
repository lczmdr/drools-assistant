package org.drools.assistant.info.drl;

public enum DRLContentTypeEnum {
	
	PACKAGE("Package"),
	IMPORT("Import"),
	GLOBAL("Global"),
	RULE("Rule"),
	RULE_LHS_LINE("Rule LHS Line"),
	RULE_RHS_LINE("Rule RHS Line");
	
	private String description;

	DRLContentTypeEnum(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}

}
