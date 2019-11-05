package model;



import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;
import parsers.Parser;
import parsers.TagParser;
import parsers.exceptions.ParsingException;

import java.util.*;

// Represents a Task having a description, status, priorities, set of tags and due date.
public class Task {
    public static final DueDate NO_DUE_DATE = null;

    private String task;
    private DueDate dueDate;
    private Status status;
    private Priority priority;
    private boolean urgent;
    private boolean important;
    private String nameOfStatus;
    private Date date;
    private Set<Tag> tags;
    public Task taskOne;
    private TagParser tp;


    // MODIFIES: this
    // EFFECTS: constructs a task with the given description
    //    parses the description to extract meta-data (i.e., tags, status, priority and deadline).
    //    If description does not contain meta-data, the task is set to have no due date,
    //    status of 'To Do', and default priority level (i.e., not important nor urgent)
    //          @throws EmptyStringException if the description is empty or null
    public Task(String description) throws EmptyStringException {
        if (description == null || description.isEmpty()) {
            throw new EmptyStringException();
        }
        this.task = description;
        dueDate = NO_DUE_DATE;
        status = Status.TODO;
        Parser tp = new TagParser();
        priority = new Priority(4);
        this.tags = new HashSet<>();
        try {
            tp.parse(description,this);
            this.task = tp.getDescription();
        } catch (ParsingException e) {
            System.out.println("The description does not meet the requirement");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a tag with name tagName and adds it to this task
    // Note: no two tags are to have the same name
    //          @throws EmptyStringException if the tagName is empty or null
    public void addTag(String tagName) {
        if (tagName == null || tagName.isEmpty()) {
            throw new EmptyStringException();
        }
        Tag tag = new Tag(tagName);
        for (Tag t: tags) {
            if (tagName == t.getName()) {
                return;
            }
        }
        tags.add(tag);
    }

    // MODIFIES: this
    // EFFECTS: removes the tag with name tagName from this task
    //          @throws EmptyStringException if the tagName is empty or null
    public void removeTag(String tagName) {
        if (tagName == null || tagName.isEmpty()) {
            throw new EmptyStringException();
        }
        List<Tag> listTags = new ArrayList<>(tags);
        //listTags.addAll(tags);
        for (int i = 0; i < listTags.size(); i++) {
            if (listTags.get(i).getName() == tagName) {
                listTags.remove(i);
            }
        }
        tags = new HashSet<>(listTags);
    }

    // EFFECTS: returns an unmodifiable set of tags
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    // EFFECTS: returns the priority of this task
    public Priority getPriority() {
        return priority;
    }

    // MODIFIES: this
    // EFFECTS: sets the priority of this task
    //          @throws NullArgumentException if thr priority is null
    public void setPriority(Priority priority) {
        if (priority == null) {
            throw new NullArgumentException();
        }
        this.priority = priority;
    }

    // EFFECTS: returns the status of this task
    public Status getStatus() {
        return status;
    }

    // MODIFIES: this
    // EFFECTS: sets the status of this task
    //          @throws NullArgumentException if thr status is null
    public void setStatus(Status status) {
        if (status == null) {
            throw new NullArgumentException();
        }
        this.status = status;
    }

    // EFFECTS: returns the description of this task
    public String getDescription() {
        return this.task;
    }

    // MODIFIES: this
    // EFFECTS:  sets the description of this task
    //     parses the description to extract meta-data (i.e., tags, status, priority and deadline).
    //          @throws EmptyStringException if the description is empty or null
    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new EmptyStringException();
        }
        this.task = description;
    }

    // EFFECTS: returns the due date of this task
    public DueDate getDueDate() {
        return dueDate;
    }

    // MODIFIES: this
    // EFFECTS: sets the due date of this task
    public void setDueDate(DueDate dueDate) {
        this.dueDate = dueDate;
    }

    // EFFECTS: returns true if task contains a tag with tagName,
    //     returns false otherwise
    public boolean containsTag(String tagName) {
        for (Tag t: tags) {
            if (t.getName().equals(tagName)) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: returns a string representation of this task in the following format
    //    {
    //        Description: Read collaboration policy of the term project
    //        Due date: Sat Feb 2 2019 11:59
    //        Status: IN PROGRESS
    //        Priority: IMPORTANT & URGENT
    //        Tags: #cpsc210, #project
    //    }
    @Override
    public String toString() {
        if (!(dueDate == null)) {
            String desString = "Description: " + task;
            String dueDateForTask = "Due date: " + dueDate.toString();
            String statusString = "Status: " + status.getDescription();
            String priorityString = "Priority: " + priority.toString();
            String tagsString = "Tags: " + tags.toString();
            return desString + "\n" + dueDateForTask + "\n" + statusString + "\n" + priorityString + "\n" + tagsString;
        } else {
            String desString = "Description: " + task;
            String dueDateForTask = "Due date: ";
            String statusString = "Status: " + status.getDescription();
            String priorityString = "Priority: " + priority.toString();
            String tagsString = "Tags: " + tags.toString();
            return desString + "\n" + dueDateForTask + "\n" + statusString + "\n" + priorityString + "\n" + tagsString;
        }

    }
}
