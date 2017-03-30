'use strict';

app
    .run(
        function ($rootScope,   $state,   $stateParams,$localStorage,$http) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $localStorage.auth;
            $rootScope.$state = $state;
            $rootScope.$stateParams = $stateParams;
            $rootScope.$on('$stateChangeSuccess', function(event, to, toParams, from, fromParams) {
                $rootScope.previousState = from;
                $rootScope.previousStateParams = fromParams;
            });
        }
    )
    .config(
        function ($stateProvider,   $urlRouterProvider) {
            $urlRouterProvider
                .otherwise('/auth/loading');
            $stateProvider
                .state('auth',{
                    abstract: true,
                    url:'/auth',
                    template: '<div ui-view class="fade-in"></div>',
                    resolve: {
                        deps: ['$ocLazyLoad',
                            function( $ocLazyLoad ){
                                return $ocLazyLoad.load('admin/auth/ctrl.js');
                            }]
                    }
                })
                .state('auth.loading',{
                    url:'/loading',
                    templateUrl:'admin/auth/loading.html',
                })
                .state('auth.login',{
                    url:'/login',
                    templateUrl:'admin/auth/login.html',
                })

                .state('app', {
                    abstract: true,
                    url: '/app',
                    templateUrl: 'admin/app.html',
                })
                .state('app.dashboard', {
                    url: '/dashboard',
                    templateUrl: 'admin/dashboard.html',
                    ncyBreadcrumb: {
                        label: '<i class="fa fa-home"></i> 首页'
                    }
                })
                .state('app.user', {
                    abstract: true,
                    url: '/user',
                    template: '<div ui-view class="fade-in"></div>',
                    resolve: {
                        deps: ['$ocLazyLoad',
                            function( $ocLazyLoad ){

                                return $ocLazyLoad.load('admin/user/ctrl.js');

                            }]
                    }
                })
                .state('app.user.list', {
                    url: '/list?page&search',
                    templateUrl: 'admin/user/list.html',
                    ncyBreadcrumb: {
                        parent:'app.dashboard',
                        label: '用户列表',
                    }
                })
                .state('app.user.detail', {
                    url: '/detail/{id}',
                    templateUrl: 'admin/user/detail.html',
                    ncyBreadcrumb: {
                        parent:'app.user.list',
                        label: '编辑',
                    }
                })
                .state('app.user.create', {
                    url: '/create',
                    templateUrl: 'admin/user/detail.html',
                    ncyBreadcrumb: {
                        parent:'app.user.list',
                        label: '新增',
                    }
                })
                .state('app.resource', {
                    abstract: true,
                    url: '/resource',
                    template: '<div ui-view class="fade-in"></div>',
                    resolve: {
                        deps: ['$ocLazyLoad',
                            function( $ocLazyLoad ){

                                return $ocLazyLoad.load('admin/resource/ctrl.js');

                            }]
                    }
                })
                .state('app.resource.list',{
                    url:'/list',
                    templateUrl:'admin/resource/list.html',
                    ncyBreadcrumb:{
                        parent:'app.dashboard',
                        label:'资源列表'
                    }
                })
                .state('app.resource.detail',{
                    url:'/detail/{id}',
                    templateUrl:'admin/resource/detail.html',
                    ncyBreadcrumb:{
                        parent:'app.resource.list',
                        label:'编辑'
                    }
                })
                .state('app.resource.create', {
                    url:'/create',
                    templateUrl:'admin/resource/detail.html',
                    ncyBreadcrumb:{
                        parent:'app.resource.list',
                        label:'新增',
                    }
                })
                .state('app.authority',{
                    abstract:true,
                    url:'/authority',
                    template:'<div ui-view class="fade-in"></div>',
                    resolve:{
                        deps:['$ocLazyLoad',
                            function ($ocLazyLoad) {
                                return $ocLazyLoad.load('admin/authority/ctrl.js')
                            }]
                    }
                })
                .state('app.authority.list',{
                    url:'/list',
                    templateUrl:'admin/authority/list.html',
                    ncyBreadcrumb:{
                        parent:'app.dashboard',
                        label:'权限列表'
                    }
                })
                .state('app.authority.detail', {
                    url:'detail/{id}',
                    templateUrl:'admin/authority/detail.html',
                    ncyBreadcrumb:{
                        parent:'app.authority.list',
                        label:'编辑'
                    }
                })
                .state('app.authority.create',{
                    url:'/create',
                    templateUrl:'admin/authority/detail.html',
                    ncyBreadcrumb:{
                        parent:'app.authority.list',
                        label:'新增'
                    }
                })

        }
    );

app.config(function ($httpProvider) {
    $httpProvider.interceptors.push('AuthInterceptor');
})
app.factory('AuthInterceptor', function ($rootScope, $q,$location) {
    return {
        responseError: function (response) {
            if(response.status==401)
            {
                $location.url('/auth/login');
            }
            return $q.reject(response);
        }
    };
})