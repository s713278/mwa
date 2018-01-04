<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Mayurinagar Welfare Association</title> 
     <link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet"></link>
     <link href="<c:url value='/css/app.css' />" rel="stylesheet"></link>
  </head>
  <jsp:include page="/common/header.jsp"></jsp:include>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="ReportController as ctrl">
         <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead"></span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                          	  <th>#SRNO</th>
                          	  <th>#CATEGORY</th>
                           	  <th>#ROADNO</th>
                              <th>#DISTINMETERS</th>                             
                              <th>#ROADWIDTHINFEETS</th>
                            <!--   <th>#BALANCE</th> -->
                              <!-- <th>#MODE</th> -->
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.data | orderBy:'category'">
                          	  <td><span>{{ $index + 1 }}</span></td>
                          	  <td><span>{{u.category}}</span></td>
                          	  <td><span ng-bind="u.roadNo"></span></td>
                              <td><span ng-bind="u.distInMeters"></span></td>
                              <td><span ng-bind="u.roadWidthInFeets"></span></td>
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