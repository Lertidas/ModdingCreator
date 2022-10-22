//package com.example.moddingcreator.services;
//
//import com.example.moddingcreator.data.LoadedModData;
//import org.gradle.tooling.*;
//import org.gradle.tooling.model.GradleProject;
//
//import java.io.File;
//import java.io.IOException;
//
//public class GradleCommandExecutor {
//    public static void runGradleCommand(String command) {
//        try (ProjectConnection connection = GradleConnector.newConnector()
//                .forProjectDirectory(new File(LoadedModData.modGradlePath))
//                .connect()) {
//            ProgressListener progressListener = event -> System.out.println(event.getDescription());
//            BuildLauncher buildLauncher = connection.newBuild();
//            buildLauncher.forTasks(command)
//                    .addProgressListener(progressListener)
//                    .setColorOutput(true);
//            buildLauncher.run();
//        }
//    }
//
//    public static void setup() throws IOException, InterruptedException {
//        runGradleCommand("./gradlew.bat genIntellijRuns");
//    }
//}
