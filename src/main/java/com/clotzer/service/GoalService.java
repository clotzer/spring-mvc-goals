package com.clotzer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.clotzer.model.Goal;

@Service
public class GoalService {
	private static List<Goal> goals = new ArrayList<Goal>();
	private static int goalCount = 3;

	static {
		goals.add(new Goal(1, "clotzer", "Wash the dishes", new Date(), false));
		goals.add(new Goal(2, "clotzer", "Mow the lawn", new Date(), false));
		goals.add(new Goal(3, "clotzer", "Organize office", new Date(), false));
	}

	public List<Goal> retrieveGoals(String user) {
		List<Goal> filteredGoals = new ArrayList<Goal>();
		for (Goal goal : goals) {
			if (goal.getUser().equals(user))
				filteredGoals.add(goal);
		}
		return filteredGoals;
	}

	public void addGoal(String name, String description, Date date, boolean isDone) {
		goals.add(new Goal(++goalCount, name, description, date, isDone));
	}

	public Goal retrieveGoal(int id) {
		for (Goal goal : goals) {
			if (goal.getId() == id)
				return goal;
		}
		return null;
	}

	public Goal updateGoal(Goal goal) {
		goals.remove(goal);
		goals.add(goal);
		return goal;
	}

	public void deleteGoal(int id) {
		Iterator<Goal> iterator = goals.iterator();
		while (iterator.hasNext()) {
			Goal goal = iterator.next();
			if (goal.getId() == id) {
				iterator.remove();
			}
		}
	}

}