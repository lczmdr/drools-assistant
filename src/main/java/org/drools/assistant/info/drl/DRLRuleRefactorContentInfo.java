package org.drools.assistant.info.drl;

public class DRLRuleRefactorContentInfo {

	private String name;
	private String LHS;
	private String RHS;
	private int offset;
	private boolean selected;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLHS() {
		return LHS;
	}
	public void setLHS(String lhs) {
		LHS = lhs;
	}
	public String getRHS() {
		return RHS;
	}
	public void setRHS(String rhs) {
		RHS = rhs;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
}
