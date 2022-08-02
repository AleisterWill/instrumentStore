<%-- 
    Document   : accounts
    Created on : Jul 1, 2022, 3:04:39 PM
    Author     : three
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="signup" value="/accounts/signup" />
<c:url var="signin" value="/accounts" />

<div class="container d-flex justify-content-center bg-light" style="height: 150vh;">
    <div class="jumbotron col-md-6 my-auto bg-white shadow" style="border-radius: 50px;">
        <c:if test="${signupSuccess.equals('success')}">
            <div class="alert alert-success">Đăng ký thành công, bạn có thể đăng nhập</div>
        </c:if>
        <ul class="nav nav-pills mb-3 justify-content-center" id="pills-tab" role="tablist">

            <li class="nav-item" role="presentation">
                <a class="nav-link btn btn-outline-dark active" id="pills-signin-tab" data-toggle="pill" href="#pills-signin" role="tab" aria-controls="pills-signin" aria-selected="true">
                    <i class="fa fa-book"></i> Đăng nhập
                </a>
            </li>
            <li class="nav-item" role="presentation">
                <a class="nav-link btn btn-outline-dark" id="pills-signup-tab" data-toggle="pill" href="#pills-signup" role="tab" aria-controls="pills-signup" aria-selected="false">
                    <i class="fa fa-comment-o"></i> Đăng ký
                </a>
            </li>
        </ul>

        <div class="tab-content mb-3" id="pills-tabContent">

            <!-- Sign In -->
            <div class="tab-pane fade show active" id="pills-signin" role="tabpanel" aria-labelledby="pills-signin-tab">
                <div class="card mx-auto" style="width: 60%;">
                    <form method="post" action="${signin}">
                        <div class="card-body">
                            <div class="input-group mb-2">
                                <div class="input-group-prepend">
                                    <div class="input-group-text"><i class="fa fa-envelope"></i></div>
                                </div>
                                <input class="form-control" name="email" type="email" placeholder="Email" />
                            </div>
                            <div class="input-group mb-2">
                                <div class="input-group-prepend">
                                    <div class="input-group-text"><i class="fa fa-key"></i></div>
                                </div>
                                <input class="form-control" name="password" type="password" placeholder="Password" />
                            </div>               
                        </div>
                        <div class="card-footer text-center">
                            <input type="submit" class="btn btn-outline-dark p-2" value="Đăng nhập"/>
                        </div>
                    </form>

                </div>
            </div>

            <!-- Sign Up-->
            <div class="tab-pane fade show" id="pills-signup" role="tabpanel" aria-labelledby="pills-signup-tab">
                <div class="card mx-auto" style="width: 100%;">
                    <form:form action="${signup}" method="post" enctype="multipart/form-data" cssClass="form-group" modelAttribute="newUser">
                        <div class="card-body">
                            <div class="form-row mb-4">
                                <div class="col">
                                    <div class="form-label-group">
                                        <label class="label-float" for="firstName">First Name</label>
                                        <form:input placeholder="First Name" path="firstName" type="text" cssClass="form-control input-group"/>
                                        <form:errors path="firstName" cssClass="font-italic text-danger" element="p" />
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-label-group">
                                        <label class="label-float" for="lastName">Last Name</label>
                                        <form:input placeholder="Last Name" path="lastName" type="text" cssClass="form-control input-group"/>
                                        <form:errors path="lastName" cssClass="font-italic text-danger" element="p" />
                                    </div>
                                </div>
                            </div>
                            <div class="form-label-group mb-4">
                                <label class="label-float" for="phone">Phone</label>
                                <form:input placeholder="Phone" path="phone" type="text" cssClass="form-control input-group"/>

                            </div>
                            <div class="form-label-group mb-4">
                                <label class="label-float" for="email">Email address</label>
                                <form:input placeholder="Email address" path="email" type="text" cssClass="form-control input-group"/>
                                <c:if test="${errExisted != null}">
                                    <div class="alert alert-danger">${errExisted}</div>
                                </c:if>
                                <form:errors path="email" cssClass="font-italic text-danger" element="p" />
                            </div>
                            <div class="form-label-group mb-4">
                                <label class="label-float" for="password">Password</label>   
                                <form:input placeholder="Password" path="password" type="password" cssClass="form-control input-group"/>
                                <form:errors path="password" cssClass="font-italic text-danger" element="p" />
                            </div>
                            <div class="form-label-group mb-4">
                                <label class="label-float" for="confirmPW">Confirm Password</label>   
                                <form:input placeholder="Confirm Password" path="confirmPW" type="password" cssClass="form-control input-group"/>
                                <c:if test="${errPW != null}">
                                    <div class="alert alert-danger">${errPW}</div>
                                </c:if>
                            </div>
                            <div class="form-label-group mb-4">
                                <label class="label-float" for="file">Avatar</label>  
                                <form:input placeholder="Avatar" path="file" type="file" cssClass="form-control input-group"/>

                            </div>

                        </div>
                        <div class="card-footer text-center">
                            <input type="submit" class="btn btn-outline-dark p-2" value="Đăng ký" />
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>