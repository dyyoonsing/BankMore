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
	
	<c:when test="${ empty selectedAccountList }">
		<div class="p-2">
		뱅크모어에 등록한 계좌가 없습니다
		계좌를 새롭게 추가해보세요!
		</div>
	</c:when>
	
	<c:otherwise>
	<div id="accountSelected">	
		<form action="${ contextPath }/accountDeleteProcess.do" method="post" class="w-100 p-3" onsubmit="return true">
			<c:forEach items="${ selectedAccountList }" var="account">
			<ul class="list-group w-100">
				<li class="list-group-item" >
			  		<div class="custom-control custom-checkbox">
			    		<input type="checkbox" class="custom-control-input" id="noSelected${ account.accountNo }" name="no" value="${ account.accountNo }">
			    		<label class="custom-control-label" for="noSelected${ account.accountNo }">
			    			${ account.bankName } ${ account.accountNo }
			    			<br>${ account.balance }원
			    		</label>
			  		</div>
				</li>
			</ul>
			</c:forEach>
			<button type="submit" class="btn m-2 ">
				삭제하기 
			</button>
		</form>
	</div>
	
	</c:otherwise>
	
	</c:choose>


</body>
</html>