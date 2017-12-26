'use strict';
var myApp = angular.module('myApp', []); 
angular.module('myApp').factory('UserService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = '/listAll';
    var ALL_PAYMENTS_URI = '/allPayments';
    var PAY_DUE_PAYMENT_URI = '/payDueAmout';
 
    var factory = {
        fetchAllUsers: fetchAllUsers,
        fetchAllPayments: fetchAllPayments,
        createUser: createUser
        /*updateUser:updateUser,
        deleteUser:deleteUser*/
    };
 
    return factory;
 
    function fetchAllUsers() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function fetchAllPayments() {
        var deferred = $q.defer();
        $http.get(ALL_PAYMENTS_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching payments');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    function createUser(user) {
    	alert("request:"+user);
        var deferred = $q.defer();
        $http.post(PAY_DUE_PAYMENT_URI, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    /*
    function updateUser(user, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteUser(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 */
}]);