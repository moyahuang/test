<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <title>地图导航</title>
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
        /* wx.config({
         debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
         appId: '${requestScope.appId}', // 必填，公众号的唯一标识
         timestamp: '${requestScope.timestamp}', // 必填，生成签名的时间戳
         nonceStr: '${requestScope.nonceStr}', // 必填，生成签名的随机串
         signature: '${requestScope.signature}',// 必填，签名，见附录1
         jsApiList: ['checkJsApi', 'openLocation', 'getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
         });

         wx.ready(function () {
         wx.getLocation({
         type: 'gcj02',
         success: function (res) {
         var center = new qq.maps.LatLng(res.latitude, res.longitude);
         var map = new qq.maps.Map(document.getElementById('container'),{
         center: center,
         zoom: 13
         });
         //创建marker
         var marker = new qq.maps.Marker({
         position: center,
         map: map
         });


         },
         cancel: function (res) {
         alert('用户拒绝授权获取地理位置');
         }
         });
         });
         */
        /*
         $().ready(function(){
         $.ajax({
         type: "POST",
         url: "${pageContext.request.contextPath}/location/getLocation",
         data:{lid:'123'},
         success:function(result){
         alert(result.lat + " " + result.lon);
         },
         error:function(result){
         alert("错误")
         }
         });
         });
         */
    </script>



    <script type="text/javascript">
//以下代码是我整合部分，上面罗辉代码仍保留
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: '${requestScope.appId}', // 必填，公众号的唯一标识
            timestamp: '${requestScope.timestamp}', // 必填，生成签名的时间戳
            nonceStr: '${requestScope.nonceStr}', // 必填，生成签名的随机串
            signature: '${requestScope.signature}',// 必填，签名，见附录1
            jsApiList: ['checkJsApi', 'openLocation', 'getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });

        var init = function() {
            var timer;
            var lat=0;
            var lon=0;
            //这部分代码是第一次读项圈数据，后期将取代lat,lon的固定值
            while(lat==0 && lon==0){
            $().ready(function(){
                $.ajax({
                    type: "GET",
                    url: "${pageContext.request.contextPath}/location/getLocation",
                    async:false,
                    data:{lid:'123'},
                    success:function(result){
                        //alert(result.lat + " " + result.lon);
                        lat = result.lat;
                        lon = result.lon;
                        //alert("lat="+lat+"lon="+lon);
                    },
                    error:function(result){
                        alert("错误")
                    }
                });
            });
            }
            //将坐标进行转换——腾讯坐标
            var maker1 = null;
            var label1 = null;
            var maker2 = null;
            var label2 = null;

            qq.maps.convertor.translate(new qq.maps.LatLng(lat,lon),1,function(res){
                latlng = res[0];

                var map = new qq.maps.Map(document.getElementById('container'),{
                    center: latlng,
                    zoom: 15 
                });

                //设置定时器，从服务器端每隔3秒读取一次数据，然后在地图上进行标示
                function mark() {
                    //手机坐标获取
                   if(maker1 != null){
                     maker1.setMap(null);
                     label1.setMap(null);
                     }
                    /*
                     if(maker2 != null) {
                     maker2.setMap(null);
                     label2.setMap(null);
                     }*/
                    wx.ready(function () {
                        wx.getLocation({
                            type: 'gcj02',
                            success: function (res) {
                                var center = new qq.maps.LatLng(res.latitude, res.longitude);
                                //创建marker
f
                                maker1 = new qq.maps.Marker({
                                    position: center,
                                    map: map,
                                    animation: qq.maps.MarkerAnimation.BOUNCE
                                });
                                //创建文本标示
                                 label1 = new qq.maps.Label({
                                    position:  center,
                                    map: map,
                                    content: '我的位置'
                                });
                            },
                            cancel: function (res) {
                                alert('用户拒绝授权获取地理位置');
                            }
                        });
                    });

                    //项链坐标获取
                    // $().ready(function(){
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/location/getLocation",
                        data:{lid:'123'},
                        success:function(result){

                            //alert(result.lat + " " + result.lon);
                            //将坐标进行转换——腾讯坐标
                            qq.maps.convertor.translate(new qq.maps.LatLng(result.lat,result.lon),1,function(res){
                                latlng = res[0];
                            });
                            if(maker2 != null) {
                                maker2.setMap(null);
                                label2.setMap(null);
                            }
                            //创建位置标示marker
                            maker2 = new qq.maps.Marker({
                                position: latlng,
                                map: map,
                                animation: qq.maps.MarkerAnimation.BOUNCE
                            });

                            //创建文本标示
                             label2 = new qq.maps.Label({
                                position: latlng,
                                map: map,
                                content: '项链位置'
                            });

                        },
                        error:function(result){
                            alert("错误！");
                        }
                    });

                }
                timer=setInterval(mark,3000);
            });
        }

    </script>
</head>
<body onload="init()">
<div id="container"></div>
</body>
</html>