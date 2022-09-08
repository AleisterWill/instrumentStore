<%-- 
    Document   : admin-imgSet
    Created on : Aug 9, 2022, 12:45:18 PM
    Author     : three
--%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        </div>

        <div class="col-sm-12 col-md-6">
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

    <table class="table table-responsive-sm">
        <thead>
            <tr>
                <th>Mã</th>
                <th>Mô tả</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <tr id="addRow" class="d-none">
                <form:form action="${action}" method="post" modelAttribute="newSet">
                    <td>AUTO_INCREMENT</td>
                    <td><form:input path="description" name="description" class="form-control" /></td>
                    <td><input id="btnSubmit" type="submit" class="d-none"/></td>
                </form:form>
            </tr>
            <c:forEach items="${ListImgSet}" var="p">
                <tr id="${p[0]}">
                    <td>${p[0]}</td>
                    <td>${p[1]}</td>
                    <td>
                        <a class="btn btn-primary" href="<c:url value="/admin/product/imageSet/${p[0]}"/>">Xem chi tiết</a>
                        <a onclick="deleteImgSet(${p[0]})" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <button id="btnAdd" class="btn btn-primary" onclick="insertRow()">Thêm</button>
    <label for="btnSubmit" id="btnSave" class="d-none" onclick="">Lưu thay đổi</label>
    <button id="btnCancel" class="d-none" onclick="cancel()">Hủy</button>
</div>