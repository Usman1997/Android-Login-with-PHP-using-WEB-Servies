<!doctype html>
<html>
<head>
	<title>
		Register
	</title>
	</head>
<body>
<form action="register.php" method="post">
Email<br>
<input type="email" name="email">
<br>
Password<br>
<input type="password" name="password">

<br>
<input type="submit" name="submit" value="submit">

</form>

</body>

</html>


<?php
include 'connection.php';


if(isset($_POST['email']) && isset($_POST['password'])){

	$email = $_POST['email'];
	$password = $_POST['password'];

	
		$insert = "insert into androidlogin.login(email,password)values('$email','$password')";
		$run = mysqli_query($con,$insert);
		if($run){
			if(isset($_POST['mobile']) && $_POST['mobile']=="android"){
				echo "success";
				exit;
			}
			
         echo "<script>alert('Registered Successfully')</script>";
     }
     else{
echo "<script>alert('Registered UnSuccessfully')</script>";
     }
}


?>