<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Struts2 HelloWorld</title>
    <style type="text/css">
        div.divp {
            border:#800080 solid 1px;
            padding: 5px 10px;
            margin: 5px 0px;
        }
    </style>
</head>
<body>
Hello World Struts2
<div data-ng-app="myApp">
    <div class="divp">
        <b>ng-model示例</b>
        <br />
        名字:<input type="text" data-ng-model="name" />
        Hello {{name}}
        <br />
        性别:
        <select data-ng-model="gender">
            <option value="1">male</option>
            <option value="2">female</option>
        </select>
        {{gender}}
    </div>
    <div class="divp" data-ng-init="yourName='Tom'">
        <b>data-ng-init / data-ng-bind 示例</b><br/>
        <input type="text" data-ng-model="yourName" /><br/>
        <span data-ng-bind="yourName" style="background-color: aqua;"></span><br/>
        {{ yourName }}
    </div>
    <div class="divp">
        <b>表达式</b> {{ 5 + 5 }}
    </div>
    <div class="divp" data-ng-controller="myCtrl">
        <b>controller / $scope</b>
        firstName:<input type="text" data-ng-model="firstName" />
        lastName:<input type="text" data-ng-model="lastName" />
        {{ firstName + " " + lastName }}
        <span data-ng-bind="firstName + ' ' + lastName" style="background-color: chartreuse;"></span>
    </div>
    <div>
        <b>$rootScope</b>
        {{ rootVar | uppercase }}
    </div>
    <div class="divp" data-ng-init="person={ firstName:'Tom', lastName:'Cat' }">
        <b>对象</b> {{ person.firstName }}
    </div>
    <div class="divp" data-ng-init="arr1=[ 'Naruto', 'Sasuke', 'Orochimaru', 'Sakura']">
        <b>数组 / 循环遍历</b>
        <ul>
            <li data-ng-repeat="x in arr1">{{ x }}</li>
        </ul>
    </div>
    <div class="divp" data-ng-controller="myCtrl2">
        <b>{{ title }}</b> {{ myUrl }}
    </div>
</div>
<div data-ng-app="myApp2">
    <div class="divp" data-ng-init="person={ firstNam,e:'Tom', lastName:'Cat' }" style="background-color: #FF0000;">
        {{ person.firstName }} 只能有一个 data-ng-app
    </div>
</div>
</body>
<script type="text/javascript" src="/js/angular.1.5.6.min.js"></script>
<script>
    var app = angular.module('myApp', []);
    app.controller('myCtrl', function($scope, $rootScope) {
        $scope.firstName = "Tom"; // 创建变量
        $scope.lastName = "Cat";
        $rootScope.rootVar = "Hello Angular"
    });
    app.controller("myCtrl2", function($scope, $location) {
        $scope.title = "$location";
        $scope.myUrl = $location.absUrl();
    });
</script>
</html>
