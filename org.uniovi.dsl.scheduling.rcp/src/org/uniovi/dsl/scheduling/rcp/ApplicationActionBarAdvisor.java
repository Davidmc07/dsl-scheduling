package org.uniovi.dsl.scheduling.rcp;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}
	
	@Override
	protected void makeActions(IWorkbenchWindow window) {
		super.makeActions(window);
	}
	
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		super.fillMenuBar(menuBar);
		menuBar.add(getFileMenu());
		menuBar.add(getEditMenu());
	}
	
	private MenuManager getFileMenu() {
		IWorkbenchWindow window = getActionBarConfigurer().getWindowConfigurer().getWindow();
		IWorkbenchAction newAction = ActionFactory.NEW.create(window);
		newAction.setText("New...");
		
		MenuManager fileMenu = new MenuManager("&File", "org.uniovi.dsl.scheduling.rcp.menus.file");
		fileMenu.add(newAction); 
		fileMenu.add(new Separator());
		fileMenu.add(ActionFactory.SAVE.create(window));
		fileMenu.add(ActionFactory.SAVE_AS.create(window));
		fileMenu.add(ActionFactory.SAVE_ALL.create(window));
		fileMenu.add(ActionFactory.REVERT.create(window));
		fileMenu.add(new Separator());
		fileMenu.add(ActionFactory.RENAME.create(window));
		fileMenu.add(ActionFactory.REFRESH.create(window));
		fileMenu.add(new Separator());
		fileMenu.add(ActionFactory.QUIT.create(window)); 
		
		return fileMenu;
	}
	
	private MenuManager getEditMenu() {
		IWorkbenchWindow window = getActionBarConfigurer().getWindowConfigurer().getWindow();
		
		MenuManager editMenu = new MenuManager("&Edit", "org.uniovi.dsl.scheduling.rcp.menus.edit");
		editMenu.add(ActionFactory.UNDO.create(window));
		editMenu.add(ActionFactory.REDO.create(window));
		editMenu.add(new Separator());
		editMenu.add(ActionFactory.CUT.create(window));
		editMenu.add(ActionFactory.COPY.create(window));
		editMenu.add(ActionFactory.PASTE.create(window));
		editMenu.add(new Separator());
		editMenu.add(ActionFactory.DELETE.create(window));
		editMenu.add(ActionFactory.SELECT_ALL.create(window));
		editMenu.add(new Separator());
		editMenu.add(ActionFactory.FIND.create(window));
		
		return editMenu;
	}
	
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		super.fillCoolBar(coolBar);
	}

}

