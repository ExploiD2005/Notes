package com.example.notes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinnerDaysOfWeek;
    private RadioGroup radioGroupPriority;

    private MainViewModel viewModel;
    //private NotesDatabase database;
   /* private NotesDBHelper dbHelper;
    private SQLiteDatabase database;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        //database = NotesDatabase.getInstance(this);
        /*dbHelper = new NotesDBHelper(this);
        database = dbHelper.getWritableDatabase();*/
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        spinnerDaysOfWeek = findViewById(R.id.spinnerDayOfWeek);
        radioGroupPriority = findViewById(R.id.radioGroupPriority);
    }

    public void onClickSaveNotes(View view) {
        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        int dayOfWeek = spinnerDaysOfWeek.getSelectedItemPosition();
        int radioButtonId = radioGroupPriority.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);
        int priority = Integer.parseInt(radioButton.getText().toString());
        if (isFilled(title, description)) {
            Note note = new Note(title, description, dayOfWeek, priority);
            viewModel.insertNote(note);
            //database.notesDao().insertNote(note);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.warning_fill_field), Toast.LENGTH_SHORT).show();
        }
        /*if (isFilled(title, description)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NotesContract.NotesEntry.COLUMN_TITLE, title);
            contentValues.put(NotesContract.NotesEntry.COLUMN_DESCRIPTION, description);
            contentValues.put(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK, dayOfWeek + 1);
            contentValues.put(NotesContract.NotesEntry.COLUMN_PRIORITY, priority);
            //database.insert(NotesContract.NotesEntry.TABLE_NAME, null, contentValues);
            //Note note = new Note(title, description, dayOfWeek, priority);
            //MainActivity.notes.add(note);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.warning_fill_field), Toast.LENGTH_SHORT).show();
        }*/

    }
    private boolean isFilled(String title, String description) {
        return !title.isEmpty() && !description.isEmpty();
    }
}
