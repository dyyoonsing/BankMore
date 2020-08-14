<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>게시물 작성</title>
	<meta charset="UTF-8">
	<meta name="description" content="LoginForm page">
	<jsp:include page="/include/headcss.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/include/header.jsp"/>
	<!-- Page info section -->
	<section class="page-info-section">
		<div class="container">
			<c:choose>
				<c:when test="${ type eq 'answer' }">
				<h2>답변 남기기</h2>
				</c:when>
				<c:otherwise>
				<h2>질문 남기기</h2>
				</c:otherwise>
			</c:choose>
			<div class="site-beradcamb">
				<a href="${ pageContext.request.contextPath }">메인화면</a>
				<span>
					<i class="fa fa-angle-right"></i> 
					<a href="${ pageContext.request.contextPath }/boardList.do">Q&amp;A 게시판</a>
				</span>
				<span><i class="fa fa-angle-right"></i>
					<c:choose>
						<c:when test="${ type eq 'answer' }">
						답변 남기기
						</c:when>
						<c:otherwise>
						질문 남기기
						</c:otherwise>
					</c:choose>
				</span>
			</div>
		</div>
	</section>
	<!-- Page info end -->
	
	
	<div class="container boardList boardWriteForm-con">
		<form action="${ pageContext.request.contextPath }/boardWriteProcess.do" method="post" name="wForm" onsubmit="return true"
			enctype="multipart/form-data">
		<input type="hidden" name="writer" value="${ userVO.id }">
		<c:choose>
				<c:when test="${ type eq 'answer' }">
					<input type="hidden" name="type" value="A">
					<input type="hidden" name="groupNo" value="${ groupNo }">
				</c:when>
				<c:otherwise>
					<input type="hidden" name="type" value="Q">
				</c:otherwise>
			</c:choose>
		<table class="table table-bordered">
		     <tbody>
		      <tr>
		        <th class="board-sm">제목</th>
		        <td class="board-lg"><input type="text" name="title" class="form-control"></td>
		      </tr>
		      <tr>
		        <th class="board-sm">작성자</th>
		        <td class="board-lg">${ userVO.id }</td>
		      </tr>
		      <tr>
		        <th class="board-sm">내용</th>
		        <td class="board-lg"><textarea rows="7" cols="70" name="content" class="form-control"></textarea></td>
		      </tr>
		      <tr>
		        <th class="board-sm">첨부파일</th>
		        <td class="board-lg">
		        	<input type="file" name="attachfile1"><br>
					<input type="file" name="attachfile2"><br>
				</td>
		      </tr>
		    </tbody>
		  </table>
		  <div class="text-center">
			  <button type="submit" class="btn btn-primary">등록</button>
			  <button type="button" class="btn btn-primary" onclick="window.location.href='${ contextPath }/boardList.do'">목록</button>
		  </div>
		  </form>
	
	</div>
	
	
	
	
	
	<jsp:include page="/include/footer.jsp" />
	<jsp:include page="/include/jsFiles.jsp"/>
</body>
</html>