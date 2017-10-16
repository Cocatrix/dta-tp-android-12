package fr.codevallee.formation.td12;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

class UserDAO {
    private static final String TABLE_NAME = "User";
    private static final String COL_ID = "id";
    private static final String COL_FAMILY_NAME = "family_name";
    private static final String COL_FIRST_NAME = "first_name";
    private static final String COL_AGE = "age";
    private static final String COL_JOB = "job";
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
        String clause = COL_ID + " = ?";
        String[] clauseArgs = new String[] {user.getId().toString()};
        // Processing update request
        userDataSource.getDB().update(TABLE_NAME,values,clause,clauseArgs);

        return user;
    }

    public synchronized void delete(User user) {
        // Adding clause "where id = []"
        String clause = COL_ID + " = ?";
        String[] clauseArgs = new String[] {user.getId().toString()};
        // Processing delete request
        userDataSource.getDB().delete(TABLE_NAME,clause,clauseArgs);
    }

    public synchronized User read(User user) {
        String allColumns[] = new String[]{COL_ID,COL_FAMILY_NAME,COL_FIRST_NAME,COL_AGE,COL_JOB};
        // Adding clause "where id = []"
        String clause = COL_ID + " = ?";
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
}
