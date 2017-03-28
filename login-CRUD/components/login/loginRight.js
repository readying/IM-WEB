/**
 * Created by Administrator on 2017/3/24.
 */

angular.module("login", [])
    .factory("login", function () {

    })
    .directive("loginDemo", function (login) {

        return {
            restrict: "E",
            templateUrl: "components/login/loginRight.html",
            controller: function ($scope) {

            }
        }
    });