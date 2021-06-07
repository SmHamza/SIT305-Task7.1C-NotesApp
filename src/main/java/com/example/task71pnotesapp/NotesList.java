package com.example.task71pnotesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.task71pnotesapp.data.DatabaseHelper;
import com.example.task71pnotesapp.model.Note;

import java.util.List;

public class NotesList extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    List<Note> notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        DatabaseHelper db = new DatabaseHelper(this);
        notes = db.getNotes();
        recyclerView = findViewById(R.id.notesRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, notes);
        recyclerView.setAdapter(adapter);
    }
}