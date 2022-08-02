<%-- 
    Document   : productsList
    Created on : Jun 29, 2022, 12:32:27 PM
    Author     : three
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/" var="url"/>
<div class="justify-content-end row mt-3">
    <div class="btn-group btn-group-toggle" data-toggle="buttons">
        <a href="${url}cate${cateId}?subCate=${subCateId}&sort=nameASC&page=${page}" class="btn btn-outline-dark active">
            <input type="radio" /> Tên A-Z
        </a>
        <a href="${url}cate${cateId}?subCate=${subCateId}&sort=nameDESC&page=${page}" class="btn btn-outline-dark">
            <input type="radio" /> Tên Z-A
        </a>
        <a href="${url}cate${cateId}?subCate=${subCateId}&sort=priceASC&page=${page}" class="btn btn-outline-dark">
            <input type="radio" /> Giá tăng
        </a>
        <a href="${url}cate${cateId}?subCate=${subCateId}&sort=priceDESC&page=${page}" class="btn btn-outline-dark">
            <input type="radio" /> Giá giảm
        </a>
    </div>
    <div class="row justify-content-center">
        <c:forEach items="${ListProducts}" var="p">
            <div class="card col-md-4 py-3 mb-3">
                <a class="h-75" href="${url}product/${p.getId()}">
                    <c:if test="${!p.imageSetId.getImagePathCollection().get(0).path.isEmpty() && p.imageSetId.getImagePathCollection().get(0).path.startsWith('https')  }">
                        <img class="card-img-top h-100" src="${p.imageSetId.getImagePathCollection().get(0).path}" alt="alt"/>
                    </c:if>
                    <c:if test="${p.imageSetId.getImagePathCollection().get(0).path.isEmpty() || !p.imageSetId.getImagePathCollection().get(0).path.startsWith('https')  }">
                        <img src="https://res.cloudinary.com/aleisterw/image/upload/v1656401601/jumbotron_uatrfg.jpg" class="card-img-top h-100" alt="...">
                    </c:if>

                </a>
                <div class="card-body">
                    <h5 class="card-title">${p.getName()}</h5>
                    <h4 class="card-text text-danger"><strong><fmt:formatNumber value="${p.getPrice()}" /> VND</strong></h4>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="row">
        <ul class="pagination justify-content-end">
            <li class="page-item active">
                <c:forEach begin="1" end="${Math.ceil(countProducts / 9)}" var="i">
                    <c:if test="${page != 1}">
                    <li class="page-item"><a class="page-link" href="${url}cate${cateId}?subCate=${subCateId}&sort=${sort}&page=${page-1}"><</a></li>
                    </c:if>
                    <c:forEach begin="1" end="${Math.ceil(countProducts/9)}" var="i">
                        <c:if test="${page == i}">
                        <li class="page-item active"><a class="page-link" href="${url}cate${cateId}?subCate=${subCateId}&sort=${sort}&page=${i}">${i}</a></li>
                        </c:if>
                        <c:if test="${page != i}">
                        <li class="page-item"><a class="page-link" href="${url}cate${cateId}?subCate=${subCateId}&sort=${sort}&page=${i}">${i}</a></li>
                        </c:if>
                    </c:forEach>  
                    <c:if test="${page != Math.ceil(countProducts/9)}">
                    <li class="page-item"><a class="page-link" href="${url}cate${cateId}?subCate=${subCateId}&sort=${sort}&page=${page+1}">></a></li>
                    </c:if>
            </c:forEach>
        </ul>
    </div>
</div>