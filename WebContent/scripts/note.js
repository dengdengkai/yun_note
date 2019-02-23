//生成笔记列表li元素
function createNoteLi(noteId,noteTitle){
	var sli="";
	sli+='<li class="online">';
	sli+='<a>'; //'<a  class="checked">'; 加checked颜色会变深
	sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	sli+=noteTitle;
	sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
	sli+='</a>';
	sli+='<div class="note_menu" tabindex="-1">';
	sli+='<dl>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
	sli+='</dl>';
	sli+='</div>';
	sli+='</li>';
	
	//转换成JQyery对象
	var $li=$(sli);
	$li.data("noteId",noteId);
	//将li追加到ul中
	$("#note_ul").append($li);
	
};

//加载笔记本的笔记列表
function loadBookNotes(){
	//设置选中效果
	$("#book_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	  
	  // alert("绑定成功!");
	 //获取在book.js中设置的bookid
	 //注意等号左右不能有空格
	 var bookId=$(this).data("bookId");

	 //测试取值
	 
	// alert(bookId);
	 $.ajax({
		 url:base_path+"/note/loadnotes.do",
		 type:"post",
		 data:{"bookId":bookId},
		 dataType:"json",
		 success:function(result){
			//获取笔记信息
			var notes=result.data;
			//清除原列表信息
			$("#note_ul").empty();
			//$("#note_ul li").remove();//也可以清除
			//循环添加li
			for(var i=0;i<notes.length;i++){
				//获取笔记id
				var noteId=
					notes[i].cn_note_id;
				//获取笔记标题
				var noteTitle=
					notes[i].cn_note_title;
			//生成笔记
				createNoteLi(noteId,noteTitle);
				
			}
		},
		error:function(){
			alert("笔记加载失败");
		}
		 
	 });
	  
  };
  
  //加载笔记,显示笔记具体信息（显示标题，与内容）
  function loadnote(){
	//设置选中效果
	  $("#note_ul a").removeClass("checked");
		$(this).find("a").addClass("checked");
	  
	  
	  //获取请求参数noteId
	 var noteId=$(this).data("noteId");
	 $("#input_note_title").val("");
		
	 //设置笔记内容
	 um.setContent("");
	  //发送ajax请求
	 // alert(noteId);//测试获取请求参数
	  $.ajax({
		url:base_path+"/note/load.do",
		type:"post",
		data:{"noteId":noteId},
	    dataType:"json",
		success:function(result){
			
			//获取返回的笔记标题
			var title=result.data.cn_note_title;
			//获取返回的笔记内容
			var body=result.data.cn_note_body;
			//设置笔记标题
			 $("#input_note_title").val(title);
			
			 //设置笔记内容
			 um.setContent(body);
			
		},
		error:function(){
						  
			alert("加载笔记失败")	;	  
		  }
	 
	     });
	  
  };
  
  
  
  //保存笔记
  function noteSave(){
	  //alert("点击成功");//测试点击保存的按钮
	  //获取li的原因是，之前将noteId保存在li的data中
	  //所以获取li的对象<li><a class=”checked”></a></li>
	  var $li=$("#note_ul a.checked").parent();//获取被选定的li
	 //获取笔记Id
	  var noteId=$li.data("noteId");
	  //获取笔记的标题和内容
	  var noteTitle=$("#input_note_title").val().trim();
	  var noteBody=um.getContent();
	 // alert(noteId+","+noteTitle+","+noteBody);//测试获取
	  $.ajax({
		  url:base_path+"/note/update.do",
		  type:"post",
		  data:{"noteId":noteId,"title":noteTitle,"body":noteBody},
		  dataType:"json",
		  success:function(result){
			  if(result.status==0){
				  var str="";
				str+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
				str+=noteTitle;
			    str+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
			   //将str替换到li的a元素里
			    $li.find("a").html(str);
			    //提示成功
			    alert(result.msg);
			  }
			 
		  },
		  error:function(){
			  alert("保存笔记失败!");
		  }
	  });
	  
		  
  }
  
  //添加笔记
  function sureAddNote(){
	  
 	 //  alert("绑定生效！"); //测试绑定
 	   //获取请求参数
 	   //获取笔记标题
 	   var title=$("#input_note").val().trim();
 	   //获取用户Id
 	   var userId=getCookie("userId");
 	   //获取笔记本ID
 	   var $li=$("#book_ul a.checked").parent();
 	   var bookId=$li.data("bookId");
 	   
 	//  alert(title+","+userId+","+bookId); //测试获取
      //数据格式检查
      var ok=true;
 	 if (title==""){//判断是否为空
 		 ok=false;
 	     $("#notetitle_span").html("标题不能为空！");

 	 }
 	 if(userId==null){//检查是否失效
 		 ok=false;
 		 alert("登录超时，请重新登录");
 	     window.location.href="log_in.html";
 		 
 	 }
 	 //发送请求
 	 if(ok){
 		 $.ajax({
 			 url:base_path+"/note/add.do",
 		     type:"post",
 		     data:{"userId":userId,"bookId":bookId,"title":title},
 		     dataType:"json",
 		     success:function(result){
 		    	    if(result.status==1){
 		    	    	alert(result.msg);
 		    	    }
 		    	    else if(result.status==0){
 		    	    	var noteId=result.data.cn_note_id;
 		    	    	var noteTitle=result.data.cn_note_title;
 		    	    	//创建列表元素
 		   			   createNoteLi(noteId,noteTitle);
 		    	    	
 		    	    	alert("创建笔记成功");
 		    	    }
 		     },
 		     error:function(){
 		    	 alert("创建笔记失败！");
 		     }
 		 });
 		 
 	 }
    }
  
  //笔记下拉菜单
  function slideButtons() {
	  //测试绑定
	 // alert("绑定成功");
	  //隐藏笔记菜单
	  $("#note_ul div").hide();
	 
	  //显示点击菜单）(this指代（.btn_slide_down）绑定的button)
	  //获取他的父元素而不是子元素的原因是其内部没有元素li,及子元素
	  //所以先获取其父元素再从父元素的子元素中去找需要的元素
	  var note_menu =  
		  $(this).parents("li").find("div");
	  note_menu.slideDown(1000);
	  return false;//组织冒泡事件
		  }
  
  //分享笔记
  function noteShare(){
	  //测试绑定分享按钮
	  //alert("绑定按钮分享成功");
		 //获取请求参数笔记Id
	 var $li=$("#note_ul a.checked").parent();//获取被选定的li
	 //上面的等价于 $("#note_ul").on("click",".btn_share",noteShare)
	 // | $li=$(this).parents("li")  this指代点击的分享按钮
	 

	 
	 var noteId=$li.data("noteId");
	 
	// alert("获得noteId: "+noteId);//测试获得noteId
	$.ajax({
		url:base_path+"/share/add.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
	    success:function(result){
	    	if(result.status==1){
	    		alert(result.msg);
	    	}
	    	else if(result.status==0){
	           //获取已经显示的li(或者通过result获取)
	    		var noteTitle=$li.text();
	    		var sli="";
	            sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	        	sli+=noteTitle;
	        	sli+='&nbsp;&nbsp;&nbsp;<i class="fa fa-sitemap"></i>';
	        	sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
	        	//将笔记元素的<a>标签内容替换掉
	        	$li.find("a").html(sli);
	            
	    		//提示分享成功
	    		alert(result.msg);
	    	}
	    },
	    error:function(){
	    	alert("笔记分享失败！");
	    }
	});
	  
  }
  
  //删除笔记
  function deleteNote(){
	  //由于是数据库设置状态2为删除，显示只显示状态为1的笔记
	 //alert("删除绑定成功");//测试绑定
	 // 获取请求参数
	  var $li=$("#note_ul a.checked").parent();//获取被选定的li
		 //上面的等价于 $("#note_ul").on("click",".btn_share",noteShare)
		 // | $li=$(this).parents("li")  this指代点击的分享按钮
	  var noteId=$li.data("noteId");
		//发送ajax请求
	  $.ajax({
		  url:base_path+"/note/delete.do",
		  type:"post",
		  data:{"noteId":noteId},
		  dataType:"json",
		  success:function(result){
			  if(result.status==1){
				  alert(result.msg);
			  }
			  if(result.status==0){
				 
					  alert(result.msg);
				  
			  }
		  },
		  error:function(){
			  alert("删除笔记失败");
		  }
	  });
  }
  
  //搜素分享笔记

  	
  		
