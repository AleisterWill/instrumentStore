<%-- 
    Document   : productsList
    Created on : Jun 29, 2022, 12:32:27 PM
    Author     : three
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/" var="url"/>
<div class="row justify-content-center">
    <c:forEach items="${ListProducts}" var="p">
        <div class="card col-md-4 py-3 mt-3">
            <a class="h-75" href="${url}product/${p.getId()}">
                <img class="card-img-top h-100" src="${p.getImageSetId().getImagePathCollection().get(0).getPath()}" alt="alt"/>
            </a>
            <div class="card-body">
                <h5 class="card-title">${p.getName()}</h5>
                <h4 class="card-text text-danger"><strong><fmt:formatNumber value="${p.getPrice()}" /> VND</strong></h4>
            </div>
        </div>
    </c:forEach>
</div>