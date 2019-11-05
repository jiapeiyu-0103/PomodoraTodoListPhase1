package model;

import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestTask {

    private Task taskOne;
    private Set<Tag> tags;
    private DueDate dueDate;
    private Priority priority;
    private Calendar rightNow;
    private Tag tag1;
    private Tag tag2;



    @BeforeEach
    public void runBefore() {
        taskOne = new Task("Task One");
        rightNow = Calendar.getInstance();
        dueDate = new DueDate(rightNow.getTime());
        taskOne.addTag("math 303");
        taskOne.addTag("cpsc 210");
        taskOne.addTag("cpsc 110");
        taskOne.addTag("stat 302");
        Priority p4 = new Priority(4);
        Priority p1 = new Priority(1);
        taskOne.setPriority(p4);
        taskOne.setDescription("No description");
        taskOne.setStatus(Status.TODO);
    }

    @Test
    public void testESExcp1() {
        try {
            Task tNull = new Task(null);
        }
        catch (EmptyStringException e) {
            System.out.println("EmptyStringException are expected to be threw");
        }
    }

    @Test
    public void testESExcp2() {
        try {
            Task tEmptyString = new Task("");
        }
        catch (EmptyStringException e) {
            System.out.println("EmptyStringException are expected to be threw");
        }
    }

    @Test
    public void testAddNameESExcp1() {
        try {
            taskOne.addTag(null);
            fail("EmptyStringException should have been thrown");
        }
        catch (EmptyStringException e) {
            System.out.println("EmptyStringException are expected to be threw");
        }
    }

    @Test
    public void testAddNameESExcp2() {
        try {
            taskOne.addTag("");
            fail("EmptyStringException should have been thrown");
        }
        catch (EmptyStringException e) {
            System.out.println("EmptyStringException are expected to be threw");
        }
    }

    @Test
    public void testRemoveTagESExcp1() {
        try {
            taskOne.removeTag(null);
            fail("EmptyStringException should have been thrown");
        }
        catch (EmptyStringException e) {
            System.out.println("EmptyStringException are expected to be threw");
        }
    }

    @Test
    public void testRemoveTagESExcp2() {
        try {
            taskOne.removeTag("");
            fail("EmptyStringException should have been thrown");
        }
        catch (EmptyStringException e) {
            System.out.println("EmptyStringException are expected to be threw");
        }
    }

    @Test
    public void testSetPriorityNAExcp() {
        try {
            taskOne.setPriority(null);
            fail("NullArgumentException should have been thrown");
        }
        catch (NullArgumentException e) {
            System.out.println("NullArgumentException are expected to be threw");
        }
    }

    @Test
    public void testSetStatusNAExcp() {
        try {
            taskOne.setStatus(null);
            fail("NullArgumentException should have been thrown");
        }
        catch (NullArgumentException e) {
            System.out.println("NullArgumentException are expected to be threw");
        }
    }

    @Test
    public void testSetDescriptionESExcp1() {
        try {
            taskOne.setDescription(null);
            fail("EmptyStringException should have been thrown");
        }
        catch (EmptyStringException e) {
            System.out.println("EmptyStringException are expected to be threw");
        }
    }

    @Test
    public void testSetDescriptionESExcp2() {
        try {
            taskOne.setDescription("");
            fail("EmptyStringException should have been thrown");
        }
        catch (EmptyStringException e) {
            System.out.println("EmptyStringException are expected to be threw");
        }
    }

    @Test
    public void testAddTag() {
        taskOne.addTag("cpsc 320");
        assertEquals(5,taskOne.getTags().size());
    }

    @Test
    public void testNotAddTagCaseOne() {
        taskOne.addTag("cpsc 210");
        assertEquals(4,taskOne.getTags().size());
    }

    @Test
    public void testNotAddTagCaseTwo() {
        taskOne.addTag("math 303");
        assertEquals(4,taskOne.getTags().size());
    }

    @Test
    public void testNotAddTagCaseThree() {
        taskOne.addTag("stat 302");
        assertEquals(4,taskOne.getTags().size());
    }

    @Test
    public void testRemoveTagCaseOne() {
        taskOne.removeTag("cpsc 210");
        assertEquals(3,taskOne.getTags().size());
    }


    @Test
    public void testNotRemoveTag() {
        taskOne.removeTag("cpsc 320");
        assertEquals(4,taskOne.getTags().size());

    }

    @Test
    public void testContainsTag() {
        assertTrue(taskOne.containsTag("cpsc 210"));
    }

    @Test
    public void testNotContainsTag() {
        assertFalse(taskOne.containsTag("stat 305"));
    }

    @Test
    public void testToString() {
        taskOne.setDueDate(dueDate);
        assertEquals("Description: "+taskOne.getDescription()+"\n"+
                "Due date: "+taskOne.getDueDate().toString()+"\n"+
                "Status: "+taskOne.getStatus()+"\n" + "Priority: "+taskOne.getPriority()+"\n"+
                "Tags: "+taskOne.getTags(),taskOne.toString());
    }

    @Test
    public void testNoToString() {
        taskOne.setDueDate(null);
        assertEquals("Description: "+taskOne.getDescription()+"\n"+
                "Due date: "+"\n"+
                "Status: "+taskOne.getStatus()+"\n" + "Priority: "+taskOne.getPriority()+"\n"+
                "Tags: "+taskOne.getTags(),taskOne.toString());
    }

}