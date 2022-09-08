<%-- 
    Document   : admin-product-addOrEdit
    Created on : Sep 5, 2022, 12:05:56 PM
    Author     : three
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${mode.equals('add')}">
    <c:url value="/admin/product/add" var="action"/>
</c:if>
<c:if test="${mode.equals('edit')}">
    <c:url value="/admin/product/edit/${newProduct.id}" var="action"/>
</c:if>

<div id="vnav-content" class="shadow p-md-3" >

    <div class="m-3">
        <a class="bg-dark text-dark" href="<c:url value="/admin/product"/>">
            <h5><i class="fa fa-undo"></i> Trở về danh sách sản phẩm</h5>
        </a>
    </div>
            
        <c:if test="${error.equals('violation')}">
            <div class="alert alert-danger">
                Lỗi <span class="text-danger">ràng buộc quan hệ</span> trong CSDL. Kiểm tra lại dữ liệu nhập!
            </div>
        </c:if>
        <form:form action="${action}" method="post" enctype="multipart/form-data" modelAttribute="newProduct">
        <form:errors path="*" element="div" cssClass="alert alert-danger" />
        <div class="form-group mb-2">
            <label>Mã sản phẩm</label>
            <form:input id="idinput" path="id" cssClass="form-control" />
            <small class="form-text text-muted">Điền vào trường này. Có thể bỏ trống để tự sinh mã.</small>
        </div>
        <div class="form-group mb-2">
            <label>Tên sản phẩm</label>
            <form:input path="name" cssClass="form-control"/>
            <small class="form-text text-muted">Trường bắt buộc. Điền tối đa 45 ký tự.</small>
        </div>
        <div class="form-group mb-2">
            <label>Mô tả</label>
            <form:textarea path="description" cssClass="form-control"/>
            <small class="form-text text-muted">
                Điền tối đa 18000 ký tự, có thể bỏ trống.<br>
                Mô tả sử dụng định dạng theo các thẻ HTML hoặc các class của Boostrap v4.6
            </small>
        </div>

        <div class="form-group mb-2">
            <label>Giá</label>
            <form:input path="price" type="number" cssClass="form-control"/>
        </div>
        <div class="form-group mb-2">
            <label>Bộ ảnh</label>
            <form:input path="imgsetid" type="text" list="imgSets" cssClass="form-control" />
            <small class="form-text text-muted">
                Nhập vào trường để tìm kiếm, sau đó chọn giá trị theo đề xuất.<br>
                Nếu không tìm thấy, có thể bỏ trống hoặc vui lòng <a href="<c:url value="/admin/product/imageSet" />">thêm bộ ảnh mới</a>.
            </small>
        </div>
        <div class="form-group mb-2">
            <label>Loại</label>
            <form:input path="subcatid" type="text" list="subCats" cssClass="form-control"/>
            <small class="form-text text-muted">
                Nhập vào trường để tìm kiếm, sau đó chọn giá trị theo đề xuất.<br>
                Nếu không tìm thấy, có thể bỏ trống hoặc vui lòng <a href="<c:url value="/admin/product/category" />">thêm loại mới</a>.
            </small>
        </div>
        <div class="form-group mb-2">
            <label>Thương hiệu</label>
            <form:input path="brandid" type="text" list="brands" cssClass="form-control"/>
            <small class="form-text text-muted">
                Nhập vào trường để tìm kiếm, sau đó chọn giá trị theo đề xuất.<br>
                Nếu không tìm thấy, có thể bỏ trống hoặc vui lòng <a href="<c:url value="/admin/product/brand" />">thêm thương hiệu mới</a>.
            </small>
        </div>
        <div class="form-group mb-2">
            <label>Số lượng</label>
            <form:input path="inStock" type="number" cssClass="form-control"/>
        </div>
        
        <input class="btn btn-primary" type="submit">
        <input class="btn btn-secondary" type="reset">

        <datalist id="imgSets">
            <c:forEach items="${ListImgSet}" var="s">
                <option value="${s.id}">${s.description}</option>
            </c:forEach>
        </datalist>

        <datalist id="subCats">
            <c:forEach items="${ListCategory}" var="c">
                <c:forEach items="${c.getSubCategoryCollection()}" var="sc">
                    <option value="${sc.id}">${sc.name}</option>
                </c:forEach>
            </c:forEach>
        </datalist>

        <datalist id="brands">
            <c:forEach items="${ListBrand}" var="b">
                <option value="${b.id}">${b.name}</option>
            </c:forEach>
        </datalist>
        
    </form:form>
</div>
            <script>
                <c:if test="${mode.equals('edit')}">
                    let inp = document.getElementById("idinput");
                    inp.setAttribute("readonly", "true");
                </c:if>
            </script>