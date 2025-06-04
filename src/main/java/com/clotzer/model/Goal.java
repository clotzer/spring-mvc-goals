package com.clotzer.model;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Goal {
	
	public enum Category {
		SOON("Soon"),
		NEXT_1_YEAR("Next Year"),
		NEXT_2_YEARS("In the Next 2 Years"),
		NEXT_5_YEARS("In the Next 5 Years"),
		NEXT_10_YEARS("In the Next 10 Years"),
		LATER("Later");
		
		public final String friendly;
		
		Category(String friendly) {
			this.friendly = friendly;
		}
		
		public String getFriendly() {
			return this.friendly;
		}
	}
	
	public enum Priority {
		URGENT("Urgent"),
		HIGH("High"),
		IMPORTANT("Important"),
		MEDIUM("Medium"),
		LOW("Low"),
		NONE("None");
			
		private final String friendly;
		
		Priority(String friendly) {
			this.friendly = friendly;
		}

		public String getFriendly() {
			return this.friendly;
		}
	}
	
	private int id;
	private String user;
	
	@Size(min = 5, message = "Enter at least 5 characters")
	private String description;
	
	private Category category;
	
	private Priority priority;
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	private Date date;
	
	private boolean isComplete;

	public Goal() {}
	
	public Goal(int id, String user, String description, Date date, boolean isComplete) {
		this(id, user, description, Category.SOON, Priority.NONE, date, isComplete);
	}

	public Goal(int id, String user, String description, Category category, Priority priority, Date date,
			boolean isComplete) {
		super();
		this.id = id;
		this.user = user;
		this.description = description;
		this.category = category;
		this.priority = priority;
		this.date = date;
		this.isComplete = isComplete;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Goal other = (Goal) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Goal [id=" + id + ", user=" + user + ", description=" + description + ", category=" + category
				+ ", priority=" + priority + ", date=" + date + ", isComplete=" + isComplete + "]";
	}

}