package edu.tkumar.fetchrewardsandroidexcercise;

public class Data implements Comparable<Data> {

    private String id;
    private String listID;
    private String name;

    public Data(String id, String listID, String name) {
        this.id = id;
        this.listID = listID;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getListID() {
//        return listID;
//    }
//
//    public void setListID(String listID) {
//        this.listID = listID;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Data o) {
        return Integer.parseInt(name.substring(5)) - (Integer.parseInt(o.getName().substring(5)));
    }
}