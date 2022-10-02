package com.example.moddingcreator.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

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
}
