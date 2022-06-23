<%-- 
    Document   : searchResult
    Created on : Jun 21, 2022, 1:33:59 PM
    Author     : three
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row my-2">
    <div class="col-md-3">
        <form class="card bg-light rounded p-3">
            <div class="form-group">
                <label for="kw">Search:</label>
                <input name="kw" class="form-control" id="kw" placeholder="Input search keyword here" />
            </div>
            <div class="form-group">
                <label minPrice>Minimum Price:</label>
                <input name="minPrice" class="form-control" id="minPrice" placeholder="Minimum Price" />
            </div>
            <div class="form-group">
                <label maxPrice>Maximum Price:</label>
                <input name="maxPrice" class="form-control" id="maxPrice" placeholder="Maximum Price" />
            </div>
            <input class="form-group btn btn-secondary" type="submit" value="Search" />
        </form>
    </div>
    <div class="col-md-9">
        <div class="row justify-content-center">
            <c:forEach items="${ListProducts}" var="p">
                <div class="col-md-4">
                    <div class="card mb-2 bg-light" style="height: 80vh;">
                        <div class="card-header">
                            <h4>${p.getName()}</h4>
                        </div>
                        <div class="card-body">
                            
                            <img class="img-fluid my-3" src="https://res.cloudinary.com/aleisterw/image/upload/v1655965660/istockphoto-894058154-612x612_phuu1n.jpg" alt="alt"/>
                            <p>${p.getDescription()}</p>

                        </div>
                        <div class="card-footer">
                            <h5 class="text-danger"><strong><fmt:formatNumber value="${p.getPrice()}"/> VND</strong></h4>
                                <div class="btn btn-primary">Liên hệ đặt hàng</div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

