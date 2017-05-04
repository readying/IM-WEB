'use strict';

app.controller('FrameworkController', function ($scope, $http) {

    $scope.tree_data = {};

    $http({
        url:$scope.app.host+"/department/90b5e399-1419-11e7-8bf3-5863ea6b5879/tree",
        // url:"http://localhost:9091/ma/department/90b5e399-1419-11e7-8bf3-5863ea6b5879/tree",
        method:'GET'
    }).success(function (data) {
        $scope.tree_data = data.data;
        console.log($scope.tree_data);
    });

    $scope.expanding_property={
        field:"id",
        displayName:'组织架构',
        cellTemplate:"{{row.branch[expandingProperty.filed]}}"
    };

    $scope.col_defs = [
        {
            field:"name",
            displayName:"cx"
        }
    ];




});