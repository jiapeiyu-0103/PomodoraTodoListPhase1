package parsers;


import model.*;
import model.exceptions.EmptyStringException;
import parsers.exceptions.ParsingException;

import java.util.ArrayList;
import java.util.List;


public class TagParser extends Parser {

    private String[] listOfStr;
    private ArrayList<String> listOfInf;
    private ArrayList<String> listOfStringSeenSoFar;

    @Override
    public void parse(String input, Task task) throws ParsingException {
        if (!input.contains("##")) {
            task.setDescription(input);
            throw new ParsingException("dd");
        }
        listOfStringSeenSoFar = new ArrayList<>();
        listOfInf = new ArrayList<>();
        description = input.substring(0, input.indexOf("##"));
        task.setDescription(description);
        listOfStr = input.substring(input.indexOf("##") + 2).split(";");
        for (String s : listOfStr) {
            listOfInf.add(s.trim());
        }
        parseTime(task);
        parseStatus(task);
        parsePriority(task);
        parseTags(task);
    }

    private void checkDate(Task task, List<String> listOfDate) {
        for (String str : listOfInf) {
            if (str.equalsIgnoreCase("today") || str.equalsIgnoreCase("tomorrow")) {
                listOfDate.add(str);
            }
        }
    }


    private void parseTime(Task task) {
        DueDate due = new DueDate();
        ArrayList<String> listOfDate = new ArrayList<>();
        checkDate(task, listOfDate);
        if (!(listOfDate.size() == 0)) {
            if (listOfDate.get(0).equalsIgnoreCase("today")) {
                task.setDueDate(due);
                listOfStringSeenSoFar.add("today");
            } else if (listOfDate.get(0).equalsIgnoreCase("tomorrow")) {
                due.postponeOneDay();
                task.setDueDate(due);
                listOfStringSeenSoFar.add("tomorrow");
            }
        }
    }


    private void getListOfStatus(List<String> listOfStatus) {
        for (String str : listOfInf) {
            if (str.equalsIgnoreCase("to do") || str.equalsIgnoreCase("up next")
                    || str.equalsIgnoreCase("in progress") || str.equalsIgnoreCase("done")) {
                listOfStatus.add(str);
            }
        }
    }

    private void parseStatus(Task task) {
        ArrayList<String> listOfStatus = new ArrayList<>();
        getListOfStatus(listOfStatus);
        if (!listOfStatus.isEmpty()) {
            if (listOfStatus.get(0).equalsIgnoreCase("to do")) {
                task.setStatus(Status.TODO);
                listOfStringSeenSoFar.add("to do");
            } else if (listOfStatus.get(0).equalsIgnoreCase("up next")) {
                task.setStatus(Status.UP_NEXT);
                listOfStringSeenSoFar.add("up next");
            } else if (listOfStatus.get(0).equalsIgnoreCase("in progress")) {
                task.setStatus(Status.IN_PROGRESS);
                listOfStringSeenSoFar.add("in progress");
            } else {
                task.setStatus(Status.DONE);
                listOfStringSeenSoFar.add("done");
            }
        }
    }



    private void parsePriority(Task task) {
        for (String str: listOfInf) {
            if (str.equalsIgnoreCase("important")) {
                task.getPriority().setImportant(true);
                listOfStringSeenSoFar.add("important");
            } else if (str.equalsIgnoreCase("urgent")) {
                task.getPriority().setUrgent(true);
                listOfStringSeenSoFar.add("urgent");
            }
        }
    }

    private void parseTags(Task task) {
        if (!listOfInf.isEmpty()) {
            for (String s: listOfStr) {
                if (listOfStringSeenSoFar.contains(s.trim().toLowerCase())) {
                    listOfInf.remove(s.trim());
                }
            }
            for (String s: listOfInf) {
                if (!listOfStringSeenSoFar.contains(s.trim().toLowerCase()) && !s.trim().isEmpty()) {
                    task.addTag(s.trim());
                }
                listOfStringSeenSoFar.add(s.toLowerCase().trim());
            }
        }
    }

//        for (String s: listOfInf) {
//            if (listOfStringSeenSoFar.contains(s.toLowerCase())) {
//                listOfInf.remove(s);
//            } else {
//                task.addTag(s);
//                listOfStringSeenSoFar.add(s);
//            }
//        }


//    public void parseStatus(Task task) {
//        Boolean b1 = string.equalsIgnoreCase("to do");
//        Boolean b2 = string.equalsIgnoreCase("up next");
//        Boolean b3 = string.equalsIgnoreCase("in progress");
//        Boolean b4 = string.equalsIgnoreCase("done");
//        while (b1 || b2 || b3 || b4 || (!b1 && !b2 && !b3 && !b4)) {
//            helpSetStatus(b1, b2, b3, b4, task);
//        }
//    }
//
//    public void helpSetStatus(boolean b1, boolean b2, boolean b3, boolean b4, Task task) {
//        if (b1 || b2) {
//            helpTodoAndUpNext(b1, b2, task);
//        } else if (b3) {
//            task.setStatus(Status.IN_PROGRESS);
//            b3 = false;
//            listOfStringSeenSoFar.add("in progress");
//        } else if (b4) {
//            task.setStatus(Status.DONE);
//            b4 = false;
//            listOfStringSeenSoFar.add("done");
//        } else {
//            task.setStatus(Status.TODO);
//            b1 = b2 = b3 = b4 = false;
//        }
//    }
//
//    public void helpTodoAndUpNext(boolean b1, boolean b2, Task task) {
//        if (b1) {
//            task.setStatus(Status.TODO);
//            b1 = false;
//            listOfStringSeenSoFar.add("to do");
//        } else if (b2) {
//            task.setStatus(Status.UP_NEXT);
//            b2 = false;
//            listOfStringSeenSoFar.add("up next");
//        }
//    }

//    public void parsePriority(Task task) {
//        Boolean b1 = string.equalsIgnoreCase("important");
//        Boolean b2 = string.equalsIgnoreCase("urgent");
//        while (b1 || b2) {
//            if (b1) {
//                task.getPriority().setImportant(true);
//                b1 = false;
//                listOfStringSeenSoFar.add("important");
//            } else if (b2) {
//                task.getPriority().setUrgent(true);
//                b2 = false;
//                listOfStringSeenSoFar.add("urgent");
//            }
//        }
//    }

}

