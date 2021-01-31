package edu.tkumar.fetchrewardsandroidexcercise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerViewHolder> {

    private final List<GroupData> groupDataList;

    public MainRecyclerAdapter(List<GroupData> groupDataList) {
        this.groupDataList = groupDataList;
    }

    @NonNull
    @Override
    public MainRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.grouped_list_entry, parent, false);
        return new MainRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerViewHolder holder, int position) {
        GroupData groupData = groupDataList.get(position);
        List<Data> dataList = groupData.getGroupItems();

        holder.groupName.setText(String.format("List Id: %s", groupData.getGroupName()));

        GroupedDataRecyclerAdapter groupedDataRecyclerAdapter = new GroupedDataRecyclerAdapter(dataList);
        holder.groupedRecycler.setAdapter(groupedDataRecyclerAdapter);
        //holder.groupedRecycler.setLayoutManager(new LinearLayoutManager(mainActivity));
    }

    @Override
    public int getItemCount() {
        return groupDataList.size();
    }
}
