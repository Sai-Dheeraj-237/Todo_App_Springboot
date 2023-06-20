<%@include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

	<div class="container">
		<table class="table table-striped">
			<caption>${todo.user}, Your todos are </caption>
			<thead>
				<tr>
					<th>Description</th>
					<th>Target Date</th>
					<th>Is it Done?</th>
					<th>Operations</th>
				</tr>
			</thead>
			<tbody>
			
				<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.description}</td>
						<td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
						<td>${todo.done}</td>
						<td><div><a type="button"  class="btn btn-primary" href="/update-todo?id=${todo.id}" >Update</a></div></td>
						<td><div><a type="button"  class="btn btn-danger" href="/delete-todo?id=${todo.id}" >Delete</a></div></td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
		
		
		<div> <a class="btn btn-success" href="/add-todo">Add a Todo</a></div>

	</div>
	
	<%@ include file="common/footer.jspf" %>
	