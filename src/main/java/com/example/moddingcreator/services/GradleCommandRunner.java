package com.example.moddingcreator.services;

import com.example.moddingcreator.data.InstanceData;

import java.io.*;

public class GradleCommandRunner {
    public static void setup(String modName) throws IOException, InterruptedException {
        String path = InstanceData.modOutputPath + modName;
        runProcess(path + "/./gradlew.bat genIntellijRuns -p " + path);
    }

    public static void buildAndRunClient(String modName) throws IOException, InterruptedException {
        String path = InstanceData.modOutputPath + modName;
        runProcess(path + "/./gradlew.bat build runClient -p " + path);
    }

    public static void runProcess(String command) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(process.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(process.getErrorStream()));

        String line;
        System.out.println("<OUTPUT>");
        while ((line = stdInput.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("</OUTPUT>");
        System.out.println("<ERROR>");
        while ((line = stdError.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("</ERROR>");
    }
}
