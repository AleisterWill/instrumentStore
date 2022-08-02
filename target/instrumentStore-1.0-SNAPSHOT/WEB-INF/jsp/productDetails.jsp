<%-- 
    Document   : productDetails
    Created on : Jun 24, 2022, 10:46:22 AM
    Author     : three
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="row my-3">
    <div class="col-md-7">
        <div id="custCarousel" class="carousel slide" data-ride="carousel" align="center" style="height: 60vh;">

            <!-- Indicator -->
            <ol class="carousel-indicators bg-dark" style="opacity: 0.5;">
                <c:forEach begin="1" end="${product.getImageSetId().getImagePathCollection().size()}" var="i">
                    <c:if test="${i == 1}">
                        <li data-target="#custCarousel" data-slide-to="${i - 1}" class="active"></li>
                        </c:if>
                        <c:if test="${i != 1}">
                        <li data-target="#custCarousel" data-slide-to="${i - 1}" class=""></li>
                        </c:if>
                    </c:forEach>
            </ol>

            <!-- slides -->
            <div class="carousel-inner">
                <c:forEach begin="1" end="${product.getImageSetId().getImagePathCollection().size()}" var="i">
                    <c:if test="${i == 1}">
                        <div class="carousel-item active">
                            <img class="img-fluid h-100" src="${product.getImageSetId().getImagePathCollection().get(i-1).getPath()}" alt="alt"/>
                        </div>
                    </c:if>
                    <c:if test="${i != 1}">
                        <div class="carousel-item">
                            <img class="img-fluid h-100" src="${product.getImageSetId().getImagePathCollection().get(i-1).getPath()}" alt="alt"/>
                        </div>
                    </c:if>
                </c:forEach>
            </div>

            <!<!-- Left Right -->
            <a class="carousel-control-prev bg-dark" href="#custCarousel" data-slide="prev" style="opacity: 0.2;">
                <span class="carousel-control-prev-icon"></span>
            </a>
            <a class="carousel-control-next bg-dark" href="#custCarousel" data-slide="next" style="opacity: 0.2;">
                <span class="carousel-control-next-icon"></span>
            </a>


        </div>
    </div>

    <div class="col-md-5">
        <h4>${product.getName()}</h4>
        <p>${product.getDescription()}</p>
        <p><strong class="text-danger">Thương hiệu: </strong>${product.getBrandId().getName()}</p>
        <p><strong class="text-danger"> Quốc gia: </strong>${product.getBrandId().getCountry()}</p>
        <p></p>
        <h5 class="text-danger"><strong>Giá hiện tại: <fmt:formatNumber value="${product.getPrice()}"/> VND</strong></h4>
            <c:if test="${currentUser == null}">
                <div href="<c:url value="/accounts" />" class="h3 rounded-pill shadow text-center alert alert-secondary">
                    <i class="fa fa-cart-plus"></i> Đăng nhập để thêm vào giỏ
                </div>
            </c:if>
            <a class="mt-auto" href="javascript:;" onclick="addToCart(${product.id}, '${product.imageSetId.imagePathCollection.get(0).path}', '${product.name}', ${product.price})">
                <c:if test="${currentUser != null}">
                    <div class="h3 rounded-pill shadow text-center alert alert-success">
                        <i class="fa fa-cart-plus"></i> Thêm vào giỏ hàng
                    </div>
                </c:if>
            </a>
    </div>
</div>

