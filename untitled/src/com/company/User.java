package com.company;

public class User implements Comparable {
    /*! Properties */
    private String id;
    private String group;
    private String subgroup;

    /*! Get Methods */
    public String getGroup() {
        return group;
    }

    public String getId() {
        return id;
    }

    public String getSubgroup() {
        return subgroup;
    }

    /*! Set methods */
    public void setGroup(String group) {
        this.group = group;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
    }

    /*! Constructors */

    /*! Default */
    User() {
        this.id = null;
        this.group = null;
        this.subgroup = null;
    }

    /*! Explicit by all params */
    User(String id, String group, String subgroup) {
        this.id = id;
        this.group = group;
        this.subgroup = subgroup;
    }

    /*! Explicit by group */
    User(String group) {
        this.id = null;
        this.group = group;
        this.subgroup = null;
    }

    /*! Comparable */
    @Override
    public int compareTo(Object o) {
        return id.compareTo(((User)o).id);
    }

    @Override
    public String toString() {
        return "ID: "  + this.id + "\n" +
                "Group: "  + this.group + "\n" +
                "Subgroup: "  + this.subgroup + "\n";
    }

}
