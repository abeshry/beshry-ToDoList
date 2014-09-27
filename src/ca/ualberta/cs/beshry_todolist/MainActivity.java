/*
A To Do List - To help remind you of daily tasks

Copyright (C) 2014 Ahmed Beshry beshry@ualberta.ca

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.

*/



package ca.ualberta.cs.beshry_todolist;

import java.util.ArrayList;
import java.util.Collection;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		ListView listView = (ListView) findViewById(R.id.taskList);
		Collection<Task> task = ListController.getTaskList().getTask();
		final ArrayList<Task> list = new ArrayList<Task>(task);
		final ArrayAdapter<Task> taskAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(taskAdapter);
		
		
		ListController.getTaskList().addListener(new Listener() {			
			@Override
			public void update() {
				list.clear();
				Collection<Task> task = ListController.getTaskList().getTask();
				list.addAll(task);
				taskAdapter.notifyDataSetChanged();
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Task taskCheck = list.get(position);
				taskCheck.check();
				ListController.getTaskList().addCheck(taskCheck);
			}});   
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				AlertDialog.Builder archiveOrDelete = new AlertDialog.Builder(MainActivity.this);
				archiveOrDelete.setMessage("What do you want to do with this Task?");
				archiveOrDelete.setCancelable(true);
				final int finalPosition = position;
				
				archiveOrDelete.setPositiveButton("Archive", new OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Task archive = list.get(finalPosition);
						ListController.getArchiveList().addArchive(archive);
						Toast.makeText(MainActivity.this,"To Do Archived",Toast.LENGTH_SHORT).show();
						}
				});
				
				archiveOrDelete.setNegativeButton("Delete", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Task task = list.get(finalPosition);				
						ListController.getTaskList().removeTask(task);	
						Toast.makeText(MainActivity.this, "Task Deleted", Toast.LENGTH_SHORT).show();
					}										
				});
				archiveOrDelete.show();
				return false;
			}		
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    	case R.id.archiveID:
	    	   	Toast.makeText(this,"Archives Page",Toast.LENGTH_SHORT).show();
	           	Intent intent= new Intent(MainActivity.this, ArchiverActivity.class);
	        	startActivity(intent);
	        	return true;
	    	case R.id.summaryID:
	    	   	Toast.makeText(this,"Summary Page",Toast.LENGTH_SHORT).show();
	           	Intent intentSummary= new Intent(MainActivity.this, SummaryActivity.class);
	        	startActivity(intentSummary);
	        	return true;
	    	case R.id.mailID:
	    	   	Toast.makeText(this,"Email Page",Toast.LENGTH_SHORT).show();
	           	Intent intentMail= new Intent(MainActivity.this, MailActivity.class);
	        	startActivity(intentMail);
	        	return true;

	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

	public void addTask(View v) {
		ListController newTask = new ListController();
		EditText textView = (EditText) findViewById(R.id.taskEditText);
		String text = textView.getText().toString();
		if (text.matches("")) {
		    Toast.makeText(this, "You didn't enter a Task!", Toast.LENGTH_SHORT).show();
		} else {
			newTask.addTask(new Task(textView.getText().toString()));
			textView.setText("");
			Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();
		}
	}
}