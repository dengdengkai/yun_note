//按回车，加载搜索结果的第一页
function sureSearchShare(event){
            	   var code=event.keyCode;
            	   if(code==13){//回车键按下
            		   $("#search_ul li").remove();
            		   //获取请求参数
            		   var keyword=$("#search_note").val().trim();
            		 //发送ajax请求，加载列表
            		    page=1;//默认搜索开始为1
            			loadPageShare(keyword,page);
            	   }
               };
 //按更多按钮，加载显示查询结果的下一页
function moreSearchShare(){
	//获取参数
	var keyword=$("#search_note").val().trim();
	page=page+1;
	//发送ajax请求，加载列表
	loadPageShare(keyword,page);
	
};

function loadPageShare(keyword,page){
	   //发送ajax请求
	   $.ajax({
		   url:base_path+"/share/search.do",
		   type:"post",
		   data:{"keyword":keyword,"page":page},
		   dataType:"json",
		   success:function(result){
			   //获取数据
			   var shares=result.data;
			   for(var i=0;i<shares.length;i++){
				   //记录shareId
				   var shareId=shares[i].cn_share_id;
				   //获取shareTitle
				   var shareTitle=shares[i].cn_share_title;
				   //获取Li对象
				   var sli="";
				   sli+='<li class="online">';
					sli+='<a>'; 
					sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
					sli+=shareTitle;
					sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
					sli+='</a>';
					sli+='</li>';
					var $li=$(sli);
				   //绑定shareId
				   $li.data("shareId",shareId);
				   //将li对象添加到ul当中
				   $("#search_ul").append($li);
				   //切换显示区
				   $("#pc_part_2").hide();//隐藏
				   $("#pc_part_6").show();//显示
			   }
		   },
		   error:function(){
			   alert("索索笔记失败");
		   }
	   });
};