package org.drools.assistant;

import java.util.Map;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.drools.assistant.engine.DRLParserEngine;
import org.drools.assistant.info.drl.DRLRuleRefactorInfo;

public class DRLParserEngineTest extends TestCase {

	private String rule;
	private DRLParserEngine engine;
	private DRLRuleRefactorInfo info;

	@Override
	protected void setUp() throws Exception {
		rule = 	"package cl.ifitec.ificep;\n" +
		"import    carcass;\n import dethtel;" +
		"importante de declarar para que no parsee;" +
		"import cl.ifitec.ificep.core.model.ClosePrice;" +
		"import     function               my.package.Foo.hello " +
		"import function just.another.function " +
		"global     cl.ifitec.ificep.core.model.ClosePriceResult    results "+
		"global cl.ifitec.ificep.core.model.ClosePrice current\n"+ 
		"global cl.ifitec.ificep.core.model.ClosePrice previous " +
		"expander mi-expander.dsl" +
		"query \"all clients\"" +
		"	result : Clients() " +
		"end " +
		"function String hello(String name) {\n"+
		"    return \"Hello \"+name+\"!\";\n"+
		"}\n" +
		"function String hello2(String name, Integer age) {\n"+
		"    return \"Hello2 \"+name+\"! \" + age;\n"+
		"}\n" +
		"rule   \"MA20\"\n" +
		"when\n"+ 
		"	$ma20 : Double() from accumulate( ClosePrice( $value : open ) over window:time( 20ms ),	average( $value ) )\n" +
		"	$results : Results()\n" + 
		"then\n"+ 
		"resultssetMat(20)\n" +
		"retract\n"+ 
		"end\n";
		
		 engine = new DRLParserEngine(rule);
		 
	}
	
	public void testExecuteEngine() {
		info = (DRLRuleRefactorInfo) engine.parse();

		Assert.assertEquals(true, info!=null);

		String packageName = info.getPackageName();
		if (packageName!=null) 
			System.out.println(packageName);
		Map<Integer, String> imports = info.getImports();
		for (String s : imports.values())
			System.out.println(s);
	}
	
}
