package com.example.android.androidarchitecturecomponents;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = (LiveData<List<Note>>) noteDao.getAllNotes();
    }


    public void insert (Note note){
        new InsertNoteAsyncTask(noteDao).execute(note);
    }
    public  void update (Note note){
        new UpdateNoteAsyncTask(noteDao).execute(note);

    }
    public void delete (Note note){
        new DeleteNoteAsyncTask(noteDao).execute(note);

    }
    public  void  deleteAllNotes(){
        new DeleteAllNotesAsyncTask(noteDao).execute();

    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    // static so that it does have a reference to repository
    //prevents memory leak
    //async task
    private static class InsertNoteAsyncTask extends AsyncTask <Note, Void, Void>{
        //create member variable for note Dao
        private NoteDao noteDao;
        //pass noteDao over a constructor, neede to create db operations
        //class is static
        private InsertNoteAsyncTask (NoteDao noteDao){
            this.noteDao =noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
    private static class UpdateNoteAsyncTask extends AsyncTask <Note, Void, Void>{
        //create member variable for note Dao
        private NoteDao noteDao;
        //pass noteDao over a constructor, neede to create db operations
        //class is static
        private UpdateNoteAsyncTask (NoteDao noteDao){
            this.noteDao =noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    private static class DeleteNoteAsyncTask extends AsyncTask <Note, Void, Void>{
        //create member variable for note Dao
        private NoteDao noteDao;
        //pass noteDao over a constructor, neede to create db operations
        //class is static
        private DeleteNoteAsyncTask (NoteDao noteDao){
            this.noteDao =noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
    private static class DeleteAllNotesAsyncTask extends AsyncTask <Void, Void, Void>{
        //create member variable for note Dao
        private NoteDao noteDao;
        //pass noteDao over a constructor, neede to create db operations
        //class is static
        private DeleteAllNotesAsyncTask (NoteDao noteDao){
            this.noteDao =noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}