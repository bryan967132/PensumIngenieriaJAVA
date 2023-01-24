package BackEnd;
import java.io.Serializable;
public class Class implements Serializable {
	private static final long serialVersionUID = 1L;
	private int code;
	private int credits;
	private int crereq;
	private int semester;
	private int[] prerequisites;
	private boolean approved;
	private boolean required;
	private String name;
	public Class(int code,int credits,int crereq,int semester,int[] prerequisites,boolean approved,boolean required,String name) {
		setCode(code);
		setCredits(credits);
		setCreReq(crereq);
		setSemester(semester);
		setPrerequisites(prerequisites);
		setApproved(approved);
		setRequired(required);
		setName(name);
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public int getCreReq() {
		return crereq;
	}
	public void setCreReq(int crereq) {
		this.crereq = crereq;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public int[] getPrerequisites() {
		return prerequisites;
	}
	public void setPrerequisites(int[] prerequisites) {
		this.prerequisites = prerequisites;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		String pre = "\n\tPrerequisites: ";
		if(crereq > 0 || prerequisites != null) {
			if(crereq > 0) {
				pre += crereq + "Cr.";
			}
			if(prerequisites != null) {
				if(crereq > 0) pre += " - ";
				for(int i = 0; i < prerequisites.length; i ++) {
					pre += String.valueOf(prerequisites[i]);
					if(i < prerequisites.length - 1) {
						pre += ", ";
					}
				}
			}
		}
		else pre += "NA";
		return "Code: " + code + ", Credits: " + credits + ", Semester: " + semester + ", Name: " + name + pre + "\n";
	}
}