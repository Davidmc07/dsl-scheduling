package org.uniovi.dsl.scheduling.ui.syntaxhighlighting;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfigurationAcceptor;
import org.eclipse.xtext.ui.editor.utils.TextStyle;

public class SchedulingHighlightingConfiguration extends DefaultHighlightingConfiguration {
	
	public static final String DEFAULT_ID = "rule_default";
	public static final String STRING_ID = "rule_string";
	public static final String NUMBER_ID = "rule_number";
	public static final String SECTION_ID = "section";
	public static final String TYPE_ID = "type";
	public static final String ATTRIBUTE_ID = "attribute";
	
	
	private static final RGB CYAN = new RGB(0, 255, 255);
	private static final RGB RED = new RGB(255, 0, 128);
	private static final RGB RED2 = new RGB(255, 16, 128);
	private static final RGB GREEN = new RGB(0, 255, 64);

    @Override
	public void configure(IHighlightingConfigurationAcceptor acceptor) {
    	acceptor.acceptDefaultHighlighting(DEFAULT_ID, "Default", defaultTextStyle());
		acceptor.acceptDefaultHighlighting(STRING_ID, "String", stringTextStyle());
		acceptor.acceptDefaultHighlighting(NUMBER_ID, "Number", numberTextStyle());
        acceptor.acceptDefaultHighlighting(SECTION_ID, "section", sectionTextStyle());
        acceptor.acceptDefaultHighlighting(TYPE_ID, "Type", typeTextStyle());
        acceptor.acceptDefaultHighlighting(ATTRIBUTE_ID, "Attribute", attributeTextStyle());
    }
    
    @Override
    public TextStyle defaultTextStyle() {
        TextStyle textStyle = super.defaultTextStyle().copy();
        textStyle.setFontData(new FontData("Consolas", 12, SWT.NORMAL));
        textStyle.setColor(new RGB(255, 128, 0));
        return textStyle;
    }
    
    @Override
    public TextStyle numberTextStyle() {
		TextStyle textStyle = defaultTextStyle().copy();
		textStyle.setColor(new RGB(94, 150, 255));
		return textStyle;
	}

    @Override
	public TextStyle stringTextStyle() {
		TextStyle textStyle = defaultTextStyle().copy();
		textStyle.setColor(new RGB(255, 255, 128));
		return textStyle;
	}
    
    public TextStyle sectionTextStyle() {
        TextStyle textStyle = defaultTextStyle().copy();
        textStyle.setFontData(new FontData("Palatino Linotype", 16, SWT.BOLD));
        textStyle.setColor(new RGB(255, 255, 255));
        return textStyle;
    }
    
    public TextStyle typeTextStyle() {
        TextStyle textStyle = defaultTextStyle().copy();
        textStyle.setFontData(new FontData("Leelawadee UI", 12, SWT.BOLD));
        textStyle.setColor(new RGB(255, 0, 128));
        return textStyle;
    }
    
    public TextStyle attributeTextStyle() {
        TextStyle textStyle = defaultTextStyle().copy();
        textStyle.setFontData(new FontData("Leelawadee UI", 12, SWT.NORMAL));
        textStyle.setColor(new RGB(70, 202, 202));
        return textStyle;
    }
}