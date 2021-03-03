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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
<noscript>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/assets/css/noscript.css" />
</noscript>
<script type="text/javascript">
	function idduchk() { //	실행 시점이 클라이언트가 이벤트 핸들러를 발생하여 호출했을 경우
		//	alert("아이디 더블 체크");
		var inputObj = document.getElementById('uidinput'); //객체를 통째로 가지고옴
		var inputmsg = document.getElementById('uidinput').value; //	input의 id, String을 가지고옴 inputObj.value도 가능

		//	비동기 통신으로 서버에 inputmsg를 보내고 success가 되면 ineerHTML로 동적으로 바꿔 주는 것
		var outputObj = document.getElementById('idinfo'); //	표시할 곳 id
		outputObj.innerHTML = "<font color=red>입력한 ID: </font>" + inputmsg; //	동적인 제어

	}
	$(function() {
//		$('#idcheckbtn').click(function() {
//			var inputid = $('#uidinput').val();
//			// $('#idinfo').html("입력한 ID : " + inputid);
//			$('#idinfo').html("입력한 ID : " + inputid).addClass('idchk').css('border','1px solid black');
//		});

/* 엔터쳐서 표시 */
		$('#uidinput').on("change",function() {
			var inputid = $('#uidinput').val();
			// $('#idinfo').html("입력한 ID : " + inputid);
			$('#idinfo').html("입력한 ID : " + inputid).addClass('idchk').css('border','1px solid black');
		});
	});
</script>
<style>
	.idchk{
		color:blue;
		background-color:red;
	}
</style>
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
			<div class="button-wrap">
				<!-- 회원가입폼 -->
				회원가입
                <form id="register" action="register" method=get class="input-group">
                    <input type="text" class="input-field" placeholder="id" required name="uid">
                    <input type="email" class="input-field" placeholder="Your Email" required name="uemail">
                    <input type="password" class="input-field" placeholder="Enter Password" required name="upwd">
                    <input type="checkbox" class="checkbox"><span>Terms and conditions</span>
                    <button class="submit">REGISTER</button>
                </form>
			</div>
		</div>
		<script>
			var x = document.getElementById("login");
			var y = document.getElementById("register");
			var z = document.getElementById("btn");

			function login() {
				x.style.left = "50px";
				y.style.left = "450px";
				z.style.left = "0";
			}

			function register() {
				x.style.left = "-400px";
				y.style.left = "50px";
				z.style.left = "110px";
			}
		</script>

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