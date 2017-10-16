package fr.codevallee.formation.td12;


import android.database.sqlite.SQLiteDatabase;

class UserDAO {
    private final UserDataSource userDataSource;

    public UserDAO(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }


}
