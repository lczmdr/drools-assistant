package org.drools.assistant.refactor;

import java.util.ArrayList;
import java.util.List;

import org.drools.assistant.info.RuleRefactorInfo;
import org.drools.assistant.option.AssistantOption;

public class DRLRuleRefactor extends AbstractRuleRefactor {
	
	public DRLRuleRefactor(RuleRefactorInfo refactorInfo) {
		this.ruleRefactorInfo = refactorInfo;
		this.options = new ArrayList<AssistantOption>();
	}

	@Override
	public List<AssistantOption> execute(int offset) {
		if ((option = this.bindVariable())!=null)
			this.options.add(option);
		if ((option = this.fixImports())!=null)
			this.options.add(option);
		return this.options;
	}
	
	@Override
	public AssistantOption bindVariable() {
		return null;
	}

	@Override
	public AssistantOption fixImports() {
		return null;
	}

}