<div class="row rounded shadow justify-content-center bg-light p-3 mb-3">
    <div class="col-12 justify-content-center">
        <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
            <li class="nav-item" role="presentation">
                <a class="nav-link btn rounded-pill active" id="pills-desc-tab" data-toggle="pill" href="#pills-desc" role="tab" aria-controls="pills-desc" aria-selected="true">
                    <i class="fa fa-book"></i> Mô tả sản phẩm
                </a>
            </li>
            <li class="nav-item" role="presentation">
                <a class="nav-link btn rounded-pill" id="pills-comment-tab" data-toggle="pill" href="#pills-comment" role="tab" aria-controls="pills-comment" aria-selected="false">
                    <i class="fa fa-comment-o"></i> Bình Luận (${ListComments.size()})
                </a>
            </li>
        </ul>
    </div>

    <div class="col-12 justify-content-center">
        <div class="tab-content mb-3" id="pills-tabContent">

            <div class="tab-pane fade show active" id="pills-desc" role="tabpanel" aria-labelledby="pills-desc-tab">
                <div class="col-md-12 rounded bg-white p-5">
                    ${product.getDescription()}Placeholder content for the tab panel. This one relates to the home tab. Takes you miles high, so high, 'cause she’s got that one international smile. There's a stranger in my bed, there's a pounding in my head. Oh, no. In another life I would make you stay. ‘Cause I, I’m capable of anything. Suiting up for my crowning battle. Used to steal your parents' liquor and climb to the roof. Tone, tan fit and ready, turn it up cause its gettin' heavy. Her love is like a drug. I guess that I forgot I had a choice.
                </div>  
            </div>

            <div class="tab-pane fade justify-content-center" id="pills-comment" role="tabpanel" aria-labelledby="pills-comment-tab">
                <c:forEach items="${ListComments}" var="comment">
                    <div class="row mb-3">
                        <div class="col-2 col-md-1">
                            <c:if test="${comment.getUserId().getAvatar() != null || comment.getUserId().getAvatar().startsWith('https')}">
                                <img class="w-100" src="${comment.getUserId().getAvatar()}" alt="alt"/>
                            </c:if>
                            <c:if test="${comment.getUserId().getAvatar() == null || !comment.getUserId().getAvatar().startsWith('https')}">
                                <img class="w-100" src="https://res.cloudinary.com/aleisterw/image/upload/v1656569686/default-user_vr4tyj.png" alt="alt"/>
                            </c:if>
                        </div>
                        <div class="col-10 shadow p-3 bg-white rounded mydate">
                            <h3>${comment.getUserId().getFirstName()} ${comment.getUserId().getLastName()}</h3>
                            <p>${comment.getIndex()}</p>
                            <i class="text-muted">${comment.createdDate}</i>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div> 
    </div>   
</div>        

