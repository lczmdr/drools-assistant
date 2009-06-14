package org.drools.assistant;

import java.util.List;

import junit.framework.TestCase;

import org.drools.assistant.option.AssistantOption;
import org.drools.assistant.processor.AbstractRuleAssistantProcessor;
import org.drools.assistant.processor.DRLRefactorProcessor;

public class DRLAssistantTest extends TestCase {
	
	private AbstractRuleAssistantProcessor ruleAssistant;
	private String rule;
	
	@Override
	protected void setUp() throws Exception {
		ruleAssistant = new DRLRefactorProcessor();
		rule = 	"package org.drools.assistant.test;\n\n" +
		"import org.drools.assistant.test.model.Company;\n" +
		"IMPORT org.drools.assistant.test.model.Employee;\n\n" +
		"import function org.drools.assistant.model.Class1.anotherFunction \n" +
		"import		function org.drools.assistant.model.Class1.mathFunction \n" +
		"global     org.drools.assistant.test.model.Class2    results \n"+
		"GLOBAL org.drools.assistant.test.model.Class3 current\n"+ 
		"expander help-expander.dsl\n" +
		"query \"all clients\"\n" +
		"	result : Clients()\n" +
		"end\n" +
		"query \"new query\"\n" +
		"	objects : Clients()\n" +
		"end\n" +
		"function String hello(String name) {\n"+
		"    return \"Hello \"+name+\"!\";\n"+
		"}\n" +
		"function String helloWithAge(String name, Integer age) {\n"+
		"    return \"Hello2 \"+name+\"! \" + age;\n"+
		"}\n" +
		"rule   \"My Test Rule\"\n" +
		"when\n"+ 
		"	$employee : Employee($company : company, $company1 : oldcompany, $age : age > 80, salary > 400)\n" +
		"	$result : Company(company==$company, retireAge <= $age)\n" + 
		"then\n"+ 
		"	System.out.println(\"can retire\")\n" +
		"end\n";
	}

	public void testAssignSalaryFieldToVariable() throws Exception {
		List<AssistantOption> options = ruleAssistant.getRuleAssistant(rule, 820);
		assertEquals(options.size(), 1);
		System.out.println(options.get(0).getContent());
	}
	
	public void testDontAssignFieldInsideRHS() throws Exception {
		List<AssistantOption> options = ruleAssistant.getRuleAssistant(rule, 840);
		assertEquals(options.size(), 0);
	}
	
}
