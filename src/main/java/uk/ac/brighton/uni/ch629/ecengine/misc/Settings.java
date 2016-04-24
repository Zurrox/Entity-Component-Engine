package uk.ac.brighton.uni.ch629.ecengine.misc;

import java.util.HashMap;

public class Settings {
    public HashMap<String, String> settings = new HashMap<String, String>();

    public Settings() {
    }

    public void setSetting(String name, String value) {
        settings.put(name, value);
    }

    public String getValue(String name) {
        return settings.get(name);
    }
}