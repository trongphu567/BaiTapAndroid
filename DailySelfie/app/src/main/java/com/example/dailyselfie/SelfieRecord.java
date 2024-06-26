package com.example.dailyselfie;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SelfieRecord {
    private String path;
    private String name;
    private boolean isSelected;

    public SelfieRecord(String path, String name) {
        this.path = path;
        this.name = name;
        this.isSelected = false;

    }

    public String getPath() {
        return path;
    }

    public String getDisplayName() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Date date = format.parse(name, new ParsePosition(0));

        return new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(date);
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean isChecked) {
        this.isSelected = isSelected;
    }

    @Override
    public String toString() {
        return name;
    }
}
