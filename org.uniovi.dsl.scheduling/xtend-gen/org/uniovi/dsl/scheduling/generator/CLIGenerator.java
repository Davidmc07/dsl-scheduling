package org.uniovi.dsl.scheduling.generator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.List;
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
import org.uniovi.dsl.scheduling.scheduling.DMYDate;
import org.uniovi.dsl.scheduling.scheduling.InstallationDef;
import org.uniovi.dsl.scheduling.scheduling.Installations;
import org.uniovi.dsl.scheduling.scheduling.MaintDef;
import org.uniovi.dsl.scheduling.scheduling.Maintenances;
import org.uniovi.dsl.scheduling.scheduling.Program;

@SuppressWarnings("all")
public class CLIGenerator {
  private PolymorphicDispatcher<List<String>> dispatcher;

  private Resource resource;

  private IFileSystemAccess fsa;

  private List<String> args;

  public CLIGenerator(final Resource resource, final IFileSystemAccess fsa) {
    this.resource = resource;
    this.fsa = fsa;
    this.dispatcher = PolymorphicDispatcher.<List<String>>createForSingleTarget("compile", this);
    ArrayList<String> _arrayList = new ArrayList<String>();
    this.args = _arrayList;
  }

  public List<String> compile() {
    this.args.clear();
    this.args.add("--dsl-filename");
    this.args.add(this.getFilename(this.resource));
    Iterable<Program> _filter = Iterables.<Program>filter(IteratorExtensions.<EObject>toIterable(this.resource.getAllContents()), Program.class);
    for (final Program i : _filter) {
      this.dispatcher.invoke(i);
    }
    return this.args;
  }

  public String getFilename(final Resource resource) {
    String path = resource.getURI().path();
    boolean _startsWith = path.startsWith("/resource/");
    if (_startsWith) {
      return path.substring("/resource/".length());
    }
    return path;
  }

  public List<String> compile(final Program program) {
    this.dispatcher.invoke(program.getInstallations());
    this.dispatcher.invoke(program.getMaintenances());
    this.dispatcher.invoke(program.getOptions());
    return this.args;
  }

  public List<String> compile(final Installations installations) {
    this.args.add("--installations");
    EList<InstallationDef> _installations = installations.getInstallations();
    for (final InstallationDef installation : _installations) {
      {
        String str = "{";
        String _str = str;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("\"id\":\"");
        String _id = installation.getId();
        _builder.append(_id);
        _builder.append("\",");
        str = (_str + _builder);
        String _str_1 = str;
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("\"name\":\"");
        String _name = this.getName(installation);
        _builder_1.append(_name);
        _builder_1.append("\",");
        str = (_str_1 + _builder_1);
        String _str_2 = str;
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append("\"capacity\":");
        int _capacity = installation.getCapacity();
        _builder_2.append(_capacity);
        str = (_str_2 + _builder_2);
        String _str_3 = str;
        str = (_str_3 + "}");
        this.args.add(str.replace("\"", "\\\""));
        System.out.println(str.replace("\"", "\\\""));
      }
    }
    return this.args;
  }

  private String getName(final InstallationDef installation) {
    String _xifexpression = null;
    String _installationName = installation.getInstallationName();
    boolean _tripleEquals = (_installationName == null);
    if (_tripleEquals) {
      _xifexpression = installation.getId();
    } else {
      _xifexpression = installation.getInstallationName();
    }
    return _xifexpression;
  }

  public List<String> compile(final Maintenances maintenances) {
    this.args.add("--maintenances");
    EList<MaintDef> _maints = maintenances.getMaints();
    for (final MaintDef maint : _maints) {
      {
        String str = "{";
        String _str = str;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("\"id\":\"");
        String _id = maint.getId();
        _builder.append(_id);
        _builder.append("\",");
        str = (_str + _builder);
        String _str_1 = str;
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("\"name\":\"");
        String _xifexpression = null;
        String _maintName = maint.getMaintName();
        boolean _tripleEquals = (_maintName == null);
        if (_tripleEquals) {
          _xifexpression = maint.getId();
        } else {
          _xifexpression = maint.getMaintName();
        }
        _builder_1.append(_xifexpression);
        _builder_1.append("\",");
        str = (_str_1 + _builder_1);
        String _str_2 = str;
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append("\"flight_hours\":");
        int _hours = maint.getHours();
        _builder_2.append(_hours);
        _builder_2.append(",");
        str = (_str_2 + _builder_2);
        String _str_3 = str;
        StringConcatenation _builder_3 = new StringConcatenation();
        _builder_3.append("\"duration\":");
        int _duration = maint.getDuration();
        _builder_3.append(_duration);
        _builder_3.append(",");
        str = (_str_3 + _builder_3);
        String _str_4 = str;
        StringConcatenation _builder_4 = new StringConcatenation();
        _builder_4.append("\"priority\":");
        int _priority = maint.getPriority();
        _builder_4.append(_priority);
        _builder_4.append(",");
        str = (_str_4 + _builder_4);
        String _str_5 = str;
        StringConcatenation _builder_5 = new StringConcatenation();
        _builder_5.append("\"places\":[");
        String _processList = this.processList(maint.getInstallations());
        _builder_5.append(_processList);
        _builder_5.append("],");
        str = (_str_5 + _builder_5);
        String _str_6 = str;
        StringConcatenation _builder_6 = new StringConcatenation();
        _builder_6.append("\"includes\":[");
        {
          String _includes = maint.getIncludes();
          boolean _tripleNotEquals = (_includes != null);
          if (_tripleNotEquals) {
            String _processList_1 = this.processList(maint.getIncludes());
            _builder_6.append(_processList_1);
          }
        }
        _builder_6.append("]");
        str = (_str_6 + _builder_6);
        String _str_7 = str;
        str = (_str_7 + "}");
        this.args.add(str.replace("\"", "\\\""));
      }
    }
    return this.args;
  }

