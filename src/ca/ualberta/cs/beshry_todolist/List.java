package ca.ualberta.cs.beshry_todolist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class List implements Serializable {


	private static final long serialVersionUID = 1L;
	private ArrayList<Listener> listeners;
	private ArrayList<Task> taskList;
	private ArrayList<Task> archiveList;
	
	public List(){
		listeners= new ArrayList<Listener>();
		taskList = new ArrayList<Task>();
		archiveList= new ArrayList<Task>();
	}
	
	public Collection<Task> getTask(){
		return taskList;
	}
	
	public Collection<Task> getArchive(){
		return archiveList;
	}
	
	public void addTask(Task newtask){
		taskList.add(newtask);
		notifyListeners();
	}
	
    public void addArchive(Task archivetask){
    	archiveList.add(archivetask);
		taskList.remove(archivetask);
    	notifyListeners();
    }
	
	public void removeTask(Task removetask){
		taskList.remove(taskList.indexOf(removetask));
		notifyListeners();
	}
	
	public void removeArchive(Task removearchive){
    	taskList.add(removearchive);
		archiveList.remove(removearchive);
		notifyListeners();
	}
	
	public void deleteArchive(Task deletearchive){
		archiveList.remove(deletearchive);
		notifyListeners();
	}
	
	public void addCheck(Task addcheck) {
		notifyListeners();
	}

	public int taskSize(){
		return taskList.size();
	}
	
	public int archiveSize(){
		return archiveList.size();
	}	
    
	public void addListener(Listener l) {
		getListeners().add(l);
	}

	public void removeListener(Listener l) {
		getListeners().remove(l);
	}
    
	public void notifyListeners(){
    	for (Listener  listener : getListeners()) {
			listener.update();
		}
    }
    
	private ArrayList<Listener> getListeners() {
		if (listeners == null ) {
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
	
}