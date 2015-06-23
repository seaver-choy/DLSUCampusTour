package com.qualcomm.QCARSamples.ImageTargets.model;

/**
 * Created by Interns on 5/29/2015.
 */
public class Location {
    private String name;
    private String description;
    private String icon_name;
    private int loc_id;
    private boolean hasVisited;
    private int icon;
    private boolean isBuilding;

    public Location(int loc_id, String name, String description, String icon_name, boolean hasVisited, boolean isBuilding)
    {
        this.loc_id = loc_id;
        this.name = name;
        this.description = description;
        this.icon_name = icon_name;
        this.hasVisited = hasVisited;
        this.isBuilding = isBuilding;
    }
    public Location(int loc_id, String name, String description, String icon_name, boolean hasVisited)
    {
        this.loc_id = loc_id;
        this.name = name;
        this.description = description;
        this.icon_name = icon_name;
        this.hasVisited = hasVisited;
    }

    public Location(String name, String description, String icon_name, boolean hasVisited, boolean isBuilding)
    {
        this.name = name;
        this.description = description;
        this.icon_name = icon_name;
        this.hasVisited = hasVisited;
        this.isBuilding = isBuilding;
    }

    public Location(String name, String description, String icon_name, boolean hasVisited)
    {
        this.name = name;
        this.description = description;
        this.icon_name = icon_name;
        this.hasVisited = hasVisited;
    }

    public Location()
    {
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIconName(String icon_name) {
        this.icon_name = icon_name;
    }

    public void setLocId(int loc_id) {
        this.loc_id = loc_id;
    }

    public void setHasVisited(boolean hasVisited) {
        this.hasVisited = hasVisited;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setIsBuilding(boolean isBuilding)
    {
        this.isBuilding = isBuilding;
    }
    //Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIconName() {
        return icon_name;
    }

    public int getIcon() {
        return icon;
    }

    public int getLocId() {
        return loc_id;
    }

    public boolean isHasVisited() {
        return hasVisited;
    }

    public boolean isBuilding()
    {
        return isBuilding;
    }
}
