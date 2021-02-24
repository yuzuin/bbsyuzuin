<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				href="write" class="icon solid fa-envelope"><span>Write</span></a> <a
				href="https://twitter.com/ajlkn" class="icon brands fa-twitter"><span>Twitter</span></a>
		</nav>

		<!-- Main -->
		<div id="main">

			<!-- Work -->
			<article id="work" class="panel">
				<header>
					<h2>${post.title}</h2>
				</header>
				<p>
					<!-- 글쓴이 / 글쓴 시간 / 내용 등 -->
					글쓴이 ${post.name} 시간 ${post.writeDate}
				</p>
				<section>
					<!-- 글 내용 -->
					<c:forEach var="imsi" items="${images }">
					<img
						src="${pageContext.request.contextPath}/download?filename=${imsi.img }" width="800">
					
					</c:forEach>
					
					<br><br>
					${post.content}
				</section>
				<section>
					<!-- 첨부파일 -->
					첨부파일 <a
						href="${pageContext.request.contextPath}/download?filename=${post.fname }">{다운로드}</a>
				</section>

				<!-- 댓글쓰기 -->
				<form action="writeComment" method="get">
					<input type="hidden" name="postNum" value="${post.num }" />
					<div class="col-6 col-12-medium">
						<input type="text" name="name" placeholder="닉네임" /> <input
							type="text" name="password" placeholder="비번" />
					</div>
					<div class="col-6 col-12-medium">
						<input type="text" name="content" placeholder="댓글" />
					</div>
					<input type="submit" value="댓글쓰기" />
				</form>
				<!-- 댓글 -->
				<h3>댓글</h3>

				<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	height: 10px;
}

.tg td {
	border-color: black;
	border-style: solid;
	border-width: 1px;
	font-family: Arial, sans-serif;
	font-size: 14px;
	overflow: hidden;
	padding: 10px 5px;
	word-break: normal;
	height: 10px;
}

.tg th {
	border-color: black;
	border-style: solid;
	border-width: 1px;
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	overflow: hidden;
	padding: 10px 5px;
	word-break: normal;
	height: 10px;
}

.tg .tg-7eit {
	background-color: #ffffff;
	border-color: #ffffff;
	text-align: center;
	vertical-align: middle
}

.tg .tg-6woi {
	background-color: #ffccc9;
	border-color: #ffffff;
	color: #ce6301;
	font-family: "Lucida Sans Unicode", "Lucida Grande",
		sans-serif !important;;
	font-size: 14px;
	font-weight: bold;
	text-align: center;
	vertical-align: middle
}

.tg .tg-8n49 {
	background-color: #ffffff;
	border-color: #ffffff;
	text-align: left;
	vertical-align: middle
}
</style>
				<table class="tg" style="table-layout: fixed; width: 740px;">
					<colgroup>
						<col style="width: 148px;">
						<col style="width: 347px;">
						<col style="width: 144px;">
						<col style="width: 57px;">
						<col style="width: 53px;">
					</colgroup>
					<thead>
						<tr>
							<th class="tg-6woi">닉네임</th>
							<th class="tg-6woi">내용</th>
							<th class="tg-6woi">시간</th>
							<th class="tg-6woi">수정</th>
							<th class="tg-6woi">삭제</th>
						</tr>
					</thead>
					<tbody>
						<!-- 댓글 테이블 반복 -->
						<c:forEach var="temp" items="${commentList }">
							<tr>
								<td class="tg-7eit"><c:out value="${temp.name }" /></td>
								<td class="tg-8n49"><c:out value="${temp.content }" /></td>
								<td class="tg-7eit"><c:out value="${temp.writeDate }" /></td>
								<td class="tg-7eit">수정</td>
								<td class="tg-7eit">삭제</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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