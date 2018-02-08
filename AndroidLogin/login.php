<?php
include 'connection.php';

 if(isset($_POST['email']) && isset($_POST['password'])){

	$email = $_POST['email'];
	$password = $_POST['password'];

	

		$check = "select * from login where email = '$email' AND password = '$password'";
		$run = mysqli_query($con,$check);
		if(mysqli_num_rows($run)>0){
			if(isset($_POST['mobile']) && $_POST['mobile']=="android"){
				echo "success";
				exit;
			}
			 echo "<script>alert('Login Successful')</script>";
		}
		else{
			echo "<script>alert('Login Failed')</script>";
		}
}


?>



<!doctype html>
<html>
<head>
	<title>
		Login
	</title>
	</head>
<body>
<form action="<?php $_PHP_SELF ?>" method="post">
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


