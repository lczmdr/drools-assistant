package org.drools.assistant.refactor;

import java.util.ArrayList;
import java.util.List;

import org.drools.assistant.info.RuleRefactorInfo;
import org.drools.assistant.info.drl.RuleBasicContentInfo;
import org.drools.assistant.option.AssistantOption;

public class DSLRuleRefactor extends AbstractRuleRefactor {
	
	private List<AssistantOption> options;
	
	public DSLRuleRefactor(RuleRefactorInfo ruleRefactorInfo) {
		this.ruleRefactorInfo = ruleRefactorInfo;
		this.options = new ArrayList<AssistantOption>();
	}
	
	@Override
	public List<AssistantOption> execute(int offset) {
		if ((option = this.bindVariable(null))!=null)
			this.options.add(option);
		if ((option = this.fixImports(null))!=null)
			this.options.add(option);
		return this.options;
	}

	@Override
	protected AssistantOption bindVariable(RuleBasicContentInfo contentInfo) {
		return null;
	}

	@Override
	protected AssistantOption fixImports(RuleBasicContentInfo contentInfo) {
		return null;
	}

	@Override
	protected AssistantOption renameVariable(RuleBasicContentInfo contentInfo) {
		return null;
	}

}
