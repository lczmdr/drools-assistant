package org.drools.assistant.processor;

import java.util.List;

import org.drools.assistant.engine.DRLParserEngine;
import org.drools.assistant.info.RuleRefactorInfo;
import org.drools.assistant.option.AssistantOption;
import org.drools.assistant.refactor.DRLRuleRefactor;

public class DRLRefactorProcessor extends AbstractRuleRefactorProcessor {
	
	@Override
	public List<AssistantOption> getRuleAssistant(String text, Integer offset) {
		RuleRefactorInfo ruleRefactorInfo = generateRuleRefactorInfo(text);
		ruleRefactorEngine = new DRLRuleRefactor(ruleRefactorInfo);
		return ruleRefactorEngine.execute(offset);
	}
	
	@Override
	protected RuleRefactorInfo generateRuleRefactorInfo(String text) {
		ruleParserEngine = new DRLParserEngine(text);
		RuleRefactorInfo info = ruleParserEngine.parse();
		return info;
	}

}
