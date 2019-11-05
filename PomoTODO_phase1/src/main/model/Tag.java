package model;

import model.exceptions.EmptyStringException;

// Represents a tag having a name
public class Tag {

    private String tag;


    // MODIFIES: this
    // EFFECTS: creates a Tag with the given name
    //          @throws EmptyStringException if the string is empty or null
    public Tag(String name) {
        if (name == null || name.isEmpty()) {
            throw new EmptyStringException();
        }
        this.tag = name;
    }
    
    // EFFECTS: returns the name of this tag
    public String getName() {
        return tag;
    }
    
    // EFFECTS: returns the tag name preceded by #
    @Override
    public String toString() {
        return ("#" + tag);
    }
}
