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
class MaintenancesTest {

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

			[Installations]
			Installation i1
				Name: "i1"
				Capacity: 10
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertNotNull(model)
        Assert.assertNotNull(model.getOptions())
        Assert.assertNotNull(model.getMaintenances())
        Assert.assertNotNull(model.getInstallations())
        Assert.assertTrue(issues.exists[message.contains("Invalid maintenance definition")])
    }
    
    @Test
    def void testRepeatedMaintenance() {
        val model = parse('''
			[Options]
			Input: "data.csv"

			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: 5
				Periods: 1
				Installations: i1
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
        Assert.assertTrue(issues.exists[message.contains("Maintenance ID already defined")])
    }
    
    @Test
    def void testMultipleMaintenances() {
        val model = parse('''
			[Options]
			Input: "data.csv"

			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: 5
				Periods: 1
				Installations: i1
			Maintenance m2
				Name: "m2"
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
    def void testIncludesOthers() {
        val model = parse('''
			[Options]
			Input: "data.csv"

			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: 5
				Periods: 1
				Installations: i1
			Maintenance m2
				Name: "m2"
				Interval: 5
				Periods: 1
				Includes: m1, m3
				Installations: i1
			Maintenance m3
				Name: "m3"
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
    def void testIncludeItself() {
        val model = parse('''
			[Options]
			Input: "data.csv"

			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: 5
				Periods: 1
				Installations: i1
				Includes: m1

			[Installations]
			Installation i1
				Name: "i1"
				Capacity: 10
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertTrue(issues.exists[message.contains("A maintenance can not include itself")])
    }
    
    @Test
    def void testIncludeNotDefined() {
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
				Capacity: 10
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertTrue(issues.exists[message.contains("Maintenance not defined")])
    }
    
    @Test
    def void testNegativePeriods() {
        val model = parse('''
			[Options]
			Input: "data.csv"

			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: 5
				Periods: -1
				Installations: i1
				Includes: m2

			[Installations]
			Installation i1
				Name: "i1"
				Capacity: 10
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertTrue(issues.exists[message.contains("extraneous input '-' expecting RULE_INT")])
    }
    
    @Test
    def void testNegativeInterval() {
        val model = parse('''
			[Options]
			Input: "data.csv"

			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: -5
				Periods: 1
				Installations: i1
				Includes: m2

			[Installations]
			Installation i1
				Name: "i1"
				Capacity: 10
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertTrue(issues.exists[message.contains("extraneous input '-' expecting RULE_INT")])
    }
    
    @Test
    def void testInstallationNotDefined() {
        val model = parse('''
			[Options]
			Input: "data.csv"

			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: -5
				Periods: 1
				Installations: i2
				Includes: m2

			[Installations]
			Installation i1
				Name: "i1"
				Capacity: 10
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertTrue(issues.exists[message.contains("Installation not defined")])
    }
    
}