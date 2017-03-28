'use strict';

app.controller('ListController', function($scope,$modal) {
    $scope.users ={};
    $scope.haha = {};
        $.ajax({
            type:'get',
            url:$scope.app.host + "/userinfo/userinfos",
            success:function (data) {
                var obj = JSON.parse(data);
                $scope.users = obj;
                $scope.haha =  $scope.users.data;
                $scope.$apply();
            }
        })


   $scope.delete=function(id){
       //弹出删除确认
       var modalInstance = $modal.open({
           templateUrl: 'admin/confirm.html',
           controller: 'ConfirmController',
           size:'sm',
       });
       modalInstance.result.then(function () {
           $.ajax({
               type:'get',
               url:$scope.app.host + "/userinfo/"+id.id+"/delete",
               success:function (data) {
                   $state.go('app.news.list');
               }
           })

       });
    }

    });

app.controller('ConfirmController', ['$scope', '$modalInstance', function($scope, $modalInstance){
    $scope.ok = function () {
        $modalInstance.close();
    };
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}]);
app.controller('DetailController', function($rootScope,$scope, $resource, $stateParams,$state) {
    $scope.edit_mode = !!$stateParams.id;
    if($scope.edit_mode){
        $.ajax({
            type:'get',
            url:$scope.app.host + "/userinfo/"+$stateParams.id,
            success:function (data) {
                var obj = JSON.parse(data);
                $scope.userInfo = obj.data;
                $scope.$apply();
            }
        })
    }
    else{
        $scope.data = {};
    }

    $scope.submit = function(){
        alert($scope.app.host + "/userinfo/"+$scope.userInfo.id+"/update");
        if($scope.edit_mode){
            $.ajax({
                type:'post',
                url:$scope.app.host + "/userinfo/"+$scope.userInfo.id+"/update",
                data:$scope.userInfo,
                success:function (data) {
                    alert(data);
                    $state.go('app.news.list');
                }
            })
        }

        else{
            $.ajax({
                type:'post',
                url:$scope.app.host + "/userinfo/add",
                data:$scope.userInfo,
                success:function (data) {
                    alert(data);
                    $state.go('app.news.list');
                }
            })

        }
    };




});