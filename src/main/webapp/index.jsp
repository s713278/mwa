<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Mayurinagar Welfare Association</title> 
     <link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet"></link>
     <link href="<c:url value='/css/app.css' />" rel="stylesheet"></link>
     
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="UserController as ctrl">
          <!-- <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Member Payment</span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal" method="Post">
                      <input type="hidden" ng-model="ctrl.user.id" />
                      <div class="row">
                      	<div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="address">Category</label>
                              <div class="col-md-7">
                                 <input type="radio" ng-model="ctrl.user.categeyId" checked="checked" name="categeyId">Apartment</input>
                                 <input type="radio" ng-model="ctrl.user.categeyId" name="categeyId">Independent</input>
                                 <input type="radio" ng-model="ctrl.user.categeyId" name="categeyId">Commercial</input>
                              </div>
                          </div>
                      </div>
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="memberId">Plot No</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.user.memberId" id="memberId" class="memberId form-control input-sm" placeholder="Enter your name" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.uname.$error.required">This is a required field</span>
                                      <span ng-show="myForm.uname.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.uname.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                         
                       
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="feeId">Fee ID</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.user.feeId" id="feeId" class="form-control input-sm" placeholder="Enter your Address. [This field is validation free]"/>
                              </div>
                          </div>
                      </div>
 					
 					 <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="amount">Amount</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.user.amount" id="amount" class="form-control input-sm" placeholder="Enter amount."/>
                              </div>
                          </div>
                      </div>
                      
 					 <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="mobile">Mobile No</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.user.mobile" id="mobile" class="form-control input-sm" placeholder="Enter your Address. [This field is validation free]"/>
                              </div>
                          </div>
                      </div>
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="email">Communication Email</label>
                              <div class="col-md-7">
                                  <input type="email" ng-model="ctrl.user.email" id="email" class="email form-control input-sm" placeholder="Enter your Email" required/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.email.$error.required">This is a required field</span>
                                      <span ng-show="myForm.email.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
 
                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.user.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
  -->         <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">CC Camera's fund list  &nbsp;  &nbsp; &nbsp;<input type="text" ng-model="searchFilter" placeholder="Enter MobileNo/Plot/Name" size="40"></span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                          	  <th>#SRNO</th>
                          	  <th>#PAIDDATE</th>
                           	  <th>#PLOT</th>
                              <th>#NAME</th>                             
                              <th>#CATEGORY</th>
                              <th>#RECEIPT</th>
                              <th>#AMOUNT</th>
                            <!--   <th>#BALANCE</th> -->
                              <!-- <th>#MODE</th> -->
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.users | filter:searchFilter | orderBy:'paidAmount'">
                          	  <td><span>{{ $index + 1 }}</span></td>
                          	  <td><span>{{u.paidDate | date : "dd-MMM-y"}}</span></td>
                          	  <td><span ng-bind="u.member.plotNo"></span></td>
                          	  <td ng-if="u.member.category.code == 'INDEPENDENT'">
                          	  	<span>{{u.member.firstName}}&nbsp;{{u.member.middleName}}&nbsp;{{u.member.lastName}}</span>
                          	  </td>
                          	  <td ng-if="u.member.category.code == 'APARTMENT'">
                          	  	<span>{{u.member.aprtmentName}}</span>
                          	  </td>
                          	  <td ng-if="u.member.category.code == 'COMMERCIAL'">
                          	  	<span>{{u.member.businessName}}</span>
                          	  </td>
                          	  
                          	  <td ng-if="u.member.category.code == 'INDEPENDENT'">
                          	  	<span>{{u.member.category.code}}</span>
                          	  </td>
                          	  <td ng-if="u.member.category.code == 'APARTMENT'">
                          	  	<span>{{u.member.category.code}}</span>
                          	  </td>
                          	  <td ng-if="u.member.category.code == 'COMMERCIAL'">
                          	  	<span>DONATION</span>
                          	  </td>
                              <td><span ng-bind="u.receiptNo"></span></td>
                              <td><span ng-bind="u.paidAmount"></span></td>
                              <!-- <td><span ng-bind="u.paidAmount"></span></td> -->
                             <!--  <td><span ng-bind="u.type"></span></td> -->
                             <!--  <td>
                              <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>
                              </td> -->
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
       
      <script src="<c:url value='/js/angular.js'/>"></script>
      <%-- <script src="<c:url value='/js/app.js' />"></script> --%>
      <script src="<c:url value='/js/service.js' />"></script>
      <script src="<c:url value='/js/controller.js' />"></script>
  </body>
</html>