package org.uniovi.dsl.scheduling.generator

import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.util.PolymorphicDispatcher
import org.uniovi.dsl.scheduling.scheduling.Config
import org.uniovi.dsl.scheduling.scheduling.InstallationDef
import org.uniovi.dsl.scheduling.scheduling.Installations
import org.uniovi.dsl.scheduling.scheduling.Maintenances
import org.uniovi.dsl.scheduling.scheduling.Program
import java.util.List
import java.util.ArrayList

class CLIGenerator { 
    PolymorphicDispatcher<List<String>> dispatcher;
    Resource resource;
    IFileSystemAccess fsa;
    List<String> args;
	
    new (Resource resource, IFileSystemAccess fsa) {
		this.resource = resource;
		this.fsa = fsa;
		this.dispatcher = PolymorphicDispatcher.createForSingleTarget("compile", this);
		this.args = new ArrayList<String>();
    }
	
    def compile() {
    	args.clear();
		args.add("--dsl-filename");
		args.add(getFilename(resource));

		for (i: resource.allContents.toIterable.filter(typeof(Program))) {
			dispatcher.invoke(i);
		}
		//fsa.generateFile("output.txt", sb.toString);
		return args;
	}
	
	def getFilename(Resource resource) {
		var path = resource.getURI().path();
		if (path.startsWith("/resource/")) {
			return path.substring("/resource/".length);
		}
		return path;
	}
	
	def compile(Program program) {
		dispatcher.invoke(program.installations);
		dispatcher.invoke(program.maintenances);
		dispatcher.invoke(program.options);
		return args;
	}
	
	// INSTALLATIONS
	
	def compile(Installations installations) {
		args.add("--installations");
		
		for (installation : installations.installations) { 
			var str = "{";
			str += '''"id":"«installation.id»", ''';
			str += '''"name":"«getName(installation)»", ''';
			str += '''"capacity":«installation.capacity»''';
			str += "}";
			args.add(str.replace('"', '\\"'));
			System.out.println(str.replace('"', '\\"'))
		}
		return args;
	}
	
	private def getName(InstallationDef installation) {
		return (installation.installationName === null) ? installation.id : installation.installationName;
	}
	
	// MAINTENANCES
	
	def compile(Maintenances maintenances) {
		args.add("--maintenances");
		
		for (maint : maintenances.maints) { 
			var str = "{";
			str += '''"id":"«maint.id»", ''';
			str += '''"name":"«(maint.maintName === null) ? maint.id : maint.maintName»", ''';
			str += '''"flight_hours":«maint.hours», ''';
			str += '''"duration":«maint.duration», ''';
			str += '''"priority":«maint.priority», ''';
			str += '''"places":[«processList(maint.installations)»], ''';
			str += '''"includes":[«IF (maint.includes !== null) »«processList(maint.includes)»« ENDIF»]''';
			str += "}";
			args.add(str.replace('"', '\\"'));
		}
		return args;
	}

	// MCTS AND OPTIONS
	
	def compile(Config options) {
		val projectName = resource.getURI().segment(1);
		val project = ResourcesPlugin.workspace.root.getProject(projectName) as IProject
        val output = project.getLocation().toString() + '/output';
        
        args.add('--input');
        args.add(options.input);
        args.add('--output');
        args.add(output);
		if (options.daysPerPeriod > 0) {
			args.add('--days-per-period');
        	args.add(options.daysPerPeriod.toString());
		}
		if (options.numberOfPeriods > 0) {
			args.add('--number-of-periods');
        	args.add(options.numberOfPeriods.toString());
		}
		if (options.randomState >= 0) {
			args.add('--random-state');
        	args.add(options.randomState.toString());
		}
		if (options.extraHours >= 0) {
			args.add('--extra-hours');
        	args.add(options.extraHours.toString());
		}
		if (options.probBeforeScheduling !== null) {
			args.add('--prob-before-scheduling');
        	args.add(options.probBeforeScheduling.toString());
		}
		if (options.rateOfChange !== null) {
			args.add('--rate-of-change');
        	args.add(options.rateOfChange.toString());
		}
		if (options.maxIters > 0) {
			args.add('--max-iters');
        	args.add(options.maxIters.toString());
		}
		return args
	}
	
	def processList(String list) {
		var parts = list.split(",");
		var res = "";
		for (part : parts) {
			if (res != "") res += ",";
			res += "\""+part.strip()+"\"";
		}
		return res;
	}

}