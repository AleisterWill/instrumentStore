<%-- 
    Document   : admin-imgPath
    Created on : Aug 9, 2022, 2:15:56 PM
    Author     : three
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="vnav-content" class="shadow p-md-3">
    <table class="table" id="myTable">
        <thead>
        <th>Mã</th>
        <th>Đường dẫn</th>
        <th>Action</th>
        </thead>
        <tbody>
            <tr id="addRow" class="d-none">
                <form action="<c:url value="/admin/product/imageSet/${imgSetId}/addImg"/>" method="post">
                    <td><input name="id" class="form-control"></td>
                    <td><input name="path" class="form-control"></td>
                    <td><input id="btnSubmit" type="submit" class="d-none"/></td>
                </form>
            </tr>
        <c:forEach items="${ListImgPath}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>${p.path}</td>
                <td>
                    <a class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button id="btnAdd" class="btn btn-primary" onclick="insertRow()">Thêm</button>
    <label for="btnSubmit" id="btnSave" class="d-none" onclick="">Lưu thay đổi</label>
    <button id="btnCancel" class="d-none" onclick="cancel()">Hủy</button>
</div>

<script>
    function insertRow() {
        var addRow = document.getElementById('addRow');
        var btnAdd = document.getElementById('btnAdd');
        var btnSave = document.getElementById('btnSave');
        var btnCancel = document.getElementById('btnCancel');

        addRow.setAttribute("class", "");
        btnAdd.setAttribute("disabled", "true");
        btnSave.setAttribute("class", "btn btn-success mt-1");
        btnCancel.setAttribute("class", "btn btn-secondary");
    }

    function cancel() {
        var addRow = document.getElementById('addRow');
        var btnAdd = document.getElementById('btnAdd');
        var btnSave = document.getElementById('btnSave');
        var btnCancel = document.getElementById('btnCancel');

        addRow.setAttribute("class", "d-none");
        btnAdd.removeAttribute("disabled");
        btnSave.setAttribute("class", "d-none");
        btnCancel.setAttribute("class", "d-none");
    }
</script>