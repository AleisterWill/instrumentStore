<%-- 
    Document   : header-admin
    Created on : Aug 8, 2022, 12:19:54 PM
    Author     : three
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light col-md-2" id="vnav">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto flex-column vertical-nav">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/admin"/>"> <i class="fa fa-windows"></i> Dashboard</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="collapse" href="#collapse1" role="button" aria-expanded="false" aria-controls="collapse1">
                    Quản lý sản phẩm
                </a>

                <div class="collapse" id="collapse1">
                    <ul>
                        <li>
                            <a class="nav-link" href="<c:url value="/admin/product"/>">Danh sách sản phẩm</a>
                        </li>
                        <li>
                            <a class="nav-link" href="<c:url value="/admin/product/imageSet"/>">Bộ ảnh</a>
                        </li>
                    </ul>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="collapse" href="#collapse2" role="button" aria-expanded="false" aria-controls="collapse2">
                    Quản lý nhân viên
                </a>

                <div class="collapse" id="collapse2">
                    <a class="nav-link" href="#">Page 1</a>
                    <a class="nav-link" href="#">Page 1</a>
                    <a class="nav-link" href="#">Page 1</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
