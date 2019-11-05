package parser;

import model.DueDate;
import model.Status;
import model.Tag;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.Parser;
import parsers.TagParser;
import parsers.exceptions.ParsingException;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class TestTagParser {

    Task task;
    Parser parser;
    Calendar rightNow;
    DueDate due;


    @BeforeEach
    void runBefore() {
        task = new Task("task description");
        parser = new TagParser();
        rightNow = Calendar.getInstance();
        due = new DueDate(rightNow.getTime());
    }

    @Test
    void testParseWithNoDeliminator() {
        try {
            parser.parse("task description", task);
            assertEquals("task description",task.getDescription());
            fail("ParsingException should have been thrown");
        } catch (ParsingException e) {
            System.out.println("ParsingException are expected to be threw");
        }
    }

    @Test
    void testParsingWithOneTag() {
        try {
            parser.parse("task description ## tAg1", task);
            assertEquals("task description ", task.getDescription());
            assertNull(task.getDueDate());
            assertEquals(Status.TODO,task.getStatus());
            assertFalse(task.getPriority().isUrgent());
            assertFalse(task.getPriority().isImportant());
            System.out.println(task.toString());
        } catch (ParsingException e) {
            fail();
        }
    }

    @Test
    void testTmrWithEverythingElse() {
        try {
            parser.parse("Some description ## tag1;tomorrow;up next;tomorrow;in progress", task);
            assertEquals("Some description ", task.getDescription());
            assertEquals(Status.UP_NEXT,task.getStatus());
            assertFalse(task.getPriority().isUrgent());
            assertFalse(task.getPriority().isImportant());
        } catch (ParsingException e) {
            fail();
        }
    }

    @Test
    void testTODO() {
        try {
            parser.parse("Some description ## tag1; to do ;up next;tomorrow;in progress",task);
            assertEquals("Some description ", task.getDescription());
            assertEquals(Status.TODO,task.getStatus());
            assertFalse(task.getPriority().isUrgent());
            assertFalse(task.getPriority().isImportant());
            assertTrue(task.containsTag("tag1"));
            assertTrue(task.containsTag("up next"));
            assertTrue(task.containsTag("in progress"));
        } catch (ParsingException e) {
            fail();
        }
    }

    @Test
    void testDONE() {
        try {
            parser.parse("Some description ## tag1; done;up next;tomorrow;in progress",task);
            assertEquals("Some description ", task.getDescription());
            assertEquals(Status.DONE,task.getStatus());
            assertFalse(task.getPriority().isUrgent());
            assertFalse(task.getPriority().isImportant());
        } catch (ParsingException e) {
            fail();
        }
    }


    @Test
    void testEmptyTag() {
        try {
            parser.parse("Some description ##",task);
            assertEquals("Some description ", task.getDescription());
            assertEquals(0,task.getTags().size());
            System.out.println(task.toString());
        } catch (ParsingException e) {
            fail();
        }
    }

    @Test
    void testParsingWithMultipleTag() {
        try {
            parser.parse("       Task description ## tag1; tag2; tag3",task);
            assertEquals("       Task description ", task.getDescription());
            assertEquals(Status.TODO,task.getStatus());
            assertFalse(task.getPriority().isUrgent());
            assertFalse(task.getPriority().isImportant());
            System.out.println(task.toString());
            assertTrue(task.containsTag("tag1"));
            assertTrue(task.containsTag("tag2"));
            assertTrue(task.containsTag("tag3"));
            assertNull(task.getDueDate());
        } catch (ParsingException e) {
            fail();
        }
    }

    @Test
    void testRegularCaseWithMultipleTmr() {
        try {
            parser.parse("Some description ## tag1;tomorrow;tomorrow;urgent;in progress;important;",task);
            assertEquals("Some description ",task.getDescription());
            assertTrue(task.getPriority().isUrgent());
            assertTrue(task.getPriority().isImportant());
            assertEquals(Status.IN_PROGRESS,task.getStatus());
            assertTrue(task.containsTag("tag1"));
            System.out.println(task.toString());
        } catch (ParsingException e) {
            fail();
        }
    }

    @Test
    void testRegularCaseWithTmrToday() {
        try {
            parser.parse("Some description ## tag1;tomorrow;today; urgent;in progress;important;",task);
            assertEquals("Some description ",task.getDescription());
            assertTrue(task.getPriority().isUrgent());
            assertTrue(task.getPriority().isImportant());
            assertEquals(Status.IN_PROGRESS,task.getStatus());
            assertTrue(task.containsTag("tag1"));
            System.out.println(task.toString());
        } catch (ParsingException e) {
            fail();
        }
    }

    @Test
    void testSameTagButAllUpCaseSomeLowCase() {
        try {
            parser.parse("Some description ## tag1; tag1; tag1; tag1",task);
            assertEquals("Some description ",task.getDescription());
            assertEquals(Status.TODO,task.getStatus());
            assertFalse(task.getPriority().isUrgent());
            assertFalse(task.getPriority().isImportant());
            assertNull(task.getDueDate());
            System.out.println(task.toString());
            assertTrue(task.containsTag("tag1"));
        } catch (ParsingException e) {
            fail();
        }
    }

    @Test
    void testNolistOfInf() {
        try {
            parser.parse("Some description ##    ;  ;",task);
            assertEquals("Some description ",task.getDescription());
            assertEquals(Status.TODO,task.getStatus());
            assertFalse(task.getPriority().isUrgent());
            assertFalse(task.getPriority().isImportant());
            assertNull(task.getDueDate());
            System.out.println(task.toString());
        } catch (ParsingException e) {
            fail();
        }
    }

    @Test
    void testOnlyTmrCase() {
        try {
            parser.parse("Some description ## tomorrow; tag1",task);
            assertEquals("Some description ",task.getDescription());
            assertEquals(Status.TODO,task.getStatus());
            assertFalse(task.getPriority().isUrgent());
            assertFalse(task.getPriority().isImportant());
            System.out.println(task.toString());
            assertTrue(task.containsTag("tag1"));
        } catch (ParsingException e) {
            fail();
        }
    }

    @Test
    void testParse27() {
        try {
            Task task2 = new Task("a");
            parser.parse("Some description ## to do;Up nexT;donE;in PROGRESS;tOday", task);
            parser.parse("Some description ## tag1;up next;tomorrow;;today ;   todAy;in progress",task2);
            assertTrue(task2.containsTag("today"));
            System.out.println(task.toString());
            System.out.println(task2.toString());
        } catch (ParsingException e) {
        }
        assertSame(task.getStatus(),Status.TODO);
        assertEquals(task.getDescription(),parser.getDescription());
    }

    @Test
    void testEmptySpace() {
        try {
            parser.parse("Some description ## tag1     ; tag 1;     tag1;    ;   tag 2",task);
            assertEquals("Some description ",task.getDescription());
            assertEquals(Status.TODO,task.getStatus());
            assertFalse(task.getPriority().isUrgent());
            assertFalse(task.getPriority().isImportant());
            assertNull(task.getDueDate());
            assertTrue(task.containsTag("tag1"));
            assertTrue(task.containsTag("tag 2"));
            assertTrue(task.containsTag("tag 1"));
            System.out.println(task.toString());
        } catch (ParsingException e) {
            fail();
        }
    }

    @Test
    void testSameTagButSomeUpCaseSomeLowCase() {
        try {
            parser.parse("Some description ## tAg1; tag1; TAG1; tag1",task);
            assertEquals("Some description ",task.getDescription());
            assertEquals(Status.TODO,task.getStatus());
            assertFalse(task.getPriority().isUrgent());
            assertFalse(task.getPriority().isImportant());
            assertNull(task.getDueDate());
            System.out.println(task.toString());
            assertTrue(task.containsTag("tAg1"));
        } catch (ParsingException e) {
            fail();
        }
    }

    @Test
    void testRegularCase() {
        try {
            parser.parse("Some description ## tag1;today;urgent;in progress;important",task);
            assertEquals("Some description ",task.getDescription());
            assertTrue(task.getPriority().isUrgent());
            assertTrue(task.getPriority().isImportant());
            assertEquals(Status.IN_PROGRESS,task.getStatus());
            assertTrue(task.containsTag("tag1"));
            System.out.println(task.toString());
        } catch (ParsingException e) {
            fail();
        }
    }



    @Test
    void testRegularCaseWithMoreDueDateAndStatus() {
        try {
            parser.parse("Some description ## tag1;today;up next;tomorrow;in progress",task);
            assertEquals("Some description ",task.getDescription());
            assertTrue(task.containsTag("tag1"));
            System.out.println(task.toString());
        } catch (ParsingException e) {
            fail();
        }
    }

    void testExcptiontwo(String str) throws ParsingException {
        if (str.equals("Good")) {
            throw new ParsingException();
        }
    }

    @Test
    void testThrowParsing() {
        try {
            testExcptiontwo("Good");
            fail();
        } catch (ParsingException e) {
            System.out.println("Sting goes wrong");
        }
    }

    @Test
    void testNotThrowParsing() {
        try {
            testExcptiontwo("How");
        } catch (ParsingException e) {
            fail();
        }
    }
}
