<%--
  Created by IntelliJ IDEA.
  User: liulongfeng
  Date: 2017/7/26 0026
  Time: 下午 3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
   <style>
       html,body {
           width: 100%;
           height: 100%;
       }
   </style>
    <title>绑定设备</title>
</head>
<body >
<div class="introduction1" style=" height:5%;font-size:50px;color:#333333;margin: 10px">
    1、请确认设备开机并联网正常。
</div>

<div class="checkNetImg" style="padding-left: 10%;padding-bottom: 2.5%; padding-top: 2.5%;width:80%; height:30%;">
    <img src="${pageContext.request.contextPath}/images/checkNet.png" style="width:100%;height:100%;">
</div>

<div class="introduction2" style=" height:10%;font-size: 50px;color:#333333;margin: 10px" >
    2、点击下方“开始绑定”，扫描设备背面的二维码，或者说明书上的二维码。
</div>

<div class="scanImg"style="padding-left: 20%;padding-top: 2.5%;padding-bottom: 2.5%;width: 60%;height: 30%">
    <img src="${pageContext.request.contextPath}/images/scan.png" style="width:100%;height:100%;">
</div>

<div id="bindButtonImg" class="bindButtonImg" style="padding-left:10%;padding-top:5%;padding-bottom:5%;width:80%;height:10%;">
    <img src="${pageContext.request.contextPath}/images/bindButton.png" style="width:100%;height:100%;">
</div>
<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script type="text/javascript" charset="utf-8">

/*    wx.config({
        debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: '', // 必填，公众号的唯一标识
        timestamp: , // 必填，生成签名的时间戳
        nonceStr: '', // 必填，生成签名的随机串
        signature: '',// 必填，签名，见附录1
        jsApiList: [] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });*/
    document.querySelector("#bindButtonImg").onclick = function(){
        /*wx.ready(function(){
            // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
            //微信扫一扫接口
            wx.scanQRCode({
                needResult: 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
                scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
                success: function (res) {
                    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
                }
            });
        });*/
        window.location.href="user/getCode";

    }

</script>
</body>
</html>
