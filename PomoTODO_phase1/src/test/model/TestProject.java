package model;

import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestProject {

    private List<Task> tasks;
    private Task taskOne;
    private Task taskTwo;
    private Task taskThree;
    private Task taskFour;
    private Task taskFive;
    private Project p;
    private Project pEmpty;


    @BeforeEach
    public void runBefore() {
        p = new Project("project");
        pEmpty = new Project("No task");
        taskOne = new Task("task 1");
        taskTwo = new Task("task 2");
        taskThree = new Task("task 3");
        taskFour = new Task("task 4");
        taskFive = new Task("task 5");
        tasks = new ArrayList<>();
        p.add(taskOne);
        p.add(taskTwo);
        p.add(taskThree);
        p.add(taskFour);
    }

    @Test
    public void testESExcp1() {
        try {
            Project pNull = new Project(null);
        }
        catch (EmptyStringException e) {
            System.out.println("EmptyStringException are expected to be threw");
        }
    }

    @Test
    public void testESExcp2() {
        try {
            Project pEmptyString = new Project("");
        }
        catch (EmptyStringException e) {
            System.out.println("EmptyStringException are expected to be threw");
        }
    }

    @Test
    public void testAddNAExcp() {
        try {
            p.add(null);
            fail("NullArgumentException should have been thrown");
        }
        catch (NullArgumentException e) {
            System.out.println("NullArgumentException are expected to be threw");
        }
    }

    @Test
    public void testRemoveNAExcp() {
        try {
            p.remove(null);
            fail("NullArgumentException should have been thrown");
        }
        catch (NullArgumentException e) {
            System.out.println("NullArgumentException are expected to be threw");
        }
    }

    @Test
    public void testContiansNAExcp() {
        try {
            Project pNull = new Project("No tasks");
            pNull.contains(null);
            fail("NullArgumentException should have been thrown");
        }
        catch (NullArgumentException e) {
            System.out.println("NullArgumentException are expected to be threw");
        }
    }

    @Test
    public void testDescription() {
        assertEquals("project",p.getDescription());
    }

    @Test
    public void testAddCaseOne() {
        assertEquals(4, p.getTasks().size());
        p.add(taskFive);
        assertEquals(5, p.getTasks().size());
    }

    @Test
    public void testNotAddCaseOne() {
        assertEquals(4, p.getTasks().size());
        p.add(taskOne);
        assertEquals(4, p.getTasks().size());
    }

    @Test
    public void testNotAddCaseTwo() {
        assertEquals(4, p.getTasks().size());
        p.add(taskThree);
        assertEquals(4, p.getTasks().size());
    }

    @Test
    public void testNotAddCaseThree() {
        assertEquals(4, p.getTasks().size());
        p.add(taskFour);
        assertEquals(4, p.getTasks().size());
    }

    @Test
    public void testRemoveCaseOne() {
        assertEquals(4, p.getTasks().size());
        p.remove(taskThree);
        assertEquals(3, p.getTasks().size());
    }

    @Test
    public void testNotRemoveCaseOne() {
        assertEquals(4,p.getTasks().size());
        p.remove(taskFive);
        assertEquals(4,p.getTasks().size());
    }

    @Test
    public void testGetNumberOfTasksCaseOne() {
        assertEquals(4,p.getNumberOfTasks());
    }

    @Test
    public void testGetNumberOfTasksCaseTwo() {
        p.add(taskFive);
        assertEquals(5,p.getNumberOfTasks());
    }

    @Test
    public void testGetNumberOfTasksCaseThree() {
        p.remove(taskTwo);
        assertEquals(3,p.getNumberOfTasks());
    }

    @Test
    public void testGetProgressCaseOne() {
        taskOne.setStatus(Status.TODO);
        taskTwo.setStatus(Status.IN_PROGRESS);
        taskThree.setStatus(Status.IN_PROGRESS);
        taskFour.setStatus(Status.UP_NEXT);
        assertEquals(0,p.getProgress());
    }

    @Test
    public void testGetProgressCaseTwo() {
        taskOne.setStatus(Status.DONE);
        taskTwo.setStatus(Status.IN_PROGRESS);
        taskThree.setStatus(Status.IN_PROGRESS);
        taskFour.setStatus(Status.UP_NEXT);
        assertEquals(25,p.getProgress());
    }

    @Test
    public void testGetProgressCaseThree() {
        taskOne.setStatus(Status.DONE);
        taskTwo.setStatus(Status.DONE);
        taskThree.setStatus(Status.DONE);
        taskFour.setStatus(Status.DONE);
        assertEquals(100,p.getProgress());
    }

    @Test
    public void testIsCompletedCaseOne() {
        taskOne.setStatus(Status.TODO);
        taskTwo.setStatus(Status.IN_PROGRESS);
        taskThree.setStatus(Status.IN_PROGRESS);
        taskFour.setStatus(Status.UP_NEXT);
        assertFalse(p.isCompleted());
    }

    @Test
    public void testIsCompletedCaseTwo() {
        taskOne.setStatus(Status.DONE);
        taskTwo.setStatus(Status.IN_PROGRESS);
        taskThree.setStatus(Status.IN_PROGRESS);
        taskFour.setStatus(Status.UP_NEXT);
        assertFalse(p.isCompleted());
    }

    @Test
    public void testIsCompletedCaseThree() {
        taskOne.setStatus(Status.DONE);
        taskTwo.setStatus(Status.DONE);
        taskThree.setStatus(Status.DONE);
        taskFour.setStatus(Status.UP_NEXT);
        assertFalse(p.isCompleted());
    }

    @Test
    public void testIsCompletedCaseSix() {
        taskOne.setStatus(Status.DONE);
        taskTwo.setStatus(Status.DONE);
        taskThree.setStatus(Status.TODO);
        taskFour.setStatus(Status.UP_NEXT);
        assertFalse(p.isCompleted());
    }
    @Test
    public void testEmpty() {
        assertEquals(100,pEmpty.getProgress());
    }

    @Test
    public void testIsCompletedCaseFive() {
        assertFalse(pEmpty.isCompleted());
    }

    @Test
    public void testIsCompletedCaseFour() {
        taskOne.setStatus(Status.DONE);
        taskTwo.setStatus(Status.DONE);
        taskThree.setStatus(Status.DONE);
        taskFour.setStatus(Status.DONE);
        assertTrue(p.isCompleted());
    }

    @Test
    public void testContians() {
        assertTrue(p.contains(taskTwo));
    }

    @Test
    public void testNotContians() {
        assertFalse(p.contains(taskFive));
    }
}