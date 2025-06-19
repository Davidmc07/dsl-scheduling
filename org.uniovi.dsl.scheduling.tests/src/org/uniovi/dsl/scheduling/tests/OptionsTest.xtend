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
import org.uniovi.dsl.scheduling.scheduling.Config

@RunWith(XtextRunner)
@InjectWith(SchedulingInjectorProvider)
class OptionsTest {

    @Inject extension ParseHelper<Program>
    
    @Inject
    ValidationTestHelper validationTestHelper;
	
    @Test
    def void testNoInputFile() {
        val model = parse('''
			[Options]
			Number_of_periods: 5

			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: 5
				Periods: 1
				Installations: i1

			[Installations]
			Installation i1
				Name: "i1"
				Capacity: 10
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertTrue(issues.exists[message.contains("An input file must be specified")])
    }
    
    @Test
    def void testRepeatedParameter() {
        val model = parse('''
			[Options]
			Input: "data.csv"
			Input: "data.csv"

			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: 5
				Periods: 1
				Installations: i1

			[Installations]
			Installation i1
				Name: "i1"
				Capacity: 10
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertTrue(issues.exists[message.contains("no viable alternative at input 'Input:'")])
    }
    
    @Test
    def void testAllParameters() {
        val model = parse('''
			[Options]
			Input: "data.csv"
			Number_of_periods: 100
			Days_per_period: 1
			Extra_hours: 10
			Iterations: 10
			Random_state: 42
			Prob_before_restriction: 0.8
			Rate_of_change: 9.0
			Start_date: 1/2/2025

			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: 5
				Periods: 1
				Installations: i1

			[Installations]
			Installation i1
				Name: "i1"
				Capacity: 10
        ''')
        validationTestHelper.assertNoErrors(model)
        Assert.assertNotNull(model)
        Assert.assertNotNull(model.getOptions())
        Assert.assertNotNull(model.getMaintenances())
        Assert.assertNotNull(model.getInstallations())
    }
    
    @Test
    def void testInvalidParameterValue() {
        val model = parse('''
			[Options]
			Input: "data.csv"
			Number_of_periods: -100

			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: 5
				Periods: 1
				Installations: i1

			[Installations]
			Installation i1
				Name: "i1"
				Capacity: 10
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertTrue(issues.exists[message.contains("extraneous input '-' expecting RULE_INT")])
    }
}