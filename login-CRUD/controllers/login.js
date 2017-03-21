/**
 * Created by Administrator on 2017/3/2 0002.
 */


    //        定义全局数据变量
var model={
    hello:[
        {username:"zs", age:23},
        {username:"bb", age:3 }
    ]
};

var loginApp = angular.module("loginApp",['ngRoute']);

// 加载数据
loginApp.run(function ($http) {
    $http.get("users.json").success(function (data) {
        model.items = data;
    });
});

// 配置路由信息
loginApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        // .when('/success', {template:'This is head page！'});
        .when('/success', {templateUrl:'/usersList.html'})
        .when('/failure', {template:'This is failure page!'});
}]);

// 控制器
loginApp.controller("loginCtrl",function ($scope ) {

    //设置用户数据库，
    // $scope.userDB = $scope.users;

    $scope.userDB = model;

    // 处理登录情况
    $scope.Login = function () {

        // 判断用户是否存在
        var isExsit = false;

        // alert("user's total" + $scope.userDB.items.length);

        for (var i = 0; i < $scope.userDB.items.length; i++) {

//         alert($scope.users.items[i].username);
//         如果用户名存在，那么就将状态设置为true

            if ($scope.user != null && $scope.user.username != '' && $scope.userDB.items[i].username == $scope.user.username) {

                isExsit = true;
            }
        }
        // alert("zhenjia" + isExsit);
        if(isExsit) {
            // $location
            window.location='usersList.html';
        }
        else {
            // window.location='index.html';
            alert("用户名/密码不对");
        }
    };

});

// 页面跳转
loginApp.controller("UrlCtrl", function ($scope, $location) {
    $scope.jumpToUrl = function (path) {
        // $location.path(path);
        $location.path(path);
        alert("path() is " + $location.path());

        alert("absUrl() 1 is " + $location.absUrl());

        $location.absUrl("http://localhost:5000/hello.html");
        // $location.absUrl("/hello.html");

        alert("absUrl() 2 is " + $location.absUrl());
        // var url = $location.absUrl();
        // console(url);
        // alert(url);
    }
});
