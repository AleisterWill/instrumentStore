<%-- 
    Document   : adminindex
    Created on : Aug 5, 2022, 12:02:27 PM
    Author     : three
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="card mx-1 my-3 py-3 bg-light">
    <div class="row justify-content-center">
        <div class="card mx-1 mb-3 col-md-5 bg-light">
            <div class="row no-gutters align-items-center">
                <div class="col-md-4">
                    <img class="d-flex img-fluid" src="https://res.cloudinary.com/aleisterw/image/upload/v1659767924/barcode_wht1e3.png" alt="...">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title"> Quản lý sản phẩm</h5>
                        <p class="card-text">Xem, thêm, sửa, xóa sản phẩm</p>
                        <h6 class="card-text">Hiện tại có </span></h6>
                        <a href="<c:url value="/admin/product"/>" class="card-text btn btn-primary">Đến trang quản lý</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="card mb-3 mx-1 col-md-5 bg-light">
            <div class="row no-gutters align-items-center">
                <div class="col-md-4">
                    <img class="d-flex img-fluid" src="https://res.cloudinary.com/aleisterw/image/upload/v1638717971/employees_fblpkn.png" alt="...">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">Quản lý nhân viên</h5>
                        <p class="card-text">Xem, thêm, sửa, xóa nhân viên</p>
                        <h6 class="card-text">Hiện tại có X nhân viên</span></h6>
                        <a href="" class="card-text btn btn-primary">Đến trang quản lý</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="card mb-3 mx-1 col-md-5 bg-light">
            <div class="row no-gutters align-items-center">
                <div class="col-md-4">
                    <img class="d-flex img-fluid" src="https://res.cloudinary.com/aleisterw/image/upload/v1638715746/route_u19wjc.jpg" alt="...">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title"><spring:message code="label.routes"/> <spring:message code="label.management"/></h5>
                        <p class="card-text"><strong><spring:message code="label.search"/> and <spring:message code="label.add"/>, <spring:message code="label.view"/>, Edit or <spring:message code="label.delete"/></strong>  routes here.</p>
                        <h6 class="card-text">Current <spring:message code="label.routes"/>: <span class="text-success">${countRoutes}</span></h6>
                        <a href="<c:url value="/admin/routes"/>" class="card-text btn btn-primary">Go to <spring:message code="label.manager"/></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="card mb-3 mx-1 col-md-5 bg-light">
            <div class="row no-gutters align-items-center">
                <div class="col-md-4">
                    <img class="d-flex img-fluid" src="https://res.cloudinary.com/aleisterw/image/upload/v1638716441/bus_jvntgz.png" alt="...">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title"><spring:message code="label.buses"/> <spring:message code="label.management"/></h5>
                        <p class="card-text"><strong><spring:message code="label.search"/> and <spring:message code="label.add"/>, <spring:message code="label.view"/>, Edit or <spring:message code="label.delete"/></strong>  buses here.</p>
                        <h6 class="card-text">Current <spring:message code="label.buses"/>: <span class="text-success">${countBuses}</span></h6>
                        <a href="<c:url value="/admin/buses"/>" class="card-text btn btn-primary">Go to <spring:message code="label.manager"/></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="card mb-3 mx-1 col-md-5 bg-light">
            <div class="row no-gutters align-items-center">
                <div class="col-md-4">
                    <img class="d-flex img-fluid" src="https://res.cloudinary.com/aleisterw/image/upload/v1640059257/statistic_cs2ack.jpg" alt="...">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title"><spring:message code="label.statistic"/></h5>
                        <p class="card-text"></p>
                        <h6 class="card-text"></h6>
                        <a href="<c:url value="/admin/statistics/traffics"/>" class="card-text btn btn-primary"><spring:message code="label.view"/> <spring:message code="label.statistic"/></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>