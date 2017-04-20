'use strict';

app.controller('ListController', function ($rootScope, $scope, $modal, $http, Base64, BasicAuth) {
    $scope.users = {};
    $scope.haha = {};
    let username = "Lion_account";
    let password = "111111";
    let tok = username + ':' + password;
    let authdata = "Basic "+Base64.encode(username + ':' + password);
    // let authdata = "Basic" + atob(tok);

    // $.ajax({
    //     // type:'get',
    //     url:$scope.app.host + "/userinfo/userinfos",
    //     method:'GET',
    //     beforeSend:function (req) {
    //         req.setRequestHeader('Authorization', authdata);
    //     },
    //     // header:{
    //     //     "Authorization":"Basic"+authdata
    //     // },
    //     // username:"Lion_account",
    //     // password:"111111",
    //     success:function (data) {
    //         $scope.users =JSON.parse(data);
    //         $scope.haha =  $scope.users.data;
    //         $scope.$apply();
    //     }
    // });

    // $http.get($scope.app.host + "/userinfo/userinfos",
    //
    // ).success(function (data) {
    //     // $scope.test = JSON.toJSON(data);
    //     // $scope.users = JSON.parse(data);
    //     $scope.haha = data.data;
    //     // $scope.$apply();
    // });

// 'Basic TGlvbl9hY2NvdW50OjExMTExMQ=='
    $http({
        url: $scope.app.host + "/userinfo/userinfos",
        method:'GET',
        headers:{'Authorization':BasicAuth.Data}
    }).success(function (data) {
        $scope.haha = data.data;
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
        var scope = $rootScope.$new();
        scope.data = {
            userid: id.id,
            app: $scope.app
        }

        var modalInstance = $modal.open({
            templateUrl: 'admin/user/account.html',
            controller: 'AccountController',
            scope: scope
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
            $scope.accountlist = JSON.parse(data);
            $scope.list = $scope.accountlist.data;
            $scope.$apply();
        }
    });

    $scope.add = function () {
        var obj = {userName: '', userinfoAccountRoleList: {}};
        $scope.list.push(obj);
    };

    $scope.del = function (idx) {
        $scope.list.splice(idx, 1);
    };

    $scope.ok = function () {
        alert($scope.list);
        $modalInstance.close();
    };
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}]);


app.controller('DetailController', function ($rootScope, $scope,
            $resource, $stateParams, $state, $http, BasicAuth) {
    $scope.edit_mode = !!$stateParams.id;
    if ($scope.edit_mode) {
        $.ajax({
            type: 'get',
            url: $scope.app.host + "/userinfo/" + $stateParams.id,
            success: function (data) {
                let obj = JSON.parse(data);
                $scope.userInfo = obj.data;
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
            let dd = JSON.stringify($scope.userInfo);

            let postData = {realName:'test', gender:'s'};

            // $.ajax({
            //     type: 'post',
            //     url: $scope.app.host + "/userinfo/add",
            //     data: postData,
            //     success: function (data) {
            //         $state.go('app.user.list');
            //     }
            // });


            $http({
                url:$scope.app.host + "/userinfo/add",
                method:'POST',
                data:$scope.userInfo,
                 headers:{'Authorization':BasicAuth.Data}
                      // 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                // transformRequest: transform
            }).success(function () {
                $state.go('app.user.list');
            });
        }
    };
});
app.factory('Base64',function(){
    var keyStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
    return {
        encode: function (input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;

            do {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);

                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;

                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }

                output = output +
                    keyStr.charAt(enc1) +
                    keyStr.charAt(enc2) +
                    keyStr.charAt(enc3) +
                    keyStr.charAt(enc4);
                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";
            } while (i < input.length);

            return output;
        },

        decode: function (input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;

            // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
            var base64test = /[^A-Za-z0-9\+\/\=]/g;
            if (base64test.exec(input)) {
                window.alert("There were invalid base64 characters in the input text.\n" +
                    "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
                    "Expect errors in decoding.");
            }
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

            do {
                enc1 = keyStr.indexOf(input.charAt(i++));
                enc2 = keyStr.indexOf(input.charAt(i++));
                enc3 = keyStr.indexOf(input.charAt(i++));
                enc4 = keyStr.indexOf(input.charAt(i++));

                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;

                output = output + String.fromCharCode(chr1);

                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }

                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";

            } while (i < input.length);

            return output;
        }
    };
});

// 通过factory来在不同的controller之间传递参数
app.factory('BasicAuth', function (Base64) {
    let username = "Lion_account";
    let password = "111111";
    let tok = username + ':' + password;
    let authdata = "Basic "+Base64.encode(username + ':' + password);
    // let authdata = "Basic" + atob(tok);

    return{
        Data:authdata
    }
});
