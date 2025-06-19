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
public class OptionsTest {
  @Inject
  @Extension
  private ParseHelper<Program> _parseHelper;

  @Inject
  private ValidationTestHelper validationTestHelper;

  @Test
  public void testNoInputFile() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Number_of_periods: 5");
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
          return Boolean.valueOf(it.getMessage().contains("An input file must be specified"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testRepeatedParameter() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
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
          return Boolean.valueOf(it.getMessage().contains("no viable alternative at input \'Input:\'"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testAllParameters() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
      _builder.newLine();
      _builder.append("Number_of_periods: 100");
      _builder.newLine();
      _builder.append("Days_per_period: 1");
      _builder.newLine();
      _builder.append("Extra_hours: 10");
      _builder.newLine();
      _builder.append("Iterations: 10");
      _builder.newLine();
      _builder.append("Random_state: 42");
      _builder.newLine();
      _builder.append("Prob_before_restriction: 0.8");
      _builder.newLine();
      _builder.append("Rate_of_change: 9.0");
      _builder.newLine();
      _builder.append("Start_date: 1/2/2025");
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
  public void testInvalidParameterValue() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
      _builder.newLine();
      _builder.append("Number_of_periods: -100");
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
}
