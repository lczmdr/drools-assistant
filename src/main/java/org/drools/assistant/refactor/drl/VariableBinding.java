package org.drools.assistant.refactor.drl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.drools.assistant.info.drl.RuleBasicContentInfo;
import org.drools.assistant.info.drl.RuleLineContentInfo;


public class VariableBinding {
	
	private static final String VARIABLE_PATTERN = "[\\$\\d\\w]*[\\s]*:";
	private static final Pattern pattern = Pattern.compile(VARIABLE_PATTERN);
	private static Matcher matcher;
	
	private static final String DEFAULT_VARIABLE_NAME = "$default";
	private static List<String> variables = new ArrayList<String>();
	
	public static String execute(RuleBasicContentInfo contentInfo, int offset) {
		detectCurrentVariables(contentInfo);
		return execute(contentInfo.getContent(), offset);
	}

	private static void detectCurrentVariables(RuleBasicContentInfo contentInfo) {
		variables.clear();
		String lhs = "";
		List<RuleLineContentInfo> ruleLines = ((RuleLineContentInfo)contentInfo).getRule().getLHSRuleLines();
		for (RuleLineContentInfo ruleLineContentInfo : ruleLines)
			lhs = lhs.concat(ruleLineContentInfo.getContent());
		matcher = pattern.matcher(lhs);
		String varname;
		while (matcher.find()) {
			varname = matcher.group().replace(":", "").trim();
			addVariableName(varname);
		}
	}

	public static String execute(String line, int offset) {
		if (offset > line.length())
			return line;
		int position;
		for (position = offset-1; position >= 0; position--) {
			if (line.charAt(position)==' ' || line.charAt(position)=='(' || line.charAt(position)=='\t' || line.charAt(position)==',') {
				String left = line.substring(0, position+1);
				String right = line.substring(position+1, line.length());
				if (!VariableBinding.isVariableOrParameter(right, left) && !VariableBinding.hasVariableAssigned(left))
					return left + getVariableName(right) + right; 
				return line;
			}
		}
		// search to the right the first : before ( and ,
		for (position = 0; position < line.length(); position++) {
			if (line.charAt(position)==':')
				return line;
			if (line.charAt(position)=='(' || line.charAt(position)==',')
				return getVariableName(line) + line;
		}
		return line;
	}
	
	// detect if already has an assigned variable or is the right side of the comparator parameter
	private static boolean isVariableOrParameter(String right, String left) {
		int position;
		for (position = 0; position <= right.length()-1; position++) {
			if (right.charAt(position)==',' || right.charAt(position)=='(')
				break;
			if (right.charAt(position)==':')
				return true;
		}
		for (position = left.length()-1; position >=0; position--) {
			if (left.charAt(position)=='>' || left.charAt(position)=='<' || left.charAt(position)=='=')
				return true;
			if (left.charAt(position)==',')
				return false;
		}
		return false;
	}
	
	private static String getVariableName(String right) {
		for (int position = 0; position < right.length(); position++) {
			if (right.charAt(position)=='(' || right.charAt(position)==')' || right.charAt(position)==',' ||
				right.charAt(position)=='<' || right.charAt(position)=='>' || right.charAt(position)=='=') {
				String varname = "$" + right.substring(0, position).toLowerCase().trim();
				return generateVariableName(varname);
			}
		}
		return DEFAULT_VARIABLE_NAME;
	}
	
	private static boolean hasVariableAssigned(String line) {
		for (int position = line.length()-1; position >=0; position--) {
			if (line.charAt(position)==':')
				return true;
			if (line.charAt(position)==',' || line.charAt(position)=='(')
				return false;
		}
		return false;
	}
	
	private static boolean existsVariableWithSameName(String varname) {
		return variables.contains(varname);
	}
	
	private static void addVariableName(String variableName) {
		if (!variables.contains(variableName))
			variables.add(variableName);
	}
	
	private static String generateVariableName(String varname) {
		if (!existsVariableWithSameName(varname))
			return varname + " : ";
		// generate a pseudo-random variable name
		for (int count=1; count <= 100; count++) {
			if (!existsVariableWithSameName(varname+count))
				return varname+count + " : ";
		}
		return varname;
	}
	
}