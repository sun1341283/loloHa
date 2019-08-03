<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>客户信息管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/jquery-3.4.1.js"></script>
<script>

    function firstPage(path,currentPage){
         if(currentPage!=1){
             //重新发请求
         }
    }
    function get_date(source){

        //把时间戳转换成date
        var date=new Date(source);
        var year=date.getFullYear();
        var month=formatDate(date.getMonth()+1);
        var day=formatDate(date.getDate());
        return year+"-"+month+"-"+day;
    }
    function formatDate(value){
          if(value<10)
          {
              return "0"+value;
          }else{
              return value;
          }
    }
    //当页面加载完成之后触发
    $(function(){

           $.ajax({
               type:"get",
               url:"${pageContext.request.contextPath}/cus/query",
               dataType:"json",
               cache:false,
               success:function(data){
                    $(data.records).each(function(index,elem){
                        var tr="<tr align='center' bgcolor=\"#FFFFFF\" onMouseMove=\"javascript:this.bgColor='#FCFDEE';\" onMouseOut=\"javascript:this.bgColor='#FFFFFF';\" height=\"22\" >\n" +
                            "\t<td><input name='id\' type='checkbox' value="+elem.id+" class=\"np\"></td>\n" +
                            "\t<td>"+(index+1)+"</td>\n" +
                            "\t<td>"+elem.companyperson+"</td>\n" +
                            "\t<td align=\"center\">"+elem.comname+"</td>\n" +
                            "\t<td>"+get_date(elem.addtime)+"</td>\n" +
                            "\t<td>"+elem.comphone+"</td>\n" +
                            "\t<td><a href=\"customer-edit.jsp\">编辑</a> | <a href=\"customer-look.jsp\">查看详情</a></td>\n" +
                            "</tr>";
                        //把tr插入table的指定位置
                        $("#showData").before(tr).val();
                    });
                    //进行分页
                   /* */
                   var last_row="<tr align='right' bgcolor='EEF4EA'>" +
                       "<td height='36' colspan='4' align='left'>总共"+data.totalrecords+"条纪录，当前第"+data.currentPage+"页/总共"+data.totalPage+"页，每页"+data.pageSize+"条纪录</td>" +
                       "<td height='36' colspan='3' align='right'>" +
                       "<a href=\"javascript:firstPage('${pageContext.request.contextPath}/cus/query?currentPage=1','"+data.currentPage+"')\"><img src='/crm/images/first.gif'/></a>&nbsp;&nbsp;&nbsp;" +
                       "<a href='javascript:prePage("+data.currentPage+");'><img src='/crm/images/back.gif'/></a>&nbsp;&nbsp;&nbsp;" +
                       "<a href='javascript:nextPage("+data.currentPage+","+data.totalPage+");'><img src='/crm/images/next.gif'/></a>&nbsp;&nbsp;&nbsp;" +
                       "<a href=\"javascript:lastPage('${pageContext.request.contextPath}/analysis/show?currentPage="+data.totalPage+"')\"><img src='/crm/images/last.gif'/></a>&nbsp;&nbsp;&nbsp;</td></tr>";

                    $("#showData").after(last_row);

               }
           });

    });

    function seacherCustomer(){

        var searchContentType=$("#searchContentType").val();
        var searchContent = $("#searchContent").val();
        var orderby=$("#orderby").val();
        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath}/cus/seacher/query",
            data:{"searchContentType":searchContentType,"searchContent":searchContent,"orderby":orderby},
            dataType:"json",
            cache:false,
            success:function(data){
                //清空数据
                //$("#content tr:not(:lt(2)):not(:eq(-1)) ").remove();
                $("#content tr:not(:lt(2)):not([id=showData]) ").remove();
                $(data).each(function(index,elem){
                    var tr="<tr align='center' bgcolor=\"#FFFFFF\" onMouseMove=\"javascript:this.bgColor='#FCFDEE';\" onMouseOut=\"javascript:this.bgColor='#FFFFFF';\" height=\"22\" >\n" +
                        "\t<td><input name='id' type='checkbox' value="+elem.id+" class=\"np\"></td>\n" +
                        "\t<td>"+elem.id+"</td>\n" +
                        "\t<td>"+elem.companyperson+"</td>\n" +
                        "\t<td align=\"center\">"+elem.comname+"</td>\n" +
                        "\t<td>"+get_date(elem.addtime)+"</td>\n" +
                        "\t<td>"+elem.comphone+"</td>\n" +
                        "\t<td><a href=\"customer-edit.jsp\">编辑</a> | <a href=\"customer-look.jsp\">查看详情</a></td>\n" +
                        "</tr>";
                    //把tr插入table的指定位置
                    $("#showData").before(tr);
                });
            }
        });
    }
    function deleteCustomer() {
        var selected ="";
        $("input[name=id]:checked").each(function () {
            selected+=$(this).val()+",";
        });
        alert(selected);
        $.post("${pageContext.request.contextPath}/cus/delete",{id:$(selected)},function (data) {
            alert(data.msg)
        })

    }

</script>
</head>
<body leftmargin="8" topmargin="8" background='${pageContext.request.contextPath}/skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="${pageContext.request.contextPath}/skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:客户信息管理>>客户信息
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/cus/forward/customer-add';" value='添加客户信息' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form name='form3' action='' method='get'>
<input type='hidden' name='dopost' value='' />
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='${pageContext.request.contextPath}/skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select id="searchContentType" name='searchContentType' style='width:150px'>
          <option value='0'>选择类型...</option>
          	<option value='1'>客户所在公司名称</option>
          	<option value='2'>联系人姓名</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input type='text' name='searchContent' id="searchContent" value='' style='width:120px' />
        </td>
        <td width='110'>
    		<select name='orderby' id="orderby" style='width:120px'>
            <option value='0'>排序...</option>
            <option value='1'>添加时间</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;
            <a href="javascript:seacherCustomer();"><img src="${pageContext.request.contextPath}/skin/images/frame/search.gif" width="45" height="20" border="0" class="np"></a>
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form name="form2">

<table id="content" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="7" background="${pageContext.request.contextPath}/skin/images/tbg.gif">&nbsp;需求列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">联系人</td>
	<td width="10%">公司名称</td>
	<td width="8%">添加时间</td>
	<td width="8%">联系电话</td>
	<td width="10%">操作</td>
</tr>
<!--
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="101" class="np"></td>
	<td>1</td>
	<td>李彦宏</td>
	<td align="center">百度</td>
	<td>2015-02-03</td>
	<td>13257634888</td>
	<td><a href="customer-edit.jsp">编辑</a> | <a href="customer-look.jsp">查看详情</a></td>
</tr>
-->


<tr bgcolor="#FAFAF1" id="showData">
<td height="28" colspan="12">
	&nbsp;
	<a href="" class="coolbg">全选</a>
	<a href="" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a onclick="deleteCustomer()" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>

</table>

</form>
  

</body>
</html>