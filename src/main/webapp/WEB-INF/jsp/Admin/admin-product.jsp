<%-- 
    Document   : admin-product
    Created on : Aug 6, 2022, 2:22:22 PM
    Author     : three
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="vnav-content" class="shadow p-md-3">
    <div class="row">
        <div class="col-sm-12 col-md-6">
            <form class="form">
                <div class="form-group form-inline">
                    <label class="col-1"><i class="fa fa-search"></i></label>
                    <input class="form-control col-11" name="keyword" placeholder="Tìm kiếm">
                </div>
            </form>
            <a href="<c:url value="/admin/product/add" />" class="btn btn-primary"><i class="fa fa-plus fa-lg"></i>  Thêm sản phẩm</a>
        </div>

        <div class="col-sm-12 col-md-6">
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

    <table class="table table-responsive-sm mt-2">
        <thead>
        <th>Mã</th>
        <th>Tên</th>
        <th>Giá</th>
        <th>Mô tả</th>
        <th>Bộ Ảnh</th>
        <th>Loại chính</th>
        <th>Loại phụ</th>
        <th>Thương hiệu</th>
        <th>Action</th>
        </thead>
        <tbody>
            <c:forEach items="${ListProducts}" var="p">
                <tr id="${p.id}">
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.price}</td>
                    <td>${p.description}</td>
                    <td>${p.imageSetId.id}</td>
                    <td>${p.subCategoryId.categoryId.id}</td>
                    <td>${p.subCategoryId.id}</td>
                    <td>${p.brandId.name}</td>
                    <td>
                        <a href="<c:url value="/admin/product/edit/"/>${p.id}" class="btn btn-secondary">Edit</a>
                        <a onclick="deleteProduct(${p.id})" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>