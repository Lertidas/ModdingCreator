package com.example.moddingcreator.services;

import com.example.moddingcreator.util.FileUtil;

public class Validator {
    public static boolean validateModSave(String output, String modName) {
        return validateName(modName) && !FileUtil.hasItemInDirectory(output, modName);
    }

    public static boolean validateModLoad(String modName) {
        return validateName(modName);
    }

    public static boolean validateName(String name) {
        return name != null && !name.equals("");
    }
}
