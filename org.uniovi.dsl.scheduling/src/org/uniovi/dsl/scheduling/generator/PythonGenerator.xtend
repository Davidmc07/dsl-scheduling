package org.uniovi.dsl.scheduling.generator

import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.util.PolymorphicDispatcher
import org.uniovi.dsl.scheduling.scheduling.Config
import org.uniovi.dsl.scheduling.scheduling.InstallationDef
import org.uniovi.dsl.scheduling.scheduling.Installations
import org.uniovi.dsl.scheduling.scheduling.MaintDef
import org.uniovi.dsl.scheduling.scheduling.Maintenances
import org.uniovi.dsl.scheduling.scheduling.Program

class PythonGenerator {
	
    PolymorphicDispatcher<String> dispatcher;
    Resource resource;
    IFileSystemAccess fsa;
	
    new (Resource resource, IFileSystemAccess fsa) {
		this.resource = resource;
		this.fsa = fsa;
		this.dispatcher = PolymorphicDispatcher.createForSingleTarget("compile", this);
    }
	
    def compile() {
		var sb = new StringBuilder();
		sb.append(config());
		for (i: resource.allContents.toIterable.filter(typeof(Program))) {
			sb.append( "\n" + dispatcher.invoke(i));
		}
		fsa.generateFile("output.py", sb.toString);
		return sb.toString();
	}
	
	def config() {
		return "from dependencies import *"
			 + "\nimport warnings; warnings.filterwarnings('ignore')"
			 + "\nimport sys; sys.stdout.reconfigure(encoding='utf-8')"
			 + "\nLogger.new_file('" + getFilename(resource) + "')\n";
	}
	def getFilename(Resource resource) {
		var path = resource.getURI().path();
		if (path.startsWith("/resource/")) {
			return path.substring("/resource/".length);
		}
		return path;
	}
	
	def compile(Program program) {
		var sb = new StringBuilder();
		
		sb.append("\n" + dispatcher.invoke(program.installations));
		sb.append("\n" + dispatcher.invoke(program.maintenances));
		sb.append("\n" + dispatcher.invoke(program.options));
		return sb.toString();
	}
	
	// INSTALLATIONS
	
	def compile(Installations installations) {
		var str = "";
		
		for (installation : installations.installations) { 
			str += "\n";
			str += '''«installation.id» = MaintenancePlace(«getName(installation)», «installation.capacity»)''';
		}
		return str;
	}
	
	private def getName(InstallationDef installation) {
		return ''' '«(installation.installationName === null) ? installation.id : installation.installationName»' ''';
	}
	
	// MAINTENANCES
	
	def compile(Maintenances maintenances) {
		var str = "";
		
		for (maint : maintenances.maints) { 
			str += "\n";
			str += '''«maint.id» = Maintenance(«getMaintParams(maint)»)''';
		}
		str += "\nmaintenances = [";
		for (m: maintenances.maints) str += (m.id + ",");
		return str + "]";
	}
	
	private def getMaintParams(MaintDef maint) {
		return '''«
			»'«(maint.maintName === null) ? maint.id : maint.maintName»', «
			maint.hours», «
			maint.duration», «
			»[«maint.installations»], «
			»priority=«maint.priority», «
			»includes=[«IF (maint.includes !== null) »«maint.includes»« ENDIF»]'''
	}
	
	// MCTS AND OPTIONS
	
	def compile(Config options) {
		val projectName = resource.getURI().segment(1);
		val project = ResourcesPlugin.workspace.root.getProject(projectName) as IProject
        val output = project.getLocation().toString() + '/output';

		return "\n"+'''run_MCTS(maintenances,«
			»'«options.input»',out_filename='«output»',«
			IF (options.daysPerPeriod > 0) »days_per_period=«options.daysPerPeriod»,« ENDIF »«
			IF (options.numberOfPeriods > 0) »number_of_periods=«options.numberOfPeriods»,« ENDIF »«
			IF (options.randomState >= 0) »random_state=«options.randomState»,« ENDIF »«
			IF (options.extraHours >= 0) »additional_hours=«options.extraHours»,« ENDIF »«
			IF (options.probBeforeScheduling !== null) »prob_before_schedule=«options.probBeforeScheduling»,« ENDIF »«
			IF (options.rateOfChange !== null) »m=«options.rateOfChange»,« ENDIF »«
			IF (options.maxIters > 0) »max_iter=«options.maxIters»,« ENDIF »)''';
	}

}