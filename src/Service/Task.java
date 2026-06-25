package Service;

public class Task {
	
	    private int taskId;
	    private String taskName;
	    private int startTime;
	    private int endTime;

	    public Task(int taskId,
	                String taskName,
	                int startTime,
	                int endTime) {

	        this.taskId = taskId;
	        this.taskName = taskName;
	        this.startTime = startTime;
	        this.endTime = endTime;
	    }

	    public int getTaskId() {
	        return taskId;
	    }

	    public String getTaskName() {
	        return taskName;
	    }

	    public int getStartTime() {
	        return startTime;
	    }

	    public int getEndTime() {
	        return endTime;
	    }

	    @Override
	    public String toString() {
	        return "Task ID: " + taskId +
	               ", Name: " + taskName +
	               ", Start: " + startTime +
	               ", End: " + endTime;
	    }
}
