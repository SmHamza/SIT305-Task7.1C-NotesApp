package com.example.task71pnotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.task71pnotesapp.data.DatabaseHelper;
import com.example.task71pnotesapp.model.Note;

public class CreateNote extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        EditText noteTitle = findViewById(R.id.editNoteTitle);
        EditText noteDescription = findViewById(R.id.editNoteText);
        Button saveButton = findViewById(R.id.editSaveButton);
        db = new DatabaseHelper(CreateNote.this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note_title = noteTitle.getText().toString();
                String note_description = noteDescription.getText().toString();
                if(!TextUtils.isEmpty(note_title) && !TextUtils.isEmpty(note_description))
                {
                    Toast.makeText(CreateNote.this, note_title, Toast.LENGTH_SHORT).show();
                    long result = db.insertNote(new Note(note_title,note_description));
                    if (result > 0)
                    {
                        Toast.makeText(CreateNote.this, "Note Successfully added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(CreateNote.this, NotesList.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(CreateNote.this, "Error Adding the note " + result, Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(CreateNote.this, "Please enter a title and a description", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}