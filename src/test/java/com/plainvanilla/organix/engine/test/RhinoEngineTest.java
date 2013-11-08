package com.plainvanilla.organix.engine.test;

import org.junit.Test;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.Scriptable;


public class RhinoEngineTest {

	@Test
	public void test() {
		
		Context cx = Context.enter();
        try {
            // Initialize the standard objects (Object, Function, etc.)
            // This must be done before scripts can be executed. Returns
            // a scope object that we use in later calls.
            Scriptable scope = new ImporterTopLevel(cx);
            

            // Collect the arguments into a single string.
            String s = "importPackage(Packages.com.plainvanilla.organix.engine); new java.util.Date(); var obj = new ObjectInstance()";
            
            
            // Now evaluate the string we've colected.
            Object result = cx.evaluateString(scope, s, "<cmd>", 1, null);

            // Convert the result to a string and print it.
            System.err.println(Context.toString(result));

        } finally {
            // Exit from the context.
            Context.exit();
        }
		
	}

}
