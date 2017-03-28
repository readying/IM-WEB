/**
 * Created by Administrator on 2017/3/24.
 */
var dataUrl="http://192.168.5.183:9099/v1.0/ma/userinfo/userinfos";


angular.module("usersListApp")
    .constant("Url", dataUrl)
    .controller("loginCtrl", function ($scope, login, $http, Url, $q) {
        $scope.login = {};

        var userInfo;

        $scope.Login = function (userName, password) {

            var deferred = $q.defer();

            $http.get(Url,{
                userName: userName,
                password: password
            }).then(function (result) {
                userInfo = {
                    accessToken: result.data.access_token,
                    userName:result.data.userName
                };
                $window.sessionStorage["userInfo"] = JSON.stringify(userInfo);
                deferred.resolve(userInfo);
            }, function (error) {
                deferred.reject(error);
            });

            return deferred.promise;
        }
        return{
            login:login
        };

    });