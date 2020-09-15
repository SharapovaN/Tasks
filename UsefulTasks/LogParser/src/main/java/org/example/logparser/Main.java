package org.example.logparser;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("C:\\Users\\natal\\IdeaProjects\\UsefulTasks\\LogParser\\src\\main\\java\\org\\example\\logparser\\logs"));

        System.out.println(logParser.execute("get user for date = \"30.08.2012 16:08:13\" and date between \"11.12.2011 0:00:00\" and \"03.01.2014 23:59:59\""));
        System.out.println(logParser.execute("get user for event = \"DOWNLOAD_PLUGIN\""));
        System.out.println(logParser.execute("get user for status = \"OK\""));
        System.out.println(logParser.execute("get date for ip = \"127.0.0.1\""));
        System.out.println(logParser.execute("get date for user = \"Eduard Petrovich Morozko\""));
        System.out.println(logParser.execute("get date for event = \"DOWNLOAD_PLUGIN\""));
        System.out.println(logParser.execute("get date for status = \"OK\""));
        System.out.println(logParser.execute("get event for ip = \"127.0.0.1\""));
        System.out.println(logParser.execute("get event for user = \"Eduard Petrovich Morozko\""));
        System.out.println(logParser.execute("get event for date = \"30.08.2012 16:08:13\""));
        System.out.println(logParser.execute("get event for status = \"OK\""));
        System.out.println(logParser.execute("get status for ip = \"127.0.0.1\""));
        System.out.println(logParser.execute("get status for user = \"Amigo\""));
        System.out.println(logParser.execute("get status for date = \"30.08.2012 16:08:13\""));
        System.out.println(logParser.execute("get status for event = \"DOWNLOAD_PLUGIN\""));
        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
    }
}