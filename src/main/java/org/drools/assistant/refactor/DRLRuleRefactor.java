package org.drools.assistant.refactor;

import java.util.ArrayList;
import java.util.List;

import org.drools.assistant.info.RuleRefactorInfo;
import org.drools.assistant.info.drl.DRLContentTypeEnum;
import org.drools.assistant.info.drl.DRLRuleRefactorInfo;
import org.drools.assistant.info.drl.RuleBasicContentInfo;
import org.drools.assistant.option.AssistantOption;
import org.drools.assistant.refactor.drl.FixImport;
import org.drools.assistant.refactor.drl.VariableBinding;

public class DRLRuleRefactor extends AbstractRuleRefactor {
	
	public DRLRuleRefactor(RuleRefactorInfo refactorInfo) {
		this.ruleRefactorInfo = refactorInfo;
		this.options = new ArrayList<AssistantOption>();
	}

	@Override
	public List<AssistantOption> execute(int offset) {
		this.offset = offset;
		RuleBasicContentInfo contentInfo = ((DRLRuleRefactorInfo)ruleRefactorInfo).getContentAt(offset);
		if (contentInfo==null)
			return this.options;
		if ((option = this.bindVariable(contentInfo))!=null)
			this.options.add(option);
		if ((option = this.fixImports(contentInfo))!=null)
			this.options.add(option);
		if ((option = this.renameVariable(contentInfo))!=null)
			this.options.add(option);
		return this.options;
	}
	
	@Override
	protected AssistantOption bindVariable(RuleBasicContentInfo contentInfo) {
		if (contentInfo==null) return null;
		if (!contentInfo.getType().equals(DRLContentTypeEnum.RULE_LHS_LINE))
			return null;
		String response = VariableBinding.execute(contentInfo, offset-contentInfo.getOffset());
		if (response.equals(contentInfo.getContent()))
			return null;
		return new AssistantOption("assign to variable", response, contentInfo.getOffset(), contentInfo.getContentLength());
	}

	@Override
	protected AssistantOption fixImports(RuleBasicContentInfo contentInfo) {
		if (contentInfo==null) return null;
		if (!contentInfo.getType().equals(DRLContentTypeEnum.RULE_LHS_LINE) &&
				!contentInfo.getType().equals(DRLContentTypeEnum.RULE_RHS_LINE))
			return null;
		List<RuleBasicContentInfo> imports = ((DRLRuleRefactorInfo)ruleRefactorInfo).getImports();
		FixImport.execute(contentInfo, imports);
		return null;
	}

	@Override
	protected AssistantOption renameVariable(RuleBasicContentInfo contentInfo) {
		return null;
	}

}
