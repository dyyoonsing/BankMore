<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
	
	<c:when test="${ empty allAccountList }">
		<div class="p-2">
		존재하는 계좌가 없습니다
		</div>
	</c:when>
	
	<c:otherwise>
	<div id="accountAddDelete">	
		<form action="${ contextPath }/accountAddProcess.do" method="post" class="w-100 p-3" onsubmit="return true">
			<c:forEach items="${ allAccountList }" var="account">
			<ul class="list-group w-100">
				<li class="list-group-item" >
			  		<div class="custom-control custom-checkbox">
			    		<input type="checkbox" class="custom-control-input" id="no${ account.accountNo }" name="no" value="${ account.accountNo }"
			    			<c:if test="${ account.openCheck eq 'Y' }">disabled</c:if> >
			    		<label class="custom-control-label" for="no${ account.accountNo }">
			    			${ account.bankName } ${ account.accountNo }
			    			<c:if test="${ account.openCheck eq 'Y' }"><span class="badge badge-primary">BankMore 등록됨</span></c:if>
			    			<br>${ account.balance }원
			    			
			    		</label>
			  		</div>
				</li>
			</ul>
			</c:forEach>
			<button type="submit" class="btn m-2 ">
				추가하기 
			</button>
		</form>
	</div>
	
	</c:otherwise>
	
	</c:choose>
</body>
</html>