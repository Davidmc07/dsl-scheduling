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
    private Pattern ansiPattern = Pattern.compile("(?=(\033\\[\\d+m))");
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
	addStream("\033[30m", console, new Color(0, 0, 0)); // Black
	addStream("\033[31m", console, new Color(255, 0, 0)); // Red
	addStream("\033[32m", console, new Color(0, 255, 0)); // Green
	addStream("\033[33m", console, new Color(255, 255, 0)); // Yellow
	addStream("\033[34m", console, new Color(0, 0, 255)); // Blue
	addStream("\033[35m", console, new Color(255, 0, 255)); // Magenta
	addStream("\033[36m", console, new Color(0, 255, 255)); // Cyan
	addStream("\033[37m", console, new Color(255, 255, 255));// White
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
	    this.currentStream = streamPool.get("default");
	} else {
	    this.currentStream = stream;
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
