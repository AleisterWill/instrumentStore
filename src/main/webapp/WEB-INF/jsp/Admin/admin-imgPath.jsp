<%-- 
    Document   : admin-imgPath
    Created on : Aug 9, 2022, 2:15:56 PM
    Author     : three
--%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/admin/product/imageSet/${imgSetId}/addImg" var="action" />

<div id="vnav-content" class="shadow p-md-3">

    <div class="m-3">
        <a class="bg-dark text-dark" href="<c:url value="/admin/product/imageSet"/>">
            <h5><i class="fa fa-undo"></i> Trở về bộ ảnh</h5>
        </a>
    </div>

    <table class="table table-responsive-sm" id="myTable">
        <thead>
        <th>Mã</th>
        <th>Đường dẫn</th>
        <th>Xem trước</th>
        <th>Action</th>
        </thead>
        <tbody>
            <tr id="addRow" class="d-none">
                <form:form action="${action}" method="post" enctype="multipart/form-data" modelAttribute="newImg">
                    <td><form:input path="id" name="id" class="form-control" /></td>
                    <td><form:input path="file" name="file" type="file" class="form-control" /></td>
                    <td></td>
                    <td><input id="btnSubmit" type="submit" class="d-none"/></td>
                    </form:form>
            </tr>
            <c:forEach items="${ListImgPath}" var="p">
                <tr id="${p.id}">
                    <td>${p.id}</td>
                    <td>${p.path}</td>
                    <td><img src="${p.path}" width="100" height="100"/></td>
                    <td>
                        <a onclick="deleteImgPath(${p.id})" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <button id="btnAdd" class="btn btn-primary" onclick="insertRow()">Thêm</button>
    <label for="btnSubmit" id="btnSave" class="d-none" onclick="">Lưu thay đổi</label>
    <button id="btnCancel" class="d-none" onclick="cancel()">Hủy</button>
</div>