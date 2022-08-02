<%-- 
    Document   : searchResult
    Created on : Jun 21, 2022, 1:33:59 PM
    Author     : three
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row my-2">
    <div class="col-md-3">
        <form class="card bg-light rounded p-3">
            <div class="form-group">
                <label for="kw">Search:</label>
                <input name="kw" class="form-control" id="kw" placeholder="Input search keyword here" />
            </div>
            <div class="form-group">
                <label for="minPrice">Minimum Price:</label>
                <input name="minPrice" class="form-control" id="minPrice" placeholder="Minimum Price" />
            </div>
            <div class="form-group">
                <label for="maxPrice">Maximum Price:</label>
                <input name="maxPrice" class="form-control" id="maxPrice" placeholder="Maximum Price" />
            </div>
            <div class="form-group">
                <p>Sắp xếp: </p>
                <div class="form-group">
                    <label for="nameAZ">Tên A-Z</label>
                    <input id="nameAZ" type="radio" name="sort" value="nameASC" checked />
                    <label for="nameZA">Tên Z-A</label>
                    <input id="nameZA" type="radio" name="sort" value="nameDESC" />
                </div>                
                <div class="form-group">
                    <label for="priceASC">Giá tăng</label>
                    <input id="priceASC" type="radio" name="sort" value="priceASC" />
                    <label for="priceDESC">Giá giảm</label>
                    <input id="priceDESC" type="radio" name="sort" value="priceDESC" />
                </div>
            </div>
            <input class="form-group btn btn-secondary" type="submit" value="Search" />
        </form>
    </div>
    <div class="col-md-9">
        <div class="row justify-content-center">
            <c:forEach items="${ListProducts}" var="p">
                <div class="card mt-3 mx-auto" style="width: 30%; height: 60vh;">
                    <a class="h-75" href="<c:url value="/product"/>/${p.id}">
                        <c:if test="${!p.imageSetId.getImagePathCollection().get(0).path.isEmpty() && p.imageSetId.getImagePathCollection().get(0).path.startsWith('https')  }">
                            <img src="${p.imageSetId.getImagePathCollection().get(0).path}" class="card-img-top h-100" alt="...">
                        </c:if>
                        <c:if test="${p.imageSetId.getImagePathCollection().get(0).path.isEmpty() || !p.imageSetId.getImagePathCollection().get(0).path.startsWith('https')  }">
                            <img src="https://res.cloudinary.com/aleisterw/image/upload/v1656401601/jumbotron_uatrfg.jpg" class="card-img-top h-100" alt="...">
                        </c:if>
                    </a>
                    <div class="card-body">
                        <h5 class="card-title">${p.name}</h5>
                        <h4 class="card-text text-danger"><strong><fmt:formatNumber value="${p.price}" /> VND</strong></h4>
                    </div>
                </div>
            </c:forEach>  
        </div>
        <div class="row justify-content-center mt-3">
            <ul class="pagination justify-content-end">
                <c:if test="${Math.ceil(countProducts/9) != 0}">
                    <c:if test="${page != 1}">
                        <li class="page-item"><a class="page-link" href="<c:url value="/search"/>?kw=${kw}&minPrice=${minPrice}&maxPrice=${maxPrice}&page=${page-1}"><</a></li>
                        </c:if>
                        <c:forEach begin="1" end="${Math.ceil(countProducts/9)}" var="i">
                            <c:if test="${page == i}">
                            <li class="page-item active"><a class="page-link" href="<c:url value="/search"/>?kw=${kw}&minPrice=${minPrice}&maxPrice=${maxPrice}&page=${i}">${i}</a></li>
                            </c:if>
                            <c:if test="${page != i}">
                            <li class="page-item"><a class="page-link" href="<c:url value="/search"/>?kw=${kw}&minPrice=${minPrice}&maxPrice=${maxPrice}&page=${i}">${i}</a></li>
                            </c:if>
                        </c:forEach>  
                        <c:if test="${page != Math.ceil(countProducts/9)}">
                        <li class="page-item"><a class="page-link" href="<c:url value="/search"/>?kw=${kw}&minPrice=${minPrice}&maxPrice=${maxPrice}&page=${page+1}">></a></li>
                        </c:if>
                    </c:if>
            </ul>
        </div>
    </div>
</div>

