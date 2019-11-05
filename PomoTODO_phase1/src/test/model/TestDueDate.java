package model;

import model.exceptions.InvalidTimeException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class TestDueDate {

    private DueDate dueDate;
    private DueDate dueDate_other;
    private Calendar rightNow;

    @BeforeEach
    public void runBefore() {
        rightNow = Calendar.getInstance();
        dueDate = new DueDate(rightNow.getTime());
        dueDate_other = new DueDate();
    }

    @Test
    public void testDueDateNAExcp() {
        try {
            DueDate duedate = new DueDate(null);
        }
        catch (NullArgumentException e) {
            System.out.println("NullArgumentException are expected to be threw");
        }
    }

    @Test
    public void testSetDueDateNAExcp() {
        try {
            dueDate.setDueDate(null);
            fail("NullArgumentException should have been thrown");
        }
        catch (NullArgumentException e) {
            System.out.println("NullArgumentException are expected to be threw");
        }
    }

    @Test
    public void testSetDueDate() {
        try {
            dueDate.setDueDate(rightNow.getTime());
        }
        catch (NullArgumentException e) {
            fail("NullArgumentException are not expected to be threw");
        }
    }

    @Test
    public void testSetDueTimeITExcp1() {
        try {
            dueDate.setDueTime(-36,30);
            fail("InvalidTimeException should have been thrown");
        }
        catch (InvalidTimeException e) {
            System.out.println("InvalidTimeException are expected to be threw");
        }
    }

    @Test
    public void testSetDueTimeITExcp2() {
        try {
            dueDate.setDueTime(30,30);
            fail("InvalidTimeException should have been thrown");
        }
        catch (InvalidTimeException e) {
            System.out.println("InvalidTimeException are expected to be threw");
        }
    }

    @Test
    public void testSetDueTimeITExcp3() {
        try {
            dueDate.setDueTime(10,-27);
            fail("InvalidTimeException should have been thrown");
        }
        catch (InvalidTimeException e) {
            System.out.println("InvalidTimeException are expected to be threw");
        }
    }

    @Test
    public void testSetDueTimeITExcp4() {
        try {
            dueDate.setDueTime(10,100);
            fail("InvalidTimeException should have been thrown");
        }
        catch (InvalidTimeException e) {
            System.out.println("InvalidTimeException are expected to be threw");
        }
    }

    @Test
    public void testSetDueTimeITExcp5() {
        try {
            dueDate.setDueTime(-10,-40);
            fail("InvalidTimeException should have been thrown");
        }
        catch (InvalidTimeException e) {
            System.out.println("InvalidTimeException are expected to be threw");
        }
    }

    @Test
    public void testSetDueTimeITExcp6() {
        try {
            dueDate.setDueTime(40,-10);
            fail("InvalidTimeException should have been thrown");
        }
        catch (InvalidTimeException e) {
            System.out.println("InvalidTimeException are expected to be threw");
        }
    }

    @Test
    public void testSetDueTimeITExcp7() {
        try {
            dueDate.setDueTime(120,320);
            fail("InvalidTimeException should have been thrown");
        }
        catch (InvalidTimeException e) {
            System.out.println("InvalidTimeException are expected to be threw");
        }
    }

    @Test
    public void testSetDueTimeITExcp8() {
        try {
            dueDate.setDueTime(30,-39);
            fail("InvalidTimeException should have been thrown");
        }
        catch (InvalidTimeException e) {
            System.out.println("InvalidTimeException are expected to be threw");
        }
    }

    @Test
    public void testConstructor() {
        rightNow.set(Calendar.HOUR,11);
        rightNow.set(Calendar.MINUTE,59);
        rightNow.set(Calendar.AM_PM, Calendar.PM);
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int minutes = rightNow.get(Calendar.MINUTE);
        dueDate.setDueDate(rightNow.getTime());
        assertEquals(hour, dueDate.getDate().getHours());
        assertEquals(minutes, dueDate.getDate().getMinutes());
    }

    @Test
    public void testPostponeOneWeek() {
        dueDate.postponeOneWeek();
        int next_day = Calendar.getInstance().get(Calendar.DATE)+7;
        int postpone_day = dueDate.getDate().getDate();
        assertEquals(next_day,postpone_day);
    }

    @Test
    public void testNotPostponeOneWeekCaseOne() {
        dueDate.postponeOneWeek();
        int next_day = Calendar.getInstance().get(Calendar.DATE)+5;
        int postpone_day = dueDate.getDate().getDate();
        assertFalse(next_day==postpone_day);
    }

    @Test
    public void testNotPostponeOneWeekCaseTwo() {
        dueDate.postponeOneWeek();
        int next_day = Calendar.getInstance().get(Calendar.DATE)+10;
        int postpone_day = dueDate.getDate().getDate();
        assertFalse(next_day==postpone_day);
    }

    @Test
    public void testPostponeOneDay() {
        dueDate.postponeOneDay();
        int next_day = Calendar.getInstance().get(Calendar.DATE)+1;
        int postpone_day = dueDate.getDate().getDate();
        assertEquals(next_day,postpone_day);
    }

    @Test
    public void testNotPostponeOneDayCaseOne() {
        dueDate.postponeOneDay();
        int previous_day = Calendar.getInstance().get(Calendar.DATE)-12;
        int postpone_day = dueDate.getDate().getDate();
        assertFalse(previous_day==postpone_day);
    }

    @Test
    public void testNotPostponeOneDayCaseTwo() {
        dueDate.postponeOneDay();
        int next_fewday = Calendar.getInstance().get(Calendar.DATE)+5;
        int postpone_day = dueDate.getDate().getDate();
        assertFalse(next_fewday==postpone_day);
    }

    @Test
    public void testIsOverDueCaseOne() {
        rightNow.add(Calendar.DATE,-2);
        dueDate.setDueDate(rightNow.getTime());
        assertTrue(dueDate.isOverdue());
    }

    @Test
    public void testIsOverDueCaseTwo() {
        rightNow.add(Calendar.HOUR,-2);
        dueDate.setDueDate(rightNow.getTime());
        assertTrue(dueDate.isOverdue());
    }

    @Test
    public void testIsOverDueCaseThree() {
        rightNow.add(Calendar.MONTH,-2);
        dueDate.setDueDate(rightNow.getTime());
        assertTrue(dueDate.isOverdue());
    }

    @Test
    public void testIsOverDueCaseFour() {
        rightNow.add(Calendar.MINUTE,-2);
        dueDate.setDueDate(rightNow.getTime());
        assertTrue(dueDate.isOverdue());
    }

    @Test
    public void testIsOverDueCaseFive() {
        rightNow.add(Calendar.YEAR,-2);
        dueDate.setDueDate(rightNow.getTime());
        assertTrue(dueDate.isOverdue());
    }

    @Test
    public void testIsNotOverDueCaseOne() {
        rightNow.add(Calendar.DATE,5);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isOverdue());
    }

    @Test
    public void testIsNotOverDueCaseTwo() {
        rightNow.add(Calendar.HOUR,5);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isOverdue());
    }

    @Test
    public void testIsNotOverDueCaseThree() {
        rightNow.add(Calendar.MINUTE,30);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isOverdue());
    }

    @Test
    public void testIsNotOverDueCaseFour() {
        rightNow.add(Calendar.YEAR,5);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isOverdue());
    }

    @Test
    public void testIsNotOverDueCaseFive() {
        rightNow.add(Calendar.MONTH,5);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isOverdue());
    }

    @Test
    public void testIsDueTodayCaseOne() {
        assertTrue(dueDate.isDueToday());
    }

    @Test
    public void testIsDueTodayCaseTwo() {
        dueDate.setDueTime(10,45);
        dueDate.setDueDate(rightNow.getTime());
        assertTrue(dueDate.isDueToday());
    }

    @Test
    public void testIsDueTodayCaseThree() {
        rightNow.set(Calendar.HOUR_OF_DAY,23);
        rightNow.set(Calendar.MINUTE,59);
        dueDate.setDueDate(rightNow.getTime());
        assertTrue(dueDate.isDueToday());
    }

    @Test
    public void testIsNotDueTodayCaseOne() {
        rightNow.add(Calendar.DATE,10);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueToday());
    }

    @Test
    public void testIsNotDueTodayCase4() {
        rightNow.add(Calendar.DATE,10);
        rightNow.add(Calendar.MONTH,3);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueToday());
    }

    @Test
    public void testIsNotDueTodayCaseTwo() {
        rightNow.add(Calendar.MONTH,2);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueToday());
    }

    @Test
    public void testIsNotDueTodayCaseThree() {
        rightNow.add(Calendar.YEAR,1);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueToday());
    }

    @Test
    public void testIsNotDueTodayCase5() {
        rightNow.add(Calendar.YEAR,1);
        rightNow.add(Calendar.DATE,6);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueToday());
    }

    @Test
    public void testIsNotDueTodayCase6() {
        rightNow.add(Calendar.YEAR,1);
        rightNow.add(Calendar.MONTH,4);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueToday());
    }

    @Test
    public void testIsNotDueTodayCase7() {
        rightNow.add(Calendar.YEAR,1);
        rightNow.add(Calendar.MONTH,4);
        rightNow.add(Calendar.DATE,6);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueToday());
    }

    @Test
    public void testIsDueTmrCaseOne() {
        rightNow.set(Calendar.DATE,Calendar.getInstance().get(Calendar.DATE) +1);
        dueDate.setDueTime(10,45);
        dueDate.setDueDate(rightNow.getTime());
        assertTrue(dueDate.isDueTomorrow());
    }

    @Test
    public void testIsDueTmrCaseTwo() {
        rightNow.set(Calendar.DATE,Calendar.getInstance().get(Calendar.DATE) +1);
        dueDate.setDueDate(rightNow.getTime());
        assertTrue(dueDate.isDueTomorrow());
    }


    @Test
    public void testIsNotDueTmrCaseOne() {
        rightNow.set(Calendar.DATE,Calendar.getInstance().get(Calendar.DATE) +1);
        rightNow.add(Calendar.YEAR,2);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueTomorrow());
    }

    @Test
    public void testIsNotDueTmrCaseTwo() {
        rightNow.set(Calendar.DATE,Calendar.getInstance().get(Calendar.DATE) +1);
        rightNow.add(Calendar.MONTH,2);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueTomorrow());
    }

    @Test
    public void testIsNotDueTmrCaseFive() {
        rightNow.set(Calendar.DATE,Calendar.getInstance().get(Calendar.DATE) +1);
        rightNow.add(Calendar.DATE,2);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueTomorrow());
    }

    @Test
    public void testIsNotDueTmrCase6() {
        rightNow.set(Calendar.DATE,Calendar.getInstance().get(Calendar.DATE) +1);
        rightNow.add(Calendar.DATE,2);
        rightNow.add(Calendar.MONTH,3);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueTomorrow());
    }

    @Test
    public void testIsNotDueTmrCase7() {
        rightNow.set(Calendar.DATE,Calendar.getInstance().get(Calendar.DATE) +1);
        rightNow.add(Calendar.DATE,2);
        rightNow.add(Calendar.YEAR,5);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueTomorrow());
    }

    @Test
    public void testIsNotDueTmrCase8() {
        rightNow.set(Calendar.DATE,Calendar.getInstance().get(Calendar.DATE) +1);
        rightNow.add(Calendar.MONTH,2);
        rightNow.add(Calendar.YEAR,2);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueTomorrow());
    }

    @Test
    public void testIsNotDueTmrCase9() {
        rightNow.set(Calendar.DATE,Calendar.getInstance().get(Calendar.DATE) +1);
        rightNow.add(Calendar.DATE,2);
        rightNow.add(Calendar.MONTH,2);
        rightNow.add(Calendar.YEAR,2);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueTomorrow());
    }

    @Test
    public void testIsDueWithinOneWeekFisrtDay() {
        rightNow.set(Calendar.DATE,Calendar.getInstance().get(Calendar.DATE));
        dueDate.setDueDate(rightNow.getTime());
        assertTrue(dueDate.isDueWithinAWeek());
    }

    @Test
    public void testIsDueWithinOneWeekFisrtDayTwo() {
        rightNow.set(Calendar.DATE,Calendar.getInstance().get(Calendar.DATE));
        dueDate.setDueTime(10,45);
        dueDate.setDueDate(rightNow.getTime());
        assertTrue(dueDate.isDueWithinAWeek());
    }

    @Test
    public void testIsDueWithinOneWeekMidDay() {
        rightNow.set(Calendar.DATE,Calendar.getInstance().get(Calendar.DATE) + 4);
        dueDate.setDueTime(10,45);
        dueDate.setDueDate(rightNow.getTime());
        assertTrue(dueDate.isDueWithinAWeek());
    }

    @Test
    public void testIsDueWithinOneWeekLastDay() {
        rightNow.set(Calendar.DATE,Calendar.getInstance().get(Calendar.DATE) + 6);
        dueDate.setDueTime(10,45);
        dueDate.setDueDate(rightNow.getTime());
        assertTrue(dueDate.isDueWithinAWeek());
    }

    @Test
    public void testIsNotDueWithinOneWeekBeforeCase() {
        rightNow.set(Calendar.DATE, Calendar.getInstance().get(Calendar.DATE) - 1);
        dueDate.setDueTime(10,45);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueWithinAWeek());
    }

    @Test
    public void testIsDueWithinOneWeekTodayCase() {
        rightNow.set(Calendar.DATE, Calendar.getInstance().get(Calendar.DATE));
        dueDate.setDueTime(10,45);
        dueDate.setDueDate(rightNow.getTime());
        assertTrue(dueDate.isDueWithinAWeek());
    }

    @Test
    public void testIsNotDueWithinOneWeekAfterCase() {
        rightNow.set(Calendar.DATE, Calendar.getInstance().get(Calendar.DATE) + 16);
        dueDate.setDueTime(10,45);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueWithinAWeek());
    }

    @Test
    public void testIsNotDueWithOneWeekYearCase() {
        rightNow.set(Calendar.DATE, Calendar.getInstance().get(Calendar.DATE) + 6);
        dueDate.setDueTime(10,45);
        rightNow.add(Calendar.YEAR, 2);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueWithinAWeek());
    }

    @Test
    public void testIsNotDueWithOneWeekMonthCase() {
        rightNow.set(Calendar.DATE, Calendar.getInstance().get(Calendar.DATE) + 4);
        dueDate.setDueTime(10,45);
        rightNow.add(Calendar.MONTH, 2);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueWithinAWeek());
    }

    @Test
    public void testIsNotDueWithOneWeekYearMonthCase() {
        rightNow.set(Calendar.DATE, Calendar.getInstance().get(Calendar.DATE) + 4);
        dueDate.setDueTime(10,45);
        rightNow.add(Calendar.YEAR, 2);
        rightNow.add(Calendar.MONTH, 2);
        dueDate.setDueDate(rightNow.getTime());
        assertFalse(dueDate.isDueWithinAWeek());
    }

    @Test
    public void testToString() {
        SimpleDateFormat format1 = new SimpleDateFormat("EEE MMM dd YYYY KK:mm a");
        String due1 = format1.format(rightNow.getTime());
        String due2 = dueDate.toString();
        assertEquals(due1,due2);
    }


}
