<?php

include 'connection.php';

if(isset($_POST['title']) && isset($_POST['description']) && isset($_POST['price']) && 
	isset($_POST['image_url'])){


	$title = $_POST['title'];
    $description =$_POST['description'];
    $price = $_POST['price'];
    $image_url = $_POST['image_url'];

    $insert = "insert into products (title,description,price,image_url) values('$title','$description','$price','$image_url')";


    $run = mysqli_query($con,$insert);
    if($run){
         if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){
         	echo "success";
         	exit;
         }
         echo "Successfully Inserted";

    }
    else{
    	echo "Try Again";
    }
}

?>


<!doctype html>
<html>
<head>
</head>
<body>

<form action="insert_product.php" method="post">
Title<br>
<input type="text" name="title">
<br>
Description<br>
<input type="text" name="description">

<br>


Price<br>
<input type="text" name="price">

<br>
Image
<input type="text" name="image_url">

<br>

<input type="submit" name="submit" value="submit">

</form>


	</body>
	</html>