package org.uniovi.dsl.scheduling.ui;

import java.io.File;

import javax.inject.Inject;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;
import org.eclipse.xtext.generator.GeneratorContext;
import org.eclipse.xtext.generator.IGenerator2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.eclipse.xtext.util.IResourceScopeCache;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.uniovi.dsl.scheduling.generator.CodeExecutionManager;
import org.uniovi.dsl.scheduling.ui.theme.ThemeUtils;

import com.google.inject.Provider;

public class GenerationHandler extends AbstractHandler implements IHandler {

    @Inject
    private IGenerator2 generator;

    private IGeneratorContext ctx = new GeneratorContext();

    @Inject
    private Provider<EclipseResourceFileSystemAccess2> fileAccessProvider;

    @Inject
    IResourceDescriptions resourceDescriptions;

    @Inject
    IResourceSetProvider resourceSetProvider;
    
    @Inject
    private IResourceScopeCache cache;
    

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
		String mode = "";
	
		try {
		    mode = event.getCommand().getName();
		} catch (NotDefinedException e1) {
		    e1.printStackTrace();
		}
	
		// Get file
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow window = wb.getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		IEditorPart editor = page.getActiveEditor();
	
		if (editor == null)
		    return null;
	
		IEditorInput input = editor.getEditorInput();
		IPath path = ((FileEditorInput) input).getPath();
	
		String name = path.toString();
		File myfile = new File(name);
	
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IPath location = Path.fromOSString(myfile.getAbsolutePath());
		IFile file = workspace.getRoot().getFileForLocation(location);
	
		// Create src-gen
		IProject project = file.getProject();
		IFolder srcGenFolder = project.getFolder("src-gen");
		/*
		 * if (!srcGenFolder.exists()) { try { srcGenFolder.create(true, true, new
		 * NullProgressMonitor()); } catch (CoreException e) { return null; } }
		 */
	
		// Get fsa
		final EclipseResourceFileSystemAccess2 fsa = fileAccessProvider.get();
		fsa.setProject(project);
		fsa.setOutputPath(srcGenFolder.getName().toString());
		fsa.setMonitor(new NullProgressMonitor());
	
		// Create resource
		URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
		ResourceSet rs = resourceSetProvider.get(project);
		
		Resource r = rs.getResource(uri, true);
		if (r instanceof XtextResource) {
			IResourceValidator validator = ((XtextResource) r)
					.getResourceServiceProvider()
					.getResourceValidator();
			
			if (!validator.validate(r, CheckMode.ALL, null).isEmpty()) {
				return null;
			}
		}
		SimpleAnyType wrapper = XMLTypeFactory.eINSTANCE.createSimpleAnyType();
		wrapper.setInstanceType(EcorePackage.eINSTANCE.getEString());
		wrapper.setValue(mode);
		r.getContents().add(wrapper);
	
		String workspacePath = workspace.getRoot().getLocation().toFile().getAbsolutePath();
		CodeExecutionManager.setProjectPath(workspacePath + project.getFullPath().toString());
		ResourcesPlugin.getWorkspace();
		
		cache.get("IS_DARK_THEME", r, () -> ThemeUtils.isDarkThemeEnabled());
		
		generator.doGenerate(r, fsa, ctx);
	
		return null;
    }

    @Override
    public boolean isEnabled() {
    	return true;
    }

}
