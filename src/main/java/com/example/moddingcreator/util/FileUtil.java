package com.example.moddingcreator.util;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtil {
    public static String[] getItemsInDirectory(String path) {
        return new File(path).list();
    }

    public static boolean hasItemInDirectory(String path, String item) {
        String[] strings = new File(path).list();
        if (strings != null) {
            for (String s : strings) {
                if (item.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void cloneRepository(String source, String destination, String repoName) throws IOException {
        copyDirectory(new File(source), new File(destination + "/" + repoName));
    }

    public static void copyDirectory(File sourceDirectory, File destinationDirectory) throws IOException {
        FileUtils.copyDirectory(sourceDirectory, destinationDirectory);
    }

    public static boolean renameFile(String currentPath, String renamedPath) {
        return new File(currentPath).renameTo(new File(renamedPath));
    }

    public static void replaceAllOccurrences(String path, String currentString, String newString, boolean lowerAndUpperCase) {
        replaceAllOccurrences(path, currentString, newString);
        replaceAllOccurrences(path, currentString.toLowerCase(), newString.toLowerCase());
    }

    public static void replaceAllOccurrences(String path, String currentString, String newString) {
        List<String> newFileLines = new ArrayList<>();
        // Reading
        try (Scanner fileReader = new Scanner(new File(path))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                newFileLines.add(line.replace(currentString, newString));
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Writing
        try (FileWriter fileWriter = new FileWriter(path)) {
            for (String line : newFileLines) {
                fileWriter.write(line + "\n");
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void replaceMultiLineOccurrence(String path, String[] currentStrings, String newString) {
        List<String> newFileLines = new ArrayList<>();
        int index = 0;
        // Reading
        try (Scanner fileReader = new Scanner(new File(path))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.equals(currentStrings[index])) {
                    index += 1;
                    if (index == currentStrings.length) {
                        newFileLines.add(newString);
                        index = 0;
                    }
                }
                else {
                    index = 0;
                    newFileLines.add(line);
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Writing
        try (FileWriter fileWriter = new FileWriter(path)) {
            for (String line : newFileLines) {
                fileWriter.write(line + "\n");
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
