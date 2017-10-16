package fr.codevallee.formation.td12;


import android.content.Context;
import android.database.SQLException;
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

    private void open() throws SQLException {
        db = helper.getWritableDatabase();
    }

    private void close() {
        helper.close();
    }

    // Factory
    public UserDAO newUserDAO() {
        return new UserDAO(this);
    }
}
