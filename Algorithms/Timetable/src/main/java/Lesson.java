import java.util.Date;

public class Lesson {
    private String name;
    private Date startTime, endTime;

    public Lesson (String name, Date startTime, Date endTime) throws Exception {
        this.name = name;
        if (startTime.after(endTime) || startTime.equals(endTime)) {
            throw new Exception("Object " + name + " wasn't created, check dates");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
}
