package org.example.logparser;

import org.example.logparser.query.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;
    private Set<String[]> setArrays = new HashSet<>();
    private Set<UserLog> setLogs = new HashSet<>();



    public LogParser(Path logDir) {
        this.logDir = logDir;
        getArraysOfLogStrings();
        createSetLogs();
    }

    private boolean inTime(Date date, Date after, Date before) {
            if (after != null || before != null) {
                if (after != null && before != null && date.after(after) && date.before(before))
                    return true;
                if (after == null && date.before(before))
                    return true;
                if (before == null && date.after(after))
                    return true;
            } else
                return true;
        return false;
    }

    private Date stringToDate(String str) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH).parse(str);
        } catch (Exception e) {}
        return date;
    }

    private void getArraysOfLogStrings() {
        Set<File> logFiles = new HashSet();
        File[] folder = logDir.toFile().listFiles();
        for (File file : folder) {
            if (file.getName().endsWith(".log"))
                logFiles.add(file);
        }
        for (File file : logFiles) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                while (reader.ready()) {
                    String string = reader.readLine();
                    String[] array = string.split("\\t");
                    setArrays.add(array);
                }
            } catch (Exception e) {

            }
        }
    }

    private void createSetLogs() {
        for (String[] array : setArrays) {
            setLogs.add(new UserLog(array));
        }
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (UserLog user : setLogs) {
                if (inTime(user.getData(), after, before))
                    set.add(user.getIP());
        }
        return set.size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (inTime(user.getData(), after, before))
                set.add(user.getIP());
        }
        return set;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (UserLog users : setLogs) {
            if (users.getUserName().equals(user)) {
                if (inTime(users.getData(), after, before))
                    set.add(users.getIP());
            }
        }
        return set;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getEvent().equals(event)) {
                if (inTime(user.getData(), after, before))
                    set.add(user.getIP());
            }
        }
        return set;
    }

    public Set<String> getIPsForEvent(String string, Date after, Date before) {
        Set<String> set = new HashSet<>();
        Event event = Event.valueOf(string);
        return set = getIPsForEvent(event, after, before);
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getStatus().equals(status)) {
                if (inTime(user.getData(), after, before))
                    set.add(user.getIP());
            }
        }
        return set;
    }

    public Set<String> getIPsForStatus(String string, Date after, Date before) {
        Set<String> set = new HashSet<>();
        Status status = Status.valueOf(string);
        return set = getIPsForStatus(status, after, before);
    }

    @Override
    public Set<String> getAllUsers() {
        Set<String> set = new HashSet<>();
        for (UserLog user : setLogs) {
            set.add(user.getUserName());
        }
        return set;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (inTime(user.getData(), after, before))
                set.add(user.getUserName());
        }
        return set.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (UserLog users : setLogs) {
            if (users.getUserName().startsWith(user) && inTime(users.getData(), after, before)) {
                set.add(users.getEvent());
            }
        }
        return set.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getIP().startsWith(ip) && inTime(user.getData(), after, before))
                set.add(user.getUserName());
        }
        return set;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getEvent().equals(Event.LOGIN) && inTime(user.getData(), after, before))
                set.add(user.getUserName());
        }
        return set;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getEvent().equals(Event.DOWNLOAD_PLUGIN) && inTime(user.getData(), after, before))
                set.add(user.getUserName());
        }
        return set;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getEvent().equals(Event.WRITE_MESSAGE) && inTime(user.getData(), after, before))
                set.add(user.getUserName());
        }
        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getEvent().equals(Event.SOLVE_TASK) && inTime(user.getData(), after, before))
                set.add(user.getUserName());
        }
        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getEvent().equals(Event.SOLVE_TASK) && user.getTask() == task && inTime(user.getData(), after, before))
                set.add(user.getUserName());
        }
        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getEvent().equals(Event.DONE_TASK) && inTime(user.getData(), after, before))
                set.add(user.getUserName());
        }
        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getEvent().equals(Event.DONE_TASK) && user.getTask() == task && inTime(user.getData(), after, before))
                set.add(user.getUserName());
        }
        return set;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (UserLog users : setLogs) {
            if (users.getUserName().equals(user) && users.getEvent().equals(event) && inTime(users.getData(), after, before)) {
                    set.add(users.getData());
            }
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getStatus().equals(Status.FAILED) && inTime(user.getData(), after, before)) {
                    set.add(user.getData());
            }
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getStatus().equals(Status.ERROR) && inTime(user.getData(), after, before)) {
                    set.add(user.getData());
            }
        }
        return set;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Date result = null;
        for (UserLog users : setLogs) {
            if (users.getUserName().equals(user) && users.getEvent().equals(Event.LOGIN) && inTime(users.getData(), after, before)) {
                    if (result == null)
                        result = users.getData();
                    if (users.getData().before(result))
                        result = users.getData();
            }
        }
        return result;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Date result = null;
        for (UserLog users : setLogs) {
            if (users.getUserName().equals(user) && users.getEvent().equals(Event.SOLVE_TASK) && users.getTask() == task) {
                if (inTime(users.getData(), after, before)) {
                    if (result == null)
                        result = users.getData();
                    if (users.getData().before(result))
                        result = users.getData();
                }
            }
        }
        return result;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Date result = null;
        for (UserLog users : setLogs) {
            if (users.getUserName().equals(user) && users.getEvent().equals(Event.DONE_TASK) && users.getTask() == task) {
                if (inTime(users.getData(), after, before)) {
                    if (result == null)
                        result = users.getData();
                    if (users.getData().before(result))
                        result = users.getData();
                }
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (UserLog users : setLogs) {
            if (users.getUserName().equals(user) && users.getEvent().equals(Event.WRITE_MESSAGE) && inTime(users.getData(), after, before)) {
                    set.add(users.getData());
            }
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (UserLog users : setLogs) {
            if (users.getUserName().equals(user) && users.getEvent().equals(Event.DOWNLOAD_PLUGIN) && inTime(users.getData(), after, before)) {
                    set.add(users.getData());
            }
        }
        return set;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (inTime(user.getData(), after, before)) {
                set.add(user.getEvent());
            }
        }
        return set.size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (inTime(user.getData(), after, before)) {
                set.add(user.getEvent());
            }
        }
        return set;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getIP().equals(ip) && inTime(user.getData(), after, before)) {
                    set.add(user.getEvent());
            }
        }
        return set;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (UserLog users : setLogs) {
            if (users.getUserName().equals(user) && inTime(users.getData(), after, before)) {
                    set.add(users.getEvent());
            }
        }
        return set;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getStatus().equals(Status.FAILED) && inTime(user.getData(), after, before)) {
                    set.add(user.getEvent());
            }
        }
        return set;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getStatus().equals(Status.ERROR) && inTime(user.getData(), after, before)) {
                    set.add(user.getEvent());
            }
        }
        return set;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int count = 0;
        for (UserLog user : setLogs) {
            if (user.getEvent().equals(Event.SOLVE_TASK) && user.getTask() == task && inTime(user.getData(), after, before)) {
                    count++;
            }
        }
        return count;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int count = 0;
        for (UserLog user : setLogs) {
            if (user.getEvent().equals(Event.DONE_TASK) && user.getTask() == task && inTime(user.getData(), after, before)) {
                    count++;
            }
        }
        return count;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> map = new HashMap<>();
        for (UserLog user : setLogs) {
            if (user.getEvent().equals(Event.SOLVE_TASK) && inTime(user.getData(), after, before)) {
                Integer key = user.getTask();
                if (!map.containsKey(key))
                    map.put(key, 1);
                else {
                    Integer newValue = map.get(key) + 1;
                    map.replace(key, newValue);
                }
            }
        }
        return map;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> map = new HashMap<>();
        for (UserLog user : setLogs) {
            if (user.getEvent().equals(Event.DONE_TASK) && inTime(user.getData(), after, before)) {
                Integer key = user.getTask();
                if (!map.containsKey(key))
                    map.put(key, 1);
                else {
                    Integer newValue = map.get(key) + 1;
                    map.replace(key, newValue);
                }
            }
        }
        return map;
    }

    public Set<Date> getDates() {
        Set<Date> set = new HashSet<>();
        for (UserLog user : setLogs) {
            set.add(user.getData());
        }
        return set;
    }

    public Set<Status> getStatuses() {
        Set<Status> set = new HashSet<>();
        for (UserLog user : setLogs) {
            set.add(user.getStatus());
        }
        return set;
    }

    public Set<String> getIPsForDate (String date, Date after, Date before) {
        Set<String> set = new HashSet<>();
        Date d = stringToDate(date);
        if (getDates().contains(d)) {
           for (UserLog user : setLogs) {
               if (user.getData().equals(d) && inTime(user.getData(), after, before))
                   set.add(user.getIP());
           }
        }
        return set;
    }

    public Set<String> getUsersForDate(String string, Date after, Date before) {
        Set<String> set = new HashSet<>();
        Date date = stringToDate(string);
        for (UserLog user : setLogs) {
            if (user.getData().equals(date) && inTime(user.getData(), after, before))
                set.add(user.getUserName());
        }
        return set;
    }

    public Set<String> getUserForEvent(String string, Date after, Date before) {
        Set<String> set = new HashSet<>();
        Event event = Event.valueOf(string);
        for (UserLog user : setLogs) {
            if (user.getEvent().equals(event) && inTime(user.getData(), after, before))
                set.add(user.getUserName());
        }
        return set;
    }

    public Set<String> getUserForStatus(String string, Date after, Date before) {
        Set<String> set = new HashSet<>();
        Status status = Status.valueOf(string);
        for (UserLog user : setLogs) {
            if (user.getStatus().equals(status) && inTime(user.getData(), after, before))
                set.add(user.getUserName());
        }
        return set;
    }

    public Set<Date> getDateForIP(String string, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getIP().equals(string) && inTime(user.getData(), after, before))
                set.add(user.getData());
        }
        return set;
    }

    public Set<Date> getDateForUser(String string, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getUserName().equals(string) && inTime(user.getData(), after, before))
                set.add(user.getData());
        }
        return set;
    }

    public Set<Date> getDateForEvent(String string, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        Event event = Event.valueOf(string);
        for (UserLog user : setLogs) {
            if (user.getEvent().equals(event) && inTime(user.getData(), after, before))
                set.add(user.getData());
        }
        return set;
    }

    public Set<Date> getDateForStatus(String string, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        Status status = Status.valueOf(string);
        for (UserLog user : setLogs) {
            if (user.getStatus().equals(status) && inTime(user.getData(), after, before))
                set.add(user.getData());
        }
        return set;
    }

    public Set<Event> getEventForDate(String string, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        Date date = stringToDate(string);
        for (UserLog user : setLogs) {
            if (user.getData().equals(date) && inTime(user.getData(), after, before))
                set.add(user.getEvent());
        }
        return set;
    }

    public Set<Event> getEventForStatus(String string, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        Status status = Status.valueOf(string);
        for (UserLog user : setLogs) {
            if (user.getStatus().equals(status) && inTime(user.getData(), after, before))
                set.add(user.getEvent());
        }
        return set;
    }

    public Set<Status> getStatusForIp(String ip, Date after, Date before) {
        Set<Status> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getIP().equals(ip) && inTime(user.getData(), after, before))
                set.add(user.getStatus());
        }
        return set;
    }

    public Set<Status> getStatusForUser(String name, Date after, Date before) {
        Set<Status> set = new HashSet<>();
        for (UserLog user : setLogs) {
            if (user.getUserName().equals(name) && inTime(user.getData(), after, before))
                set.add(user.getStatus());
        }
        return set;
    }

    public Set<Status> getStatusForDate(String string, Date after, Date before) {
        Set<Status> set = new HashSet<>();
        Date date = stringToDate(string);
        for (UserLog user : setLogs) {
            if (user.getData().equals(date) && inTime(user.getData(), after, before))
                set.add(user.getStatus());
        }
        return set;
    }

    public Set<Status> getStatusForEvent(String string, Date after, Date before) {
        Set<Status> set = new HashSet<>();
        Event event = Event.valueOf(string);
        for (UserLog user : setLogs) {
            if (user.getEvent().equals(event) && inTime(user.getData(), after, before))
                set.add(user.getStatus());
        }
        return set;
    }

    @Override
    public Set<Object> execute(String query) {
        switch (query) {
            case "get ip" :
                return new HashSet<>(getUniqueIPs(null, null));
            case "get user" :
                return new HashSet<>(getAllUsers());
            case  "get date" :
                return new HashSet<>(getDates());
            case "get event" :
                return new HashSet<>(getAllEvents(null, null));
            case "get status" :
                return new HashSet<>(getStatuses());
        }
        String[] data;
        String field1;
        String field2;
        String value;
        Date after = null;
        Date before = null;

        String[] test = query.split("\"");
        if (test.length > 3) {
            value = test[1];
            after = stringToDate(test[3]);
            before = stringToDate(test[5]);
        } else {
            value = test[1];
        }
        data = query.split("\\s");
        field1 = data[0] + data[1];
        field2 = data[2] + data[3];
            Set<Object> result = null;

            Method[] methods = LogParser.class.getMethods();
            for (Method method : methods) {
                String methodName = method.getName().toLowerCase();
                if (methodName.contains(field1) && methodName.endsWith(field2) &&
                        methodName.indexOf(field1) < methodName.indexOf(field2)) {
                    System.out.println(method.getName());
                        try {
                            return result = (Set<Object>) (method.invoke(this, value, after, before));
                        } catch (Exception e) {
                            e.getMessage();
                        }
                }
            }
            return null;

    }
}
