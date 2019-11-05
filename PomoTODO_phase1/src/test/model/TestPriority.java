package model;

import model.exceptions.InvalidPriorityLevelException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPriority {
    private Priority p1;
    private Priority p2;
    private Priority p3;
    private Priority p4;
    private Priority p_default;

    @BeforeEach
    public void runBefore() {
        p1 = new Priority(1);
        p2 = new Priority(2);
        p3 = new Priority(3);
        p4 = new Priority(4);
        p_default = new Priority();
    }

    @Test
    public void testIPLExcp1() {
        try {
            Priority p5 = new Priority(8);
        }
        catch (InvalidPriorityLevelException e) {
            System.out.println("InvalidPriorityLevelException are expected to be threw");
        }

    }

    @Test
    public void testIPLExcp2() {
        try {
            Priority p5 = new Priority(0);
        }
        catch (InvalidPriorityLevelException e) {
            System.out.println("InvalidPriorityLevelException are expected to be threw");
        }

    }

    @Test
    public void testIPLExcp3() {
        try {
            Priority p5 = new Priority(-35);
        }
        catch (InvalidPriorityLevelException e) {
            System.out.println("InvalidPriorityLevelException are expected to be threw");
        }

    }

    @Test
    public void testDefaultOfPriority() {
        assertFalse(p_default.isImportant());
        assertFalse(p_default.isUrgent());

    }

    @Test
    public void testQuadrantOne() {
        assertTrue(p1.isImportant());
        assertTrue(p1.isUrgent());
    }

    @Test
    public void testQudrantTwo() {
        assertTrue(p2.isImportant());
        assertFalse(p2.isUrgent());
    }
    @Test
    public void testQudrantThree() {
        assertFalse(p3.isImportant());
        assertTrue(p3.isUrgent());
    }

    @Test
    public void testQudrantFour() {
        assertFalse(p4.isImportant());
        assertFalse(p4.isUrgent());
    }

    @Test
    public void testStringQuadrantOne() {
        p1.setImportant(true);
        p1.setUrgent(true);
        assertEquals("IMPORTANT & URGENT", p1.toString());
    }

    @Test
    public void testStringQuadrantTwo() {
        p2.setImportant(true);
        p2.setUrgent(false);
        assertEquals("IMPORTANT", p2.toString());
    }

    @Test
    public void testStringQuadrantThree() {
        p3.setImportant(false);
        p3.setUrgent(true);
        assertEquals("URGENT", p3.toString());
    }

    @Test
    public void testStringQuadrantFour() {
        p4.setImportant(false);
        p4.setUrgent(false);
        assertEquals("DEFAULT", p4.toString());
    }

    @Test
    public void testStringDef() {
        p_default.setImportant(false);
        p_default.setUrgent(false);
        assertEquals("DEFAULT", p_default.toString());
    }

    @Test
    public void testSetImportant() {
        p1.setImportant(true);
        assertTrue(p1.isImportant());
    }

    @Test
    public void testSetUrgent() {
        p1.setUrgent(true);
        assertTrue(p1.isUrgent());
    }

}
