<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row w-100 my-5 p-5 shadow bg-light ml-1" style="border-radius: 50px;">
    <div class="col-lg-12 col-md-12 col-12">
        <c:choose>
            <c:when test="${resp.get('vnp_TransactionStatus').toString().equals('00')}">
                <h3 class="text-center"><i class="fa fa-2x fa-check-circle text-success"></i></h3>
                <h3 class="display-5 text-center">Giao dịch thành công</h3>
                <h6 class="text-center text-muted">
                    Hệ thống đã ghi nhận giao dịch của bạn
                </h6>
            </c:when>
            <c:otherwise>
                <h3 class="text-center"><i class="fa fa-2x fa-times-circle text-danger"></i></h3>
                <h3 class="display-5 text-center">Giao dịch thất bại</h3>
                <h6 class="text-center text-muted">
                    Kết quả giao dịch đã hủy bỏ
                </h6>
            </c:otherwise>
        </c:choose>



        <table class="col-md-12 text-center table table-striped">
            <tbody>
                <tr>
                    <td class="col-md-6">
                        <label><strong>Mã giao dịch: </strong></label>
                        <p>${resp.get('vnp_TxnRef')}</p>
                    </td>
                    <td class="col-md-6">
                        <label><strong>Phương thức giao dịch: </strong></label>
                        <p>${resp.get('vnp_CardType')}</p>
                    </td>
                </tr>
                <tr>
                    <td class="col-md-6">
                        <label><strong>Ngân hàng: </strong></label>
                        <p>${resp.get('vnp_BankCode')}</p>
                    </td>
                    <td class="col-md-6">
                        <label><strong>Số tiền: </strong></label>
                        <p><fmt:formatNumber value="${Long.parseLong(resp.get('vnp_Amount').toString())/100}" /> VND</p>
                    </td>
                </tr>
                <tr>
                    <td class="col-md-6">
                        <label><strong>Nội dung: </strong></label>
                        <p>${resp.get('vnp_OrderInfo')}</p>
                    </td>
                    <td class="col-md-6">
                        <label><strong>Ngày giao dịch: </strong></label>
                        <fmt:parseDate pattern="yyyyMMddHHmmss" value="${resp.get('vnp_PayDate').toString()}" var="parseDate" />
                        <p><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${parseDate}" /></p>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

