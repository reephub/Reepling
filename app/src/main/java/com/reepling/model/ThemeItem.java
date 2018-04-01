package com.reepling.model;

/**
 * Created by Michaël on 24/03/2018.
 */

public class ThemeItem {

    public String name;
    public int resourceColorId;
    public int resourceThemeId;


    public ThemeItem(int resourceColorId, int resourceThemeId){
        this.resourceColorId = resourceColorId;
        this.resourceThemeId = resourceThemeId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResourceColorId() {
        return resourceColorId;
    }

    public void setResourceColorId(int resourceColorId) {
        this.resourceColorId = resourceColorId;
    }

    public int getResourceThemeId() {
        return resourceThemeId;
    }

    public void setResourceThemeId(int resourceThemeId) {
        this.resourceThemeId = resourceThemeId;
    }
}
