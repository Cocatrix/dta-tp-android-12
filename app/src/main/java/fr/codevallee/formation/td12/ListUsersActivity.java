package fr.codevallee.formation.td12;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListUsersActivity extends AppCompatActivity {
    private UserDataSource userDataSource = new UserDataSource(this);
    private UserDAO userDAO = userDataSource.newUserDAO();
    private final String[] users = {"A","B","C"};//getResources().getStringArray(R.array.tab_users);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        Intent intentAddOrDelete = getIntent();
        Boolean addIntent = intentAddOrDelete.getBooleanExtra("add", false);
        Boolean deleteIntent = intentAddOrDelete.getBooleanExtra("delete", false);

        if(addIntent) {
            String familyName = intentAddOrDelete.getStringExtra("family");
            String firstName = intentAddOrDelete.getStringExtra("first");
            Integer age = intentAddOrDelete.getIntExtra("age",0);
            String job = intentAddOrDelete.getStringExtra("job");
            this.userDAO.create(new User((Integer) null, familyName, firstName, age, job));
        }
        if(deleteIntent) {
            Integer idUserToDelete = intentAddOrDelete.getIntExtra("id", -1);
            this.userDAO.delete(new User(idUserToDelete, null, null, null, null));
        }
        this.printListUsers();
    }

    protected void printListUsers() {
        // Does not work anyway : this.userDAO.deleteAll();
        for(String familyName : this.users) {
            this.userDAO.create(new User(-1, familyName, "Jean", 21, null));
        }
        List<User> newUsers = this.userDAO.readAll();

        ListView listUsers = (ListView) findViewById(R.id.list_users);
        final ArrayAdapter<User> adapter = new ArrayAdapter<User>(ListUsersActivity.this,
                android.R.layout.simple_list_item_1,newUsers);
        listUsers.setAdapter(adapter);

        listUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User item = adapter.getItem(position);

                Intent intent = new Intent(ListUsersActivity.this,AddDeleteActivity.class);
                intent.putExtra("id",item.getId());
                intent.putExtra("family", item.getFamilyName());
                intent.putExtra("first",item.getFirstName());
                intent.putExtra("age",item.getAge());
                intent.putExtra("job",item.getJob());
                startActivity(intent);
            }
        });
    }

    protected void buttonAddUser() {
        Intent intent = new Intent(ListUsersActivity.this,AddDeleteActivity.class);
        intent.putExtra("id",-1);
        startActivity(intent);
    }
}
