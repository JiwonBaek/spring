<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${path}/css/reset.css" type="text/css" rel="stylesheet" />
<link href="${path}/css/style.css" type="text/css" rel="stylesheet" />
</head>
    
    
    <!-- header 부분 -->
    <jsp:include page="../inc/header.jsp"/>
    
    <!-- visual 부분 -->
    <jsp:include page="inc/visual.jsp"/>
   <div id="body" class="clearfix">
      <div class="content-container">      
      
    <!-- aside 부분 -->
    <jsp:include page="inc/aside.jsp"/>
        
        <main id="main">
        
   
      </main>   
      </div>
   </div>
   
    <!-- footer 부분 -->
    <jsp:include page="../inc/footer.jsp"/>   
   
</body>
</html>