<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.scu.cn/mytag" prefix="mt"%>
<!DOCTYPE html >

<head>

    <meta name="viewport" content="width=device-width" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0,
     \minimum-scale=1.0, maximum-scale=1.0">
    <%--<link type="text/css" href="${pageContext.request.contextPath}/css/jquery.mobile-1.4.5.css" rel="stylesheet"/>--%>
   <%-- <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/petWalk.css"/>
</head>

<body >


<%--<h2>${user.username}</h2>--%>
<%--<c:forEach items="${user}"  var="user" varStatus="t" step="1">
   <p >【${user.username}】${user.id}</p>
</c:forEach>--%>
<img  class="frontImg" src="${pageContext.request.contextPath}/images/frontView.png" >

<div class="functionZone" >
    <div class="dogFunction" id="petLocationDiv">
         <img class="dogFunctionImg" src="${pageContext.request.contextPath}/images/petLocation.png" >

        <div >宠物定位</div>
    </div>
    <div class="dogFunction"  id="historyTraceDiv">
        <img   class="dogFunctionImg" src="${pageContext.request.contextPath}/images/petTrack.png" >
        <div >宠物轨迹</div>
    </div>
    <div class="dogFunction">
       <img  class="dogFunctionImg" src="${pageContext.request.contextPath}/images/healthManager.png" >
        <div >健康管理</div>
    </div>

</div>

<div id="mytag" style="display: none">
    <mt:mytag>/user/showUser</mt:mytag>
</div>

<div class="spaceDiv"> &nbsp;</div>



<div style="width: 100%;border-bottom:1px solid #ddd;" class="addPetDiv">
    <div style="width: 100%;font-size:20px;"> &nbsp;有宠物宝宝状态</div>
    <div class="petDiv">
        <div class="petSubDiv" style="float:left;width:30%;">
            <img class="petHead"  src="${pageContext.request.contextPath}/images/petHead.jpg">
        </div>
        <div class="petSubDiv" style="float:left;width:40%;">
            <div class="spaceDivInPetSubDiv1" > &nbsp; </div>
            <div style="font-size: 25px;">
                小宝
            </div>
            <div >
                未绑定
            </div>

        </div >

        <div class="petSubDiv" style="float:left;width:20%;" id="bindPage">
                <div class="spaceDivInPetSubDiv2" > &nbsp; </div>
                <scan style="float:left;">
                    立即绑定
                </scan>
                <img class="enterIcon" style="float:left;" src="${pageContext.request.contextPath}/images/enterIcon.png " >
        </div>
    </div>
</div>


<div style="width: 100%;text-align: center;" id="addPetDiv">
    <div style="height:10px;">
        &nbsp;
    </div>
    <div style="font-size:25px;" >
        增加宠物
    </div>

    <img class="addPetIcon"  src="${pageContext.request.contextPath}/images/addPet.png">
    <div style="height:20px;" 
    >
        &nbsp;
    </div>
</div>

    <%--</div>--%>




<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script type="text/javascript" charset="utf-8">

    //mui(".frontImg")[0].style.height=window.innerWidth*0.7+"px";
    $(".frontImg")[0].style.height=window.innerWidth*0.7+"px";
    $(".functionZone")[0].style.height=window.innerWidth*0.2+"px";
    $(".spaceDiv")[0].style.height=window.innerWidth*0.05+"px";

    $(".addPetDiv")[0].style.height=window.innerWidth*0.45+"px";
    $(".petHead")[0].style.width=window.innerWidth*0.3+"px";
    $(".petHead")[0].style.height=window.innerWidth*0.3+"px";
    $(".petHead")[0].style.borderRadius=window.innerWidth*0.15+"px";
    $(".spaceDivInPetSubDiv1")[0].style.height=window.innerWidth*0.2*0.3+"px";
    $(".spaceDivInPetSubDiv2")[0].style.height=window.innerWidth*0.2*0.5+"px";

    //$(".frontImg")[0].style.height=window.screen.availHeight+"px";
    //$(".frontImg")[0].style.height=document.body.scrollHeight;



    document.querySelector("#petLocationDiv").onclick = function(){
        //alert(window.localStorage.categoryId);
        //alert(window.localStorage.openId);
        //如果不在当前页面才返回
        //if(currentPageFlag!=2)
        var url = document.querySelector("#mytag").innerText;
        window.location.href = url;
    }

    document.querySelector("#historyTraceDiv").onclick = function(){
        //alert(window.localStorage.categoryId);
        //alert(window.localStorage.openId);
        //如果不在当前页面才返回
        //if(currentPageFlag!=2)
        window.location.href="user/historyTrace";
    }
//绑定
    document.querySelector("#bindPage").onclick = function(){
        window.location.href="bindDevice";
    }
    //增加宠物
    document.querySelector("#addPetDiv").onclick = function(){
        //alert(window.localStorage.categoryId);
        //alert(window.localStorage.openId);
        //如果不在当前页面才返回
        //if(currentPageFlag!=2)
        window.location.href="petInformation";
    }


</script>

</body>



</html>
