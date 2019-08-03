<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>创建任务</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/theAlert.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/theAlert.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/jquery-3.4.1.js"></script>

<script>
        ///project/show/employee/projects
        $(function(){
			//查询项目 根据登陆的用户查  自己是项目的经理
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/project/show/employee/projects",
                dataType:"json",
                cache:false,
                success:function(data){
                    $(data).each(function(index,elem){
                        var option="<option pid='"+elem.pid+"' value='"+elem.pname+"'>"+elem.pname+"</option>";
                        $("#proname").append(option);
                    });
                }
            });
            //查询执行者
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/emp/Programmer/emps",
				data:{"postionName":"开发工程师"},
                dataType:"json",
                cache:false,
                success:function(data){
                    $(data).each(function(index,elem){
                        var option="<option  value='"+elem.eid+"'>"+elem.ename+"-"+elem.position.name+"</option>";
                        $("#empFk2").append(option);
                    });
                }
            });



        });
        function getAnalysisName(){
            var pid=$("#proname option:selected").attr("pid");
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/analysis/proId/query",
                data:{"id":pid},
                dataType:"json",
                cache:false,
                success:function(data){
                    $("#analysisname").val(data.title);
                    $.ajax({
                        type:"post",
                        url:"${pageContext.request.contextPath}/module/analysis/modules",
                        data:{"analysisId":pid},
                        dataType:"json",
                        cache:false,
                        success:function(data){
                            //清理模块下框中的值
                            $("#modeleFk option:not(:eq(0))").remove();
                            $(data).each(function(index,elem){
                                var option="<option  value='"+elem.id+"'>"+elem.modname+"</option>";
                                $("#modeleFk").append(option);
                            });

                        }
                    });
                }
            });
        }

        function getFunction(){
            var moduelId=$("#modeleFk").val();
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/function/task/functions",
                data:{"mid":moduelId},
                dataType:"json",
                cache:false,
                success:function(data){
                    $("#funFk option:not(:eq(0))").remove();
                    if(data.length==0){
                        theAlert("该模块下没有功能或者功能都已分配完毕！","alert");
                    }else{
                        $(data).each(function(index,elem){
                            var option="<option  value='"+elem.id+"'>"+elem.functionname+"</option>"
                            $("#funFk").append(option);
                        });
                    }

                }
            });
        }

        function saveTask(){
            $.ajax({
                type:"post",
                url:$("#taskForm").attr("action"),
                data:$("#taskForm").serialize(),
                dataType:"json",
                cache:false,
                success:function(data){
                    if(data.status==200){
                        theAlert(data.msg,"alert",function(){
                            //window.location="${pageContext.request.contextPath}/task/forward/task";
                        });
                    }else if(data.status=400){
                        theAlert(data.msg,"alert");
                    }
                }
            });
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
    当前位置:任务管理>>创建任务
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form id="taskForm" action="${pageContext.request.contextPath}/task/add" method="post">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="${pageContext.request.contextPath}/skin/images/tbg.gif">&nbsp;创建任务&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">参考位置：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
        <select  id="proname" onchange="getAnalysisName();">
            <option>请选择---</option>
        </select>-

        <input type="text" id="analysisname">-

        <select id="modeleFk" onchange="getFunction();">
            <option>请选择---</option>
        </select>-

        <select name="funFk" id="funFk">
            <option>请选择---</option>
        </select>
    </td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">任务标题：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="tasktitle"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">开始时间：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="starttime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">结束时间：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="endtime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">执行者：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="empFk2" id="empFk2">
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">优先级：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="level">
			<option value="高">高</option>
			<option value="中">中</option>
			<option value="低">低</option>
			<option value="暂缓">暂缓</option>
		</select>
	</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >详细说明：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=10 cols=130 name="remark"></textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:saveTask();" class="coolbg">保存</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>