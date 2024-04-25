<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList,java.util.Enumeration,java.sql.Date,java.util.Random,com.pennant.irctc.mvc.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ticket success</title>
</head>
<body>
	<table style="text-align: center; border: 1px dotted red;">
		<caption>Please Find Your Ticket Below</caption>
		<%
		TicketModel ticket = (TicketModel) request.getAttribute("ticket");
		PassengerListModel psngrs = (PassengerListModel) request.getAttribute("passengers");
		int count = 1;
		%>
		<tr>
			<th>Train no::</th>
			<td><%=ticket.getTrain_No()%></td>
			<th>ticket no::</th>
			<td><%=ticket.getTicket_No()%>
			<th>pnr no::</th>
			<td><%=ticket.getPnr_No()%>
			<th>journey stations::</th>
			<td><%=ticket.getFrom() + "--->" + ticket.getTo()%>
			<th>journey date::</th>
			<td><%=ticket.getDoj()%>
		</tr>
		<tr>
			<td align="center" colspan="5">
				<table style="border: 1px solid black; text-align: center;">
					<tr>
						<th>s.no</th>
						<th>passenger name</th>
						<th>passenger age</th>
						<th>passenger gender</th>
						<th>passenger birth preference</th>
						<%
						for (PassengerModel psngr : psngrs) {
						%>
					
					<tr>
						<td><%=count++%></td>
						<td><%=psngr.getName()%></td>
						<td><%=psngr.getAge()%></td>
						<td><%=psngr.getGender()%></td>
						<td><%=psngr.getPreference()%></td>
					</tr>
					<%
					}
					%>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>