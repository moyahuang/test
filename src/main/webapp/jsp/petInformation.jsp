<%--
  Created by IntelliJ IDEA.
  User: liulongfeng
  Date: 2017/7/27 0027
  Time: 上午 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<head>
    <title>填写宠物信息</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/petInformation.css"/>
</head>
<body>
<form method="post" enctype="multipart/form-data" action="petWalk">
    <div class="photoRow" >
        <div class="photoText">头像</div>
        <div class="photoDiv">
            <input type="file" name="file" id="file" onchange="PreviewImage()" class="uploadBtn">
           <img src="${pageContext.request.contextPath}/images/defaultPhoto.png" class="defaultPhoto" id="defaultPhoto1" >

        </div>
    </div>
    <div class="wholeDiv">
        <div class="textDiv">昵称</div>
        <div class="inputNameDiv">
            <input type="text" name="nickname" value="" placeholder="点击设置宠物名称" class="inputName">
        </div>
    </div>
    <div class="wholeDiv">
        <div class="textDiv">性别</div>
        <div class="radioDiv" >
            <div class="GGDiv">
                <div class="radioStyle">
                    <input class="radio" type="radio" name="petSex" value="male" checked="checked" onclick="toBlue();"/>
                    <img src="${pageContext.request.contextPath}/images/GBlue.png" id="GRadio">
                </div>
                <span style="color:#3399FF " class="GM">GG</span></div>
            <div class="MMDiv">
                <div class="radioStyle">
                    <input class="radio" type="radio" name="petSex" value="female" onclick="toRed();"/>
                    <img src="${pageContext.request.contextPath}/images/MNoRed.png" id="MRadio">
                </div>
                <span style="color: #FF6666" class="GM">MM</span></div>
        </div>
    </div>
    <div class="wholeDiv">
        <div class="textDiv">品种</div>
        <div class="inputNameDiv">
            <select class="inputName" name="dogs">
                <option value="others">其他</option>
                <option value="aLaSiJia">阿拉斯加</option>
                <option value="beiJingQuan">北京犬</option>
                <option value="biXiong">比熊</option>
                <option value="boMei">博美</option>
                <option value="banDianQuan">斑点犬</option>
                <option value="chaBeiQuan">茶杯犬</option>
                <option value="dieErQuan">蝶耳犬</option>
                <option value="douNiuQuan">斗牛犬</option>
                <option value="guiBinQuan">贵宾犬</option>
                <option value="haShiQi">哈士奇</option>
                <option value="huLiQuan">狐狸犬</option>
                <option value="jinMaoQuan">金毛犬</option>
                <option value="keJi">柯基</option>
                <option value="laBuLaDuo">拉布拉多</option>
                <option value="shiZiQuan">狮子犬</option>
                <option value="jiWaWa">吉娃娃</option>
                <option value="qiuTianQuan">秋田犬</option>
                <option value="saMoYe">萨摩耶</option>
                <option value="songShi">松狮</option>
                <option value="muYangQuan">牧羊犬</option>
                <option value="songShuQuan">松鼠犬</option>
                <option value="zhongHuaTianYuanQuan">中华田园犬</option>
                <option value="zangAo">藏獒</option>
            </select>
        </div>
    </div>

    <div class="wholeDiv">
        <div class="textDiv">生日</div>
        <div class="inputNameDiv">
        <input type="date" value="" class="inputName" style=""/>
        </div>
    </div>
    <div  class="saveDiv">

        <input type="submit" name="submit" value="保存" class="saveBtn">
    </div>
</form>

<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script type="text/javascript" charset="utf-8">


    //mui(".frontImg")[0].style.height=window.innerWidth*0.7+"px";

    function PreviewImage()
    {
        //alert("dd");
        //file = imgFile.files[0];
        //url=document.getElementById("file").value;
        //alert(url);
        var f = document.getElementById('file').files[0];
        var url = window.URL.createObjectURL(f);
      //  alert(url);
        document.getElementById("defaultPhoto1").setAttribute("src",url);

    }
    function toBlue() {
        var GG = document.getElementById('GRadio');
        GG.setAttribute('src','${pageContext.request.contextPath}/images/GBlue.png')
        document.getElementById("MRadio").setAttribute('src','${pageContext.request.contextPath}/images/MNoRed.png');
    }
    function toRed() {
        var MM = document.getElementById('MRadio');
        MM.setAttribute('src','${pageContext.request.contextPath}/images/MRed.png')
        document.getElementById("GRadio").setAttribute('src','${pageContext.request.contextPath}/images/GNoBlue.png');
    }

</script>

</body>
</html>
