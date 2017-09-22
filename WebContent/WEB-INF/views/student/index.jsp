<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/reset.css" type="text/css" rel="stylesheet" />
<link href="../css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>

	<!--  <header> -->
	<jsp:include page="../inc/header.jsp"></jsp:include>


	<!--  visual   -->
	<jsp:include page="inc/visual.jsp"></jsp:include>


	<div id="body" class="clearfix">
		<div class="content-container">

			<!--      aside  -->

         <jsp:include page="inc/aside.jsp" />



			<main id="main">
			<h2>공지사항</h2>

			<div>
				<h3>경로</h3>
				<ol>
					<li>home</li>
					<li>고객센터</li>
					<li>공지사항</li>
				</ol>
			</div>


         학생페이지


			</main>
		</div>
	</div>

	<!-- footer -->
	<jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>








