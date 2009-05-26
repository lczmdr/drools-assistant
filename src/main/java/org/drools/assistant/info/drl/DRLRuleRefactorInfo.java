package org.drools.assistant.info.drl;

import java.util.List;
import java.util.Map;

import org.drools.assistant.info.RuleRefactorInfo;

public class DRLRuleRefactorInfo implements RuleRefactorInfo {
	
	private String packageName;
	private Integer packageIndex;
	private Map<Integer, String> imports;
	private Map<Integer, String> globals;
	private Map<Integer, String> expanders;
	private Map<Integer, String> functions;
	private Map<Integer, String> functionsImports;
	private Map<Integer, String> queries;
	private List<DRLRuleRefactorContentInfo> rules; 
	
	public DRLRuleRefactorInfo() {	}
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName, Integer packageIndex) {
		this.packageName = packageName;
		this.packageIndex = packageIndex;
	}
	public Integer getPackageIndex() {
		return this.packageIndex;
	}
	public Map<Integer, String> getImports() {
		return imports;
	}
	public void setImports(Map<Integer, String> imports) {
		this.imports = imports;
	}
	public Map<Integer, String> getGlobals() {
		return globals;
	}
	public void setGlobals(Map<Integer, String> globals) {
		this.globals = globals;
	}
	public Map<Integer, String> getExpanders() {
		return expanders;
	}
	public void setExpanders(Map<Integer, String> expanders) {
		this.expanders = expanders;
	}
	public Map<Integer, String> getFunctions() {
		return functions;
	}
	public void setFunctions(Map<Integer, String> functions) {
		this.functions = functions;
	}
	public Map<Integer, String> getFunctionsImports() {
		return functionsImports;
	}
	public void setFunctionsImports(Map<Integer, String> functionsImports) {
		this.functionsImports = functionsImports;
	}
	public void setQueries(Map<Integer, String> queries) {
		this.queries = queries;
	}
	public Map<Integer, String> getQueries() {
		return queries;
	}
	public List<DRLRuleRefactorContentInfo> getRules() {
		return rules;
	}
	public void setRules(List<DRLRuleRefactorContentInfo> rules) {
		this.rules = rules;
	}
	
}
