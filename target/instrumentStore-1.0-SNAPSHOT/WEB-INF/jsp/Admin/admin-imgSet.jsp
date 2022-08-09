<%-- 
    Document   : admin-imgSet
    Created on : Aug 9, 2022, 12:45:18 PM
    Author     : three
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="vnav-content" class="shadow p-md-3">
    <div class="row">
        <form class="form form-inline">
            <div class="form-group">
                <label><i class="fa fa-search"></i></label>
                <input class="form-control" name="keyword" placeholder="Tìm kiếm">
            </div>
        </form>

        <div class="col ">
            <ul class="pagination justify-content-end">
                <li class="page-item active">
                    <c:forEach begin="1" end="${Math.ceil(countList / 9)}" var="i">
                        <c:if test="${page == i}">
                        <li class="page-item active"><a class="page-link" href="<c:url value="/admin/product/imageSet"/>?keyword=${keyword}&page=${i}">${i}</a></li>
                        </c:if>
                        <c:if test="${page != i}">
                        <li class="page-item"><a class="page-link" href="<c:url value="/admin/product/imageSet"/>?keyword=${keyword}&page=${i}">${i}</a></li>
                        </c:if>
                    </c:forEach>
                </li>
            </ul>
        </div>
    </div>
    
    <table class="table">
        <thead>
            <tr>
                <td>Mã</td>
                <td>Mô tả</td>
                <td>Action</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${ListImgSet}" var="p">
                <tr>
                    <td>${p[0]}</td>
                    <td>${p[1]}</td>
                    <td>
                        <a class="btn btn-primary" href="<c:url value="/admin/product/imageSet/${p[0]}"/>">Xem chi tiết</a>
                        <a class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>