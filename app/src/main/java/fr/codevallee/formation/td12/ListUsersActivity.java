package fr.codevallee.formation.td12;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListUsersActivity extends AppCompatActivity {
    private final String[] users = {"A","B","C"};//getResources().getStringArray(R.array.tab_users);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        this.printListUsers();
    }

    protected void printListUsers() {
        UserDataSource userDataSource = new UserDataSource(this);
        userDataSource.open();
        UserDAO userDAO = userDataSource.newUserDAO();
        for(String familyName : this.users) {
            userDAO.create(new User(-1, familyName, "Jean", 21, null));
        }
        List<User> newUsers = userDAO.readAll();

        ListView listUsers = (ListView) findViewById(R.id.list_users);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListUsersActivity.this,
                android.R.layout.simple_list_item_1,users);
        listUsers.setAdapter(adapter);
    }
}
