package org.uniovi.dsl.scheduling.ui.syntaxhighlighting;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.ide.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.ide.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.PolymorphicDispatcher;

@SuppressWarnings("unused") // Called from the dispatcher
public class SchedulingHighlightingCalculator implements ISemanticHighlightingCalculator {
	
	
	
	private PolymorphicDispatcher<String> dispatcher = 
			PolymorphicDispatcher.createForSingleTarget("highlight", this);
	
	private IHighlightedPositionAcceptor acceptor;

	@Override
	public void provideHighlightingFor(XtextResource resource, IHighlightedPositionAcceptor acceptor,
			CancelIndicator cancelIndicator) {
		
		if (resource == null) return;
		
		this.acceptor = acceptor;
	    Iterator<EObject> iter = EcoreUtil.getAllContents(resource, true);
	    while (iter.hasNext()) {
	        dispatcher.invoke(iter.next());
	    }
	}
	
	// DEFAULT
	
	private void highlight(EObject obj) {
		// Do nothing
	}
	
	// SECTION HEADERS
	/*
	private void highlight(OptionsHeader obj) {
		addHighlighting(obj, SchedulingHighlightingConfiguration.SECTION_ID);
	}
	private void highlight(MaintenancesHeader obj) {
		addHighlighting(obj, SchedulingHighlightingConfiguration.SECTION_ID);
	}
	private void highlight(InstallationsHeader obj) {
		addHighlighting(obj, SchedulingHighlightingConfiguration.SECTION_ID);
	}
	*/
	// OBJECTS
	
	// ...
	
	private void addHighlighting(EObject obj, String id) {
		ICompositeNode node = NodeModelUtils.findActualNodeFor(obj);
		acceptor.addPosition(node.getOffset(), node.getLength(), id);
	}

}
