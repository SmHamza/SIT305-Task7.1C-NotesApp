package com.example.task71pnotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.task71pnotesapp.data.DatabaseHelper;
import com.example.task71pnotesapp.model.Note;

public class NoteDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        TextView titleTextView = findViewById(R.id.sNoteTitle);
        TextView descriptionTextView = findViewById(R.id.sNoteDetails);
        Button updateButton =  findViewById(R.id.updateButton);
        Button deleteButton = findViewById(R.id.deleteButton);

        Intent intent = getIntent();
        int id = intent.getIntExtra("ID", 0);

        DatabaseHelper db = new DatabaseHelper(this);
        Note note = db.getNote(id);

        titleTextView.setText(note.getNote_title());
        descriptionTextView.setText(note.getNote_description());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteDetails.this, EditNote.class);
                intent.putExtra("ID", note.getNote_id());
                startActivity(intent);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteNote(note);
                startActivity(new Intent(getApplicationContext(), NotesList.class));
            }
        });

    }
}