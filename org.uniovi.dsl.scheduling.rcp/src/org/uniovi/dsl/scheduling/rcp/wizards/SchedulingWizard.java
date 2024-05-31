package org.uniovi.dsl.scheduling.rcp.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

public class SchedulingWizard extends BasicNewResourceWizard {

    private SchedulingPage page;

    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle("New Maintenance Scheduling File");
    }

    @Override
    public void addPages() {
		page = new SchedulingPage(getSelection());
		addPage(page);
    }

    @Override
    public boolean performFinish() {
		IFile file = page.createNewFile();
		if (file == null) {
		    return false;
		}
		
		setXtextNature(file.getProject());
		
		selectAndReveal(file);
		IWorkbenchWindow dw = getWorkbench().getActiveWorkbenchWindow();
		try {
		    if (dw != null) {
				IWorkbenchPage page = dw.getActivePage();
				if (page != null) {
				    IDE.openEditor(page, file, true);
				}
		    }
		} catch (PartInitException e) {
		    // Ignore
		}
		return true;
    }
    
    private void setXtextNature(IProject project) {
    	try {
			IProjectDescription description;
			description = project.getDescription();
			
			String[] natures = description.getNatureIds();
			String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 0, natures.length);
			newNatures[natures.length] = "org.eclipse.xtext.ui.shared.xtextNature";

			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IStatus status = workspace.validateNatureSet(newNatures);

			if (status.getCode() == IStatus.OK) {
				description.setNatureIds(newNatures);
				project.setDescription(description, null);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
    }
}