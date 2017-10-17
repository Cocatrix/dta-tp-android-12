package fr.codevallee.formation.td12;


import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

class UserDAO {
    private static final String TABLE_NAME = /*/Resources.getSystem().getString(R.string.user_table_name); /*/ "User";/**/
    private static final String COL_ID = /*/Resources.getSystem().getString(R.string.user_col_id); /*/ "id";/**/
    private static final String COL_FAMILY_NAME = /*/Resources.getSystem().getString(R.string.user_col_family_name); /*/ "family_name";/**/
    private static final String COL_FIRST_NAME = /*/Resources.getSystem().getString(R.string.user_col_first_name); /*/ "first_name";/**/
    private static final String COL_AGE = /*/Resources.getSystem().getString(R.string.user_col_age); /*/ "age";/**/
    private static final String COL_JOB = /*/Resources.getSystem().getString(R.string.user_col_job); /*/ "job";/**/
    private static final String CLAUSE = /*/Resources.getSystem().getString(R.string.clause); /*/ " = ?";/**/
    private final UserDataSource userDataSource;

    public UserDAO(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    public synchronized User create(User user) {
        // Using equivalent of a hash table to store a request
        ContentValues values = new ContentValues();
        values.put(COL_FAMILY_NAME, user.getFamilyName());
        values.put(COL_FIRST_NAME, user.getFirstName());
        values.put(COL_AGE, user.getAge());
        values.put(COL_JOB, user.getJob());
        // Processing insert request
        Integer id = (int) userDataSource.getDB().insert(TABLE_NAME,null,values);

        return user;
    }
    public synchronized User update(User user) {
        ContentValues values = new ContentValues();
        values.put(COL_FAMILY_NAME, user.getFamilyName());
        values.put(COL_FIRST_NAME, user.getFirstName());
        values.put(COL_AGE, user.getAge());
        values.put(COL_JOB, user.getJob());
        // Adding clause "where id = []"
        String clause = COL_ID + CLAUSE;
        String[] clauseArgs = new String[] {user.getId().toString()};
        // Processing update request
        userDataSource.getDB().update(TABLE_NAME,values,clause,clauseArgs);

        return user;
    }

    public synchronized void delete(User user) {
        // Adding clause "where id = []"
        String clause = COL_ID + CLAUSE;
        String[] clauseArgs = new String[] {user.getId().toString()};
        // Processing delete request
        userDataSource.getDB().delete(TABLE_NAME,clause,clauseArgs);
    }
    /* DOES NOT SEEM TO WORK
    public synchronized void deleteAll() {
        // Adding clause "where id = []"
        String clause = COL_ID + CLAUSE;
        String[] clauseArgs = new String[] {"*"};
        // Processing delete request
        userDataSource.getDB().delete(TABLE_NAME,clause,clauseArgs);
    }*/

    public synchronized User read(User user) {
        String allColumns[] = new String[]{COL_ID,COL_FAMILY_NAME,COL_FIRST_NAME,COL_AGE,COL_JOB};
        // Adding clause "where id = []"
        String clause = COL_ID + CLAUSE;
        String[] clauseArgs = new String[] {user.getId().toString()};
        // Processing select query
        Cursor cursor = userDataSource.getDB().query(TABLE_NAME,allColumns,clause,clauseArgs,null,null,null);

        // Read the cursor
        cursor.moveToFirst();
        user.setFamilyName(cursor.getString(1));
        user.setFirstName(cursor.getString(2));
        user.setAge(cursor.getInt(3));
        user.setJob(cursor.getString(4));
        cursor.close();

        return user;
    }

    public synchronized List<User> readAll() {
        String allColumns[] = new String[]{COL_ID,COL_FAMILY_NAME,COL_FIRST_NAME,COL_AGE,COL_JOB};
        // Processing select query
        Cursor cursor = userDataSource.getDB().query(TABLE_NAME,allColumns,null,null,null,null,null);

        List<User> users = new ArrayList<User>();
        // Iterate in the list to read all users
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            users.add(new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4)));
            cursor.moveToNext();
        }
        cursor.close();

        return users;
    }
}
