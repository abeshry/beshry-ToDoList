package ca.ualberta.cs.beshry_todolist;

import java.util.ArrayList;
import java.util.Collection;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ArchiverActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_archiver);
		
		ListView archiveView = (ListView) findViewById(R.id.archiveListView);
		Collection<Task> task = ListController.getArchiveList().getArchive();
		final ArrayList<Task> archive = new ArrayList<Task>(task);
		final ArrayAdapter<Task> taskAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, archive);
		archiveView.setAdapter(taskAdapter);
		
		
		ListController.getArchiveList().addListener(new Listener() {			
			@Override
			public void update() {
				archive.clear();
				Collection<Task> task = ListController.getArchiveList().getArchive();
				archive.addAll(task);
				taskAdapter.notifyDataSetChanged();
			}
		});
		
		archiveView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				AlertDialog.Builder archiveOrDelete = new AlertDialog.Builder(ArchiverActivity.this);
				archiveOrDelete.setMessage("What do you want to do with this Archived Task?");
				archiveOrDelete.setCancelable(true);
				final int finalPosition = position;
				
				archiveOrDelete.setPositiveButton("Un-Archive", new OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Task listCheck = archive.get(finalPosition);
						ListController.getArchiveList().removeArchive(listCheck);
						Toast.makeText(ArchiverActivity.this,"To Do Archived",Toast.LENGTH_SHORT).show();
						}
				});
				
				archiveOrDelete.setNegativeButton("Delete", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Task listCheck = archive.get(finalPosition);				
						ListController.getArchiveList().deleteArchive(listCheck);	
						Toast.makeText(ArchiverActivity.this, "Task Deleted", Toast.LENGTH_SHORT).show();
					}										
				});
				archiveOrDelete.show();
			}		
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archiver, menu);
		return true;
	}


}
