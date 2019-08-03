<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>添加需求分析信息</title>
<link rel="stylesheet" type="text/css" href="../../skin/css/base.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/theAlert.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/theAlert.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/jquery-3.4.1.js"></script>
	<script>
		/*查询所有project*/
		$(function () {
			$.ajax({
				type: "post",
				url: "${pageContext.request.contextPath}/project/query",
				datatype: "json",
				success: function (data) {
					$(data).each(function (index, elem) {
						var option = "<option pid='"+elem.pid+"' value='" + elem.pname + "'>" + elem.pname + "</option>"
						$("#selectProject").append(option);
					})
				}
			})

		})

		function saveAnalysis() {
			$.ajax({
				type: "post",
				url: $("#form").attr("action"),
				data: $("#form").serialize(),
				datatype: "json",
				success:function(data){
					alert(data.msg);
					if (data.status == 200) {
						theAlert(data.msg, "alert", function () {
							window.location = "${pageContext.request.contextPath}/analysis/forward/project-need"
						})
					} else {
						theAlert(data.msg, "alert")
					}
				}
			})
		}
		function giveID() {
			$("#id").val($("#selectProject :selected").attr("pid"))
		}
	</script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:项目管理>>添加需求分析基本信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form" action="${pageContext.request.contextPath}/analysis/saveAnalysis" id="form">

<table id="table" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="${pageContext.request.contextPath}/skin/images/tbg.gif">&nbsp;添加新需求&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">选择项目：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><select id="selectProject" name="proname" onchange="giveID()"><option value=1>请选择</option></select></td>
</tr>
<tr ><td><input type="hidden" name="id" id="id"></td></tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">标题：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="title"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">简单描述：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><textarea rows=10 cols=130 name="simpledis"></textarea></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">详细描述：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><textarea rows=15 cols=130 name="detaileddis"></textarea></td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=10 cols=130 name="remark"></textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:saveAnalysis()" class="coolbg">保存</a>
	<a href="project-need.jsp" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>