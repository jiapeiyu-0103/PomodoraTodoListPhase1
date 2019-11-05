package model;

import model.exceptions.InvalidPriorityLevelException;

// To model priority of a task according to the Eisenhower Matrix
//     https://en.wikipedia.org/wiki/Time_management#The_Eisenhower_Method
public class Priority {
    
    private Boolean important;
    private Boolean urgent;


    // MODIFIES: this
    // EFFECTS: construct a default priority (i.e., not important nor urgent)
    public Priority() {
        this.important = false;
        this.urgent = false;
    }

    // MODIFIES: this
    // EFFECTS: constructs a Priority according to the value of "quadrant"
    //     the parameter "quadrant" refers to the quadrants of the Eisenhower Matrix
    //          @throws InvalidPriorityLevelException if the quadrant is not between 1 and 4
    public Priority(int quadrant) {
        if (quadrant < 1 || quadrant > 4) {
            throw new InvalidPriorityLevelException();
        }

        if (quadrant == 1) {
            this.important = true;
            this.urgent = true;
        }
        if (quadrant == 2) {
            this.important = true;
            this.urgent = false;
        }
        if (quadrant == 3) {
            this.important = false;
            this.urgent = true;
        }
        if (quadrant == 4) {
            this.important = false;
            this.urgent = false;
        }
    }

    // EFFECTS: returns the importance of Priority
    //     (i.e., whether it is "important" or not)
    public boolean isImportant() {
        return important;
    }

    // MODIFIES: this
    // EFFECTS: updates the importance of Priority
    public void setImportant(boolean important) {
        this.important = important;
    }

    // EFFECTS: returns the urgency of Priority
    //     (i.e., whether it is "urgent" or not)
    public boolean isUrgent() {
        return urgent;
    }

    // MODIFIES: this
    // EFFECTS: updates the urgency of Priority
    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    //  EFFECTS: returns one of the four string representation of Priority
    //    "IMPORTANT & URGENT",  "IMPORTANT", "URGENT", "DEFAULT"
    @Override
    public String toString() {
        if (isImportant() == true && isUrgent() == true) {
            return "IMPORTANT & URGENT";
        } else if (isImportant()) {
            return "IMPORTANT";
        } else if (isUrgent()) {
            return "URGENT";
        } else {
            return "DEFAULT";
        }
    }
}