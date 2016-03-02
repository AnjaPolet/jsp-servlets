<%-- Een welkom pagina --%>
<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html lang='nl'>
<head>
<title>Pizza Luigi</title>
<link rel='icon' href='images/favicon.ico'>
<meta name='viewport' content='width=device-width,initial-scale=1'>
<link rel='stylesheet' href='styles/default.css'>
</head>
<body>
<h1>Pizza Luigi</h1>
<img src="images/pizza.jpg" alt="pizza" class="fullwidth">
<h2>${begroeting}</h2>
<h2>De zaakvoerder</h2>
<dl>
<dt>Naam</dt><dd>${zaakvoerder.naam}</dd>
<dt>Aantal kinderen</dt><dd>${zaakvoerder.aantalKinderen}</dd>
<dt>Gehuwd</dt><dd>${zaakvoerder.gehuwd ? "ja" : "nee"}</dd>
<dt>Adres</dt><dd>${zaakvoerder.adres.straat} ${zaakvoerder.adres.huisNr}<br>
${zaakvoerder.adres.postcode} ${zaakvoerder.adres.gemeente}</dl>
</body>
</html>