package org.uniovi.dsl.scheduling.tests;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.uniovi.dsl.scheduling.scheduling.Program;

@RunWith(XtextRunner.class)
@InjectWith(SchedulingInjectorProvider.class)
@SuppressWarnings("all")
public class MaintenancesTest {
  @Inject
  @Extension
  private ParseHelper<Program> _parseHelper;

  @Inject
  private ValidationTestHelper validationTestHelper;

  @Test
  public void testNoRequiredAttributes() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Maintenances]");
      _builder.newLine();
      _builder.append("Maintenance m1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"m1\"");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Installations]");
      _builder.newLine();
      _builder.append("Installation i1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"i1\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Capacity: 10");
      _builder.newLine();
      final Program model = this._parseHelper.parse(_builder);
      final List<Issue> issues = this.validationTestHelper.validate(model);
      Assert.assertNotNull(model);
      Assert.assertNotNull(model.getOptions());
      Assert.assertNotNull(model.getMaintenances());
      Assert.assertNotNull(model.getInstallations());
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("Invalid maintenance definition"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testRepeatedMaintenance() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Maintenances]");
      _builder.newLine();
      _builder.append("Maintenance m1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"m1\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Interval: 5");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Periods: 1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Installations: i1");
      _builder.newLine();
      _builder.append("Maintenance m1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"m1\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Interval: 5");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Periods: 1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Installations: i1");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Installations]");
      _builder.newLine();
      _builder.append("Installation i1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"i1\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Capacity: 10");
      _builder.newLine();
      final Program model = this._parseHelper.parse(_builder);
      final List<Issue> issues = this.validationTestHelper.validate(model);
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("Maintenance ID already defined"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testMultipleMaintenances() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Maintenances]");
      _builder.newLine();
      _builder.append("Maintenance m1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"m1\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Interval: 5");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Periods: 1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Installations: i1");
      _builder.newLine();
      _builder.append("Maintenance m2");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"m2\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Interval: 5");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Periods: 1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Installations: i1");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Installations]");
      _builder.newLine();
      _builder.append("Installation i1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"i1\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Capacity: 10");
      _builder.newLine();
      final Program model = this._parseHelper.parse(_builder);
      this.validationTestHelper.assertNoErrors(model);
      Assert.assertNotNull(model);
      Assert.assertNotNull(model.getOptions());
      Assert.assertNotNull(model.getMaintenances());
      Assert.assertNotNull(model.getInstallations());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testIncludesOthers() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Maintenances]");
      _builder.newLine();
      _builder.append("Maintenance m1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"m1\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Interval: 5");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Periods: 1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Installations: i1");
      _builder.newLine();
      _builder.append("Maintenance m2");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"m2\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Interval: 5");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Periods: 1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Includes: m1, m3");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Installations: i1");
      _builder.newLine();
      _builder.append("Maintenance m3");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"m3\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Interval: 5");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Periods: 1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Installations: i1");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Installations]");
      _builder.newLine();
      _builder.append("Installation i1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"i1\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Capacity: 10");
      _builder.newLine();
      final Program model = this._parseHelper.parse(_builder);
      this.validationTestHelper.assertNoErrors(model);
      Assert.assertNotNull(model);
      Assert.assertNotNull(model.getOptions());
      Assert.assertNotNull(model.getMaintenances());
      Assert.assertNotNull(model.getInstallations());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testIncludeItself() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Maintenances]");
      _builder.newLine();
      _builder.append("Maintenance m1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"m1\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Interval: 5");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Periods: 1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Installations: i1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Includes: m1");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Installations]");
      _builder.newLine();
      _builder.append("Installation i1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"i1\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Capacity: 10");
      _builder.newLine();
      final Program model = this._parseHelper.parse(_builder);
      final List<Issue> issues = this.validationTestHelper.validate(model);
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("A maintenance can not include itself"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testIncludeNotDefined() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Maintenances]");
      _builder.newLine();
      _builder.append("Maintenance m1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"m1\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Interval: 5");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Periods: 1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Installations: i1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Includes: m2");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Installations]");
      _builder.newLine();
      _builder.append("Installation i1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"i1\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Capacity: 10");
      _builder.newLine();
      final Program model = this._parseHelper.parse(_builder);
      final List<Issue> issues = this.validationTestHelper.validate(model);
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("Maintenance not defined"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testNegativePeriods() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Maintenances]");
      _builder.newLine();
      _builder.append("Maintenance m1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"m1\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Interval: 5");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Periods: -1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Installations: i1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Includes: m2");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Installations]");
      _builder.newLine();
      _builder.append("Installation i1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"i1\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Capacity: 10");
      _builder.newLine();
      final Program model = this._parseHelper.parse(_builder);
      final List<Issue> issues = this.validationTestHelper.validate(model);
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("extraneous input \'-\' expecting RULE_INT"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testNegativeInterval() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Maintenances]");
      _builder.newLine();
      _builder.append("Maintenance m1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"m1\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Interval: -5");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Periods: 1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Installations: i1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Includes: m2");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Installations]");
      _builder.newLine();
      _builder.append("Installation i1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"i1\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Capacity: 10");
      _builder.newLine();
      final Program model = this._parseHelper.parse(_builder);
      final List<Issue> issues = this.validationTestHelper.validate(model);
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("extraneous input \'-\' expecting RULE_INT"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testInstallationNotDefined() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Maintenances]");
      _builder.newLine();
      _builder.append("Maintenance m1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"m1\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Interval: -5");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Periods: 1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Installations: i2");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Includes: m2");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Installations]");
      _builder.newLine();
      _builder.append("Installation i1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Name: \"i1\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Capacity: 10");
      _builder.newLine();
      final Program model = this._parseHelper.parse(_builder);
      final List<Issue> issues = this.validationTestHelper.validate(model);
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("Installation not defined"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
