<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="interface" />   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="include/bootstrap.jsp" />
<title>Home</title>
</head>
<body>
	<div class="container mt-5">
		<jsp:include page="include/barra.jsp" />
		<div class="row justify-content-center mt-5">
			<div class="col-6">
				<c:if test="${sessionScope.usuario==null}">
					<form method="post">
						<label for="email" class="form-label"><fmt:message key="email" /></label> 
						<input type="text" class="form-control" id="email" name="email" required> 
						<label for="password" class="form-label mt-3"><fmt:message key="password" /></label>
						<input type="password" class="form-control" id="password"
							name="password" required>
						<c:if test="${error!=null}">
							<p class="text-danger text-small mt-3">${error}</p>
						</c:if>
						<input type="submit" class="btn btn-primary w-100 mt-3"
							value="ENTRAR" />
					</form>
				</c:if>
				<c:if test="${sessionScope.usuario!=null}">
					${pageContext.request.contextPath}/Home
				</c:if>
			</div>
		</div>
	</div>	
</body>
</html>