import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    private static List<Lesson> timetable = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        //create lessons
        Lesson painting = new Lesson("Painting", new Date(1607590800000l), new Date(1607594400000l));
        Lesson english = new Lesson("English", new Date(1607592600000l), new Date(1607596200000l));
        Lesson math = new Lesson("Math", new Date(1607594400000l), new Date(1607598000000l));
        Lesson it = new Lesson("IT", new Date(1607596200000l), new Date(1607599800000l));
        Lesson music = new Lesson("Music", new Date(1607598000000l), new Date(1607601600000l));

        //add lessons to lessonsList
        var lessonsList = new ArrayList<>(Arrays.asList(painting, english, math, it, music));

        //find lesson which ends before everyone else, it will be first lesson
        Lesson temp = lessonsList.get(0);
        for (Lesson lesson : lessonsList) {
            if (temp.getEndTime().after(lesson.getEndTime()))
                temp = lesson;
        }
        timetable.add(temp);

        //while it is possible find lesson which starts after previous lesson's end and ends before everyone else
        while (true) {
            Lesson tempLesson = null;
            for (Lesson lesson : lessonsList) {
                //if lesson starts after previous lesson and ends before tempLesson endTime save it in tempLesson
                if (!lesson.getStartTime().before(timetable.get(timetable.size() - 1).getEndTime())) {
                    if (tempLesson == null)
                        tempLesson = lesson;
                    else {
                        if (lesson.getEndTime().before(tempLesson.getEndTime()))
                            tempLesson = lesson;
                    }
                }
            }
            //if tempLesson = null go out of cycle, else add tempLesson to timetable
            if (tempLesson == null)
                break;
            else
                timetable.add(tempLesson);
        }

        //print timetable
        for (Lesson lesson : timetable) {
            System.out.println(lesson.getName());
        }
    }
}
