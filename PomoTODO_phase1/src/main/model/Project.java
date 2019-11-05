package model;

// import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;
import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Represents a Project, a collection of zero or more Tasks
// Class Invariant: no duplicated task; order in which tasks are added to project is preserved
public class Project {

    private String project;
    private List<Task> listOfTasks;
    private int done;

    // MODIFIES: this
    // EFFECTS: constructs a project with the given description
    //     the constructed project shall have no tasks.
    //          @throws EmptyStringException if the description is null or empty
    public Project(String description) {
        if (description == null || description.isEmpty()) {
            throw new EmptyStringException();
        }
        project = description;
        listOfTasks = new ArrayList<>();
    }
    
    // MODIFIES: this
    // EFFECTS: task is added to this project (if it was not already part of it)
    //          @throws NullArgumentException if the task is null
    public void add(Task task) {
        if (task == null) {
            throw new NullArgumentException();
        }
        if (listOfTasks.contains(task)) {
            return;
        } else {
            listOfTasks.add(task);
        }
    }
    
    // MODIFIES: this
    // EFFECTS: removes task from this project
    //          @throws NullArgumentException if the task is null
    public void remove(Task task) {
        if (task == null) {
            throw new NullArgumentException();
        }
        if (listOfTasks.contains(task)) {
            listOfTasks.remove(task);
        }
    }
    
    // EFFECTS: returns the description of this project
    public String getDescription() {
        return this.project;
    }
    
    // EFFECTS: returns an unmodifiable list of tasks in this project.
    public List<Task> getTasks() {
        return Collections.unmodifiableList(listOfTasks);
    }
    
    // EFFECTS: returns an integer between 0 and 100 which represents
    //     the percentage of completed tasks (rounded down to the closest integer).
    //     returns 100 if this project has no tasks!
    public int getProgress() {
        int total = getNumberOfTasks();
        done = 0;
        if (total == 0) {
            return 100;
        } else {
            for (int i = 0; i < listOfTasks.size(); i++) {
                if (listOfTasks.get(i).getStatus() == Status.DONE) {
                    done += 1;
                }
            }
        }
        int percentage = done * 100 / total;
        return percentage;
    }
    
    // EFFECTS: returns the number of tasks in this project
    public int getNumberOfTasks() {
        return listOfTasks.size();
    }
    
    // EFFECTS: returns true if every task in this project is completed
    //     returns false if this project has no tasks!
    public boolean isCompleted() {
        if (listOfTasks.size() == 0) {
            return false;
        } else {
            for (int i = 0; i < listOfTasks.size(); i++) {
                if (!(listOfTasks.get(i).getStatus() == Status.DONE)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    // EFFECTS: returns true if this project contains the task
    //          @throws NullArgumentException if the task is null
    public boolean contains(Task task) {
        if (task == null) {
            throw new NullArgumentException();
        }
        if (listOfTasks.contains(task)) {
            return true;
        } else {
            return false;
        }
    }
}