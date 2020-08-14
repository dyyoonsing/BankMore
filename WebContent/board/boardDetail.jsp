<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>보드 상세</title>
	<meta charset="UTF-8">
	<meta name="description" content="LoginForm page">
	<jsp:include page="/include/headcss.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/include/header.jsp"/>
	<!-- Page info section -->
	<section class="page-info-section">
		<div class="container">
			<h2>Q&amp;A 상세</h2>
			<div class="site-beradcamb">
				<a href="${ pageContext.request.contextPath }">메인화면</a>
				<span>
					<i class="fa fa-angle-right"></i> 
					<a href="${ pageContext.request.contextPath }/boardList.do">Q&amp;A 게시판</a>
				</span>
				<span><i class="fa fa-angle-right"></i>Q&amp;A 상세 </span>
			</div>
		</div>
	</section>
	<!-- Page info end -->
	
	<div class="container boardList boardDetail-con">
		<!-- <h4>Q&amp;A 게시물 상세</h4> -->
		<table class="table table-bordered">
		     <tbody>
		      <tr>
		        <th class="board-sm">제목</th>
		        <td class="board-lg">${ board.title } </td>
		      </tr>
		      <tr>
		        <th class="board-sm">작성자</th>
		        <td class="board-lg">${ board.writer }</td>
		      </tr>
		      <tr>
		      <tr>
		        <th class="board-sm">등록일</th>
		        <td class="board-lg">${ board.regDate }</td>
		      </tr>
		      <%-- 
		      <tr>
		        <th class="board-sm">조회수</th>
		        <td class="board-lg">${ board.viewCnt }</td>
		      </tr>
		       --%>
		      <tr>
		        <th class="board-sm  board-height-lg">내용</th>
		        <td class="board-lg  board-height-lg">${ board.content }</td>
		      </tr>
		      <tr>
		        <th class="board-sm">첨부파일</th>
		        <td class="board-lg">
		        	<form action="${ pageContext.request.contextPath }/fileDownload.do" method="post" enctype="multipart/form-data">
						<c:forEach items="${ fileList }" var="file">
							<a href="/BankMore/upload/${ file.fileSaveName }">
								<input type="hidden" name="fileSaveName" value="${ file.fileSaveName }">
								<input type="hidden" name="fileOriName" value="${ file.fileOriName }">
								<input type="hidden" name="no" value="${ file.boardNo }">
								${ file.fileOriName }
							</a>
							(${ file.fileSize } bytes)
							<input type="submit" name="Download" value="첨부파일 다운로드"><br>
							<br>
						</c:forEach>
					</form>
				</td>
		      </tr>
		    </tbody>
		  </table>
		  <div class="text-center">
		  		<%-- 
		  	  <c:if test="${ userVO.id eq board.writer }">
			  <button type="button" class="btn btn-primary">수정</button>
			  </c:if>
			   --%>
			  <c:if test="${ userVO.type eq 'S' }">
			  <button type="button" class="btn btn-primary" onclick="window.location.href='${ contextPath }/boardWrite.do?type=answer&groupNo=${ board.groupNo }'">
			  		답글 
			  </button>
			  </c:if>
			  <button type="button" class="btn btn-primary" onclick="window.location.href='${ contextPath }/boardList.do'">목록</button>
		  </div>
	
	</div>
	
	
	
	
	<jsp:include page="/include/footer.jsp" />
	<jsp:include page="/include/jsFiles.jsp"/>
</body>
</html>