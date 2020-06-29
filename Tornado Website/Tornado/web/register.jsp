<%@page import="java.util.*"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Registration form</title>
		<link rel="stylesheet" type="text/css" href="style2c.css">
		<script src="jquery.min.js"></script>
		<script src="smtp.js"></script>
	</head>
	<body>
	<img src="stuff/logo2.png" class="logo">
		<div class="box">

			<h1>Registration</h1>
			<form method="post" action="\insert">
				<label for="t1">User Name</label><br><br>
				<input type="text" class="sub2" name="Full Name" id="t1" placeholder="Enter fullname" autofocus><br>
				<label for="e1">Email</label><br><br>
				<input type="text" name="Email" class="sub2" id="e1" placeholder="Enter Email"><br>
				<input  id="verify" type="button" name="verify" value="verify" style="left:341px;top:242px; height:30px;"/>
				<label for="p1">password</label><br><br>
				<input type="password" name="password" class="sub2" id="p1" placeholder="Enter password"><br>
				<span id="showpass"><img src="stuff/hide.png" width="20px" height="20px" alt="no image" id="myicon" style="top:350px;" /></span>
				<label for="p2">Re-enter</label><br><br>
				<input type="password" name="re-enter" class="sub2" id="p2" placeholder="Re enter password"><br>
				
				<div class="access">
				<label for="acc">Access</label>&nbsp&nbsp&nbsp&nbsp
				<select id="acc">
					<option>Admin</option>
					<option>Authority</option>
					<option selected>Citizen</option>
				</select>
				</div>
				<label class="next sub3" for="n1">Phone number</label><br>
				<input type="number" name="ph number"  class="sub2 sub3" id="n1" placeholder="Enter phone number">
				
				<div class="gender">
				<label id="genhead">Gender</label><br><br>
				<input type="radio" name="gender"   id="gender"><label  id="genmale">Male</label>
				<input type="radio" name="gender"   id="gender" ><label  id="genfemale">Female</label>
				</div>
				<div class="adddr">
				<label for="addr">Address</label><br><br>
				<textarea id="addr" placeholder="Adderess" rows="7" cols="38"></textarea>
				</div>
				<input class="sub" type="reset" name="reset" value="Reset">
				<input class="sub" type="submit" name="submit" value="Register">
			</form>
		</div>
		<script>
			//$(".box").click(function(){
				
				
				$("#p2").blur(function(){
				
					if(($("p1").val()).equals( $("p2").val() )){
						$("#p1").css("border","1px solid darkgreen");
						$("#p2").css("border","1px solid darkgreen");
					}else{
						$("#p2").css("border","1px solid red");
					}
				});
				
                                
                                });
                                
                                
				
				$("#e1").blur(function(){
				var x=validate($("#e1").val());
					if(x){
						$("#e1").css("border","1px solid darkgreen");
					}else{
						$("#e1").css("border","1px solid red");
                                                $("#verify").css("border","1px solid red");
					}
				});
				function validate(email){
				    var regex =  /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
					return regex.test(email);
				}
			
				$("#p1").keydown(function(){
					$("#showpass").css("display","inline");
				});
				$("#showpass").click(function(){
				if($("#myicon").attr('src') == "stuff/show.png"){
					$("#myicon").attr("src","stuff/hide.png");
					$("#p1").attr("type","password");
					
				}else{
					$("#myicon").attr("src","stuff/show.png");
					$("#p1").attr("type","text");					
				}				
				});
				$(".logo").animate({
				left:"300px"
				
				},1000);
			
				$(".box").animate({
				'margin-left':"10px",
				'height':"600px",
				 width:"900px" 
				 
				 },
				 1000,function(){
				
				$(this).css("border-style","outset");
			
				 });
				  $(".sub").animate({
					left:"480px"
				
				},1000);
				$(".sub").animate({
					bottom:"30px"
				},300);
				$(".sub2").animate({
					width:"350px"
				},1300,function(){
				$(".access").css("display","inline");
				}); 
				$(".gender").css("display","inline");
				$(".sub3").css("display","inline");
				$(".adddr").css("display","inline");
				
		//	});
		</script>
	</body>
</html>