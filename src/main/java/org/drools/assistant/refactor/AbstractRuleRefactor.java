package org.drools.assistant.refactor;

import java.util.List;

import org.drools.assistant.info.RuleRefactorInfo;
import org.drools.assistant.option.AssistantOption;

public abstract class AbstractRuleRefactor {

	protected RuleRefactorInfo ruleRefactorInfo;
	protected List<AssistantOption> options;
	protected AssistantOption option;
	
	public abstract List<AssistantOption> execute(int offset);
	
	protected abstract AssistantOption bindVariable();
	
	protected abstract AssistantOption fixImports();
	
}
