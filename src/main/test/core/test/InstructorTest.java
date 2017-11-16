package core.test;

import core.api.IAdmin;
import core.api.IInstructor;
import core.api.impl.Admin;
import core.api.impl.Instructor;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vincent on 23/2/2017.
 */
public class InstructorTest {

    private IAdmin admin;
    private IInstructor lecturer;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.admin.createClass("ECS40", 2017, "Berndt Hamann", 20);
        this.lecturer = new Instructor();
    }
    
    @Test
    public void testBadYearAddHW() {
    	// test to ensure cannot add homework to a past year
    	this.lecturer.addHomework("Sean Davis", "ECS30", 1997, "project9");
    	assertFalse(this.lecturer.homeworkExists("ECS30", 1997, "project9"));
    }
    
    @Test
    public void testAddHW() {
    	// test adding a homework assignment
    	this.lecturer.addHomework("Berndt Hamann", "ECS40", 2017, "program1");
    	assertTrue(this.lecturer.homeworkExists("ECS40", 2017, "program1"));
    }
    
    @Test
    public void testAddHWtoClassDNE() {
    	// should not be able to add homework to a class that doesn't exist
    	this.lecturer.addHomework("Sean Davis", "ECS60", 2017, "program2");
    	assertFalse(this.lecturer.homeworkExists("ECS60", 2017, "program2"));
    }
    
    @Test
    public void testAddHWtoInstructorDNE() {
    	// test if adding homework under instructor not assigned to class
    	this.lecturer.addHomework("Sean Davis", "ECS50", 2017, "project1.tar.gz");
    	assertFalse(this.lecturer.homeworkExists("ECS50", 2017, "project1.tar.gz"));
    }
}
