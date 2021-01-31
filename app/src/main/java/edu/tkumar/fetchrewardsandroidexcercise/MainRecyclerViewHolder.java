package edu.tkumar.fetchrewardsandroidexcercise;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainRecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView groupName;
    RecyclerView groupedRecycler;

    public MainRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        groupName = itemView.findViewById(R.id.listIdTextView);
        groupedRecycler = itemView.findViewById(R.id.groupedDataRecyclerView);
    }
}
