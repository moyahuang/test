<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <title>扫码</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">

    <script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
    <script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
    <script type="text/javascript" charset="utf-8"
            src="http://map.qq.com/api/js?v=2.exp&key=YOUR_KEY&libraries=convertor"></script>

    <style type="text/css">
        * {
            margin: 0px;
            padding: 0px;
        }

        body, button, input, select, textarea {
            font: 12px/16px Verdana, Helvetica, Arial, sans-serif;
        }

        #info {
            width: 603px;
            padding-top: 3px;
            overflow: hidden;
        }

        .btn {
            width: 112px;
        }

        #container {
            min-width: 600px;
            min-height: 767px;
        }
    </style>

    <script type="text/javascript">
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: '${requestScope.appId}', // 必填，公众号的唯一标识
            timestamp: '${requestScope.timestamp}', // 必填，生成签名的时间戳
            nonceStr: '${requestScope.nonceStr}', // 必填，生成签名的随机串
            signature: '${requestScope.signature}',// 必填，签名，见附录1
            jsApiList: ['checkJsApi', 'scanQRCode'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });

        wx.ready(function () {
            wx.scanQRCode({
                needResult: 1,
                desc: '扫码结果',
                success: function (res) {
                    alert(res.resultStr);
                }
            });
        });

        wx.error(function (res) {//错误时调用
            alert(res.errMsg);
        });
    </script>


</head>
<body>
<div id="container"></div>
</body>
</html>