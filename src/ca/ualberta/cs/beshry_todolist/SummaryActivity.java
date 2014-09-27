package ca.ualberta.cs.beshry_todolist;

import java.util.ArrayList;
import java.util.Collection;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SummaryActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);

		List taskList = ListController.getTaskList();
		List archiveList = ListController.getArchiveList();
		taskSummary(taskList);
		archiveSummary(archiveList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summary, menu);
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
	
	public void taskSummary(List list) {
		
		Collection<Task> task = ListController.getTaskList().getTask();
		final ArrayList<Task> tasks = new ArrayList<Task>(task);

		int tasklength = ListController.getTaskList().taskSize();
		TextView taskChecked = (TextView) findViewById(R.id.tasksCheckedCount);
		TextView taskUnchecked = (TextView) findViewById(R.id.tasksUncheckedCount);

		int checked = 0;
		int unchecked = 0;
		for (int i = 0; i < tasklength; i++) {
			Task taskCheck = tasks.get(i);
			if (taskCheck.getListCheck() == true) {
				checked++;
			} else {
				unchecked++;
			}
		}
		taskChecked.setText(Integer.toString(checked));
		taskUnchecked.setText(Integer.toString(unchecked));
	}
	
	public void archiveSummary(List list) {
		
		Collection<Task> archive = ListController.getArchiveList().getArchive();
		final ArrayList<Task> archives = new ArrayList<Task>(archive);

		int archivelength = ListController.getArchiveList().archiveSize();
		TextView totalArchive = (TextView) findViewById(R.id.archivedTotalCount);
		TextView checkedArchive = (TextView) findViewById(R.id.archivedCheckedCount);
		TextView uncheckedArchive = (TextView) findViewById(R.id.archivedUncheckedCount);

		int checked = 0;
		int unchecked = 0;
		for (int i = 0; i < archivelength; i++) {
			Task taskCheck = archives.get(i);
			if (taskCheck.getListCheck() == true) {
				checked++;
			} else {
				unchecked++;
			}
		}
		totalArchive.setText(Integer.toString(archivelength));
		checkedArchive.setText(Integer.toString(checked));
		uncheckedArchive.setText(Integer.toString(unchecked));

	}
}
