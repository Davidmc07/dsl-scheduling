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
class InstallationsTest {

    @Inject extension ParseHelper<Program>
    
    @Inject
    ValidationTestHelper validationTestHelper;
	
    @Test
    def void testNoRequiredAttributes() {
        val model = parse('''
			[Options]
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
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertNotNull(model)
        Assert.assertNotNull(model.getOptions())
        Assert.assertNotNull(model.getMaintenances())
        Assert.assertNotNull(model.getInstallations())
        Assert.assertTrue(issues.exists[message.contains("Invalid installation definition")])
    }
    
    @Test
    def void testRepeatedInstallation() {
        val model = parse('''
			[Options]
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
			Installation i1
				Name: "i1"
				Capacity: 10
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertTrue(issues.exists[message.contains("Installation ID already defined")])
    }
    
    @Test
    def void testMultipleInstallations() {
        val model = parse('''
			[Options]
			Input: "data.csv"

			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: 5
				Periods: 1
				Installations: i1, i2
			Maintenance m2
				Name: "m2"
				Interval: 5
				Periods: 1
				Installations: i1, i2

			[Installations]
			Installation i1
				Name: "i1"
				Capacity: 10
			Installation i2
				Name: "i2"
				Capacity: 10
        ''')
        validationTestHelper.assertNoErrors(model)
        Assert.assertNotNull(model)
        Assert.assertNotNull(model.getOptions())
        Assert.assertNotNull(model.getMaintenances())
        Assert.assertNotNull(model.getInstallations())
    }
    
    @Test
    def void testNegativeCapacity() {
        val model = parse('''
			[Options]
			Input: "data.csv"

			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: 5
				Periods: 1
				Installations: i1
				Includes: m2

			[Installations]
			Installation i1
				Name: "i1"
				Capacity: -10
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertTrue(issues.exists[message.contains("extraneous input '-' expecting RULE_INT")])
    }
}