package ca.ualberta.cs.beshry_todolist;

import java.util.ArrayList;
import java.util.Collection;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MailSelectedTodoActivity extends ActionBarActivity {
	
	String body = "Tasks To-Do List: \n" + "   ";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mail_selected_todo);		

		ListView listView = (ListView) findViewById(R.id.taskListSelection);
		Collection<Task> task = ListController.getTaskList().getTask();
		final ArrayList<Task> selectedTodo = new ArrayList<Task>(task);
		final ArrayAdapter<Task> taskAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, selectedTodo);
		listView.setAdapter(taskAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				AlertDialog.Builder selectTodo = new AlertDialog.Builder(MailSelectedTodoActivity.this);
				selectTodo.setMessage("Would you like to add this Todo to an Email?");
				selectTodo.setCancelable(true);
				final int finalPosition = position;
				
				selectTodo.setPositiveButton("Yes", new OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Task addedtask = selectedTodo.get(finalPosition);
						body = body + addedtask.getTask()+"\n   ";
						Toast.makeText(MailSelectedTodoActivity.this,"Task Added To Email",Toast.LENGTH_SHORT).show();

						}
				});
				
				selectTodo.setNegativeButton("No", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}										
				});
				selectTodo.show();
			}		
		});
	}		
				
	public void mailSelectedTodos(View v) {
		
		  Intent email = new Intent(Intent.ACTION_SEND);
		  email.putExtra(Intent.EXTRA_TEXT, body);

		  email.setType("message/rfc822");

		  startActivity(Intent.createChooser(email, "Deliver Email"));
	}
}

