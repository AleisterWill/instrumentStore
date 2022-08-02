<%-- 
    Document   : accounts-setting
    Created on : Jul 11, 2022, 1:31:39 PM
    Author     : three
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${currentUser == null}">
    <h1>Ban chua dang nhap</h1>
</c:if>

<c:url value="/accounts/setting/changeProfile" var="changeProfile"/>
<c:url value="/accounts/setting/changePW" var="changePW" />
<c:url value="accounts/setting/changeAvatar" var="changeAvatar" />

<c:if test="${currentUser != null}">
    <div class="ml-1 row w-100 my-5 p-md-5 shadow bg-light" style="border-radius: 50px;">
        <div class="col-md-9">
            <c:if test="${err != null}">
                <div class="alert alert-danger">${err}</div>
            </c:if>

            <div class="tab-content" id="v-pills-tabContent">

                <div class="tab-pane fade show active" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab">
                    <div class="row bg-white p-md-5">
                        <div class="col-md-2">
                            <img src="${user.avatar}" class="w-100" alt="alt"/>
                        </div>
                        <div class="col-md-10">
                            <div class="card mx-auto w-100">
                                <form:form action="${changeProfile}" method="post" enctype="multipart/form-data" cssClass="form-group" modelAttribute="user">
                                    <form:errors path="*" element="div" cssClass="alert alert-danger" />
                                    <div class="card-body">
                                        <div class="form-row mb-4">
                                            <div class="col">
                                                <div class="form-label-group">
                                                    <label class="label-float" for="firstName">First Name</label>
                                                    <form:input placeholder="First Name" path="firstName" type="text" cssClass="form-control input-group" />
                                                </div>
                                            </div>
                                            <div class="col">
                                                <div class="form-label-group">
                                                    <label class="label-float" for="lastName">Last Name</label>
                                                    <form:input placeholder="Last Name" path="lastName" type="text" cssClass="form-control input-group" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-label-group mb-4">
                                            <label class="label-float" for="phone">Phone</label>
                                            <form:input placeholder="Phone" path="phone" type="text" cssClass="form-control input-group"/>

                                        </div>
                                        <div class="form-label-group mb-4">
                                            <label class="label-float" for="email">Email address</label>
                                            <form:input placeholder="Email address" path="email" type="text" cssClass="form-control input-group" readonly="true"/>
                                        </div>
                                        <div class="row justify-content-center mt-2">
                                            <input type="submit" class="btn btn-outline-dark p-2" value="Lưu thay đổi" />
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade justify-content-center" id="v-pills-security" role="tabpanel" aria-labelledby="v-pills-security-tab">
                    <form:form action="${changePW}" method="post" enctype="multipart/form-data" cssClass="form-group" modelAttribute="user">
                        <div class="form-group">
                            <label>Mật khẩu hiện tại</label>
                            <form:input path="confirmPW" type="password" cssClass="form-control" placeholder="Current Password" />
                        </div>
                        <div class="form-group">
                            <label>Mật khẩu mới</label>
                            <form:input path="newPW" type="password" cssClass="form-control" placeholder="New Password"/>
                        </div>
                        <div class="form-group">
                            <label>Nhập lại mật khẩu mới</label>
                            <form:input path="confirmNewPW" type="password" cssClass="form-control" placeholder="Confirm New Password"/>
                        </div>
                        <div class="row justify-content-center mt-2">
                            <input type="submit" class="btn btn-outline-dark p-2" value="Lưu thay đổi" />
                        </div>
                    </form:form>
                </div>
                <div class="tab-pane fade justify-content-center" id="v-pills-avatar" role="tabpanel" aria-labelledby="v-pills-avatar-tab">
                    <form:form action="${changeavatar}" method="post" enctype="multipart/form-data" cssClass="form-group" modelAttribute="user">
                        <div class="form-group">
                            <form:input path="file" type="file" cssClass="form-control"/>
                        </div>
                        <div class="row justify-content-center mt-2">
                            <input type="submit" class="btn btn-outline-dark p-2" value="Lưu thay đổi" />
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <a class="nav-link btn btn-outline-dark active" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile" role="tab" aria-controls="v-pills-profile" aria-selected="true">Thông tin chung</a>
                <a class="nav-link btn btn-outline-dark" id="v-pills-avatar-tab" data-toggle="pill" href="#v-pills-avatar" role="tab" aria-controls="v-pills-avatar" aria-selected="false">Ảnh đại diện</a>
                <a class="nav-link btn btn-outline-dark" id="v-pills-security-tab" data-toggle="pill" href="#v-pills-security" role="tab" aria-controls="v-pills-security" aria-selected="false">Bảo mật</a>
            </div>
        </div>
    </div>
</c:if>

