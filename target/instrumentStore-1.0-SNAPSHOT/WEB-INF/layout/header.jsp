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
                        <a class="dropdown-item" href="${url}cate${c.getId()}">Tất cả ${c.getName()}</a>
                        <c:forEach items="${c.getSubCategoryCollection()}" var="sc">
                            <a class="dropdown-item" href="${url}cate${c.getId()}?subCate=${sc.getId()}">${sc.getName()}</a>
                        </c:forEach>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <form class="form-inline mr-5 my-2 my-lg-0" action="<c:url value="/search"/>">
            <input class="form-control mr-sm-2" name="kw" type="search" placeholder="Search" aria-label="Search" >
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
        <c:if test="${currentUser == null}">
            <a href="${url}accounts" class="nav-item btn btn-light">Đăng nhập</a>
        </c:if>

        <c:if test="${currentUser != null}">
            <ul class="navbar-nav col-md-2 justify-content-center">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navUserDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
                        <c:if test="${currentUser.avatar.isEmpty() || !currentUser.avatar.startsWith('https')}">
                            <img class="rounded-circle" src="https://res.cloudinary.com/aleisterw/image/upload/v1656569686/default-user_vr4tyj.png" width="30" height="30" alt="alt"/>
                        </c:if>

                        <c:if test="${!currentUser.avatar.isEmpty() && currentUser.avatar.startsWith('https')}">
                            <img class="rounded-circle" src="${currentUser.avatar}" width="30" height="30" alt="alt"/>
                        </c:if>
                        ${currentUser.lastName} ${currentUser.firstName}
                    </a>
                    <div class="dropdown-menu" aria-labelledBy="navUserDropdown">
                        <a href="<c:url value="/accounts/setting"/>" class="dropdown-item"><i class="fa fa-cog fa-spin"></i> Chỉnh sửa tài khoản</a>
                        <hr class="w-75">
                        <a href="${url}accounts/myCart" class="dropdown-item"><i class="fa fa-shopping-cart"></i> Giỏ hàng <span class="badge badge-light" id="cartCounter">${cartCounter}</span></a>
                        <a href="#" class="dropdown-item"><i class="fa fa-history"></i> Lịch sử đơn hàng</a>
                        <hr class="w-75">
                        <a href="${url}signout" class="dropdown-item" ><i class="fa fa-sign-out"></i> Đăng xuất</a>
                    </div>
                </li>
            </ul>
        </c:if>
    </div>
</nav>