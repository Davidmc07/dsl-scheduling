package org.uniovi.dsl.scheduling.ui.theme;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.texteditor.AbstractTextEditor;

public class ThemeUtils {
	
    public static RGB getElementColor(ColorPalette element) {
    	return isDarkThemeEnabled() ? element.getDark() : element.getLight();
    }
    
    public static boolean isDarkThemeEnabled() {
    	IPreferenceStore store = EditorsUI.getPreferenceStore();
    	String bgProp = store.getString(AbstractTextEditor.PREFERENCE_COLOR_BACKGROUND);
    	RGB backgroundRGB = StringConverter.asRGB(bgProp, new RGB(255, 255, 255));

    	double luminance = (0.299 * backgroundRGB.red) 
    	                 + (0.587 * backgroundRGB.green) 
    	                 + (0.114 * backgroundRGB.blue);

    	return luminance < 130;
    }

}
