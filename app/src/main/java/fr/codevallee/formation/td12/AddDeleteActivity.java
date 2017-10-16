package fr.codevallee.formation.td12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddDeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delete);

        Intent intentDelete = getIntent();
        Integer restart = intentDelete.getIntExtra("id", -1);
        if (restart!=-1) {
            Log.d("ACTION", "Print clicked user, and delete button");
            TextView add_button = (TextView) findViewById(R.id.add_button);
            TextView delete_button = (TextView) findViewById(R.id.add_button);
            add_button.setVisibility(View.GONE);
            delete_button.setVisibility(View.VISIBLE);
        } else {
            Log.d("ACTION", "Interface to add a user");
            TextView add_button = (TextView) findViewById(R.id.add_button);
            TextView delete_button = (TextView) findViewById(R.id.add_button);
            add_button.setVisibility(View.VISIBLE);
            delete_button.setVisibility(View.GONE);
        }
    }

    protected void clickOnAdd() {
        EditText family_name_text = (EditText) findViewById(R.id.family_name_text);
        EditText first_name_text = (EditText) findViewById(R.id.first_name_text);
        EditText age_text = (EditText) findViewById(R.id.age_text);
        EditText job_text = (EditText) findViewById(R.id.job_text);

        Intent intent = new Intent(AddDeleteActivity.this,ListUsersActivity.class);
        intent.putExtra("add",true);
        intent.putExtra("family",family_name_text.getText());
        intent.putExtra("first",first_name_text.getText());
        intent.putExtra("age",age_text.getText());
        intent.putExtra("job",job_text.getText());
        startActivity(intent);
    }

    protected void clickOnDelete() {

    }
}
