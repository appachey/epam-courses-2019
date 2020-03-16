package ua.nure.bychkov.practice7.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Medicine entity.
 *
 * @author Bychkov Sergey.
 */
public class Medicine {
    private String name;
    private String pharm;
    private String group;
    private List<String> analogs;
    private List<Version> versions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPharm() {
        return pharm;
    }

    public void setPharm(String pharm) {
        this.pharm = pharm;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<String> getAnalogs() {
        if (analogs == null) {
            return analogs = new ArrayList<>();
        }
        return analogs;
    }

    public void setAnalog(String analog) {
        if (analogs == null) {
            analogs = new ArrayList<>();
            analogs.add(analog);
        } else {
            analogs.add(analog);
        }
    }

    public List<Version> getVersions() {
        if (versions == null) {
            return versions = new ArrayList<>();
        }
        return versions;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Name: " + name).append("\n")
                .append("Pharm: " + pharm).append("\n")
                .append("Group: " + group).append("\n");
        for (String analog : analogs) {
            result.append("Analog: " + analog).append("\n");
        }
        for (Version version : versions) {
            result.append(version);
        }
        return result.toString();
    }
}