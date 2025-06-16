package org.uniovi.dsl.scheduling.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;

public class CodeExecutionManager {
	private static String path = "";
    private static boolean executing = false;

    public static void execute(List<String> args) {
		if (!executing) {
		    executing = true;
		    Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
				    executePythonCode(args);
				    executing = false;
				}
		    });
		    thread.start();
		}
    }

    private static void executePythonCode(List<String> args) {
		IConsoleManager consoleManager = ConsolePlugin.getDefault().getConsoleManager();
		MessageConsole console = findOrCreateConsole("Console");
		console.clearConsole();
		consoleManager.showConsoleView(console);
		console.activate();
	
		args.add(0, "cmd");
		args.add(1, "/c");
		args.add(2, "cli-scheduler.exe");
		args.add("--path");
		args.add(path);
		ProcessBuilder processBuilder = new ProcessBuilder(args);
		processBuilder.redirectOutput(Redirect.PIPE);
		//processBuilder.redirectErrorStream(true);
		setWorkingDirectory(processBuilder);
	
		try (AnsiStream stream = new AnsiStream(console)) {
		    Process process = processBuilder.start();
		    InputStream inputStream = process.getInputStream();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
	
		    String line;
		    while ((line = reader.readLine()) != null) {
		    	System.out.println(line);
				stream.println(line);
		    }
	
		    int exitCode = process.waitFor();
		    if (exitCode == 0) {
		    	System.out.println("Python code executed successfully.");
		    } else {
		    	stream.println("\n\nAn error has occurred");
		    	System.out.println("Error executing Python code. Exit code: " + exitCode);
		    }
		} catch (IOException | InterruptedException e) {
		    e.printStackTrace();
		    executing = false;
		}
	
		consoleManager.showConsoleView(console);
    }

    public static void setWorkingDirectory(ProcessBuilder processBuilder) {
		String jarPath = CodeExecutionManager.class.getProtectionDomain().getCodeSource()
			.getLocation().getPath().replaceFirst("^/", "");
	
		String pluginsDirectory = jarPath.replaceFirst("org.uniovi.dsl.scheduling" + ".*", "");
	
		System.out.println(jarPath);
	
		for (File file : new File(pluginsDirectory).listFiles()) {
		    if (file.getName().startsWith("org.uniovi.dsl.scheduling.python")) {
		    	processBuilder.directory(file);
		    }
		}
    }

    private static MessageConsole findOrCreateConsole(String name) {
		IConsoleManager consoleManager = ConsolePlugin.getDefault().getConsoleManager();
	
		for (IConsole console : consoleManager.getConsoles()) {
		    if (console.getName().equals(name) && console instanceof MessageConsole) {
		    	System.out.println(((MessageConsole) console).getEncoding());
		    	return (MessageConsole) console;
		    }
		}
	
		MessageConsole console = new MessageConsole(name, null, null, "UTF-8", true);
		consoleManager.addConsoles(new IConsole[] { console });
		return console;
    }
    
    public static void setProjectPath(String path) {
    	CodeExecutionManager.path = path;
    }

}
