package com.example.task71pnotesapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task71pnotesapp.model.Note;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Note> notes;

    Adapter(Context context, List<Note> notes){
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = notes.get(position).getNote_title();
//        String description = notes.get(position).getNote_description();
        holder.noteTitle.setText(title);
//        holder.noteDetails.setText(description);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.myNoteTitleText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), NoteDetails.class);
                    intent.putExtra("ID", notes.get(getAdapterPosition()).getNote_id());
                    v.getContext().startActivity(intent);

                }
            });
        }
    }
}
