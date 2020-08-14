<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="col-md-3">
	<ul class="nav flex-column border rounded p-3">
	  <li class="nav-item">
	    	My 계좌
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="${ pageContext.request.contextPath}/selectAllAccount.do">계좌 관리</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="${ pageContext.request.contextPath}/accountAddDelete.do">오픈뱅킹 연결</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="${ pageContext.request.contextPath }/selectAllStatement.do">거래내역조회</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="${ pageContext.request.contextPath }/transfer.do">계좌이체</a>
	  </li>
	</ul>
	
</div>