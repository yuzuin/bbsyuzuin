<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE HTML>
<!--
	Astral by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>소식을 나눠보세요~</title>
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
			<a href="" class="icon solid fa-home"><span>Home</span></a> <a
				href="#work" class="icon solid fa-folder"><span>Work</span></a> <a
				href="write" class="icon solid fa-envelope"><span>Contact</span></a>
			<a href="https://twitter.com/ajlkn" class="icon brands fa-twitter"><span>Twitter</span></a>
		</nav>

		<!-- Main -->
		<div id="main">
			<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
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
}

.tg .tg-cyl1 {
	background-color: #ffccc9;
	border-color: #efefef;
	color: #ce6301;
	font-family: "Lucida Sans Unicode", "Lucida Grande",
		sans-serif !important;;
	font-size: 14px;
	font-weight: bold;
	text-align: center;
	vertical-align: middle
}

.tg .tg-cz4q {
	background-color: #ffffff;
	border-color: #efefef;
	text-align: left;
	vertical-align: middle
}

.tg .tg-mjko {
	background-color: #ffffff;
	border-color: #efefef;
	text-align: center;
	vertical-align: middle
}
</style>
			<table class="tg" style="table-layout: fixed; width: 844px">
				<colgroup>
					<col style="width: 58px">
					<col style="width: 335px">
					<col style="width: 144px">
					<col style="width: 164px">
					<col style="width: 53px">
					<col style="width: 44px">
					<col style="width: 44px">
				</colgroup>
				<thead>
					<tr>
						<th class="tg-cyl1">글번호</th>
						<th class="tg-cyl1">글제목</th>
						<th class="tg-cyl1">글쓴이</th>
						<th class="tg-cyl1">글 쓴 시간</th>
						<th class="tg-cyl1">조회수</th>
						<th class="tg-cyl1">수정</th>
						<th class="tg-cyl1">삭제</th>
					</tr>
				</thead>
				<tbody>
					<!-- 테이블 반복 -->
					<c:forEach var="temp" items="${postList }">
						<tr>
							<td class="tg-mjko"><c:out value="${temp.num }" /></td>
							<td class="tg-cz4q"><a href="viewPost?viewNum=${temp.num }"><c:out
										value="${temp.title }" /></a></td>
							<td class="tg-mjko"><c:out value="${temp.name }" /></td>
							<td class="tg-mjko"><c:out value="${temp.writeDate }" /></td>
							<td class="tg-mjko"><c:out value="${temp.hits }" /></td>
							<td class="tg-mjko"><a href="modPost?modNum=${temp.num }">수정</a></td>
							<td class="tg-mjko"><a href="delPost?delNum=${temp.num }">삭제</a></td>
						</tr>
					</c:forEach>
				</tbody>
				
				      <!--  page number 표시 -->
                    <tr>
						<td colspan=5 align=center>
							<hr>
							<ul class="pageUL">

								<c:if test="${pageMaker.prev > 0 }">
									<a href='list?page=${pageMaker.prev}'> [ 이전 ] </a>
								</c:if>
								
								<c:forEach begin="${pageMaker.start }" end="${pageMaker.end}"
									var="idx">
									<!-- 			<li class='<c:out value="${idx == pageMaker.page?'current':''}"/>'>   -->
									<a href='list?page=${idx}'> 
									    <c:choose>
											<c:when test="${pageMaker.page eq idx}">
												<b>[<font color=red size=3> ${idx} </font> ]
												</b>
											</c:when>
											<c:otherwise>[ ${idx} ] </c:otherwise>
										</c:choose>
									</a>
								</c:forEach>
								
								<c:if test="${pageMaker.next > 0 }">
									<a href='list?page=${pageMaker.next}'> [ 다음 ] </a>
								</c:if>

							</ul>
						</td>
					</tr>
			</table>
			
			
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