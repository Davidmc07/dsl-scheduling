package org.uniovi.dsl.scheduling.ui.syntaxhighlighting;

import java.util.regex.Pattern;

import org.eclipse.xtext.ide.editor.syntaxcoloring.DefaultAntlrTokenToAttributeIdMapper;

public class SchedulingAntlrMapper extends DefaultAntlrTokenToAttributeIdMapper {
	private static final Pattern QUOTED = Pattern.compile("(?:^\'([^\']*)\'$)|(?:^\"([^\"]*)\")$", Pattern.MULTILINE);

	@Override
    protected String calculateId(String tokenName, int tokenType) {
		if (tokenName == null) {
			return SchedulingHighlightingConfiguration.DEFAULT_ID;
		}
		if (QUOTED.matcher(tokenName).matches()) {
			return SchedulingHighlightingConfiguration.ATTRIBUTE_ID;
		}
		
		switch (tokenName) {
			// Types
			case "RULE_STRING": 
				return SchedulingHighlightingConfiguration.STRING_ID;
			case "RULE_INT": 
			case "RULE_FLOAT": 
				return SchedulingHighlightingConfiguration.NUMBER_ID;
			// Sections
			case "RULE_OPTIONS_HEADER": 
			case "RULE_MAINTENANCES_HEADER": 
			case "RULE_INSTALLATIONS_HEADER": 
				return SchedulingHighlightingConfiguration.SECTION_ID;
			// Objects
			case "RULE_MAINTENANCE_TYPE": 
			case "RULE_INSTALLATION_TYPE": 
				return SchedulingHighlightingConfiguration.TYPE_ID;
			default:
				return SchedulingHighlightingConfiguration.DEFAULT_ID;
		}
    }
}
