<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>附件管理</title>
    <link rel="stylesheet" type="text/css" href="../../skin/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/My97DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/theAlert.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/theAlert.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/jquery-3.4.1.js"></script>
    <script >
        /*查询所有project存到projects中*/
        var projects = {};
        $(function () {
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/project/query",
                datatype:"json",
                success:function (data) {
                    $(data).each(function (index,elem) {
                        var key = elem.pid;
                        var value = elem.pname;
                        projects[key] = value;
                    });
                    /*查询所有attachment*/
                    $.ajax({
                        type:"post",
                        url:"${pageContext.request.contextPath}/attachment/queryAttachment",
                        datatype:"json",
                        success:function (data) {
                            $(data).each(function (index,elem) {
                                var proname = projects[elem.proFk];
                                var tr = "<tr align='center' bgcolor=\"#FFFFFF\" onMouseMove=\"javascript:this.bgColor='#FCFDEE';\" onMouseOut=\"javascript:this.bgColor='#FFFFFF';\" height=\"22\" >\n" +
                                    "\t<td><input type='checkbox' ></td>\n" +
                                    "\t<td>"+elem.id+"</td>\n" +
                                    "\t<td align=\"left\"><a href=''><u>"+elem.attname+"</u></a></td>\n" +
                                    "\t<td>"+proname+"</td>\n" +
                                    "\t<td>"+format(elem.uploadtime)+"</td>\n" +
                                    "\t<td>"+format(elem.updatetime)+"</td>\n" +
                                    "\t<td>"+'编辑'+"</td>\n" +
                                    "</tr>";
                                $("#tr").before(tr);
                            })
                        }
                    })
                }
            })
        });


        function add0(m){return m<10?'0'+m:m }
        function format(shijianchuo) {
            //shijianchuo是整数，否则要parseInt转换
            if (shijianchuo == null){return '/'}
            var time = new Date(shijianchuo);
            var y = time.getFullYear();
            var m = time.getMonth() + 1;
            var d = time.getDate();
            var h = time.getHours();
            var mm = time.getMinutes();
            var s = time.getSeconds();
            return y + '-' + add0(m) + '-' + add0(d) + ' ' ;
        }
        /*全选*/
        function allSelect() {
            $("#form input:checkbox").prop("checked",true)
        }
        /*反选*/
        function oppositeSelect() {
            $("#form input:checkbox").each(function () {
                $(this).prop("checked",!$(this).prop("checked"))
            })
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
    当前位置:项目管理>>附件管理
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/attachment/forward/project-file-add';" value='添加新附件' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form name='form3' action='' method='get'>
<input type='hidden' name='dopost' value='' />
<table width='98%'  border='1' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='cid' style='width:150px'>
          <option value='0'>选择类型...</option>
          	<option value='1'>附件名称</option>
          	<option value='1'>项目名称</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input type='text' name='keyword' value='' style='width:120px' />
        </td>
        <td width='110'>
    		<select name='orderby' style='width:120px'>
            <option value='id'>排序...</option>
            <option value='pubdate'>添加时间</option>
            <option value='pubdate'>修改时间</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input name="imageField" type="image" src="../../skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form name="form2 " id="form">

<table width="98%" border="1" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;附件列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">附件名称</td>
	<td width="10%">所属项目</td>
	<%--<td width="10%">附件个数</td>--%>
	<td width="8%">上传时间</td>
	<td width="8%">修改时间</td>
	<td width="13%">操作</td>
</tr>

<%--<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="101" class="np"></td>
	<td>1</td>
	<td>帐户管理功能分析图</td>
	<td align="center"><a href=''><u>农业银行自助管理系统</u></a></td>
	<td>3</td>
	<td>2015-02-03</td>
	<td>2015-06-03</td>
	<td><a href="#">下载</a> |<a href="#">删除</a> |<a href="project-file-edit.jsp">编辑</a> | <a href="project-file-look.jsp">查看详情</a></td>
</tr>--%>


<tr id="tr" bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="javascript:allSelect()" class="coolbg">全选</a>
	<a href="javascript:oppositeSelect()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  

</body>
</html>