package org.uniovi.dsl.scheduling.rcp.preferences;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.prefs.BackingStoreException;
import org.uniovi.dsl.scheduling.rcp.Application;

public class PreferenceInitializer {
	public void init() {
		IEclipsePreferences node;

		if (!preferencesSet()) {
			// Use tabs with rounded corners
		    node = getNode("org.eclipse.e4.ui.workbench.renderers.swt");
		    node.putBoolean("USE_ROUND_TABS", true);
		    flush(node);

		    // Use the default dark theme of eclipse
		    node = getNode("org.eclipse.e4.ui.css.swt.theme");
		    node.put("themeid", "org.eclipse.e4.ui.css.theme.e4_dark");
		    flush(node);
		    
		    node = getNode("org.eclipse.ui.editors");
		    node.putBoolean("lineNumberRuler", true);
		    flush(node);

		    node = getNode("org.eclipse.core.resources");
		    node.put("textfileencoding", "UTF-8");
		    flush(node);
		    
		    node = getNode("org.uniovi.dsl.scheduling.ui");
		    node.put("org.uniovi.dsl.scheduling.Scheduling.templates", getTemplates());
		    flush(node);
		    
		    node = getNode("org.uniovi.dsl.scheduling");
		    node.putBoolean("prefs_initialized", true);
		    flush(node);
		    
		}
	}

	private boolean preferencesSet() {
		return InstanceScope.INSTANCE.getNode("org.uniovi.dsl.scheduling")
			.getBoolean("prefs_initialized", false);
	}

	private void flush(IEclipsePreferences node) {
		try {
		    node.flush();
		} catch (BackingStoreException e) {
		    e.printStackTrace();
		}
	}

	private IEclipsePreferences getNode(String id) {
		return InstanceScope.INSTANCE.getNode(id);
	}
	
	private String getTemplates() {
		Bundle bundle = FrameworkUtil.getBundle(getClass());
		URL templatesUrl = FileLocator.find(bundle, new Path("templates.xml"), null);
		byte[] bytes;
		
		try {	
			URI templatesURI = FileLocator.toFileURL(templatesUrl).toURI();
			bytes = Files.readAllBytes( Paths.get(templatesURI) );
			return new String(bytes, "UTF-8");
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return "";
	}
	

}
