package core.test;

import core.api.IAdmin;
import core.api.IInstructor;
import core.api.IStudent;
import core.api.impl.Admin;
import core.api.impl.Student;
import core.api.impl.Instructor;

import org.junit.Before;
import org.junit.Test;
import core.api.impl.Admin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vincent on 23/2/2017.
 */
public class AdminTest {

    private IAdmin admin;

    @Before
    public void setup() {
        this.admin = new Admin();
    }

    @Test
    public void testCreateEmptyClass() {
    	// Class capacity must be positive and non-zero.
    	this.admin.createClass("ECS120", 2017, "Michael Harrison", 0);
    	assertFalse(this.admin.classExists("ECS120", 2017));
    }
    
    @Test
    public void testCreateNegativeCapacity() {
    	// Class capacity must be non-negative.
    	this.admin.createClass("ECS120", 2017, "Michael Harrison", -20);
    	assertFalse(this.admin.classExists("ECS120", 2017));
    }
    
    @Test
    public void testMakeClass2017() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testMakeClassPre2017() {
        this.admin.createClass("Test", 2016, "Instructor", 15);
        assertFalse(this.admin.classExists("Test", 2016));
    }
    
    @Test
    public void testInstructorClassLimit() {
    	// No more than two classes per instructor.
    	this.admin.createClass("ECS50", 2017, "Sean Davis", 60);
    	this.admin.createClass("ECS60", 2017, "Sean Davis", 60);
    	this.admin.createClass("ECS154a", 2017, "Sean Davis", 60);
    	assertFalse(this.admin.classExists("ECS154a", 2017));
    }
    
    @Test
    public void testOnlyOneInstructorPerClass() {
    	// No more than one instructor per a course.
    	this.admin.createClass("ECS50", 2017, "Matthew Butner", 40);
    	this.admin.createClass("ECS50", 2017, "Sean Davis", 40);
    	assertEquals("Mathew Butner", this.admin.getClassInstructor("ECS50", 2017));
    }
    
    @Test
    public void testChangeCapacity() {
    	// Tests the ability to change the capacity of a class.
    	this.admin.createClass("ECS177", 2017, "Kwan-Lui Ma", 20);
    	this.admin.changeCapacity("ECS177", 2017, 40);
    	assertEquals(40, this.admin.getClassCapacity("ECS177", 2017));
    }
}
