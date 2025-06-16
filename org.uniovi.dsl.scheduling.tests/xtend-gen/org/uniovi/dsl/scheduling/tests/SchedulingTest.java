package org.uniovi.dsl.scheduling.tests;

import com.google.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.uniovi.dsl.scheduling.scheduling.Program;

@RunWith(XtextRunner.class)
@InjectWith(SchedulingInjectorProvider.class)
@SuppressWarnings("all")
public class SchedulingTest {
  @Inject
  @Extension
  private ParseHelper<Program> _parseHelper;

  @Inject
  private ValidationTestHelper validationTestHelper;

  @Test
  public void testParsing() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[Installations]");
      _builder.newLine();
      _builder.append("Installation plant1");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("Name: \"Power Plant 1\"");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("Capacity: 1000");
      _builder.newLine();
      _builder.newLine();
      _builder.append("Installation plant2");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("Name: \"Power Plant 2\"");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("Capacity: 1500");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Maintenances]");
      _builder.newLine();
      _builder.append("Maintenance maint1");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("Name: \"Routine Check\"");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("Interval: 24");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("Periods: 2");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("Priority: 1");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("Installations: plant1, plant2");
      _builder.newLine();
      _builder.newLine();
      _builder.append("[Options]");
      _builder.newLine();
      _builder.append("Input: \"schedule.csv\"");
      _builder.newLine();
      _builder.append("Random_state: 42");
      _builder.newLine();
      final Program model = this._parseHelper.parse(_builder);
      Assert.assertNotNull(model);
      this.validationTestHelper.assertNoErrors(model);
      Assert.assertEquals(2, model.getInstallations().getInstallations().size());
      Assert.assertEquals(1, model.getMaintenances().getMaints().size());
      Assert.assertNotNull(model.getOptions());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
