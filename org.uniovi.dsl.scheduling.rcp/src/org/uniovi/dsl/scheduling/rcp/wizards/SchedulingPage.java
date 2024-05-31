package org.uniovi.dsl.scheduling.rcp.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

public class SchedulingPage extends WizardNewFileCreationPage {

    public SchedulingPage(IStructuredSelection selection) {
		super("DataVisualizationFileWizardPage", selection);
		setTitle("Create a new Maintenance Scheduling File");
		setDescription(
			"Create a new maintenance scheduling file in the selected location.");
		setFileExtension("sched");
    }

    @Override
    protected boolean validatePage() {	
		if (super.validatePage()) {
		    String fileName = getFileName();
		    if (!fileName.endsWith(".sched")) {
		    	setFileName(fileName + ".sched");
		    }
		    return true;
		}
		return false;
    }
}
