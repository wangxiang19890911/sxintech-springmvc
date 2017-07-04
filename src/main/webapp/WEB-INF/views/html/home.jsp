<jsp:directive.page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' session='true' />
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="JP">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<%@include file="includes/header.jsp" %>
<title>Home</title>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/components/bootstrap-3.3.6/dist/css/bootstrap.css" rel="stylesheet">
<%-- <link href="${pageContext.request.contextPath}/components/bootstrap-3.3.6/dist/css/normalize.css" rel="stylesheet"> --%>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="${pageContext.request.contextPath}/components/bootstrap-3.3.6/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/home/home.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="${pageContext.request.contextPath}/components/bootstrap-3.3.6/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="${pageContext.request.contextPath}/components/bootstrap-3.3.6/assets/js/ie-emulation-modes-warning.js"></script>
<script type="text/javascript">
window.CONTEXTPATH = '${pageContext.request.contextPath}';
</script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/components/html5shiv/dist/html5shiv.min.js"></script>
      <script src="${pageContext.request.contextPath}/components/respond/dest/respond.min.js"></script>
    <![endif]-->
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<link href="${pageContext.request.contextPath}/css/driverscompass.css" rel="stylesheet">
</head>

<body>
	<nav class="navbar navbar-fixed-top navbar-inverse navbar-gradient" style="width: 100%;">
		<div class="container">
			<a class="navbar-brand header-left" href="#">
				<span>
					&nbsp;&nbsp;
					<span style="color: white;">Drivers Compass</span>
				</span>
			</a>
			<div class="btn-toolbar pull-right navbar-brand header-right">
				<button class="btn btn-default radius" data-toggle="dropdown">
					<i class="icon-user"></i>
					<span class="user-name">&nbsp;${userName}</span>
					<i class="icon-chevron-down"></i>
				</button>
				<ul class="dropdown-menu">
					<c:if test="${role==1}">
						<li class="btn-default radius" style="text-align: center;"><a href="${pageContext.request.contextPath}/secure/admin">管理画面</a></li>
					</c:if>
					<li class="btn-default radius" style="text-align: center;"><a id="a-logout">ログアウト</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="row">
			<div class="col-xs-6 col-md-6">
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/secure/report/input'" class="nav-button btn btn-primary btn-lg btn-block"><span class="glyphicon glyphicon-th-list"></span>運転日報</button>
			</div>
			<div class="col-xs-6 col-md-6">
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/secure/scheduler'" class="nav-button btn btn-primary btn-lg btn-block"><span class="glyphicon glyphicon-calendar"></span>車両予約</button>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-xs-12 col-md-12">
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/secure/emergency'" class="accident-button btn btn-danger btn-lg btn-block"><span class="glyphicon glyphicon-exclamation-sign"></span>事故・緊急故障</button>
			</div>
		</div>
		<!--/row-->
		<hr>
		<footer>
			<p>&copy; SMFLキャピタル株式会社</p>
		</footer>
	</div>
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${pageContext.request.contextPath}/components/jquery/dist/jquery.js"></script>
	<script>
		window.jQuery || document.write('<script src="${pageContext.request.contextPath}/components/bootstrap-3.3.6/assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="${pageContext.request.contextPath}/components/bootstrap-3.3.6/dist/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="${pageContext.request.contextPath}/components/bootstrap-3.3.6/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="${pageContext.request.contextPath}/js/driverscompass.js"></script>
	<%@include file="includes/footer.jsp" %>
</body>
</html>
