package com.clotzer.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import com.clotzer.model.Goal;
import com.clotzer.model.Goal.Category;
import com.clotzer.model.Goal.Priority;

public class GoalServiceTest {

	private MockMvc mockMvc;

	@InjectMocks
	private GoalService service;

	private static List<Goal> goals = new ArrayList<Goal>();
	private static int goalCount = 3;
	private static GoalService mock;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		goals.add(new Goal(1, "clotzer", "Wash the dishes", new Date(), false));
		goals.add(new Goal(2, "clotzer", "Mow the lawn", new Date(), false));
		goals.add(new Goal(3, "clotzer", "Organize office", new Date(), false));

		int year = 2030;
        int month = Calendar.JULY;
        int day = 31;
        Calendar calendar = new GregorianCalendar();
        calendar.set(year, month, day);
        Date futureDate = calendar.getTime();
        goals.add(new Goal(4, "clotzer", "Expand my network to 1000", Goal.Category.NEXT_5_YEARS, Goal.Priority.IMPORTANT, futureDate, false));
		
		assertNotNull(service);
		mock = org.mockito.Mockito.mock(GoalService.class);
	}

	@Test
	public void retrieveGoalsTest() {
		when(mock.retrieveGoals("clotzer")).thenReturn(goals);	
		List<Goal> list = mock.retrieveGoals("clotzer");
		assertEquals("Wash the dishes", list.get(0).getDescription());

		Optional<Goal> lastElement = list.stream().reduce((first, second) -> second);
		Goal goal = lastElement.get();
		assertEquals("Expand my network to 1000", goal.getDescription());
	}

	/**
	 *
	 * @see com.clotzer.service.GoalService#retrieveGoal(int)
	 */
	@Test
	public void retrieveGoal() {
		when(mock.retrieveGoal(4)).thenReturn(goals.get(4));
		Goal retrievedGoal = mock.retrieveGoal(4);
		assertEquals("Wash the dishes", retrievedGoal.getDescription());
	}

	/**
	 *
	 * @see com.clotzer.service.GoalService#updateGoal(Goal)
	 */
	@Test
	public void updateGoal() {
		Goal updatedGoal = new Goal(3, "clotzer", "Organize office", Category.LATER, Priority.HIGH, new Date(), false);
		Mockito.when(mock.updateGoal(Mockito.any(Goal.class))).thenReturn(updatedGoal);
		Goal goal = mock.updateGoal(updatedGoal);
		assertEquals(Category.LATER, goal.getCategory());
		assertEquals(Priority.HIGH, goal.getPriority());
	}

	/**
	 *
	 * @see com.clotzer.service.GoalService#deleteGoal(int)
	 */
	@Test
	public void deleteGoal() {
		when(mock.retrieveGoals("clotzer")).thenReturn(goals);	
		List<Goal> list = mock.retrieveGoals("clotzer");

		Optional<Goal> lastElement = list.stream().reduce((first, second) -> second);
		Goal goal = lastElement.get();
		
		int id = goal.getId();
		mock.deleteGoal(id);
		Goal deletedGoal = mock.retrieveGoal(id);
		assertNull(deletedGoal);
	}
}
