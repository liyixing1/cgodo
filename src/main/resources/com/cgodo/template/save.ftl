[@override baseView="common/admin_base.html" active="${remark}管理"] [@override
overrideName="title"]添加${remark} [/@override] [@override overrideName="links" ]
[/@override] [@override overrideName="breadcrumb-active"] [/@override]
[@override overrideName="scripts"] [/@override][@override
overrideName="action"] [/@override] [@override overrideName="main"]
<div class="main-content-inner">
	<div class="breadcrumbs ace-save-state" id="breadcrumbs"
		style="border-bottom: 1px dotted #e2e2e2; background-color: #fff;">
		<ul class="breadcrumb">
			<li><i class="ace-icon fa fa-home home-icon"></i> <a
				href="[@s.url '/admin/index.html'/]">后台管理系统</a></li>
			<li><i class="ace-icon"></i> <a
				href="[@s.url '/admin/${tableName}/list.html'/]">${remark}管理</a></li>
			<li><i class="ace-icon"></i> <a
				href="[@s.url '/admin/${tableName}/save.html'/]">添加${remark}</a></li>
		</ul>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<form class="form-horizontal form-validate"
				action="[@s.url '/admin/${tableName}/save.jhtml?format=json'/]"
				role="form" novalidate="novalidate" method="post"
				enctype="multipart/form-data">
				<input name="url" type="hidden"
					value="[@s.url '/admin/${tableName}/list.html'/]" />
					<#list columnModels as columnModel>
					<#if columnModel.property != 'id'>
					<div class="form-group ">
						<label class="col-sm-3 control-label no-padding-right ">
							${columnModel.remark}</label>
						<div class="col-sm-9 ">
							<input name="${columnModel.property}" type="text" placeholder="${columnModel.remark}"
								class=" col-xs-10 col-sm-5 " value="<#if columnModel.typeName == 'DATETIME'>${r"${(form."}${columnModel.property}?string('yyyy-MM-dd HH:mm:ss'))!}<#else>${r"${(form."}${columnModel.property}${r")!}"}</#if>"/>
						</div>
					</div>
					</#if>
					</#list>
				<div class="clearfix ">
					<div class="col-md-offset-3 col-md-9">
						<button class="btn btn-info" type='submit'>
							<i class="ace-icon fa fa-check bigger-110"></i> 确认
						</button>
					</div>
				</div>
			</form>
		</div>
		<!-- /.col -->
	</div>
</div>
<script type="text/javascript">
</script>
[/@override][@override overrideName="javascript"]
	<script src="[@s.url '/res/rms/js/${tableName}.js'/]"></script>
	[/@override] [/@override]
