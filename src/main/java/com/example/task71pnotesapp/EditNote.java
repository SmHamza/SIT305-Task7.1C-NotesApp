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

public class EditNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        EditText noteTitle = findViewById(R.id.editNoteTitle);
        EditText noteDescription = findViewById((R.id.editNoteText));
        Button saveButton = findViewById(R.id.editSaveButton);

        Intent intent = getIntent();
        int id = intent.getIntExtra("ID", 0);

        DatabaseHelper db = new DatabaseHelper(this);
        Note note = db.getNote(id);

        noteTitle.setText(note.getNote_title());
        noteDescription.setText(note.getNote_description());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noteTitle.getText().length() !=0 && noteDescription.getText().length() !=0)
                {
                    note.setNote_title(noteTitle.getText().toString());
                    note.setNote_description(noteDescription.getText().toString());
                    Toast.makeText(EditNote.this, noteTitle.toString(), Toast.LENGTH_SHORT).show();
                    int result = db.updateNote(note);
                    if (result > 0)
                    {
                        Toast.makeText(EditNote.this, "Note Successfully Updated ", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(EditNote.this, "Error Updating the note ", Toast.LENGTH_SHORT).show();
                    }
                    onBackPressed();
                    Intent intent = new Intent(EditNote.this, NotesList.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(EditNote.this, "Please enter a title and a description", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}