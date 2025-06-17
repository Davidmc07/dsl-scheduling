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
public class SectionTest {
  @Inject
  @Extension
  private ParseHelper<Program> _parseHelper;

  @Inject
  private ValidationTestHelper validationTestHelper;

  @Test
  public void testIncludeAllSections() {
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
  public void testChangeSectionOrder() {
    try {
      StringConcatenation _builder = new StringConcatenation();
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
      _builder.newLine();
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
  public void testEmptySections() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Installations]");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Maintenances]");
      _builder.newLine();
      final Program model = this._parseHelper.parse(_builder);
      final List<Issue> issues = this.validationTestHelper.validate(model);
      Assert.assertNull(model.getOptions());
      Assert.assertNull(model.getInstallations());
      Assert.assertNull(model.getMaintenances());
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("Incomplete section"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testThreeEqualSections() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
      _builder.newLine();
      final Program model = this._parseHelper.parse(_builder);
      final List<Issue> issues = this.validationTestHelper.validate(model);
      Assert.assertNotNull(model.getOptions());
      Assert.assertNull(model.getInstallations());
      Assert.assertNull(model.getMaintenances());
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("Missing the following sections:\n\t- Installations\n\t- Maintenances"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testOnlyOptionsSection() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
      _builder.newLine();
      final Program model = this._parseHelper.parse(_builder);
      final List<Issue> issues = this.validationTestHelper.validate(model);
      Assert.assertNotNull(model.getOptions());
      Assert.assertNull(model.getInstallations());
      Assert.assertNull(model.getMaintenances());
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("Missing the following sections:\n\t- Installations\n\t- Maintenances"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testOnlyMaintenancesSection() {
    try {
      StringConcatenation _builder = new StringConcatenation();
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
      final Program model = this._parseHelper.parse(_builder);
      final List<Issue> issues = this.validationTestHelper.validate(model);
      Assert.assertNull(model.getOptions());
      Assert.assertNull(model.getInstallations());
      Assert.assertNotNull(model.getMaintenances());
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("Missing the following sections:\n\t- Options\n\t- Installations"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testOnlyInstallationsSection() {
    try {
      StringConcatenation _builder = new StringConcatenation();
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
      Assert.assertNull(model.getOptions());
      Assert.assertNotNull(model.getInstallations());
      Assert.assertNull(model.getMaintenances());
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("Missing the following sections:\n\t- Options\n\t- Maintenances"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testMissingOptionsSection() {
    try {
      StringConcatenation _builder = new StringConcatenation();
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
      _builder.newLine();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
      _builder.newLine();
      final Program model = this._parseHelper.parse(_builder);
      final List<Issue> issues = this.validationTestHelper.validate(model);
      Assert.assertNotNull(model.getOptions());
      Assert.assertNotNull(model.getInstallations());
      Assert.assertNull(model.getMaintenances());
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("Missing the following sections:\n\t- Maintenances"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testMissingInstallationsSection() {
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
      final Program model = this._parseHelper.parse(_builder);
      final List<Issue> issues = this.validationTestHelper.validate(model);
      Assert.assertNotNull(model.getOptions());
      Assert.assertNull(model.getInstallations());
      Assert.assertNotNull(model.getMaintenances());
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("Missing the following sections:\n\t- Installations"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testEmptyInstallationsSection() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Installations]");
      _builder.newLine();
      _builder.newLine();
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
      final Program model = this._parseHelper.parse(_builder);
      final List<Issue> issues = this.validationTestHelper.validate(model);
      Assert.assertNotNull(model.getOptions());
      Assert.assertNotNull(model.getMaintenances());
      Assert.assertNull(model.getInstallations());
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("Incomplete section"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testEmptyOptionsSection() {
    try {
      StringConcatenation _builder = new StringConcatenation();
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
      _builder.newLine();
      _builder.append("[Options]");
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
      final Program model = this._parseHelper.parse(_builder);
      final List<Issue> issues = this.validationTestHelper.validate(model);
      Assert.assertNull(model.getOptions());
      Assert.assertNotNull(model.getMaintenances());
      Assert.assertNotNull(model.getInstallations());
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("Incomplete section"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testEmptyMaintenancesSection() {
    try {
      StringConcatenation _builder = new StringConcatenation();
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
      _builder.newLine();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Maintenances]");
      _builder.newLine();
      final Program model = this._parseHelper.parse(_builder);
      final List<Issue> issues = this.validationTestHelper.validate(model);
      Assert.assertNotNull(model.getOptions());
      Assert.assertNull(model.getMaintenances());
      Assert.assertNotNull(model.getInstallations());
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("Incomplete section"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testRepeatOptionsSection() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"data.csv\"");
      _builder.newLine();
      _builder.newLine();
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
      Assert.assertNotNull(model.getOptions());
      Assert.assertNull(model.getMaintenances());
      Assert.assertNull(model.getInstallations());
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("Missing the following sections:\n\t- Installations\n\t- Maintenances"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testRepeatMaintenancesSection() {
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
      Assert.assertNotNull(model.getOptions());
      Assert.assertNotNull(model.getMaintenances());
      Assert.assertNull(model.getInstallations());
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("Missing the following sections:\n\t- Installations"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testRepeatInstallationsSection() {
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
      Assert.assertNotNull(model.getOptions());
      Assert.assertNotNull(model.getMaintenances());
      Assert.assertNotNull(model.getInstallations());
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("Unexpected input at end of file"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testAdditionalInput() {
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
      _builder.newLine();
      _builder.append("asd");
      _builder.newLine();
      final Program model = this._parseHelper.parse(_builder);
      final List<Issue> issues = this.validationTestHelper.validate(model);
      Assert.assertNotNull(model.getOptions());
      Assert.assertNotNull(model.getMaintenances());
      Assert.assertNotNull(model.getInstallations());
      final Function1<Issue, Boolean> _function = new Function1<Issue, Boolean>() {
        public Boolean apply(final Issue it) {
          return Boolean.valueOf(it.getMessage().contains("Unexpected input at end of file"));
        }
      };
      Assert.assertTrue(IterableExtensions.<Issue>exists(issues, _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
