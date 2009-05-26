package org.drools.assistant.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.drools.assistant.info.RuleRefactorInfo;
import org.drools.assistant.info.drl.DRLRuleRefactorInfo;

/**
 * A simple DRL parser implemented with regular expressions to get the offset of rule components
 * 
 * @author lucas
 *
 */
public class DRLParserEngine extends AbstractParserEngine {
	
	// Regulars expressions to match DRL Rule 
	private static final String PACKAGE_PATTERN = "package[\\s]+[a-z\\.]*;"; // OK
	private static final String IMPORT_PATTERN = "import[\\s]+[\\w\\.]*;"; // OK
	private static final String GLOBAL_PATTERN = "global[\\s]+[\\w\\.]*[\\s]+[\\w]*[\\s]+"; // OK
	private static final String EXPANDER_PATTERN = "expander[\\s]+[\\w\\D]+\\.dsl"; // OK
	private static final String FUNCTION_PATTERN = "function[\\s]+[a-zA-Z]+[\\s]+[\\w\\d]*\\([\\w\\d\\s\\,]*\\)[\\s]*\\{[\\w\\s\\d\\\"\\+\\-\\,\\;\\!\\n\\*\\.\\(\\)]*\\}"; // Works, but mus be changed
	private static final String FUNCTION_IMPORT_PATTERN = "import[\\s]+function[\\s]+[\\w\\.]*"; // OK
	private static final String QUERY_PATTERN = "query[\\s]+[(^end)\\w\\\"\\s\\:\\(\\)]*"; // don't works
	private static final String RULE_PATTERN = "rule[\\w\\s\\D]*end"; // don't works with more that one rule
	private static final String RULE_LHS_PATTERN = "when[\\s\\w\\D]*then"; // OK
	private static final String RULE_RHS_PATTERN = "then[\\s\\w\\D]*end"; // OK
	private static final String RULE_LINES = "[\\w\\s]*\\n"; // don't works
	
	private Map<Integer, String> content;
	
	public DRLParserEngine(String rule) {
		this.ruleRefactorInfo = new DRLRuleRefactorInfo();
		this.rule = rule;
	}
	
	public RuleRefactorInfo parse() {
		detectPackage(rule);
		detectGlobals(rule);
		detectExpanders(rule);
		detectImports(rule);
		detectFunctions(rule);
		detectFunctionsImports(rule);
		detectQueries(rule);
		detectRules(rule);
		return ruleRefactorInfo;
	}
	
	private void detectPackage(CharSequence rule) {
		pattern = Pattern.compile(PACKAGE_PATTERN);
		matcher = pattern.matcher(rule);
		content = new HashMap<Integer, String>();
		if (matcher.find())
			((DRLRuleRefactorInfo) ruleRefactorInfo).setPackageName(matcher.group(), matcher.start());
	}

	private void detectImports(CharSequence rule) {
		pattern = Pattern.compile(IMPORT_PATTERN);
		matcher = pattern.matcher(rule);
		content = new HashMap<Integer, String>();
		while (matcher.find())
			content.put(matcher.start(), matcher.group());
		((DRLRuleRefactorInfo) ruleRefactorInfo).setImports(content);
	}
	
	private void detectGlobals(CharSequence rule) {
		pattern = Pattern.compile(GLOBAL_PATTERN);
		matcher = pattern.matcher(rule);
		content = new HashMap<Integer, String>();
		while (matcher.find())
			content.put(matcher.start(), matcher.group());
		((DRLRuleRefactorInfo) ruleRefactorInfo).setGlobals(content);
	}
	
	private void detectExpanders(CharSequence rule) {
		pattern = Pattern.compile(EXPANDER_PATTERN);
		matcher = pattern.matcher(rule);
		content = new HashMap<Integer, String>();
		while (matcher.find())
			content.put(matcher.start(), matcher.group());
		((DRLRuleRefactorInfo) ruleRefactorInfo).setExpanders(content);
	}
	 
	private void detectFunctions(CharSequence rule) {
		pattern = Pattern.compile(FUNCTION_PATTERN);
		matcher = pattern.matcher(rule);
		content = new HashMap<Integer, String>();
		while (matcher.find())
			content.put(matcher.start(), matcher.group());
		((DRLRuleRefactorInfo) ruleRefactorInfo).setFunctions(content);
	}
	
	private void detectFunctionsImports(CharSequence rule) {
		pattern = Pattern.compile(FUNCTION_IMPORT_PATTERN);
		matcher = pattern.matcher(rule);
		content = new HashMap<Integer, String>();
		while (matcher.find())
			content.put(matcher.start(), matcher.group());
		((DRLRuleRefactorInfo) ruleRefactorInfo).setFunctionsImports(content);
	}
	
	private void detectQueries(CharSequence rule) {
		pattern = Pattern.compile(QUERY_PATTERN);
		matcher = pattern.matcher(rule);
		content = new HashMap<Integer, String>();
		while (matcher.find())
			content.put(matcher.start(), matcher.group());
		((DRLRuleRefactorInfo) ruleRefactorInfo).setQueries(content);
	}
	
	private void detectRules(CharSequence rule) {
		pattern = Pattern.compile(RULE_PATTERN);
		matcher = pattern.matcher(rule);
		content = new HashMap<Integer, String>();
		while (matcher.find()) {
			System.out.println(matcher.group() + "\n\t starting at index " + matcher.start() + " and ending at index " + matcher.end());
			detectLHS(matcher.group());
//			detectRHS(matcher.group());
		}
	}
	
	private void detectLHS(CharSequence rule) {
		System.out.println("-----------LHS------------");
		pattern = Pattern.compile(RULE_LHS_PATTERN);
		matcher = pattern.matcher(rule);
		while (matcher.find()) {
			System.out.println(matcher.group() + "\n\t starting at index " + matcher.start() + " and ending at index " + matcher.end());
			detectLines(matcher.group());
		}
	}
	
	private void detectRHS(CharSequence rule) {
		System.out.println("-----------RHS------------");
		pattern = Pattern.compile(RULE_RHS_PATTERN);
		matcher = pattern.matcher(rule);
		while (matcher.find()) {
			System.out.println(matcher.group() + "\n\t starting at index " + matcher.start() + " and ending at index " + matcher.end());
			detectLines(matcher.group());
		}
	}
	
	private void detectLines(CharSequence rule) {
		System.out.println("-----------Lines------------");
		pattern = Pattern.compile(RULE_LINES);
		matcher = pattern.matcher(rule);
		while (matcher.find())
			System.out.println(matcher.group() + "\n\t starting at index " + matcher.start() + " and ending at index " + matcher.end());
	}

}
