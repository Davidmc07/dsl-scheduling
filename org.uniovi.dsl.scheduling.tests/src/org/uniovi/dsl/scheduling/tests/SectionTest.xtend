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
class SectionTest {

    @Inject extension ParseHelper<Program>
    
    @Inject
    ValidationTestHelper validationTestHelper;

	// TEST ALL SECTIONS
	
    @Test
    def void testIncludeAllSections() {
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
        ''')
        validationTestHelper.assertNoErrors(model)
        Assert.assertNotNull(model)
        Assert.assertNotNull(model.getOptions())
        Assert.assertNotNull(model.getMaintenances())
        Assert.assertNotNull(model.getInstallations())
    }
    
    // TEST SECTION ORDER
    
    @Test
    def void testChangeSectionOrder() {
        val model = parse('''
			[Installations]
			Installation i1
				Name: "i1"
				Capacity: 10

			[Options]
			Input: "data.csv"

			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: 5
				Periods: 1
				Installations: i1
        ''')
        validationTestHelper.assertNoErrors(model)
        Assert.assertNotNull(model)
        Assert.assertNotNull(model.getOptions())
        Assert.assertNotNull(model.getMaintenances())
        Assert.assertNotNull(model.getInstallations())
    }
    
    // TEST EMPTY SECTIONS
    
    @Test
    def void testEmptySections() {
        val model = parse('''
			[Installations]

			[Options]

			[Maintenances]
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertNull(model.getOptions())
        Assert.assertNull(model.getInstallations())
        Assert.assertNull(model.getMaintenances())
        Assert.assertTrue(issues.exists[message.contains("Incomplete section")])
    }
    
    // TEST REPEATED SECTION
    
    @Test
    def void testThreeEqualSections() {
        val model = parse('''
			[Options]
			Input: "data.csv"

			[Options]
			Input: "data.csv"

			[Options]
			Input: "data.csv"
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertNotNull(model.getOptions())
        Assert.assertNull(model.getInstallations())
        Assert.assertNull(model.getMaintenances())
        Assert.assertTrue(issues.exists[message.contains("Missing the following sections:\n\t- Installations\n\t- Maintenances")])
    }
    
    // TEST ONLY ONE SECTION
    
    @Test
    def void testOnlyOptionsSection() {
        val model = parse('''
			[Options]
			Input: "data.csv"
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertNotNull(model.getOptions())
        Assert.assertNull(model.getInstallations())
        Assert.assertNull(model.getMaintenances())
        Assert.assertTrue(issues.exists[message.contains("Missing the following sections:\n\t- Installations\n\t- Maintenances")])
    }
    
    @Test
    def void testOnlyMaintenancesSection() {
        val model = parse('''
			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: 5
				Periods: 1
				Installations: i1
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertNull(model.getOptions())
        Assert.assertNull(model.getInstallations())
        Assert.assertNotNull(model.getMaintenances())
        Assert.assertTrue(issues.exists[message.contains("Missing the following sections:\n\t- Options\n\t- Installations")])
    }
    
    @Test
    def void testOnlyInstallationsSection() {
        val model = parse('''
			[Installations]
			Installation i1
				Name: "i1"
				Capacity: 10
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertNull(model.getOptions())
        Assert.assertNotNull(model.getInstallations())
        Assert.assertNull(model.getMaintenances())
        Assert.assertTrue(issues.exists[message.contains("Missing the following sections:\n\t- Options\n\t- Maintenances")])
    }
    
    // TEST MISSING ONE SECTION
    
    @Test
    def void testMissingOptionsSection() {
        val model = parse('''
			[Installations]
			Installation i1
				Name: "i1"
				Capacity: 10

			[Options]
			Input: "data.csv"
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertNotNull(model.getOptions())
        Assert.assertNotNull(model.getInstallations())
        Assert.assertNull(model.getMaintenances())
        Assert.assertTrue(issues.exists[message.contains("Missing the following sections:\n\t- Maintenances")])
    }
    
    @Test
    def void testMissingInstallationsSection() {
        val model = parse('''
			[Options]
			Input: "data.csv"

			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: 5
				Periods: 1
				Installations: i1
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertNotNull(model.getOptions())
        Assert.assertNull(model.getInstallations())
        Assert.assertNotNull(model.getMaintenances())
        Assert.assertTrue(issues.exists[message.contains("Missing the following sections:\n\t- Installations")])
    }
    
    // TEST ONE EMPTY SECTION
    
    @Test
    def void testEmptyInstallationsSection() {
        val model = parse('''
			[Installations]

			[Options]
			Input: "data.csv"

			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: 5
				Periods: 1
				Installations: i1
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertNotNull(model.getOptions())
        Assert.assertNotNull(model.getMaintenances())
        Assert.assertNull(model.getInstallations())
        Assert.assertTrue(issues.exists[message.contains("Incomplete section")])
    }
    
    @Test
    def void testEmptyOptionsSection() {
        val model = parse('''
			[Installations]
			Installation i1
				Name: "i1"
				Capacity: 10

			[Options]

			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: 5
				Periods: 1
				Installations: i1
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertNull(model.getOptions())
        Assert.assertNotNull(model.getMaintenances())
        Assert.assertNotNull(model.getInstallations())
        Assert.assertTrue(issues.exists[message.contains("Incomplete section")])
    }
    
    @Test
    def void testEmptyMaintenancesSection() {
        val model = parse('''
			[Installations]
			Installation i1
				Name: "i1"
				Capacity: 10

			[Options]
			Input: "data.csv"

			[Maintenances]
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertNotNull(model.getOptions())
        Assert.assertNull(model.getMaintenances())
        Assert.assertNotNull(model.getInstallations())
        Assert.assertTrue(issues.exists[message.contains("Incomplete section")])
    }
    
    // TEST REPEATED SECTION
    
    @Test
    def void testRepeatOptionsSection() {
        val model = parse('''
			[Options]
			Input: "data.csv"

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
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertNotNull(model.getOptions())
        Assert.assertNull(model.getMaintenances())
        Assert.assertNull(model.getInstallations())
        Assert.assertTrue(issues.exists[message.contains("Missing the following sections:\n\t- Installations\n\t- Maintenances")])
    }
    
    @Test
    def void testRepeatMaintenancesSection() {
        val model = parse('''
			[Options]
			Input: "data.csv"

			[Maintenances]
			Maintenance m1
				Name: "m1"
				Interval: 5
				Periods: 1
				Installations: i1

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
        Assert.assertNotNull(model.getOptions())
        Assert.assertNotNull(model.getMaintenances())
        Assert.assertNull(model.getInstallations())
        Assert.assertTrue(issues.exists[message.contains("Missing the following sections:\n\t- Installations")])
    }
    
    @Test
    def void testRepeatInstallationsSection() {
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

			[Installations]
			Installation i1
				Name: "i1"
				Capacity: 10
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertNotNull(model.getOptions())
        Assert.assertNotNull(model.getMaintenances())
        Assert.assertNotNull(model.getInstallations())
        Assert.assertTrue(issues.exists[message.contains("Unexpected input at end of file")])
    }
    
    @Test
    def void testAdditionalInput() {
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

			asd
        ''')
        val issues = validationTestHelper.validate(model)
        Assert.assertNotNull(model.getOptions())
        Assert.assertNotNull(model.getMaintenances())
        Assert.assertNotNull(model.getInstallations())
        Assert.assertTrue(issues.exists[message.contains("Unexpected input at end of file")])
    }
}