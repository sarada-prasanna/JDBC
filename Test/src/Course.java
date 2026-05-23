public class Course {
    private int id;
    private String courseName;
    private int duration;
	public Course() {
		super();
	}
	public Course(int id, String courseName, int duration) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.duration = duration;
	}
	public Course(String courseName, int duration) {
		super();
		this.courseName = courseName;
		this.duration = duration;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
   
}
