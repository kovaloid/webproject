<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.title" var="page_title"/>
<fmt:message bundle="${loc}" key="local.subtitle.main_page" var="main_page"/>
<fmt:message bundle="${loc}" key="local.subtitle.about_page" var="about_page"/>
<fmt:message bundle="${loc}" key="local.subtitle.contacts_page" var="contacts_page"/>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand">${page_title}</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${param.page eq 'main'}">
                        <li class="active"><a href="${pageContext.request.contextPath}/">${main_page}</a></li>
                        <li><a href="${pageContext.request.contextPath}/about">${about_page}</a></li>
                        <li><a href="${pageContext.request.contextPath}/contacts">${contacts_page}</a></li>
                    </c:when>
                    <c:when test="${param.page eq 'about'}">
                        <li><a href="${pageContext.request.contextPath}/">${main_page}</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/about">${about_page}</a></li>
                        <li><a href="${pageContext.request.contextPath}/contacts">${contacts_page}</a></li>
                    </c:when>
                    <c:when test="${param.page eq 'contacts'}">
                        <li><a href="${pageContext.request.contextPath}/">${main_page}</a></li>
                        <li><a href="${pageContext.request.contextPath}/about">${about_page}</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/contacts">${contacts_page}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}/">${main_page}</a></li>
                        <li><a href="${pageContext.request.contextPath}/about">${about_page}</a></li>
                        <li><a href="${pageContext.request.contextPath}/contacts">${contacts_page}</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</div>
