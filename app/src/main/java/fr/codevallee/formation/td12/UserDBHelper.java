package fr.codevallee.formation.td12;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "Users";
    private static Integer DB_VERSION = 1;

    public UserDBHelper(Context context) {
        /**
         * Allows to call constructor with context only.
         */
        this(context, DB_NAME, null, DB_VERSION);
    }

    private UserDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        /**
         * Matches abstract parent constructor.
         */
        super(context, DB_NAME, null, DB_VERSION);
    }

    private String queryCreate() {
        //return Resources.getSystem().getString(R.string.create_table_user_query);
        return "CREATE TABLE User ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "family_name VARCHAR(30) NOT NULL,"
                + "first_name VARCHAR(15) NOT NULL,"
                + "age UNSIGNED INTEGER NOT NULL,"
                + "job VARCHAR(60)"
                + ");";
    }

    private String queryDrop() {
        //return Resources.getSystem().getString(R.string.drop_table_user_query);
        return "DROP TABLE IF EXISTS User";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.queryCreate());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(this.queryDrop());
        db.execSQL(this.queryCreate());

    }
}
