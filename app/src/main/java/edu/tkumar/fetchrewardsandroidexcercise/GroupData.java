package edu.tkumar.fetchrewardsandroidexcercise;

import java.util.List;

public class GroupData implements Comparable<GroupData>{

    private final String groupName;
    private final List<Data> groupItems;

    public GroupData(String groupName, List<Data> groupItems) {
        this.groupName = groupName;
        this.groupItems = groupItems;
    }

    public String getGroupName() {
        return groupName;
    }

//    public void setGroupName(String groupName) {
//        this.groupName = groupName;
//    }

    public List<Data> getGroupItems() {
        return groupItems;
    }

//    public void setGroupItems(List<Data> groupItems) {
//        this.groupItems = groupItems;
//    }

    @Override
    public int compareTo(GroupData o) {
        return groupName.compareTo(o.getGroupName());
    }
}
