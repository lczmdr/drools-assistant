package org.drools.assistant.option;

public class AssistantOption {
	
	private String display;
	private String content;
	private Integer length;
	private Integer offset;
	
	public AssistantOption(String display, String content, Integer offset, int length) {
		this.display = display;
		this.content = content;
		this.offset = offset;
		this.length = length;
	}
	
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getLength() {
		return length;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
}
