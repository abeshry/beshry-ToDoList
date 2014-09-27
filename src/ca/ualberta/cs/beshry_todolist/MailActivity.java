package ca.ualberta.cs.beshry_todolist;


import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MailActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mail);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void mailTodo(View v) {
		 
		EditText Recipient = (EditText) findViewById(R.id.RecipientID);
		EditText Subject = (EditText) findViewById(R.id.SubjectID);
		String body= "Tasks To-Do List: \n" + "   ";
		String subject = Subject.getText().toString();

		
		for (Task tasks : ListController.getTaskList().getTask()){
			body = body + tasks.getTask() + "\n" + "   ";
		}
		
		  Intent email = new Intent(Intent.ACTION_SEND);
		  email.putExtra(Intent.EXTRA_EMAIL, new String[] {Recipient.getText().toString()});
		  email.putExtra(Intent.EXTRA_SUBJECT, subject);
		  email.putExtra(Intent.EXTRA_TEXT, body);

		  email.setType("message/rfc822");

		  startActivity(Intent.createChooser(email, "Deliver Email"));
	}
	
	public void mailAll(View v) {
		 
		EditText Recipient = (EditText) findViewById(R.id.RecipientID);
		EditText Subject = (EditText) findViewById(R.id.SubjectID);
		String body= "Tasks To-Do List: \n" + "   ";
		String subject = Subject.getText().toString();

		
		for (Task tasks : ListController.getTaskList().getTask()){
			body = body + tasks.getTask() + "\n" + "   ";
		}
		
		for (Task tasks : ListController.getArchiveList().getArchive()){
			body = body + tasks.getTask() + "\n" + "   ";
		}
		
		  Intent email = new Intent(Intent.ACTION_SEND);
		  email.putExtra(Intent.EXTRA_EMAIL, new String[] {Recipient.getText().toString()});
		  email.putExtra(Intent.EXTRA_SUBJECT, subject);
		  email.putExtra(Intent.EXTRA_TEXT, body);

		  email.setType("message/rfc822");

		  startActivity(Intent.createChooser(email, "Deliver Email"));
	}
	
	public void mailSelectedTodo (View v) {
       	Intent intent= new Intent(MailActivity.this, MailSelectedTodoActivity.class);
    	startActivity(intent);
	}
	
	public void mailSelectedArchived (View v) {
       	Intent intent= new Intent(MailActivity.this, MailSelectedArchiveActivity.class);
    	startActivity(intent);
	}
}

