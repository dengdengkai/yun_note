function registUser(){
		   //测试注册点击事件绑定
		   //alert("点击注册按钮有效");
		   //获取参数
		   var name=$("#regist_username").val().trim();
		   var nick=$("#nickname").val().trim();
		   var password=$("#regist_password").val().trim();
		   var final_password=$("#final_password").val().trim();
		   //检查数据格式
		  // alert("信息："+name+","+nick+","+password+","+final_password);
		  
		     $("#warning_0 span").html("");  
			 $("#warning_0").show();  
			 $("#warning_1 span").html("");  
			 $("#warning_1").show();  
			 $("#warning_2 span").html("");  
			 $("#warning_2").show();  
			 $("#warning_3 span").html("");  
			 $("#warning_3").show(); 
			 $("#warning_4 span").html("");  
			 $("#warning_4").show(); 
		   
		   
		   
		   
		   //检查用户
		   var ok=true;//校验数据合法性
		   if(name==""){
			   //为div中内部标签赋值 id标签+span
			   $("#warning_1 span").html("用户不能为空");  
			   $("#warning_1").show();  
			   var ok=false;
		   }
		   //检测昵称
		  
		   if(nick==""){
			   //为div中内部标签赋值 id标签+span
			   $("#warning_0 span").html("昵称不能为空");
			   $("#warning_0").show();  
			   var ok=false;
			   
			    
		   }
		   //检测密码 1-非空2-不能小于6位
		   if(password==""){
			   $("#warning_2 span").html("密码不能为空");
			   $("#warning_2").show();  
			   var ok=false;
		   }
		   else if(password.length<6){
			   $("#warning_2 span").html("密码不能少于6位");
			   $("#warning_2").show();  
			   var ok=false;
		   }
		   //检测确认密码1-非空2-是否与密码一致
		   if(password != final_password){
			   $("#warning_3 span").html("密码你不一致呀");
			   $("#warning_3").show();  
			   var ok=false;
		   }
		   if(ok){//数据校验通过后，开始发送ajax请求
			   $.ajax({
				   
				   url:base_path+"/user/add.do",
				   type:"post",
				   data:{"name":name,"password":password,"nick":nick},
				   dataType:"json",
				   success:function(result){ 
					   //注册成功
					   if(result.status==0)
						  {
						   alert(result.msg);
						   //返回到登录页面
						   $("#back").click();
						 }
					  
					     else if(result.status==1){
						   //用户名被占用
						   //alert(result.msg);
						   $("#warning_1 span").html(result.msg);
						   $("#warning_1").show();
					   }
				   },
				   error:function(){
					   alert("注册失败！");
				   }
			   });
		   }
		   
	   }