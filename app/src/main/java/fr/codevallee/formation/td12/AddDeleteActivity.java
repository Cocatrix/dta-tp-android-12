package fr.codevallee.formation.td12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddDeleteActivity extends AppCompatActivity {
    /**
     * This screen has two uses.
     * One is to create a new user with the add button, after filling empty fields.
     * The other is to delete/modify a user displayed on screen.
     * Both are in the same fields, only buttons see their visibility changed.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delete);

        Intent intentDelete = getIntent();
        Integer printUser = intentDelete.getIntExtra("id", -1);
        // If we arrived here with an id, thus a user to display
        if (printUser!=-1) {
            Log.d("ACTION", "Print clicked user, and delete/modify buttons");
            TextView add_button = (TextView) findViewById(R.id.add_button);
            TextView delete_button = (TextView) findViewById(R.id.delete_button);
            TextView modify_button = (TextView) findViewById(R.id.modify_button);

            add_button.setVisibility(View.GONE);
            delete_button.setVisibility(View.VISIBLE);
            modify_button.setVisibility(View.VISIBLE);

            EditText family_name_text = (EditText) findViewById(R.id.family_name_text);
            EditText first_name_text = (EditText) findViewById(R.id.first_name_text);
            EditText age_text = (EditText) findViewById(R.id.age_text);
            EditText job_text = (EditText) findViewById(R.id.job_text);

            String familyName = intentDelete.getStringExtra("family");
            String firstName = intentDelete.getStringExtra("first");
            Integer age = intentDelete.getIntExtra("age",0);
            String job = intentDelete.getStringExtra("job");

            family_name_text.setText(familyName);
            first_name_text.setText(firstName);
            age_text.setText(age.toString());
            job_text.setText(job);
        // else we are here to be able to add a user
        } else {
            Log.d("ACTION", "Interface to add a user");
            TextView add_button = (TextView) findViewById(R.id.add_button);
            TextView delete_button = (TextView) findViewById(R.id.delete_button);
            TextView modify_button = (TextView) findViewById(R.id.modify_button);

            add_button.setVisibility(View.VISIBLE);
            delete_button.setVisibility(View.GONE);
            modify_button.setVisibility(View.GONE);
        }
    }

    protected void clickOnAdd(View view) {
        Log.d("ACTION","CLICKED ON ADD (FOR ENTRY)");
        EditText family_name_text = (EditText) findViewById(R.id.family_name_text);
        EditText first_name_text = (EditText) findViewById(R.id.first_name_text);
        EditText age_text = (EditText) findViewById(R.id.age_text);
        EditText job_text = (EditText) findViewById(R.id.job_text);
        // sending Intent with "add"=true, and fields for creation
        Intent intent = new Intent(AddDeleteActivity.this,ListUsersActivity.class);
        intent.putExtra("add",true);
        intent.putExtra("family",family_name_text.getText().toString());
        intent.putExtra("first",first_name_text.getText().toString());
        intent.putExtra("age",Integer.parseInt(age_text.getText().toString()));
        intent.putExtra("job",job_text.getText().toString());
        Log.d("FAMILY",family_name_text.getText().toString());
        startActivity(intent);
    }

    protected void clickOnDelete(View view) {
        Log.d("ACTION","CLICKED ON DELETE (FOR PRINTED ENTRY)");
        Intent intentReceived = getIntent();
        Integer idUserToDelete = intentReceived.getIntExtra("id", -1);
        // sending Intent with "delete"=true, and id for deletion
        Intent intentToSend = new Intent(AddDeleteActivity.this,ListUsersActivity.class);
        intentToSend.putExtra("delete",true);
        intentToSend.putExtra("id", idUserToDelete);
        startActivity(intentToSend);
    }

    protected void clickOnModify(View view) {
        Log.d("ACTION","CLICKED ON MODIFY (FOR ENTRY)");
        EditText family_name_text = (EditText) findViewById(R.id.family_name_text);
        EditText first_name_text = (EditText) findViewById(R.id.first_name_text);
        EditText age_text = (EditText) findViewById(R.id.age_text);
        EditText job_text = (EditText) findViewById(R.id.job_text);

        Intent intentReceived = getIntent();
        Integer idUserToModify = intentReceived.getIntExtra("id", -1);
        // sending Intent with "modify"=true, id and fields for update
        Intent intentToSend = new Intent(AddDeleteActivity.this,ListUsersActivity.class);
        intentToSend.putExtra("modify",true);
        intentToSend.putExtra("id", idUserToModify);
        intentToSend.putExtra("family",family_name_text.getText().toString());
        intentToSend.putExtra("first",first_name_text.getText().toString());
        intentToSend.putExtra("age",Integer.parseInt(age_text.getText().toString()));
        intentToSend.putExtra("job",job_text.getText().toString());
        Log.d("FAMILY",family_name_text.getText().toString());
        startActivity(intentToSend);
    }
}
