<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Astral by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>Astral by HTML5 UP</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
<noscript>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/assets/css/noscript.css" />
</noscript>
</head>
<body class="is-preload">

	<!-- Wrapper-->
	<div id="wrapper">

		<!-- Nav -->
		<nav id="nav">
			<a href="list" class="icon solid fa-home"><span>List</span></a> <a
				href="login" class="icon solid fa-folder"><span>Login</span></a> <a
				href="write" class="icon solid fa-envelope"><span>Write</span></a>
			<a href="https://twitter.com/ajlkn" class="icon brands fa-twitter"><span>Twitter</span></a>
		</nav>

		<!-- Main -->
		<div id="main">
			<!-- Contact -->
			<article id="contact" class="panel">
				<header>
					<h2>글 수정</h2>
				</header>
				<form action="modPostOK" method="get">
					<div>
						<div class="row">
							<input type="hidden" name="num" value="${post.num }" />
							<div class="col-6 col-12-medium">
								<input type="text" name="name" value="${post.name }" />
							</div>
							<div class="col-6 col-12-medium">
								<input type="text" name="password" value="${post.password }"
									readonly />
							</div>
							<div class="col-12">
								<input type="text" name="title" value="${post.title }" />
							</div>
							<div class="col-12">
								<textarea name="content" rows="6"> ${post.content }</textarea>
							</div>
							<div class="col-12">
								<input type="submit" value="수정 완료" />
							</div>
						</div>
					</div>
				</form>
			</article>

		</div>

		<!-- Footer -->
		<div id="footer">
			<ul class="copyright">
				<li>&copy; Untitled.</li>
				<li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
			</ul>
		</div>

	</div>

	<!-- Scripts -->
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/browser.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/breakpoints.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/util.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/main.js"></script>

</body>
</html>