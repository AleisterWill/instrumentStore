<%-- 
    Document   : ordHistory
    Created on : Aug 2, 2022, 3:54:34 PM
    Author     : three
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1>Lịch sử đơn hàng</h1>
<form class="form">
    <div class="form-group form-inline">
        <input type="text" name="ordId" class="form-control col-md-11" placeholder="Nhập mã đơn"/>
        <input type="submit" class="btn btn-secondary col-md-1" />
    </div>
</form>
<table class="table table-striped text-center">
    <thead>
            <th>Mã đơn</th>
            <th>Tổng tiền</th>
            <th>Trạng thái</th>
            <th>Ngày tạo</th>
            <th>Chi tiết</th>
    </thead>
    <tbody>
        <c:forEach items="${ListOrders}" var="o">
            <tr>
                <td>${o.id}</td>
                <td>${o.totalPrice}</td>
                <td>${o.status}</td>
                <td>${o.createdDate}</td>
                <td><a href="<c:url value="/accounts/orderHistory"/>/${o.id}">Xem chi tiết</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<ul class="pagination justify-content-end">
    <li class="page-item active">
        <c:forEach begin="1" end="${Math.ceil(countList / 9)}" var="i">
            <c:if test="${page == i}">
                <li class="page-item active">
                    <a class="page-link" href="?ordId=${ordId}&page=${i}">${i}</a>
                </li>
            </c:if>
            <c:if test="${page != i}">
                <li class="page-item">
                    <a class="page-link" href="?ordId=${ordId}&page=${i}">${i}</a>
                </li>
            </c:if>
        </c:forEach>
    </li>
</ul>


