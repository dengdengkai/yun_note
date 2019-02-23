//根据用户id显示笔记本列表
function loadUserBooks(){
	
	//获取用户的id
	var userId=getCookie("userId"); 
	//判断获取到的userId是否有效
	if(userId==null){
		//无效,返回登录页面
		window.location.href("log_in.html");
	}else{
		
	//通过第二步后，发送ajax请求
     $.ajax({
		  url:base_path+"/book/loadBooks.do",
		  type:"post",
		  data:{"userId":userId},
		  dataType:"json",
		  success:function(result){
		
              //判断查询是否成功   
			  if(result.status==0){
                	 //获取笔记本集合
				    var books=result.data;
				   
				    for(var i=0;i<books.length;i++){
				    	//获取笔记本Id
				    	var bookId=
				    		books[i].cn_notebook_id;
				        //获取笔记本名称
				    	var bookName=
				    		books[i].cn_notebook_name;
				    	//创建一个笔记本列表的li元素
				    	createBookLi(bookId,bookName);
				    }
				 
                 }

		  },
		  error:function(){
			 alert("笔记本加载失败");
		   }
		
	    });
	}

}

//创建笔记本列表元素
function createBookLi(bookId,bookName){
	var sli="";
	sli+='<li class="online">';
	sli+='<a>'  ;    // <a  class='checked'> checked表示选中状态
	sli+='<i  class="fa fa-book" title="online" rel="tooltip-bottom">';
	sli+='</i>';
	sli+=bookName;
	sli+='</a></li>';
	//将sli字符串转换成JQuery对象li元素
	var $li=$(sli);
	//将bookId值与JQuery对象绑定
	$li.data("bookId",bookId);
	//使用Jquery的append方法追加
	//将li元素添加到笔记本ul列表区
	$("#book_ul").append($li);
	
}
function sureAddBook(){
	   
	   //获取新建笔记本名字
	   var bookName=$("#input_notebook").val().trim();
	   //获取userId
		var userId=getCookie("userId"); 
	  
	  
	// alert(bookName+","+userId+"time");
	   //数据格式检查
       var ok=true;
  	 if(bookName==""){//判断是否为空
  		 ok=false;
  	     $("#notebook_span").html("笔记本名不能为空！");
  	 
  	 }
  	 if(userId==null){//检查是否失效
  		 ok=false;
  		 alert("登录超时，请重新登录");
  	     window.location.href="log_in.html";
  		
  	 }
  	 if(ok){

		   $.ajax({
			   url:base_path+"/book/add.do",
			   type:"post",
			   data:{"userId":userId,"bookName":bookName},
			   dataType:"json",
			   success:function(result){
				   
				   if(result.status==0){
				
					   var bookId=result.data.cn_notebook_id;
					   createBookLi(bookId,bookName);
					   alert(result.msg);
					   //关闭新建框后弹出测试信息
	            	 
				   }
			   },
			   error:function(){
				   alert("创建笔记本失败");
			   }
		   });
	   }
	   
	   
}



