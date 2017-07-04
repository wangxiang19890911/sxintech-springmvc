<jsp:directive.page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8' session='true' />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<%@include file="includes/header.jsp" %>
<title>Xsintech - 免税合同管理系统</title>

<link href="${pageContext.request.contextPath}/components/bootstrap-3.3.6/dist/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/components/bootstrap-3.3.6/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/login/login.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/xsintech.css" rel="stylesheet">

</head>

<body>
	<nav class="navbar navbar-fixed-top navbar-inverse navbar-gradient">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"> <span>&nbsp;&nbsp; <span style="color: white;">Xsintech - 免税合同管理系统</span> <%-- &nbsp;&nbsp;<small><i>powered by</i> Tomy</small> --%>
				</span>
				</a>
			</div>
		</div>
	</nav>
	<!-- /.navbar -->
	<div class="container">
		<div class="row row-offcanvas row-off canvas-right">
			<div class="col-sm-8 col-md-9" id="login-image" style="background-image: url('${pageContext.request.contextPath}/image/login-bg-img.jpg');">
				<p class="pull-right visible-xs">
					<button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
				</p>
				<div class="row">
					<div class="col-sm-12" id="login-msg">
						<h2>使用须知</h2>
						<ul>
							<li>本系统为大连信达联创科技有限公司内部专用系统。</li>
							<li>故意通过猜测输入用户名和密码，从而尝试非法登陆是不正当的操作行为。</li>
							<li>本公司将视情况而定追究个人法律责任。</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3" id="sidebar">
				<div class="sidebar-offcanvas gray-backgr">
					<div class="inner-sidebar">
						<form id="login-form" method="post" action="${pageContext.request.contextPath}/secure/">
							<br>
							<div class="control-group">
								<!-- Text input-->
								<label class="control-label" for="input01">ユーザーID </label>
								<div class="controls">
									<input id="userId" name="username" type="text" class="form-control" autocomplete="on" placeholder="ユーザーID">
								</div>
							</div>
							<br>
							<div class="control-group">
								<!-- Text input-->
								<label class="control-label" for="input01">パスワード </label>
								<div class="controls">
									<input name="password" type="password" class="form-control" placeholder="パスワード">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="input01"></label>
								<div class="controls">
									<button id="btn_login" type="submit" class="btn btn-info">ログイン</button>
									<a id="save_id" class="btn btn-default active">ID入力省略</a>
								</div>
							</div>
							<br />
							<div class="controls controls-row" style="width: 224.4px">
								<c:forEach items="${errors}" var="error">
									<br />
									<p class="login-error-message">${error}</p>
								</c:forEach>
							</div>
							<div class="control-group">
								<div class="controls">
									<p>
										<a id="forget-password" data-toggle="modal" class="btn　btn-info" href="#modal-password-forgot">パスワードの更新/変更および<br>お忘れの方はこちら
										</a>
									</p>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<hr>

		<footer class="col-sm-12 col-md-12" style="text-align: center;">
			<p>版权所有：信达联创（大连）信息技术有限公司 <br/>编号：辽ICP备17003444 Copyright &copy; 2016 Xsintech(Dalian) All rights reserved. <a target="_blank" href="http://sc.chinaz.com/moban/"></a></p>
		</footer>

	</div>
	<!--/.container-->

	<div id="modal-password-forgot" class="modal fade sdh-modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h3>パスワード再設定</h3>
				</div>

				<div class="modal-body">
					<table class="table responsive">
						<tbody>
							<tr>
								<td colspan="2">
									以下にご登録のユーザーIDを入力して頂き、「送信」ボタンをクリックして下さい。<br />
									※ メール受信環境に制限がある場合にメールが届かない場合がございます。<br />
									※ @smflc.co.jp で始まるドメインからのメールを許可してください。<br />
								</td>
							</tr>
							<tr>
								<td class="voice voice-data">ご登録のユーザーID</td>
								<td><input id="password-forgot-email" class="span3" type="text" style="text-align: left;">
									<p style="display: none; color: red;">ご登録のユーザーIDを入力してください。</p>
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="modal-footer">
					<a id="password-forgot-next" class="btn btn-primary">送信</a> <a class="btn" data-dismiss="modal">閉じる</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${pageContext.request.contextPath}/components/jquery/dist/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/components/jquery.cookie/jquery.cookie.js"></script>
	<script>
		window.jQuery || document.write('<script src="${pageContext.request.contextPath}/components/bootstrap-3.3.6/assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="${pageContext.request.contextPath}/components/bootstrap-3.3.6/dist/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="${pageContext.request.contextPath}/components/bootstrap-3.3.6/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="${pageContext.request.contextPath}/components/bootstrap-3.3.6/assets/js/ie-emulation-modes-warning.js"></script>
	<script type="text/javascript">
	window.CONTEXTPATH = '${pageContext.request.contextPath}';
	</script>
	<script src="${pageContext.request.contextPath}/js/login/login.js"></script>
	<%@include file="includes/footer.jsp" %>
</body>
</html>
