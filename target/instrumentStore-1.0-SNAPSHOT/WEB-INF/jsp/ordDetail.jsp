<%-- 
    Document   : ordDtl
    Created on : Aug 4, 2022, 2:31:04 PM
    Author     : three
--%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<table class="table">
    <thead>
        <tr>
            <td>Mã sản phẩm</td>
            <td>Tên sản phẩm</td>
            <td>Đơn giá</td>
            <td>Số lượng</td>
            <td>Thành tiền</td>
        </tr>

    </thead>
    <tbody>
        <c:forEach items="${OrderDetail}" var="od">
            <tr>
                <c:forEach begin="0" end="4" var="i">
                    <c:choose>
                        <c:when test="${i == 2 || i == 4}">
                            <td>
                                <fmt:formatNumber value="${od[i]}"/>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>${od[i]}</td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </c:forEach>
    </tbody>
</table>