<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<table class="table table-striped">
		<caption>Your Goals Are:</caption>
		<thead>
			<tr>
				<th>Description</th>
				<th>Expected Date</th>
				<th>Category</th>
				<th>Priority</th>
				<th>Is Completed?</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${goals}" var="goal">
				<tr>
					<td>${goal.description}</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy"
							value="${goal.date}" /></td>					
					<td>${goal.category.friendly}</td>
					<td>${goal.priority.friendly}</td>
					<td>${goal.complete}</td>
					<td><a class="btn btn-info" href="/update-goal?id=${goal.id}" value="update">Update</a>&nbsp;&nbsp<a
						class="btn btn-danger" href="/delete-goal?id=${goal.id}">Delete</a></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<div>
		<a class="btn btn-success" href="/add-goal" value="add">Add</a>
	</div>
</div>

<%@ include file="common/footer.jspf"%>