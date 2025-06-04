<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<style><%@include file="/WEB-INF/css/style.css"%></style>

<div class="container">
	<form:form method="post" commandName="goal">
		<form:hidden path="id" />
		<fieldset class="form-group">
			<form:label path="description">Description</form:label>
			<form:input path="description" type="text" class="form-control"
				required="required" />
			<form:errors path="description" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="category">Category</form:label>
			<form:select path="category" class="form-control">
				<form:options items="${categories}" itemLabel="friendly" class="form-control" />
			</form:select>
		</fieldset>

		<fieldset class="form-group">
			<form:label path="priority">Priority</form:label>
			<form:select path="priority" class="form-control">
				<form:options items="${priorities}"  itemLabel="friendly" class="form-control" />
			</form:select>
		</fieldset>

		<fieldset class="form-group">
			<form:label path="date">Goal Date</form:label>

			<form:input path="date" type="date" pattern="yyyy-MM-dd" class="form-control"
				required="required" />

			<form:errors path="date" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="complete">Goal Completed</form:label>
			<form:checkbox path="complete" class="form-control move-left" />
		</fieldset>

		<button type="submit" class="btn btn-success">Submit</button>
	</form:form>
</div>

<script>
	$('#date').datepicker({
		format : 'dd/mm/yyyy'
	});
</script>

<%@ include file="common/footer.jspf"%>
