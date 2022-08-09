<%-- 
    Document   : myCart
    Created on : Jul 5, 2022, 2:56:23 PM
    Author     : three
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${myCart == null}">
    <h3 class="display-5 mb-2 text-center">Chưa có sản phẩm nào trong giỏ hàng</h3>
</c:if>

<c:if test="${myCart != null}">
    <div class="row w-100 mt-5 p-5 shadow bg-light ml-1" style="border-radius: 50px;">
        <div class="col-lg-12 col-md-12 col-12">
            <h3 class="display-5 mb-5 text-center">Giỏ hàng của tôi</h3>
            <table id="shoppingCart" class="table table-condensed table-responsive">
                <thead>
                    <tr>
                        <th style="width:60%">Sản phẩm</th>
                        <th style="width:12%">Đơn giá</th>
                        <th style="width:10%">Số lượng</th>
                        <th style="width:16%"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${myCart}" var="p">
                        <tr id="product${p.productId}">
                            <td data-th="Product">
                                <div class="row">
                                    <div class="col-md-3 text-left">
                                        <img src="${p.productImage}" width="100" height="100" alt="alt"/>
                                    </div>
                                    <div class="col-md-9 text-left mt-sm-2">
                                        <h4>${p.productName}</h4>
                                    </div>
                                </div>

                            </td>
                            <td data-th="Price"><fmt:formatNumber value="${p.productPrice}" /></td>
                            <td data-th="Quantity">
                                <input onblur="updateCart(this, ${p.productId})" type="number" min="1" class="form-control form-control-lg text-center" value="${p.quantity}">
                            </td>
                            <td class="actions" data-th="">
                                <div class="text-right">
                                    <a onclick="deleteCartItem(${p.productId})" class="btn btn-outline-danger btn-md mb-2">
                                        <i class="fa fa-trash"></i>
                                    </a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="float-right text-right">
                <h4>Tổng cộng: <span onload="cartSubTotal()" id="subTotal"><fmt:formatNumber value="${subTotal}" /></span></h4>
                <h1 on></h1>
            </div>
        </div>
    </div>
    <div class="row mt-4 d-flex align-items-center">
        <div class="col-sm-6 order-md-2 text-right">
            <label for="btn-submit" class="btn btn-outline-dark mb-4 btn-lg pl-5 pr-5">Thanh toán</label>
            <form class="d-none" action="myCart/checkout" method="post">
                <input id="btn-submit" type="submit" />
            </form>
        </div>
        <div class="col-sm-6 mb-3 mb-m-1 order-md-1 text-md-left">
            <a href="#">
                <i class="fa fa-arrow-left mr-2"></i> Continue Shopping</a>
        </div>
    </div>
</c:if>