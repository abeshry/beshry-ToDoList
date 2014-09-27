package ca.ualberta.cs.beshry_todolist;

import java.io.Serializable;

public class Task implements Serializable{

	private static final long serialVersionUID = 1L;
	private String task;
	private boolean listCheck = false;
	private boolean archiveCheck = false;
	private char check = '\u2713';
	
	public Task (String task){
		this.task = "[   ]" + " " + task;
	}
	
	public String getTask() {
		return task;
	}
	public String toString() {
		return getTask();
	}
	
	public boolean getListCheck() {
		return listCheck;
	}
	
	public boolean getArchiveCheck() {
		return archiveCheck;
	}
	
	public void check() {
		if (listCheck == false){
			task = "[ "+check+" ] " +  task.substring(6, task.length());
			listCheck = true;
		} else {
			task = "[   ] "+ task.substring(6, task.length());
			listCheck = false;
		}
	}
	
}
