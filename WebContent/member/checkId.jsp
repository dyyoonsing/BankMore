<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${ not empty checkId }">
		<span class="text-danger">사용 불가능한 아이디입니다</span>
	</c:when>
	<c:otherwise>
		<span class="text-success">사용 가능한 아이디입니다</span>
	</c:otherwise>
</c:choose>