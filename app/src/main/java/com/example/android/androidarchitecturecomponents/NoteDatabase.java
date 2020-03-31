package com.example.android.androidarchitecturecomponents;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version =1)
public abstract class NoteDatabase extends RoomDatabase {

    //turns the class into a singleton
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();


    //create Db
    public static synchronized NoteDatabase getInstance(Context context) {

        //db is instantialised only if we dont have an instance
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();

        }

        return instance;
    }
    //to insert data when db is first created
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();

        }
    };

    //asynctask
    private static class PopulateDbAsyncTask extends AsyncTask <Void, Void, Void>{

        private NoteDao noteDao;

        public PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            noteDao.insert(new Note("Title 1", "Description 1", 1) );
            noteDao.insert(new Note("Title 2", "Description 2", 2) );
            noteDao.insert(new Note("Title 3", "Description 3", 3) );
            return null;
        }
    }






}
