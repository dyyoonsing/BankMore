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
	<div>
	 거래내역조회
	</div>
	<table class="table text-center">
	    <thead>
	      <tr>
	        <th>거래일시</th>
	        <th>보낸분/받는분</th>
	        <th>출금액(-)</th>
	        <th>입금액(+)</th>
	        <th>잔액</th>
	        <th>메모</th>
	      </tr>
	    </thead>
			<c:forEach items="${ stateList }" var="state" varStatus="loop">
				<tr>
					<td>${ state.transDate }</td>
					<td>${ state.transName } <br>(${ state.transBank }) ${ state.transAccountNo }</td>
					<td>${ state.debit }</td>
					<td>${ state.credit }</td>
					<td>${ state.balance }</td>
					<td>${ state.myDesc }</td>
				</tr>
			</c:forEach>
	</table>
</body>
</html>