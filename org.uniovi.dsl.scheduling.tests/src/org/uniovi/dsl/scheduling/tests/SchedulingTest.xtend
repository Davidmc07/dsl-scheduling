package org.uniovi.dsl.scheduling.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.uniovi.dsl.scheduling.tests.SchedulingInjectorProvider
import org.uniovi.dsl.scheduling.scheduling.Program
import org.eclipse.xtext.testing.validation.ValidationTestHelper

@RunWith(XtextRunner)
@InjectWith(SchedulingInjectorProvider)
class SchedulingTest {

    @Inject extension ParseHelper<Program>
    
    @Inject
    ValidationTestHelper validationTestHelper;

    @Test
    def void testParsing() {
        val model = parse('''
            [Installations]
            Installation plant1
                Name: "Power Plant 1"
                Capacity: 1000
            
            Installation plant2
                Name: "Power Plant 2"
                Capacity: 1500
            
            [Maintenances]
            Maintenance maint1
                Name: "Routine Check"
                Interval: 24
                Periods: 2
                Priority: 1
                Installations: plant1, plant2
            
            [Options]
            Input: "schedule.csv"
            Random_state: 42
        ''')
        Assert.assertNotNull(model)
        validationTestHelper.assertNoErrors(model)
        Assert.assertEquals(2, model.getInstallations().getInstallations().size());
        Assert.assertEquals(1, model.getMaintenances().getMaints().size());
        Assert.assertNotNull(model.getOptions());
    }
}