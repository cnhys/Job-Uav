<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>印章管理列表</title>

<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
.checkbox {
	display: inline-block;
	padding-left: 20px;
	padding-right: 20px;
	padding-top: 6px
}

.btn {
	display: inline-block;2
	width: 90px;
	height: 32px;
	border: solid 1px #c0d9fe;
	border-radius: 4px;
	margin-right: 20px
}

.btn img {
	margin: -4px 5px 0 -8px;
}

.laber {
	flex: 1;
	line-height: 40px
}

.inputModal {
	flex: 4;
	border: none;
	outline: none;
	background: #f1f2f4;
	border-radius: 4px;
	height: 40px;
	padding-left: 20px
}

.inputBox {
	display: flex;
	justify-content: center;
	margin-bottom: 10px
}
</style>
</head>
<body style="background: #ededed">
	<div class="container">
		<p></p>
		<div style="padding: 20px; background: #ffffff">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th width='10%'>全选</th>
						<th style="display: none">id</th>
						<th width='22.5%'>印章标识</th>
						<th width='22.5%'>印章ID</th>
						<th width='22.5%'>印章名称</th>
						<th width='22.5%'>印章图片</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach items="${sealInfolList }" var="sealInfo" varStatus="status">
						<tr>
							<td>${1 + 1}</td>
							<td style="display: none">${sealInfo.id }</td>
							<td>${sealInfo.sealIdentification }</td>
							<td>${sealInfo.sealId }</td>
							<td>${sealInfo.sealName }</td>
							<td>${sealInfo.sealPicture }</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="5">
							<div data-toggle="modal" data-target="#myModal"
								style="width: 120px; height: 34px; border: solid 1px #c0d9fe; border-radius: 4px; line-height: 34px; text-align: center; margin: 0 auto">添加</div>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- 
          <div>
            <div style="width: 124px; height: 42px; border: solid 1px #c0d9fe;border-radius: 4px;line-height: 42px;text-align: center;margin: 0 auto;background: #3b8cff;color: #ffffff">保存</div>
          </div>
           -->
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="false">
		<div class="modal-dialog" style="width: 500px">
			<div class="modal-content">
				<div class="modal-body" style="padding: 40px">
					<form id="addSeal_form" action="" method="post">
						<div class="inputBox">
							<span class="laber">印章标识:</span> <input id="" name=""
								class="inputModal" type="text" required="true">
						</div>
						<div class="inputBox">
							<span class="laber">印章ID:</span> <input id="" name=""
								class="inputModal" type="text" required="required">
						</div>
						<div class="inputBox">
							<span class="laber">印章名称:</span> <input id="" name=""
								class="inputModal" type="text" required="required">
						</div>
						<div class="inputBox" style="margin: 30px 0">
							<span class="laber"></span>
							<div style="flex: 4; position: relative;">
								<input type="file" value="上传图片"
									style="opacity: 0; position: absolute; top: 5px; left: 5px;" />
								<div
									style="width: 120px; height: 34px; border: solid 1px #c0d9fe; border-radius: 4px; line-height: 34px; text-align: center; color: #3b8cff; cursor: pointer">上传图片</div>
							</div>
						</div>
						<button onclick="" type="button" class="btn btn-default"
							style="margin-left: 80px; background: #3b8cff; width: 260px; height: 40px; border-radius: 4px; color: #ffffff; cursor: pointer"
							data-dismiss="modal">确定</button>
					</form>

				</div>
				<!-- <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">提交更改</button>
            </div> -->
			</div>
		</div>
	</div>
	</div>
	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		
	</script>

</body>
</html>