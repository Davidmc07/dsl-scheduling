package org.uniovi.dsl.scheduling.ui.theme;

import org.eclipse.swt.graphics.RGB;

public enum ColorPalette {
	DEFAULT(new RGB(0, 0, 255), new RGB(255, 128, 0)),
	NUMBER(new RGB(95, 0, 180), new RGB(94, 150, 255)),
	STRING(new RGB(193, 90, 0), new RGB(255, 255, 128)),
	SECTION(new RGB(0, 0, 0), new RGB(255, 255, 255)),
	TYPE(new RGB(230, 0, 115), new RGB(255, 0, 128)),
	ATTRIBUTE(new RGB(0, 68, 204), new RGB(70, 202, 202));

    private final RGB light;
    private final RGB dark;

    ColorPalette(RGB light, RGB dark) {
        this.light = light;
        this.dark = dark;
    }
    public RGB getLight() { return light; }
    public RGB getDark() { return dark; }
}
