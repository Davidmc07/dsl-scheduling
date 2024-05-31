package org.uniovi.dsl.scheduling.rcp.decorators;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class ProjectLabelDecorator extends LabelProvider implements ILabelDecorator {

    @Override
    public Image decorateImage(Image image, Object element) {
        if (element instanceof IProject) {
            if (shouldUseCustomImage((IProject) element)) {
            	ClassLoader loader = ProjectLabelDecorator.class.getClassLoader();
            	return new Image(null, loader.getResourceAsStream("icons/prj_obj.png"));
            }
        }
        return null; // Return null to use the default image
    }

    private boolean shouldUseCustomImage(IProject project) {
        return true;
    }

	@Override
	public String decorateText(String text, Object element) {
		return text;
	}

}
