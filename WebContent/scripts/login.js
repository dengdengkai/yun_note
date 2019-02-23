function userLogin(){
		   //测试绑定事件
			//alert("success");	  
		   
		   //获取参数 trim处理前后空格
		   var name=$("#count").val().trim();
		   var password=$("#password").val().trim();
		    //测试获取
		   //  alert(name+","+password);
			$("#count_span").html("");
			$("#password_span").html("");
		    var ok=true;
		  //格式检测
		  if(name==""){
			$("#count_span").html("用户名不能为空");
			ok=false;
		      }
		  if(password==""){
				$("#password_span").html("密码不能为空");
				ok=false;
			      }
		  
		 //检测格式通过
		  if(ok==true){
			 $.ajax({    //发送ajax请求
				 url:base_path+"/user/login.do",
				 type:"post",
				 data:{"name":name,"password":password}, //传递给服务器的参数
				 dataType:"json",   //指定返回的数据格式
				 success:function(result){ //回调处理返回的结果
					//result接收服务器返回的json结果包(含status，msg,data（user）
				 
			     if(result.status==0){
				 //status=0表示登陆成功，
				  //之后将用户信息保存到cookie
				    var userId = result.data.cn_user_id;
				    addCookie("userId",userId); //键值对及过期时间设置
				    window.location.href = "edit.html"; //登录成功转发到主界面	
				    }
			     else if(result.status==1){//用户名错误
			    	 $("#count_span").html(result.msg);
			     }
			     else if(result.status==2){//密码错误
			    	 $("#password_span").html(result.msg);
			     }
				  
				 },
				  
				 error:function(){
					 
				    alert("登录失败！你得到从服务器传过来的信息："+obj2);
				 }
			 }); 
		  }
		  }