package edu.tkumar.fetchrewardsandroidexcercise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GroupedDataRecyclerAdapter extends RecyclerView.Adapter<GroupDataViewHolder> {

    private final List<Data> dataList;

    public GroupedDataRecyclerAdapter(List<Data> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public GroupDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.data_list_entry, parent, false);
        return new GroupDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupDataViewHolder holder, int position) {
        Data data = dataList.get(position);
        holder.id.setText(data.getId());
        holder.name.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
