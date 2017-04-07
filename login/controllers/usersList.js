/**
 * Created by Administrator on 2017/3/2 0002.
 */

// var selectUrl = "http://192.168.5.157:9099/v1.0/ma/userinfo/userinfos";
// var baseUrl = "http://192.168.5.157:9099/v1.0/ma/userinfo/";
// var addUrl = "http://192.168.5.157:9099/v1.0/ma/userinfo/add";
// var selectUrl = "http://192.168.5.157:9090/v1.0/ma/userinfo/userinfos";
// var baseUrl = "http://192.168.5.157:9090/v1.0/ma/userinfo/";
// var addUrl = "http://192.168.5.157:9090/v1.0/ma/userinfo/add";
var selectUrl = "http://192.168.5.183:9090/v1.0/ma/userinfo/userinfos";
var baseUrl = "http://192.168.5.183:9090/v1.0/ma/userinfo/";
var addUrl = "http://192.168.5.183:9090/v1.0/ma/userinfo/add";


postCfg = {
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    transformRequest: function(data) {
        return data;
    }
};


angular.module("usersListApp")
    .constant("dataUrl", selectUrl)
    .constant("baseUrl", baseUrl)
    .constant("addUrl", addUrl)
    .controller("usersListCtrl", function ($scope, $http, dataUrl, baseUrl, addUrl) {

        $scope.currentUser = null;

        $scope.users ={};

        $scope.haha = {};

        $scope.user = {};

        $http.get(dataUrl)
            .success(function (data) {
                $scope.users = data;

                // console.log("this is code "+ $scope.users.code);
                // console.log("this is js object "+ $scope.users.data);
                // console.log("this is json "+ angular.toJson($scope.users.data));
                // console.log("------");

                 $scope.haha =  $scope.users.data;
                //
                // console.log("this "+ $scope.temp[0].realName);
                //
                // var tempdata =  angular.toJson($scope.users.data);
                //
                // console.log("this is first json "+ tempdata[0]);
                // console.log("this is first json "+ tempdata[1]);
                // console.log("this is first json "+ tempdata[2]);
            })
            .error(function (error) {
                $scope.users.error = error;
            });


        //删除用户
        $scope.deleteUser = function (user) {

            $scope.users.splice($scope.users.indexOf(user),1);

            $http({
                method:"GET",
                url: baseUrl + user.id + "/delete"

            }).success(function () {
                console.log("delete success");
                alert("deleteUser");
            });

        };

        // 增加用户
        // $scope.addUser = function (user) {
        //
        //     $http.post(addUrl, user,{'Content-Type':'application/x-www-form-urlencoded'}).success(function (newUser) {
        //         // $scope.users.push(newUser);
        //         alert("返回值是 " + newUser.code + newUser.data);
        //         alert("added success");
        //     })
        //
        //
        //
        // };

        var  postData = {text:'long blob of text'};

        $scope.addUser = function () {



            // $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";

            // $.ajax({
            //     type:'POST',
            //     url:addUrl,
            //     data:user
            //
            // });

            // var form = new FormData();
            // form.append("realName", "chenxioa" );
            // form.append("gender", "nan");
            // var data = {'realName':'test', 'gender' :"nan"};

            $http({
                url: addUrl,
                method: "POST",
                data:$scope.user
                // headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).success(function (response) {
                alert("返回值 "+response.data);
            }).error(function (response) {
                alert("返回值 "+response.data);
            });
            //
            //
            //     jQuery.post(addUrl, { foo: 'bar' }).success(function(response) {
            //         // ...
            //     });

            //
            // $http.post(addUrl, user).then(function (response) {
            //
            //     alert(response.data);
            // })
        };


        // $scope.addUser = function (user) {
        //     $http({
        //         url:addUrl,
        //         method:"POST",
        //         data:user
        //     }).success(function () {
        //         alert("added success");
        //     })
        // }



        //更新用户
        $scope.updateUser = function (user) {

            $scope.currentUser = user ;

            // $http({
            //     url: baseUrl + user.id + "/update",
            //     method: "POST",
            //     data:$scope.currentUser
            //     // headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            // })
            //     .then(function (response) {
            //             // alert(response.data);
            //         },
            //         function (response) {
            //             // alert(response.data)
            //         }
            //     );


        };


        $scope.saveEdit  = function (user) {

            $scope.currentUser = user;

            $http({
                method:"POST",
                url: baseUrl + user.id + "/update",
                data:$scope.currentUser

            }).success(function () {
                // console.log("update success");
                alert("更新成功！");
            });

        }

    });
















