'use strict';

app.controller('ListController', function ($rootScope, $scope, $modal, $http) {
    $scope.users = {};
    $scope.haha = {};
    $.ajax({
        type:'get',
        url:$scope.app.host + "/userinfo/userinfos",
        success:function (data) {
            $scope.users =JSON.parse(data);
            $scope.haha =  $scope.users.data;
            $scope.$apply();
        }
    });

    $scope.delete = function (id) {
        //弹出删除确认
        var modalInstance = $modal.open({
            templateUrl: 'admin/confirm.html',
            controller: 'ConfirmController',
            size: 'sm',
        });
        modalInstance.result.then(function () {
            $.ajax({
                type: 'get',
                url: $scope.app.host + "/userinfo/" + id.id + "/delete",
                success: function (data) {
                    $state.go('app.user.list');
                }
            })

        });
    };

    $scope.account = function (id) {
        var _scope = $rootScope.$new();
        _scope.data = {
            userid: id.id,
            app: $scope.app
        }

        var modalInstance = $modal.open({
            templateUrl: 'admin/user/account.html',
            controller: 'AccountController',
            scope: _scope
        });
        modalInstance.result.then(function () {
        });
    }

});

app.controller('ConfirmController', ['$scope', '$modalInstance', function ($scope, $modalInstance) {
    $scope.ok = function () {
        $modalInstance.close();
    };
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}]);

app.controller('AccountController', ['$scope', '$modalInstance', function ($scope, $modalInstance) {
    var data = $scope.data;

    console.log(data.userid);


    //多选框所有角色信息
    $.ajax({
        type: 'get',
        url: data.app.host + "/role/roles",
        success: function (data) {
            $scope.roles = JSON.parse(data);
            $scope.roleList = $scope.roles.data;
            $scope.$apply();

        }
    });

    //查询该用户下所有账户信息
    $.ajax({
        type: 'get',
        url: data.app.host + "/userinfo/" + data.userid + "/accounts",
        success: function (data) {
            $scope.list = JSON.parse(data);
            $scope.$apply();
        }
    });


    $scope.add = function () {
        var obj = {userName: '', userinfoAccountRoleList: {}};
        $scope.list.push(obj);
    }

    $scope.del = function (idx) {
        $scope.list.splice(idx, 1);
    }

    $scope.ok = function () {
        alert($scope.list);
        $modalInstance.close();
    };
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}]);


app.controller('DetailController', function ($rootScope, $scope, $resource, $stateParams, $state) {
    $scope.edit_mode = !!$stateParams.id;
    if ($scope.edit_mode) {
        $.ajax({
            type: 'get',
            url: $scope.app.host + "/userinfo/" + $stateParams.id,
            success: function (data) {
                $scope.userInfo = JSON.parse(data);
                $scope.$apply();
            }
        })
    }
    else {
        $scope.data = {};
    }

    $scope.submit = function () {
        if ($scope.edit_mode) {
            alert($scope.app.host + "/userinfo/" + $scope.userInfo.id + "/update");
            $.ajax({
                type: 'post',
                url: $scope.app.host + "/userinfo/" + $scope.userInfo.id + "/update",
                data: $scope.userInfo,
                success: function (data) {
                    $state.go('app.user.list');
                }
            })
        }

        else {
            $.ajax({
                type: 'post',
                url: $scope.app.host + "/userinfo/add",
                data: $scope.userInfo,
                success: function (data) {
                    $state.go('app.user.list');
                }
            })

        }
    };


});


