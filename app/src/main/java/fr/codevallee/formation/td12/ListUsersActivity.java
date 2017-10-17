package fr.codevallee.formation.td12;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * This class lists the database users. Only this activity works with the database.
 * That is why the CRUD actions are done here.
 * A ListView is used to display the users, and clicking on one of them shows details.
 * Same for add button, goes to AddDeleteActivity as well.
 */
public class ListUsersActivity extends AppCompatActivity {
    private UserDataSource userDataSource = new UserDataSource(this);
    private UserDAO userDAO = userDataSource.newUserDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        // We use the intent to know whether we come from the AddDeleteActivity
        Intent intentAddOrDelete = getIntent();
        // We used booleans to specify the action to be done on database
        Boolean addIntent = intentAddOrDelete.getBooleanExtra("add", false);
        Boolean deleteIntent = intentAddOrDelete.getBooleanExtra("delete", false);
        Boolean modifyIntent = intentAddOrDelete.getBooleanExtra("modify", false);
        // Depending on action, getting parameters and calling userDAO corresponding method
        if(addIntent) {
            Log.d("ACTION","ADDING SOMEONE");
            String familyName = intentAddOrDelete.getStringExtra("family");
            String firstName = intentAddOrDelete.getStringExtra("first");
            Integer age = intentAddOrDelete.getIntExtra("age",0);
            String job = intentAddOrDelete.getStringExtra("job");
            this.userDAO.create(new User((Integer) null, familyName, firstName, age, job));
        }
        if(deleteIntent) {
            Log.d("ACTION","DELETING SOMEONE");
            Integer idUserToDelete = intentAddOrDelete.getIntExtra("id", -1);
            this.userDAO.delete(new User(idUserToDelete, null, null, null, null));
        }
        if(modifyIntent) {
            Log.d("ACTION","MODIFYING SOMEONE");
            Integer id = intentAddOrDelete.getIntExtra("id",-1);
            String familyName = intentAddOrDelete.getStringExtra("family");
            String firstName = intentAddOrDelete.getStringExtra("first");
            Integer age = intentAddOrDelete.getIntExtra("age",0);
            String job = intentAddOrDelete.getStringExtra("job");
            this.userDAO.update(new User(id, familyName, firstName, age, job));
        }
        this.printListUsers();
    }

    private void printListUsers() {
        List<User> newUsers = this.userDAO.readAll();

        ListView listUsers = (ListView) findViewById(R.id.list_users);
        final ArrayAdapter<User> adapter = new ArrayAdapter<User>(ListUsersActivity.this,
                android.R.layout.simple_list_item_1,newUsers);
        listUsers.setAdapter(adapter);

        listUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User item = adapter.getItem(position);
                Log.d("ACTION","CLICKED ON SOMEONE : " + item.toString());
                // We cannot send a User in an intent, so we send each attribute in extra
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

    protected void buttonAddUser(View view) {
        Log.d("ACTION","CLICKED ON ADD");
        Intent intent = new Intent(ListUsersActivity.this,AddDeleteActivity.class);
        intent.putExtra("id",-1);
        startActivity(intent);
    }
}
