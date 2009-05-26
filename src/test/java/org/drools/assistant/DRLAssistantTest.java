package org.drools.assistant;

import java.util.List;

import junit.framework.TestCase;

import org.drools.assistant.option.AssistantOption;
import org.drools.assistant.processor.AbstractRuleAssistantProcessor;
import org.drools.assistant.processor.DRLRefactorProcessor;

public class DRLAssistantTest extends TestCase {
	
	private AbstractRuleAssistantProcessor ruleAssistant;
	
	@Override
	protected void setUp() throws Exception {
		ruleAssistant = new DRLRefactorProcessor();
		super.setUp();
	}

	public void testCreation() throws Exception {
		String rule = "package cl.ifitec.ificep.rules; \n" +
		"import cl.ifitec.ificep.core.model.ClosePrice; \n" +
		"     import cl.ifitec.ificep.core.model.ClosePriceResults; \n" + 
		"global cl.ifitec.ificep.core.model.ClosePriceResult results \n" +
		"global cl.ifitec.ificep.core.model.ClosePrice current \n" +
		"global cl.ifitec.ificep.core.model.ClosePrice previous \n" +
		"rule \"MA20\" \n" +
		"when \n" +
		"	$ma20 : Double() from accumulate( ClosePrice( $value : open ) over window:time( 20ms ),	average( $value ) ) \n" +
		"then \n" +
		"	results.setMa20( $ma20 ); \n" +
		"end \n";
		
		List<AssistantOption> options = ruleAssistant.getRuleAssistant(rule, 10);
		assertEquals(options.size(), 0);
		
	}
	
}
