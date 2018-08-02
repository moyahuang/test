<%--
  Created by IntelliJ IDEA.
  User: songyangguang
  Date: 2017/7/26
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <title>轨迹回放</title>
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
         $().ready(function(){
            $.ajax({
                type: "GET",
                url: "${pageContext.request.contextPath}/location/getHistoryLocations",
                data:{lid:'123'},
                async:false,
                success:function(result){
                    var array = [];
                    //画出地图
                    qq.maps.convertor.translate(new qq.maps.LatLng(result[0].lat,result[0].lon),1,function(res) {
                        var latlng = res[0];

                        var map = new qq.maps.Map(document.getElementById('container'), {
                            center: latlng,
                            zoom: 16
                        });

                        //画出历史轨迹
                        function line() {
                           /* var path = [];
                            for (var j = 0; j<result.length; j++){
                                console.log("lon="+ array[j]);
                                path.push(array[j]);
                            }*/

                            var polyline = new qq.maps.Polyline({
                                path:array,
                                strokeColor:'#FF6666',
                                strokeWeight: 5,
                                editable: false,
                                map: map
                            });
                            //polyline.setStrokeDashStyle("dash");

                        }

                        //将GPS坐标转化为腾讯支持坐标，并存储到数组内
                        for (var i = 0; i<result.length; i++) {
                            qq.maps.convertor.translate(new qq.maps.LatLng(result[i].lat,result[i].lon),1,function(res){
                                array.push(res[0]);
                                //console.log("lat="+result[i].lat+" lon"+result[i].lon);
                                //console.log("res["+i+"]="+res[0]);
                                if(array[result.length-1]){
                                    line();
                                }
                            });
                        }
                    });
                },
                error:function(result){
                    console.log("错误")
                }
            });
         });
    </script>

</head>
<body>
<div id="container"></div>
</body>
</html>
