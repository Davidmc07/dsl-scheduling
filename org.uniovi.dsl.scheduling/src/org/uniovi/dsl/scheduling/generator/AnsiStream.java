package org.uniovi.dsl.scheduling.generator;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class AnsiStream implements Flushable, Closeable {
    private Pattern ansiPattern = Pattern.compile("(?=(\033\\[[0-9;]*m))");
    private Pattern rgbParser = Pattern.compile("\033\\[38;2;(\\d+);(\\d+);(\\d+)m");
    private HashMap<String, MessageConsoleStream> streamPool = new HashMap<>();
    private MessageConsoleStream currentStream;

    public AnsiStream(MessageConsole console) {
    	Display.getDefault().syncExec(new Runnable() {
    	    public void run() {
    	    	console.setFont(new Font(null, new FontData("Consolas", 10, SWT.NORMAL)));
    	    	initStreamPool(console);
    	    	currentStream = streamPool.get("default");
    	    }
    	});
    }

    private void initStreamPool(MessageConsole console) {
		addStream("default", console, null); // Default
		streamPool.put("\033[0m", streamPool.get("default"));
    }

    private void addStream(String name, MessageConsole console, Color color) {
		MessageConsoleStream stream = console.newMessageStream();
		stream.setColor(color);
		stream.setEncoding("UTF-8");
		stream.setFontStyle(10);
		streamPool.put(name, stream);
    }

    public void print(String message) {
	String[] parts = ansiPattern.split(message);

	for (int i = 0; i < parts.length; i++) {
	    Matcher matcher = ansiPattern.matcher(parts[i]);

	    if (matcher.find()) {
			String escapeSequence = matcher.group(1);
			String text = parts[i].substring(escapeSequence.length());
			updateColor(escapeSequence);
			currentStream.print(text);
	    } else {
			String str = parts[i];
			currentStream.print(str);
	    }
	}
    }

    private void updateColor(String ansiColor) {
		MessageConsoleStream stream = streamPool.get(ansiColor);
		if (stream == null) {
		    this.currentStream = addNewAnsiColor(ansiColor);
		} else {
		    this.currentStream = stream;
		}
    }
    
    private MessageConsoleStream addNewAnsiColor(String ansiColor) {
    	Matcher rgbMatcher = rgbParser.matcher(ansiColor);
    	if (rgbMatcher.matches()) {
            try {
            	int r = Integer.parseInt(rgbMatcher.group(1));
                int g = Integer.parseInt(rgbMatcher.group(2));
                int b = Integer.parseInt(rgbMatcher.group(3));
                
                Display.getDefault().syncExec(() -> {
                    addStream(ansiColor, this.currentStream.getConsole(), 
                    		new Color(Display.getDefault(), r, g, b));
                });
                return streamPool.get(ansiColor);
            } catch (Exception e) {
                return streamPool.get("default");
            }
        } else {
        	return streamPool.get("default");
        }
    }

    public void setDefaultColor(Color color) {
		streamPool.get("default").setColor(color);
    }

    public void println() {
		currentStream.println();
    }

    public void println(String message) {
		print(message + "\n");
    }

    public MessageConsole getConsole() {
		return currentStream.getConsole();
    }

    @Override
    public void close() throws IOException {
    	for (MessageConsoleStream stream : streamPool.values()) {
		    try {
		    	stream.close();
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		}
    }

    @Override
    public void flush() throws IOException {
    	currentStream.flush();
    }

}