  public List<String> compile(final Config options) {
    final String projectName = this.resource.getURI().segment(1);
    IProject _project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
    final IProject project = ((IProject) _project);
    String _string = project.getLocation().toString();
    final String output = (_string + "/output");
    this.args.add("--input");
    this.args.add(options.getInput());
    this.args.add("--output");
    this.args.add(output);
    int _daysPerPeriod = options.getDaysPerPeriod();
    boolean _greaterThan = (_daysPerPeriod > 0);
    if (_greaterThan) {
      this.args.add("--days-per-period");
      this.args.add(Integer.valueOf(options.getDaysPerPeriod()).toString());
    }
    int _numberOfPeriods = options.getNumberOfPeriods();
    boolean _greaterThan_1 = (_numberOfPeriods > 0);
    if (_greaterThan_1) {
      this.args.add("--number-of-periods");
      this.args.add(Integer.valueOf(options.getNumberOfPeriods()).toString());
    }
    int _randomState = options.getRandomState();
    boolean _greaterEqualsThan = (_randomState >= 0);
    if (_greaterEqualsThan) {
      this.args.add("--random-state");
      this.args.add(Integer.valueOf(options.getRandomState()).toString());
    }
    int _extraHours = options.getExtraHours();
    boolean _greaterEqualsThan_1 = (_extraHours >= 0);
    if (_greaterEqualsThan_1) {
      this.args.add("--extra-hours");
      this.args.add(Integer.valueOf(options.getExtraHours()).toString());
    }
    String _probBeforeScheduling = options.getProbBeforeScheduling();
    boolean _tripleNotEquals = (_probBeforeScheduling != null);
    if (_tripleNotEquals) {
      this.args.add("--prob-before-scheduling");
      this.args.add(options.getProbBeforeScheduling().toString());
    }
    String _rateOfChange = options.getRateOfChange();
    boolean _tripleNotEquals_1 = (_rateOfChange != null);
    if (_tripleNotEquals_1) {
      this.args.add("--rate-of-change");
      this.args.add(options.getRateOfChange().toString());
    }
    int _maxIters = options.getMaxIters();
    boolean _greaterThan_2 = (_maxIters > 0);
    if (_greaterThan_2) {
      this.args.add("--max-iters");
      this.args.add(Integer.valueOf(options.getMaxIters()).toString());
    }
    DMYDate _startDate = options.getStartDate();
    boolean _tripleNotEquals_2 = (_startDate != null);
    if (_tripleNotEquals_2) {
      this.args.add("--start-date");
      String dateStr = Integer.valueOf(options.getStartDate().getDay()).toString();
      String _dateStr = dateStr;
      String _string_1 = Integer.valueOf(options.getStartDate().getMonth()).toString();
      String _plus = ("-" + _string_1);
      dateStr = (_dateStr + _plus);
      String _dateStr_1 = dateStr;
      String _string_2 = Integer.valueOf(options.getStartDate().getYear()).toString();
      String _plus_1 = ("-" + _string_2);
      dateStr = (_dateStr_1 + _plus_1);
      this.args.add(dateStr);
    }
    return this.args;
  }

  public String processList(final String list) {
    String[] parts = list.split(",");
    String res = "";
    for (final String part : parts) {
      {
        boolean _notEquals = (!Objects.equal(res, ""));
        if (_notEquals) {
          String _res = res;
          res = (_res + ",");
        }
        String _res_1 = res;
        String _strip = part.strip();
        String _plus = ("\"" + _strip);
        String _plus_1 = (_plus + "\"");
        res = (_res_1 + _plus_1);
      }
    }
    return res;
  }
}
