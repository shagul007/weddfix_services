<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
var role = '<%=session.getAttribute("role") %>';
if(role == 'null') {
	window.location.href = "login";
}

if(role != 'ADMIN') {
	window.location.href = "home";
}
</script>
<script type="text/javascript">
var jq = jQuery.noConflict();
var renum = /^[a-zA-Z0-9]*$/;
</script>
<script type="text/javascript">
var re = /^[a-zA-Z ]*$/;
jQuery(document).ready(function() {
	jq(function() {		
		jq("#grid").jqGrid(
		{
		   	url : 'load_role_master',
		   	editurl:"edit_role_master",
		   	pageable: true,
		   	loadui:"enable",
			datatype: 'json',
			mtype: 'post',
		   	colNames:['Role Id', 'Role Name','Role Description','Is Active'],
		   	colModel:[
		   		     {name:'id',align:'center', index:'id', width:100,editable:true,hidden:true,editoptions: {readonly:false,hidden:true,size:10}},
                    {name:'roleName',index:'roleName', width:100,editable:true,editrules:{required:true},editoptions:{size:10,maxlength: 30},searchoptions:{sopt: ['cn']}},
                    {name:'roleDesc',index:'roleDesc', width:100,editable:true,editrules:{required:true},editoptions:{size:10,maxlength: 250},searchoptions:{sopt: ['cn']}},
                    {name:'isActive',index:'isActive', align: 'center', width:100,editable:true,edittype:"checkbox",editrules:{required:true},formatter:'checkbox',checked:true,editoptions: { value:"True:False"},searchoptions:{sopt: ['cn']}},
		   	],	   			   			   
		   	rowNum:10,			
		   	rowList:[10,20,30],
		   	height: 300,
		   	width:1200,
	  		autowidth: false,
			rownumbers: true,			
		   	pager: '#pager',
		   	sortname: 'id',
		    viewrecords: true,		    
			sortorder: "asc",
		    caption:"Role Master",
		    emptyrecords: "Empty records",
		    loadonce: true,
		    ignoreCase: true,
		    loadComplete: function(){		    	
		    },
		    jsonReader : {
		        root: "gridModel",
		        page: "page",
		        total: "total",
		        records: "records",
		        repeatitems: false,
		       // cell: "cell"//,
		        //id: "id"
		    }
		    
		});
	
		        	jq("#grid").jqGrid('navGrid','#pager',
		    				{edit:true,add:true,del:true,search:true},
		    				{ closeAfterEdit: true,beforeShowForm: function(form){
// 		    					jQuery('#roleName',form).attr('readonly','readonly');
		    				},afterSubmit: function (response, postdata) {
		                 		window.location.reload(true);
		                	 } },
		    		        {closeAfterAdd:true,beforeShowForm: function(form) {
// 		    					jQuery('#roleName',form).removeAttr('readonly');		                    
		                  	},
		                  	
		                  	
		                  	beforeSubmit:function(postdata,form){	
		                  		    var name=jQuery('#roleName').val();
		        					var mydata = jQuery("#grid").jqGrid('getGridParam','data');
		        					//alert('mydata='+mydata.length);
		        					for(var i=0;i<mydata.length;i++){
		        					if(re.test(name) == false){
		        		            		  return [false,'Role Name should not have numbers or special characters'];
		        					}}
		        					return [true,','];
		                      },closeAfterAdd:true,afterSubmit: function (response, postdata) {
		                   		window.location.reload(true);
		                  	 }},
		        		        { }, 
		        				{ 
			    	sopt:['eq', 'ne', 'lt', 'gt', 'cn', 'bw', 'ew'],
			        closeOnEscape: true, 
			        	multipleSearch: true, 
			         	closeAfterSearch: true }
		);
	});
	
	function addRow() {
	// Get the currently selected row
	
    jq("#grid").jqGrid('editGridRow','new',
    		{ 	url: "", 
			editData: {
			    },
			    recreateForm: true,
			    beforeShowForm: function(form) {
			    },
				closeAfterAdd: true,
				reloadAfterSubmit:true,
					afterSubmit :null					
    		});
}

function editRow() {
		// Get the currently selected row
	var row = jq("#grid").jqGrid('getGridParam','selrow');
	//jq("#grid").
	if( row != null ) 
		jq("#grid").jqGrid('editGridRow',row,
			{	url: "", 
				editData: {
		        },
		        recreateForm: true,
		        beforeShowForm: function(form) {
		        },
				closeAfterEdit: true,
				reloadAfterSubmit:true,
					afterSubmit :null
			});
	else jq( "#dialogSelectRow" ).dialog();
}
	
});
</script>
</head>
<body>
<div class="main-container" style="min-height: 430px;">
  <div class="container">
  <div class="row">
	<s:url var="remoteurl" action="load_role_master" id="actionName" />
		<div id="jqgrid">
			<table id="grid"></table>
			<div id="pager"></div>
		</div>
	</div>
	</div>
	</div>
</body>
</html>