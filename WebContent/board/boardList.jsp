<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>고객센터</title>
	<meta charset="UTF-8">
	<meta name="description" content="LoginForm page">
	<jsp:include page="/include/headcss.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/include/header.jsp"/>
	
	<!-- Page info section -->
	<section class="page-info-section">
		<div class="container">
			<h2>Q&amp;A 게시판</h2>
			<div class="site-beradcamb">
				<a href="${ pageContext.request.contextPath }">메인화면</a>
				<span><i class="fa fa-angle-right"></i> Q&amp;A 게시판</span>
			</div>
		</div>
	</section>
	<!-- Page info end -->
	
	
	<div class="container boardList">
		
		  <table class="table table-hover">
		    <thead>
		      <tr>
		        <th class="board-sm">번호</th>
		        <th class="board-lg">제목</th>
		        <th class="board-sm">작성자</th>
		        <th class="board-md">작성일</th>
		      </tr>
		    </thead>
		    <tbody>
		    	<c:forEach items="${ list }" var="board" varStatus="loop">
					<tr>
						<td>${ board.rowNum }</td>
						<td class="b-align-left">
							<c:if test="${ board.type eq 'A' }">
								<img alt="" src="/BankMore/img/reply.gif">
							</c:if>
							<a href="javascript:boardDoAction('${ contextPath }', '${ board.no }', '${ userVO.id }', '${ board.writer }', '${ board.questioner }', '${ userVO.type }')">
								<c:out value="${ board.title }" />
							</a>									
							<c:if test="${ userVO.type ne 'S' and userVO.id ne board.writer and userVO.id ne board.questioner }">
								<img alt="" src="/BankMore/img/lock.gif">
							</c:if> 
							<c:if test="${ board.todayCheck eq 'Y' }">
								<img alt="" src="/BankMore/img/new.gif">
							</c:if>
						</td>
						<td>${ board.writer }</td>
						<td class="boardList-date">${ board.regDate }</td>
					</tr>
				</c:forEach>
		    	
		    </tbody>
		  </table>
		  <div>
		  <c:if test="${ not empty userVO }">
		  	<button type="button" class="btn btn-primary" onclick="window.location.href='${ contextPath }/boardWrite.do'">새글 등록</button>
		  </c:if>
		  </div>
		</div>
	
	
	
	
	
	
	
	
	
	<jsp:include page="/include/footer.jsp" />
	<jsp:include page="/include/jsFiles.jsp"/>
</body>
</html>