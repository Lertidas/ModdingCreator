package com.example.moddingcreator.services;

import com.example.moddingcreator.util.XmlUtil;


public class Validator {
    public static boolean validateModSave(String modName, String modid) {
        return validateName(modName) &&
                validateName(modid) &&
                !(XmlUtil.getSaveNames().contains(modName));
    }

    public static boolean validateModLoad(String modName) {
        return validateName(modName);
    }

    public static boolean validateName(String name) {
        return name != null && !name.equals("");
    }
}
