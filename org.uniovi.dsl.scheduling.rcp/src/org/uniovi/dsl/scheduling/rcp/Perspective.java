package org.uniovi.dsl.scheduling.rcp;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

public class Perspective implements IPerspectiveFactory {

	@Override	
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);

		IFolderLayout leftFolder = layout.createFolder("leftFolder", IPageLayout.LEFT, 0.25f,
			editorArea);
		leftFolder.addView("dsl.navigator");

		IFolderLayout consoleFolder = layout.createFolder("consoleFolder", IPageLayout.BOTTOM,
			0.25f, "leftFolder");
		consoleFolder.addView(IConsoleConstants.ID_CONSOLE_VIEW);

		layout.addStandaloneView("dsl.problems", true, IPageLayout.RIGHT, 0.75f, editorArea);
	}
}
