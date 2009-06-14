package org.drools.assistant;

import junit.framework.TestCase;

import org.drools.assistant.refactor.drl.VariableRename;

public class VariableRenameTest extends TestCase {

	private String line;
	
	@Override
	protected void setUp() throws Exception {
		line = "\tEmployee($company : company, $age : age > 80, salary > 400, $companyPostalCode : companyPostalCode = 144)\n" +
		"\t$result : Company(name == $company, associatedcompany = 22, retireAge <= $age)\n";
		super.setUp();
	}
	
	public void testSimpleRename() {
		String result = VariableRename.execute(line, 13, "$newCompany");
		System.out.println(result);
	}

}
