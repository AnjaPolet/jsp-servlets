<%-- Een welkom pagina --%>
<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@page import='java.io.PrintWriter' %>
<%@page import='java.util.Calendar' %>

<!DOCTYPE html>
<html lang='nl'>
<head>
<title>Pizza Luigi</title>
</head>
<body>
<h1>
<%
int uur = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
out.print(uur >= 6 && uur < 12 ? "Goede morgen": uur < 18 ? "Goede middag":"Goede avond");
%></h1>
</body>
</html>