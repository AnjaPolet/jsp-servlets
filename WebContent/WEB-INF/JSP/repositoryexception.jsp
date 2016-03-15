<%@ page contentType="text/html" 
    pageEncoding="UTF-8" session="false"%>
    <%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
    <%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!DOCTYPE html>
<html lang='nl'>
<head>
<vdab:head title="Repository exception"/>
</head>
<body>
<vdab:menu/>
<h1>Problemen bij het ophalen van de data</h1>
<img src='<c:url value="/images/datafout.jpg"/>' alt='datafout'>
<p>We kunnen de gevraagde data niet ophalen wegens een technische storing. Gelieve de helpdesk te contacteren.</p>
</body>
</html>