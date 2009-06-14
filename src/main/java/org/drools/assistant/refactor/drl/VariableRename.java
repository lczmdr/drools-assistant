package org.drools.assistant.refactor.drl;

import org.drools.assistant.info.drl.RuleBasicContentInfo;

public class VariableRename {

	public static void execute(RuleBasicContentInfo contentInfo, int offset) {
		
		// if is a variable -> get all lhs rule lines, search & replace!
		// TODO: how is the user going to select the new name? Eclipse native way is imposible to implement here
		
	}
	
	private static String detectVariable(String right) {
		for (int position = 0; position < right.length(); position++) {
			if (right.charAt(position)==':') {
				String varname = right.substring(0, position).trim();
					return replace(varname);
			}
		}
		return null;
	}
	
	private static String replace(String varname) {
		for (int position = 0; position < varname.length(); position++)
			if (varname.charAt(position)=='$')
				return varname.substring(0, position) + "\\$" + varname.substring(position+1, varname.length());
		return varname;
	}
	
	private static String replace(String rule, String variableName, String newVariableName) {
		rule = rule.replaceAll(variableName+"\\b", newVariableName);
		return rule;
	}
	
	// TODO: just for testing, this must be implemented in execute method
	public static String execute(String line, int offset, String newVariableName) {
		String right = line.substring(10);
		String variableName = detectVariable(right);
		newVariableName = replace(newVariableName);
		System.out.println("variable name: " + variableName + " replace with " + newVariableName );
		return replace(line, variableName, newVariableName);
	}
	
}
