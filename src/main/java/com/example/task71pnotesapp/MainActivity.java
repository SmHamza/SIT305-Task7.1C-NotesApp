package com.example.task71pnotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button createButton;
    private Button showNotesButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createButton = (Button) findViewById(R.id.newNoteButton);
        showNotesButton = (Button) findViewById(R.id.showNotesButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateNote();
            }
        });
        showNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllNotes();
            }
        });
    }

    private void showAllNotes() {
        Intent intent = new Intent(this, NotesList.class);
        startActivity(intent);
    }

    private void openCreateNote() {
        Intent intent = new Intent(this, CreateNote.class);
        startActivity(intent);
    }
}