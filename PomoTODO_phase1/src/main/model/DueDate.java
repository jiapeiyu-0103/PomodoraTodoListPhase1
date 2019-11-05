package model;


import model.exceptions.InvalidTimeException;
import model.exceptions.NullArgumentException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// Note: Any library in JDK 8 may be used to implement this class, however,
//     you must not use any third-party library in your implementation
// Hint: Explore https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html

// Represents the due date of a Task
public class DueDate {
    private Calendar dueDate;

    // MODIFIES: this
    // EFFECTS: creates a DueDate with deadline at end of day today (i.e., today at 11:59 PM)
    public DueDate() {
        dueDate = Calendar.getInstance();
        dueDate.set(Calendar.HOUR, 11);
        dueDate.set(Calendar.MINUTE, 59);
        dueDate.set(Calendar.AM_PM, Calendar.PM);
    }

    // MODIFIES: this
    // EFFECTS: creates a DueDate with deadline of the given date
    //          @throws NullArgumentException if the date is null.
    public DueDate(Date date) {
        if (date == null) {
            throw new NullArgumentException();
        }
        dueDate = Calendar.getInstance();
        dueDate.setTime(date);
    }

    // MODIFIES: this
    // EFFECTS: changes the due date to the given date
    //          @throws NullArgumentException if the date is null.
    public void setDueDate(Date date) {
        if (date == null) {
            throw new NullArgumentException();
        }
        dueDate.setTime(date);
    }


    // MODIFIES: this
    // EFFECTS: changes the due time to hh:mm leaving the month, day and year the same
    //          @throws InvalidTimeException if the hh is not between 0 to 23 or the mm is not
    //                  between 0 and 59
    public void setDueTime(int hh, int mm) {
        if (hh < 0 || hh > 23 || mm < 0 || mm > 59) {
            throw new InvalidTimeException();

        }
        dueDate.set(Calendar.HOUR_OF_DAY, hh);
        dueDate.set(Calendar.MINUTE,mm);
    }

    // MODIFIES: this
    // EFFECTS: postpones the due date by one day (leaving the time the same as
    //     in the original due date) based on the rules of the Gregorian calendar.
    public void postponeOneDay() {
        dueDate.add(Calendar.DATE, 1);
    }

    // MODIFIES: this
    // EFFECTS: postpones the due date by 7 days
    //     (leaving the time the same as in the original due date)
    //     based on the rules of the Gregorian calendar.
    public void postponeOneWeek() {
        dueDate.add(Calendar.DATE,7);
    }

    // EFFECTS: returns the due date
    public Date getDate() {
        return dueDate.getTime();
    }

    // EFFECTS: returns true if due date (and due time) is passed
    public boolean isOverdue() {
        return dueDate.before(Calendar.getInstance());
    }

    // EFFECTS: returns true if due date is at any time today, and false otherwise
    public boolean isDueToday() {
        return (dueDate.get(Calendar.DATE) == Calendar.getInstance().get(Calendar.DATE)
                && dueDate.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)
                && dueDate.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR));
    }

    // EFFECTS: returns true if due date is at any time tomorrow, and false otherwise
    public boolean isDueTomorrow() {
        return (dueDate.get(Calendar.DATE) == Calendar.getInstance().get(Calendar.DATE) + 1
                && dueDate.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)
                && dueDate.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR));
    }

    // EFFECTS: returns true if due date is within the next seven days, irrespective of time of the day,
    // and false otherwise
    // Example, assume it is 8:00 AM on a Monday
    // then any task with due date between 00:00 AM today (Monday) and 11:59PM the following Sunday is due within a week
    public boolean isDueWithinAWeek() {
        return (dueDate.get(Calendar.DATE) <= Calendar.getInstance().get(Calendar.DATE) + 6
                && dueDate.get(Calendar.DATE) >= Calendar.getInstance().get(Calendar.DATE)
                && dueDate.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)
                && dueDate.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR));
        //return dueDate.equals(Calendar.getInstance().get(Calendar.DATE + 7));
    }

    // EFFECTS: returns a string representation of due date in the following format
    //     day-of-week month day year hour:minute
    //  example: Sun Jan 25 2019 10:30 AM
    @Override
    public String toString() {
        SimpleDateFormat format1 = new SimpleDateFormat("EEE MMM dd yyyy KK:mm a");
        Date duedate = dueDate.getTime();
        String theString = format1.format(duedate);
        return theString;
    }


}