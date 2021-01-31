package edu.tkumar.fetchrewardsandroidexcercise;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroupDataViewHolder extends RecyclerView.ViewHolder {

    TextView id;
    TextView name;

    public GroupDataViewHolder(@NonNull View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.idTextView);
        name = itemView.findViewById(R.id.nameTextView);
    }
}
