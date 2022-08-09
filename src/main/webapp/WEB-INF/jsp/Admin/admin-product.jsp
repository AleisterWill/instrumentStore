<%-- 
    Document   : admin-product
    Created on : Aug 6, 2022, 2:22:22 PM
    Author     : three
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <li class="page-item active"><a class="page-link" href="<c:url value="/admin/product"/>?keyword=${keyword}&page=${i}">${i}</a></li>
                        </c:if>
                        <c:if test="${page != i}">
                        <li class="page-item"><a class="page-link" href="<c:url value="/admin/product"/>?keyword=${keyword}&page=${i}">${i}</a></li>
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
                <td>Tên</td>
                <td>Giá</td>
                <td>Mô tả</td>
                <td>Bộ Ảnh</td>
                <td>Loại chính</td>
                <td>Loại phụ</td>
                <td>Thương hiệu</td>
                <td>Action</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${ListProducts}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.price}</td>
                    <td>${p.description}</td>
                    <td>${p.imageSetId.id}</td>
                    <td>${p.subCategoryId.categoryId.id}</td>
                    <td>${p.subCategoryId.id}</td>
                    <td>${p.brandId.name}</td>
                    <td>
                        <a class="btn btn-secondary">Edit</a>
                        <a class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>