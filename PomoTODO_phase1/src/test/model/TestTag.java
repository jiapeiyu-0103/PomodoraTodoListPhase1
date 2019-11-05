package model;

import model.exceptions.EmptyStringException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTag {

   private Tag tag;

    @BeforeEach
    public void runBefore() {
        tag = new Tag("Gina");
    }

    @Test
    public void testESExcp1() {
        try {
            Tag tNull = new Tag(null);
        }
        catch (EmptyStringException e) {
            System.out.println("EmptyStringException are expected to be threw");
        }
    }

    @Test
    public void testESExcp2() {
        try {
            Tag tEmptyString = new Tag("");
        }
        catch (EmptyStringException e) {
            System.out.println("EmptyStringException are expected to be threw");
        }
    }

    @Test
    public void testToString() {
        assertEquals("#Gina",tag.toString());
    }

    @Test
    public void testGetName() {
        assertEquals("Gina", tag.getName());
    }

}
