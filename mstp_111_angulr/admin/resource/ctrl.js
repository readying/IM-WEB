'use strict';


app.controller('ResourceListController', function($scope,$modal, $http) {
    $scope.users ={};
    $scope.haha = {};

    $scope.currentResource = {};

        $.ajax({
            type:'get',
            url:$scope.app.host + "/resource/resources",
            success:function (data) {
                var obj = JSON.parse(data);
                $scope.users = obj;
                $scope.haha =  $scope.users.data;
                $scope.$apply();
            }
        });





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
               url:$scope.app.host + "/resource/"+id.id+"/delete",
               success:function (data) {
                   $state.go('app.resource.list');
               }
           })

       });
    }

    $scope.update = function (resource) {

        $scope.currentResource = resource;

        alert($scope.currentResource.resourceName);
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
app.controller('ResourceDetailController', function($rootScope,$scope, $resource, $stateParams,$state) {
    $scope.edit_mode = !!$stateParams.id;
    if($scope.edit_mode){
        $.ajax({
            type:'get',
            url:$scope.app.host + "/resource/"+$stateParams.id,
            success:function (data) {
                var obj = JSON.parse(data);
                $scope.resource = obj.data;
              //  alert($scope.userInfo);
                $scope.$apply();

            }
        })
    }
    else{
        $scope.data = {};
    }

    $scope.submit = function(){
        alert($scope.app.host + "/resource/"+$scope.resource.id+"/update");
        if($scope.edit_mode){
            $.ajax({
                type:'post',
                url:$scope.app.host + "/resource/"+$scope.resource.id+"/update",
                data:$scope.resource,
                success:function (data) {
                    alert(data);
                    $state.go('app.resource.list');
                }
            })
        }

        else{
            $.ajax({
                type:'post',
                url:$scope.app.host + "/resource/add",
                data:$scope.resource,
                success:function (data) {
                    alert(data);
                    $state.go('app.resource.list');
                }
            })

        }
    };




});