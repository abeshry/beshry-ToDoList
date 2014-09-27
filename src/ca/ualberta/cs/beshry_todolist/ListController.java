package ca.ualberta.cs.beshry_todolist;

import java.io.Serializable;

public class ListController implements Serializable {

	private static final long serialVersionUID = 1L;
	public static List taskList=null;

	public void swap(List list){
		taskList= list;
	}
	
	public static List getTaskList(){
		if (taskList == null){
			taskList=new List();
			return taskList;
		}else{
			return taskList;
		}
	}
	
	public static List getArchiveList(){
		if (taskList == null){
			taskList=new List();
			return taskList;
		}else{
			return taskList;
		}
	}
	
	public void addTask(Task task){
		getTaskList().addTask(task);
	}
	
	public void addArchive(Task archive) {
		getArchiveList().addArchive(archive);
	}
}