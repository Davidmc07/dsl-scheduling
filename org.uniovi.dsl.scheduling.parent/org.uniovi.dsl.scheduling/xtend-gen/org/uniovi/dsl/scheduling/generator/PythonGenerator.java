package org.uniovi.dsl.scheduling.generator;

import com.google.common.collect.Iterables;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.util.PolymorphicDispatcher;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.uniovi.dsl.scheduling.scheduling.Config;
import org.uniovi.dsl.scheduling.scheduling.InstallationDef;
import org.uniovi.dsl.scheduling.scheduling.Installations;
import org.uniovi.dsl.scheduling.scheduling.MaintDef;
import org.uniovi.dsl.scheduling.scheduling.Maintenances;
import org.uniovi.dsl.scheduling.scheduling.Program;

@SuppressWarnings("all")
public class PythonGenerator {
  private PolymorphicDispatcher<String> dispatcher;

  private Resource resource;

  private IFileSystemAccess fsa;

  public PythonGenerator(final Resource resource, final IFileSystemAccess fsa) {
    this.resource = resource;
    this.fsa = fsa;
    this.dispatcher = PolymorphicDispatcher.<String>createForSingleTarget("compile", this);
  }

  public String compile() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.config());
    Iterable<Program> _filter = Iterables.<Program>filter(IteratorExtensions.<EObject>toIterable(this.resource.getAllContents()), Program.class);
    for (final Program i : _filter) {
      String _invoke = this.dispatcher.invoke(i);
      String _plus = ("\n" + _invoke);
      sb.append(_plus);
    }
    this.fsa.generateFile("output.py", sb.toString());
    return sb.toString();
  }

  public String config() {
    String _filename = this.getFilename(this.resource);
    String _plus = (((("from dependencies import *" + "\nimport warnings; warnings.filterwarnings(\'ignore\')") + "\nimport sys; sys.stdout.reconfigure(encoding=\'utf-8\')") + "\nLogger.new_file(\'") + _filename);
    return (_plus + "\')\n");
  }

  public String getFilename(final Resource resource) {
    String path = resource.getURI().path();
    boolean _startsWith = path.startsWith("/resource/");
    if (_startsWith) {
      return path.substring("/resource/".length());
    }
    return path;
  }

  public String compile(final Program program) {
    StringBuilder sb = new StringBuilder();
    String _invoke = this.dispatcher.invoke(program.getInstallations());
    String _plus = ("\n" + _invoke);
    sb.append(_plus);
    String _invoke_1 = this.dispatcher.invoke(program.getMaintenances());
    String _plus_1 = ("\n" + _invoke_1);
    sb.append(_plus_1);
    String _invoke_2 = this.dispatcher.invoke(program.getOptions());
    String _plus_2 = ("\n" + _invoke_2);
    sb.append(_plus_2);
    return sb.toString();
  }

  public String compile(final Installations installations) {
    String str = "";
    EList<InstallationDef> _installations = installations.getInstallations();
    for (final InstallationDef installation : _installations) {
      {
        String _str = str;
        str = (_str + "\n");
        String _str_1 = str;
        StringConcatenation _builder = new StringConcatenation();
        String _id = installation.getId();
        _builder.append(_id);
        _builder.append(" = MaintenancePlace(");
        String _name = this.getName(installation);
        _builder.append(_name);
        _builder.append(", ");
        int _capacity = installation.getCapacity();
        _builder.append(_capacity);
        _builder.append(")");
        str = (_str_1 + _builder);
      }
    }
    return str;
  }

  private String getName(final InstallationDef installation) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(" ");
    _builder.append("\'");
    String _xifexpression = null;
    String _installationName = installation.getInstallationName();
    boolean _tripleEquals = (_installationName == null);
    if (_tripleEquals) {
      _xifexpression = installation.getId();
    } else {
      _xifexpression = installation.getInstallationName();
    }
    _builder.append(_xifexpression, " ");
    _builder.append("\' ");
    return _builder.toString();
  }

  public String compile(final Maintenances maintenances) {
    String str = "";
    EList<MaintDef> _maints = maintenances.getMaints();
    for (final MaintDef maint : _maints) {
      {
        String _str = str;
        str = (_str + "\n");
        String _str_1 = str;
        StringConcatenation _builder = new StringConcatenation();
        String _id = maint.getId();
        _builder.append(_id);
        _builder.append(" = Maintenance(");
        String _maintParams = this.getMaintParams(maint);
        _builder.append(_maintParams);
        _builder.append(")");
        str = (_str_1 + _builder);
      }
    }
    String _str = str;
    str = (_str + "\nmaintenances = [");
    EList<MaintDef> _maints_1 = maintenances.getMaints();
    for (final MaintDef m : _maints_1) {
      String _str_1 = str;
      String _id = m.getId();
      String _plus = (_id + ",");
      str = (_str_1 + _plus);
    }
    return (str + "]");
  }

  private String getMaintParams(final MaintDef maint) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\'");
    String _xifexpression = null;
    String _maintName = maint.getMaintName();
    boolean _tripleEquals = (_maintName == null);
    if (_tripleEquals) {
      _xifexpression = maint.getId();
    } else {
      _xifexpression = maint.getMaintName();
    }
    _builder.append(_xifexpression);
    _builder.append("\', ");
    int _hours = maint.getHours();
    _builder.append(_hours);
    _builder.append(", ");
    int _duration = maint.getDuration();
    _builder.append(_duration);
    _builder.append(", ");
    _builder.append("[");
    String _installations = maint.getInstallations();
    _builder.append(_installations);
    _builder.append("], ");
    _builder.append("priority=");
    int _priority = maint.getPriority();
    _builder.append(_priority);
    _builder.append(", ");
    _builder.append("includes=[");
    {
      String _includes = maint.getIncludes();
      boolean _tripleNotEquals = (_includes != null);
      if (_tripleNotEquals) {
        String _includes_1 = maint.getIncludes();
        _builder.append(_includes_1);
      }
    }
    _builder.append("]");
    return _builder.toString();
  }

  public String compile(final Config options) {
    final String projectName = this.resource.getURI().segment(1);
    IProject _project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
    final IProject project = ((IProject) _project);
    String _string = project.getLocation().toString();
    final String output = (_string + "/output");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("run_MCTS(maintenances,");
    _builder.append("\'");
    String _input = options.getInput();
    _builder.append(_input);
    _builder.append("\',out_filename=\'");
    _builder.append(output);
    _builder.append("\',");
    {
      int _daysPerPeriod = options.getDaysPerPeriod();
      boolean _greaterThan = (_daysPerPeriod > 0);
      if (_greaterThan) {
        _builder.append("days_per_period=");
        int _daysPerPeriod_1 = options.getDaysPerPeriod();
        _builder.append(_daysPerPeriod_1);
        _builder.append(",");
      }
    }
    {
      int _numberOfPeriods = options.getNumberOfPeriods();
      boolean _greaterThan_1 = (_numberOfPeriods > 0);
      if (_greaterThan_1) {
        _builder.append("number_of_periods=");
        int _numberOfPeriods_1 = options.getNumberOfPeriods();
        _builder.append(_numberOfPeriods_1);
        _builder.append(",");
      }
    }
    {
      int _randomState = options.getRandomState();
      boolean _greaterEqualsThan = (_randomState >= 0);
      if (_greaterEqualsThan) {
        _builder.append("random_state=");
        int _randomState_1 = options.getRandomState();
        _builder.append(_randomState_1);
        _builder.append(",");
      }
    }
    {
      int _extraHours = options.getExtraHours();
      boolean _greaterEqualsThan_1 = (_extraHours >= 0);
      if (_greaterEqualsThan_1) {
        _builder.append("additional_hours=");
        int _extraHours_1 = options.getExtraHours();
        _builder.append(_extraHours_1);
        _builder.append(",");
      }
    }
    {
      String _probBeforeScheduling = options.getProbBeforeScheduling();
      boolean _tripleNotEquals = (_probBeforeScheduling != null);
      if (_tripleNotEquals) {
        _builder.append("prob_before_schedule=");
        String _probBeforeScheduling_1 = options.getProbBeforeScheduling();
        _builder.append(_probBeforeScheduling_1);
        _builder.append(",");
      }
    }
    {
      String _rateOfChange = options.getRateOfChange();
      boolean _tripleNotEquals_1 = (_rateOfChange != null);
      if (_tripleNotEquals_1) {
        _builder.append("m=");
        String _rateOfChange_1 = options.getRateOfChange();
        _builder.append(_rateOfChange_1);
        _builder.append(",");
      }
    }
    {
      int _maxIters = options.getMaxIters();
      boolean _greaterThan_2 = (_maxIters > 0);
      if (_greaterThan_2) {
        _builder.append("max_iter=");
        int _maxIters_1 = options.getMaxIters();
        _builder.append(_maxIters_1);
        _builder.append(",");
      }
    }
    _builder.append(")");
    return ("\n" + _builder);
  }
}
