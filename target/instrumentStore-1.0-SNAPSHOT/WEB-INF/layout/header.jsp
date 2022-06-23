<%-- 
    Document   : header
    Created on : Jun 18, 2022, 1:09:00 PM
    Author     : three
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/" var="url"/>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" href="${url}">ἠχή</a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <c:forEach items="${ListCategory}" var="c">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
                        ${c.getName()}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="${url}${c.getName()}">Tất cả ${c.getName()}</a>
                        <c:forEach items="${c.getSubCategoryCollection()}" var="sc">
                            <a class="dropdown-item" href="${url}${c.getName()}/${sc.getId()}">${sc.getName()}</a>
                        </c:forEach>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <form class="form-inline my-2 my-lg-0" action="<c:url value="/search"/>">
            <input class="form-control mr-sm-2" name="keyword" type="search" placeholder="Search" aria-label="Search" >
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>