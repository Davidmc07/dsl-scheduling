package org.uniovi.dsl.scheduling.validation;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.FailedPredicateException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.SyntaxErrorMessage;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.antlr.SyntaxErrorMessageProvider;
import org.uniovi.dsl.scheduling.scheduling.Config;
import org.uniovi.dsl.scheduling.scheduling.InstallationDef;
import org.uniovi.dsl.scheduling.scheduling.MaintDef;
import org.uniovi.dsl.scheduling.scheduling.Program;

public class SchedulingSyntaxErrorMessageProvider extends SyntaxErrorMessageProvider {
	
	@Override
	public SyntaxErrorMessage getSyntaxErrorMessage(IParserErrorContext context) {
		EObject object = NodeModelUtils.findActualSemanticObjectFor(context.getCurrentNode());
		SyntaxErrorMessage message = super.getSyntaxErrorMessage(context);
		String messageText = null;
		
		//System.out.println(object);
		//System.out.println(context.getRecognitionException().getClass().getName());
		
		if (object == null) {
			messageText = "Options section can not be empty";
		}
		
		if (context.getRecognitionException() instanceof FailedPredicateException) {
			if (object instanceof MaintDef) {
				messageText = "Invalid maintenance definition";
			} else if (object instanceof InstallationDef) {
				messageText = "Invalid installation definition";
			} else if (object instanceof Config) {
				messageText = "An input file must be specified";
			}
		} 
		
		if (context.getCurrentContext() instanceof Program) {
			Program ctx = (Program) context.getCurrentContext();
			
			List<String> sections = new ArrayList<String>(3);
			if (ctx.getOptions() == null) {
				sections.add("Options");
			}
			if (ctx.getInstallations() == null) {
				sections.add("Installations");
			}
			if (ctx.getMaintenances() == null) {
				sections.add("Maintenances");
			}
			if (!sections.isEmpty()) {
				messageText = "Missing the following sections:\n\t-" 
						+ String.join("\n\t- ", sections);
			}
		}
		
		if (messageText == null) {
			return message;
		} else {
			return new SyntaxErrorMessage(
				messageText, message.getIssueCode(), message.getIssueData());
		}
	}

}
