'use strict';


app.controller('AuthorityListController', function($scope,$modal, $http) {
    $scope.users ={};
    $scope.haha = {};

    $scope.currentResource = {};

        $.ajax({
            type:'get',
            url:$scope.app.host + "/authority/authorities",
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
               url:$scope.app.host + "/authority/"+id.id+"/delete",
               success:function (data) {
                   $state.go('app.authority.list');
               }
           })

       });
    };

   $scope.resource = function (id) {
       var temp = $rootscope.$new();
       temp.data = {
           authority: id.id,
           app: $scope.app
       };
       var modalInstance = $modal.open({
           templateUrl: 'admin/authority/resource.html',
           controller: 'AuthorityResourceController',
           scope:temp
       });
      modalInstance.result.then(function () {

      })
   }




    $scope.update = function (authority) {

        $scope.currentResource = authority;

        alert($scope.currentResource.authorityName);
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

app.controller('AuthorityResourceController',['$scope', '$modalInstance',function ($scope, $modalInstance) {
    var data = $scope.data;

    // 所有资源信息
    $.ajax({
        type:'get',
        url:data.app.host
    })
}])

app.controller('AuthorityDetailController', function($rootScope,$scope, $resource, $stateParams,$state) {
    $scope.edit_mode = !!$stateParams.id;
    $scope.authority = {};
    if($scope.edit_mode){
        $.ajax({
            type:'get',
            url:$scope.app.host + "/authority/"+$stateParams.id,
            success:function (data) {
                var obj = JSON.parse(data);
                $scope.authority = obj.data;
              //  alert($scope.userInfo);
                $scope.$apply();

            }
        })
    }
    else{
        $scope.data = {};
    }

    $scope.submit = function(){
        alert($scope.app.host + "/authority/"+$scope.authority.id+"/update");
        if($scope.edit_mode){
            $.ajax({
                type:'post',
                url:$scope.app.host + "/authority/"+$scope.authority.id+"/update",
                data:$scope.authority,
                success:function (data) {
                    alert(data);
                    $state.go('app.authority.list');
                }
            })
        }

        else{
            $.ajax({
                type:'post',
                url:$scope.app.host + "/authority/add",
                data:$scope.authority,
                success:function (data) {
                    alert(data);
                    $state.go('app.authority.list');
                }
            })

        }
    };




});