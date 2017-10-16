package fr.codevallee.formation.td12;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class UserDataSource {
    private final UserDBHelper helper;
    private SQLiteDatabase db;

    public UserDataSource(Context context) {
        helper = new UserDBHelper(context);
    }

    public SQLiteDatabase getDB() {
        if (db == null) {
            this.open();
        }
        return db;
    }

    public void open() {
        db = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    // Factory
    public UserDAO newUserDAO() {
        return new UserDAO(this);
    }
}
