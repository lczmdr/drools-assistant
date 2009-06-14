package org.drools.assistant.refactor.drl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.drools.assistant.info.drl.RuleBasicContentInfo;
import org.drools.assistant.info.drl.RuleDRLContentInfo;
import org.drools.assistant.info.drl.RuleLineContentInfo;

public class FixImport {
	
	private static final String CLASS_PATTERN = "[\\s\\t:,]+[a-zA-Z]*\\(";
	
	private static final String[] KEYWORDS = {"new", "update", "insert"};
	
	private static final Pattern pattern = Pattern.compile(CLASS_PATTERN);
	private static Matcher matcher;
	
	private static List<String> classes = new ArrayList<String>();
	
	// detect all the Class Name and compare with the current imports
	// how detect the Classes loaded into the ClassLoader?
	public static void execute(RuleBasicContentInfo contentInfo, List<RuleBasicContentInfo> imports) {
		RuleDRLContentInfo ruleInfo = ((RuleLineContentInfo)contentInfo).getRule();
		String rule = "";
		for (RuleLineContentInfo ruleLineContentInfo : ruleInfo.getLHSRuleLines())
			rule = rule.concat(ruleLineContentInfo.getContent() + "\n");
		for (RuleLineContentInfo ruleLineContentInfo : ruleInfo.getRHSRuleLines())
			rule = rule.concat(ruleLineContentInfo.getContent() + "\n");
		matcher = pattern.matcher(rule);
		while (matcher.find()) {
			String className = matcher.group().replaceAll(":", "").replaceAll("\\(", "").replaceAll("\\t", "").replaceAll("\\n", "").trim();
			addClassName(className);
		}
		for (RuleBasicContentInfo importLine : imports) {
			System.out.println(importLine.getContent());
		}
	}
	
	private static void addClassName(String className) {
		for (int i = 0; i < KEYWORDS.length; i++)
			if (KEYWORDS[i].equals(className))
				return;
		if (!classes.contains(className)) {
			classes.add(className);
			System.out.println("added " + className);
		}
	}
	
//	public static void main(String[] args) {
//		Class<?> clase = null;
//		Field fields[];
//		
//		try {
//			clase = Class.forName("sun.misc.Launcher$AppClassLoader");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		fields = clase.getFields();
//		System.out.println("lenght " + fields.length);
//		for (int i = 0; i < fields.length; i++) {
//			System.out.println(fields[i].getName());
//		}
//		
//		Method[] methods = clase.getMethods();
//		for (Method method : methods) {
//			System.out.println(method.getName());
//			Class<?>[] parameters = method.getParameterTypes();
//			for (int i = 0; i < parameters.length; i++) {
//				System.out.println("\t" + parameters[i].getSimpleName() + " -> " + parameters[i].getCanonicalName());
//			}
//			Class<?> returnType = method.getReturnType();
//			System.out.println("\treturn: " + returnType.getCanonicalName());
//		}
//		
//		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
//		
//		URL result[];
//		try {
//			result = (URL[]) clase.getMethod("getURLs", null).invoke(systemClassLoader, null);
//			for (int i = 0; i < result.length; i++) {
//				System.out.println(result[i]);
//				Object content = result[i].getContent();
//				if (content instanceof PlainTextInputStream) {
//					PlainTextInputStream is = (PlainTextInputStream)result[i].getContent();
//					System.out.println(is);
//				}
//				System.out.println(content);
//			}
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}
	
}
