<%-- 
    Document   : index
    Created on : Jun 18, 2022, 1:07:36 PM
    Author     : three
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="jumbotron justify-content-center" style="background-image: url('https://res.cloudinary.com/aleisterw/image/upload/v1656403909/brandlogopng_q48s6j.png'); background-size: 100% 100%;">
    <div class="p-5 col-md-6 bg-light rounded-lg" style="opacity: .85;">
        <h1 class="display-4">It's time, Symphony!</h1>
        <p class="lead">Cho bạn trải nghiệm âm thanh tuyệt vời nhất</p>
        <p class="lead">Cho bạn sự hài lòng nhất</p>
        <hr class="my-4">
        <p>Trở thành thành viên của Symphony ngay hôm nay để nhận các ưu đãi hấp dẫn</p>
        <a class="btn btn-dark btn-lg rounded" href="<c:url value="/signup"/>" role="button">Đăng ký thành viên</a>
    </div>
</div>

    <div class="h3 alert alert-secondary rounded-pill">Best Sellers</div>
<div class="row mt-3">
    <div id="bestSellersCarousel" class="carousel slide" data-ride="carousel" align="center" >

        <!-- Indicators -->
        <button class="carousel-control-prev" type="button" data-target="#bestSellersCarousel" data-slide="prev">
            <i class="fa fa-arrow-circle-left text-warning fa-2x" aria-hidden="true"></i>
            <span class="sr-only">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-target="#bestSellersCarousel" data-slide="next">
            <i class="fa fa-arrow-circle-right text-warning fa-2x" aria-hidden="true"></i>
            <span class="sr-only">Next</span>
        </button>

        <!-- slides -->
        <div class="carousel-inner">
            <c:forEach begin="0" end="${BestSellers.size()-1}" var="i" step="3">
                <c:if test="${i == 0}">
                    <div class="card-deck carousel-item active">
                        <div class="card-deck justify-content-center">
                            <div class="card col-md-4">
                                <a class="h-75" href="<c:url value="/product"/>/${BestSellers.get(i).getId()}"><img src="${BestSellers.get(i).getImageSetId().getImagePathCollection().get(0).getPath()}" class="card-img-top h-100" alt="..."></a>
                                <div class="card-body">
                                    <h5 class="card-title">${BestSellers.get(i).getName()}</h5>
                                    <h4 class="card-text text-danger"><strong><fmt:formatNumber value="${BestSellers.get(i).getPrice()}" /> VND</strong></h4>
                                </div>
                            </div>
                            <c:if test="${i+1 < BestSellers.size()}">
                                <div class="card col-md-4">
                                    <a class="h-75" href="<c:url value="/product"/>/${BestSellers.get(i+1).getId()}"><img src="${BestSellers.get(i+1).getImageSetId().getImagePathCollection().get(0).getPath()}" class="card-img-top h-100" alt="..."></a>
                                    <div class="card-body">
                                        <h5 class="card-title">${BestSellers.get(i+1).getName()}</h5>
                                        <h4 class="card-text text-danger"><strong><fmt:formatNumber value="${BestSellers.get(i+1).getPrice()}" /> VND</strong></h4>
                                    </div>

                                </div>
                            </c:if>
                            <c:if test="${i+2 < BestSellers.size()}">
                                <div class="card col-md-4">
                                    <a class="h-75" href="<c:url value="/product"/>/${BestSellers.get(i+2).getId()}"><img src="${BestSellers.get(i+2).getImageSetId().getImagePathCollection().get(0).getPath()}" class="card-img-top h-100" alt="..."></a>
                                    <div class="card-body">
                                        <h5 class="card-title">${BestSellers.get(i+2).getName()}</h5>
                                        <h4 class="card-text text-danger"><strong><fmt:formatNumber value="${BestSellers.get(i+2).getPrice()}" /> VND</strong></h4>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:if>
                <c:if test="${i != 0}">
                    <div class="card-deck carousel-item">
                        <div class="card-deck justify-content-center">
                            <div class="card col-md-4">
                                <a class="h-75" href="<c:url value="/product"/>/${BestSellers.get(i).getId()}"><img src="${BestSellers.get(i).getImageSetId().getImagePathCollection().get(0).getPath()}" class="card-img-top h-100" alt="..."></a>
                                <div class="card-body">
                                    <h5 class="card-title">${BestSellers.get(i).getName()}</h5>
                                    <h4 class="card-text text-danger"><strong><fmt:formatNumber value="${BestSellers.get(i).getPrice()}" /> VND</strong></h4>
                                </div>
                            </div>
                            <c:if test="${i+1 < BestSellers.size()}">
                                <div class="card col-md-4">
                                    <a class="h-75" href="<c:url value="/product"/>/${BestSellers.get(i+1).getId()}"><img src="${BestSellers.get(i+1).getImageSetId().getImagePathCollection().get(0).getPath()}" class="card-img-top h-100" alt="..."></a>
                                    <div class="card-body">
                                        <h5 class="card-title">${BestSellers.get(i+1).getName()}</h5>
                                        <h4 class="card-text text-danger"><strong><fmt:formatNumber value="${BestSellers.get(i+1).getPrice()}" /> VND</strong></h4>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${i+2 < BestSellers.size()}">
                                <div class="card col-md-4">
                                    <a class="h-75" href="<c:url value="/product"/>/${BestSellers.get(i+2).getId()}"><img src="${BestSellers.get(i+2).getImageSetId().getImagePathCollection().get(0).getPath()}" class="card-img-top h-100" alt="..."></a>
                                    <div class="card-body">
                                        <h5 class="card-title">${BestSellers.get(i+2).getName()}</h5>
                                        <h4 class="card-text text-danger"><strong><fmt:formatNumber value="${BestSellers.get(i+2).getPrice()}" /> VND</strong></h4>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>
