[@override baseView="common/admin_base.html" active="${remark}管理"] [@override
overrideName="title"]${remark}管理 [/@override] [@override overrideName="links" ]
[/@override] [@override overrideName="breadcrumb-active"] [/@override]
[@override overrideName="scripts"] [/@override][@override
overrideName="action"] [/@override] [@override overrideName="main"]
<div class="main-content-inner">
	<div class="breadcrumbs ace-save-state" id="breadcrumbs"
		style="border-bottom: 1px dotted #e2e2e2; background-color: #fff;">
		<ul class="breadcrumb">
			<li><i class="ace-icon fa fa-home home-icon"></i> <a
				href="[@s.url '/admin/index.html'/]">管理系统</a></li>
			<li><i class="ace-icon"></i> <a
				href="[@s.url '/admin/${tableName}/list.html'/]">${remark}管理</a></li>
		</ul>

		<div class="pull-right" style="margin-right: 8px;">
			<div class="btn-group">
				<a class="btn btn-info btn-sm " href="#">操作</a>
				<button data-toggle="dropdown"
					class="btn btn-info btn-sm  dropdown-toggle">
					<span class="ace-icon fa fa-caret-down icon-only"></span>
				</button>
				<ul class="dropdown-menu dropdown-menu-right">
					<li><a id="add" href="javascript:;"
						data-href="[@s.url '/admin/${tableName}/save.jhtml?format=json'/]"
						data-title="增加${remark}" class="confirm_e menu-icon fa fa-tag">增加</a>
						<div class="none">
							<#list columnModels as columnModel>
							<#if columnModel.property != 'id'>
							<input type="text" name="${columnModel.property}" valid-rules="required"
								valid-label="${columnModel.remark}" placeholder="${columnModel.remark}" style="width: 100%;" />
							</#if>
							</#list>
						</div></li>
					<li><a id="addNewPage"
						href="[@s.url '/admin/${tableName}/save.html'/]" class=""> <i
							class="menu-icon fa fa-tag"></i> 增加数据
						</a>
						</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="page-content row">
		<input type="hidden" id="pageSize" value="${r"${page.pageSize}"}" /> <input
			type="hidden" id="pageNo" value="${r"${page.pageNo}"}" />
		<form action="[@s.url '/admin/${tableName}/list.html'/]" method="post"
			class="form-search">
			<div class="page-header"
				style="padding-top: 2px; padding-bottom: 10px;">
				<div class="row">
					<#list columnModels as columnModel>
					<#if columnModel.property != 'id'>
					<input type="text" style="width: 200px;" name="${columnModel.property}"
					value="${r"${form."}${columnModel.property}${r"!}"}" class="col-sm-1 margin-left10"
					placeholder="${columnModel.remark}">
					</#if>
					</#list>
					<button type="submit" class="btn btn-purple btn-sm pull-right">
						<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>查询
					</button>
				</div>
			</div>
		</form>
		<!-- /.page-header -->
		<div class="row ">
			<div class="col-xs-12">
				<table id="sample-table-1"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<#list columnModels as columnModel>
							<#if columnModel.property != 'id'>
							<th>${columnModel.remark}</th>
							</#if>
							</#list>
							<th class="center">操作</th>
						</tr>
					</thead>
					<tbody>
						[#list page.results as item ]
						<tr>
							<#list columnModels as columnModel>
							<#if columnModel.property != 'id'>
							<td><#if columnModel.typeName == 'DATETIME'>${r"${(item."}${columnModel.property}?string('yyyy-MM-dd HH:mm:ss'))!}<#else>${r"${(item."}${columnModel.property}${r")!}"}</#if></td>
							</#if>
							</#list>
							<td class="center">
								<div
									class="btn-group">
									<a href="javascript:;"
										data-href="[@s.url '/admin/${tableName}/delete.jhtml?id=${r"${item.id}"}&format=json'/]"
										class="btn btn-xs btn-info confirm_f" data-title="确定要删除？"> 删除
									</a>
									
									<a href="javascript:;"
										data-href="[@s.url '/admin/${tableName}/edit.jhtml?id=${r"${item.id}"}&format=json'/]"
										class="btn btn-xs btn-info confirm_e"> 修改
										<div class="none">
											<#list columnModels as columnModel>
											<#if columnModel.property != 'id'>
											<input type="text" value="<#if columnModel.typeName == 'DATETIME'>${r"${(item."}${columnModel.property}?string('yyyy-MM-dd HH:mm:ss'))!}<#else>${r"${(item."}${columnModel.property}${r")!}"}</#if>" name="${columnModel.property}"
												required="required" placeholder="${columnModel.remark}" style="width: 100%;" />
											</#if>
											</#list>
										</div>
									</a>
									<a href="[@s.url '/admin/${tableName}/edit.html?id=${r"${item.id}"}'/]"
										class="btn btn-xs btn-info"> 修改数据
									</a>
								</div></td>
						</tr>
						[/#list] [#if page.totalCount ==0 ]
						<tr>
							<td colspan="1000" class="center">暂无记录</td>
						</tr>
						[/#if]
					</tbody>
				</table>
				[@c.pageView/]
			</div>
		</div>
	</div>
</div>
[/@override] [/@override]
