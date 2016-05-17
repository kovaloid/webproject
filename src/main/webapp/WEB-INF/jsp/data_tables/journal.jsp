<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag" %>
<!DOCTYPE html>
<html>
<head>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="local.title" var="page_title"/>
    <fmt:message bundle="${loc}" key="local.subtitle.journal" var="journal_subtitle"/>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="author" content="Artem Kovalev"/>
    <title>${journal_subtitle} - ${page_title}</title>
    <link href="../../../css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="../../../css/template.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="../../../img/favicon.png"/>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/blocks/header_block.jsp"/>
<jsp:include page="/WEB-INF/jsp/blocks/login_block.jsp">
    <jsp:param name="page" value="${pageContext.request.contextPath}/main?command=journal"/>
</jsp:include>
<jsp:include page="/WEB-INF/jsp/blocks/lang_block.jsp">
    <jsp:param name="page" value="${pageContext.request.contextPath}/main?command=journal"/>
</jsp:include>
<c:if test="${sessionScope.status eq 'in' and sessionScope.user.role eq 'admin'}">
    <jsp:include page="/WEB-INF/jsp/blocks/data_controls/journal_data_block.jsp"/>
</c:if>

<div class="main">
    <div class="well">

        <c:if test="${sessionScope.journal_tables_amount ne 0}">
            <c:if test="${sessionScope.journal_tables_amount ne 1}">
                <div align="center">
                    <ul class="pagination">
                        <c:forEach var="i" begin="1" end="${sessionScope.journal_tables_amount}">
                            <c:if test="${sessionScope.journal_table_number eq i}">
                                <li class="active"><a
                                        href="${pageContext.request.contextPath}/main?command=journal&number=${i}">${i}</a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.journal_table_number ne i}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/main?command=journal&number=${i}">${i}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <mytag:print_table bean="${sessionScope.journal_table}" locale="${sessionScope.locale}"/>

        </c:if>
    </div>
</div>

</body>
</html>