<div class="h3 alert shadow alert-warning rounded-pill">Sản phẩm tương tự</div>
<div class="row">
    <div class="col-md-12 d-flex justify-content-center">
        <c:if test="${RelateProducts.size() == 0}">
            <h3>Không tìm thây sản phẩm tương tự</h3>
        </c:if>
        <c:if test="${RelateProducts.size() != 0}">
            <div id="relateItemsCarousel" class="carousel slide" data-ride="carousel" align="center" >

                <!-- Indicators -->
                <button class="carousel-control-prev" type="button" data-target="#relateItemsCarousel" data-slide="prev">
                    <i class="fa fa-arrow-circle-left text-warning fa-2x" aria-hidden="true"></i>
                    <span class="sr-only">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-target="#relateItemsCarousel" data-slide="next">
                    <i class="fa fa-arrow-circle-right text-warning fa-2x" aria-hidden="true"></i>
                    <span class="sr-only">Next</span>
                </button>

                <!-- slides -->
                <div class="carousel-inner">
                    <c:forEach begin="0" end="${RelateProducts.size()-1}" var="i" step="3">
                        <c:if test="${i == 0}">
                            <div class="card-deck carousel-item active">
                                <div class="card-deck justify-content-center">
                                    <div class="card col-md-4">
                                        <a class="h-75" href="<c:url value="/product"/>/${RelateProducts.get(i).getId()}">
                                            <c:if test="${RelateProducts.get(i).getImageSetId().getImagePathCollection().get(0).getPath() != null && RelateProducts.get(i).getImageSetId().getImagePathCollection().get(0).getPath().startsWith('https')}">
                                                <img src="${RelateProducts.get(i).getImageSetId().getImagePathCollection().get(0).getPath()}" class="card-img-top h-100" alt="...">
                                            </c:if>
                                            <c:if test="${RelateProducts.get(i).getImageSetId().getImagePathCollection().get(0).getPath() == null || !RelateProducts.get(i).getImageSetId().getImagePathCollection().get(0).getPath().startsWith('https')}">
                                                <img src="https://res.cloudinary.com/aleisterw/image/upload/v1656401601/jumbotron_uatrfg.jpg" class="card-img-top h-100" alt="...">
                                            </c:if>
                                        </a>
                                        <div class="card-body">
                                            <h5 class="card-title">${RelateProducts.get(i).getName()}</h5>
                                            <h4 class="card-text text-danger"><strong><fmt:formatNumber value="${RelateProducts.get(i).getPrice()}" /> VND</strong></h4>
                                        </div>
                                    </div>
                                    <c:if test="${i+1 < RelateProducts.size()}">
                                        <div class="card col-md-4">
                                            <a class="h-75" href="<c:url value="/product"/>/${RelateProducts.get(i).getId()}">
                                                <c:if test="${RelateProducts.get(i+1).getImageSetId().getImagePathCollection().get(0).getPath() != null && RelateProducts.get(i+1).getImageSetId().getImagePathCollection().get(0).getPath().startsWith('https')}">
                                                    <img src="${RelateProducts.get(i+1).getImageSetId().getImagePathCollection().get(0).getPath()}" class="card-img-top h-100" alt="...">
                                                </c:if>
                                                <c:if test="${RelateProducts.get(i+1).getImageSetId().getImagePathCollection().get(0).getPath() == null || !RelateProducts.get(i+1).getImageSetId().getImagePathCollection().get(0).getPath().startsWith('https')}">
                                                    <img src="https://res.cloudinary.com/aleisterw/image/upload/v1656401601/jumbotron_uatrfg.jpg" class="card-img-top h-100" alt="...">
                                                </c:if>
                                            </a>
                                            <div class="card-body">
                                                <h5 class="card-title">${RelateProducts.get(i+1).getName()}</h5>
                                                <h4 class="card-text text-danger"><strong><fmt:formatNumber value="${RelateProducts.get(i+1).getPrice()}" /> VND</strong></h4>
                                            </div>

                                        </div>
                                    </c:if>
                                    <c:if test="${i+2 < RelateProducts.size()}">
                                        <div class="card col-md-4">
                                            <a class="h-75" href="<c:url value="/product"/>/${RelateProducts.get(i).getId()}">
                                                <c:if test="${RelateProducts.get(i+2).getImageSetId().getImagePathCollection().get(0).getPath() != null && RelateProducts.get(i+2).getImageSetId().getImagePathCollection().get(0).getPath().startsWith('https')}">
                                                    <img src="${RelateProducts.get(i+2).getImageSetId().getImagePathCollection().get(0).getPath()}" class="card-img-top h-100" alt="...">
                                                </c:if>
                                                <c:if test="${RelateProducts.get(i+2).getImageSetId().getImagePathCollection().get(0).getPath() == null || !RelateProducts.get(i+2).getImageSetId().getImagePathCollection().get(0).getPath().startsWith('https')}">
                                                    <img src="https://res.cloudinary.com/aleisterw/image/upload/v1656401601/jumbotron_uatrfg.jpg" class="card-img-top h-100" alt="...">
                                                </c:if>
                                            </a>
                                            <div class="card-body">
                                                <h5 class="card-title">${RelateProducts.get(i+2).getName()}</h5>
                                                <h4 class="card-text text-danger"><strong><fmt:formatNumber value="${RelateProducts.get(i+2).getPrice()}" /> VND</strong></h4>
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
                                        <a class="h-75" href="<c:url value="/product"/>/${RelateProducts.get(i).getId()}">
                                            <c:if test="${RelateProducts.get(i).getImageSetId().getImagePathCollection().get(0).getPath() != null && RelateProducts.get(i).getImageSetId().getImagePathCollection().get(0).getPath().startsWith('https')}">
                                                <img src="${RelateProducts.get(i).getImageSetId().getImagePathCollection().get(0).getPath()}" class="card-img-top h-100" alt="...">
                                            </c:if>
                                            <c:if test="${RelateProducts.get(i).getImageSetId().getImagePathCollection().get(0).getPath() == null || !RelateProducts.get(i).getImageSetId().getImagePathCollection().get(0).getPath().startsWith('https')}">
                                                <img src="https://res.cloudinary.com/aleisterw/image/upload/v1656401601/jumbotron_uatrfg.jpg" class="card-img-top h-100" alt="...">
                                            </c:if>
                                        </a>
                                        <div class="card-body">
                                            <h5 class="card-title">${RelateProducts.get(i).getName()}</h5>
                                            <h4 class="card-text text-danger"><strong><fmt:formatNumber value="${RelateProducts.get(i).getPrice()}" /> VND</strong></h4>
                                        </div>
                                    </div>
                                    <c:if test="${i+1 < RelateProducts.size()}">
                                        <div class="card col-md-4">
                                            <a class="h-75" href="<c:url value="/product"/>/${RelateProducts.get(i).getId()}">
                                                <c:if test="${RelateProducts.get(i+1).getImageSetId().getImagePathCollection().get(0).getPath() != null && RelateProducts.get(i+1).getImageSetId().getImagePathCollection().get(0).getPath().startsWith('https')}">
                                                    <img src="${RelateProducts.get(i+1).getImageSetId().getImagePathCollection().get(0).getPath()}" class="card-img-top h-100" alt="...">
                                                </c:if>
                                                <c:if test="${RelateProducts.get(i+1).getImageSetId().getImagePathCollection().get(0).getPath() == null || !RelateProducts.get(i+1).getImageSetId().getImagePathCollection().get(0).getPath().startsWith('https')}">
                                                    <img src="https://res.cloudinary.com/aleisterw/image/upload/v1656401601/jumbotron_uatrfg.jpg" class="card-img-top h-100" alt="...">
                                                </c:if>
                                            </a>
                                            <div class="card-body">
                                                <h5 class="card-title">${RelateProducts.get(i+1).getName()}</h5>
                                                <h4 class="card-text text-danger"><strong><fmt:formatNumber value="${RelateProducts.get(i+1).getPrice()}" /> VND</strong></h4>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${i+2 < RelateProducts.size()}">
                                        <div class="card col-md-4">
                                            <a class="h-75" href="<c:url value="/product"/>/${RelateProducts.get(i).getId()}">
                                                <c:if test="${RelateProducts.get(i+2).getImageSetId().getImagePathCollection().get(0).getPath() != null && RelateProducts.get(i+2).getImageSetId().getImagePathCollection().get(0).getPath().startsWith('https')}">
                                                    <img src="${RelateProducts.get(i+2).getImageSetId().getImagePathCollection().get(0).getPath()}" class="card-img-top h-100" alt="...">
                                                </c:if>
                                                <c:if test="${RelateProducts.get(i+2).getImageSetId().getImagePathCollection().get(0).getPath() == null || !RelateProducts.get(i+2).getImageSetId().getImagePathCollection().get(0).getPath().startsWith('https')}">
                                                    <img src="https://res.cloudinary.com/aleisterw/image/upload/v1656401601/jumbotron_uatrfg.jpg" class="card-img-top h-100" alt="...">
                                                </c:if>
                                            </a>
                                            <div class="card-body">
                                                <h5 class="card-title">${RelateProducts.get(i+2).getName()}</h5>
                                                <h4 class="card-text text-danger"><strong><fmt:formatNumber value="${RelateProducts.get(i+2).getPrice()}" /> VND</strong></h4>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </c:if>

    </div>
</div>

<script>
    window.onload = function () {
        let dates = document.querySelectorAll(".mydate > i")
        for (let i = 0; i < dates.length; i++) {
            let d = dates[i];
            d.innerText = moment(d.innerText).fromNow();
        }
    }
</script>