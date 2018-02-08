<?php
// $root="http://www.pakistanspeaks.com/AndroidLogin/";

 $root="http://192.168.64.50/AndroidLogin/";
include 'connection.php';

if(isset($_POST['title']) && isset($_POST['description']) && isset($_POST['price'])){


	$title = $_POST['title'];
    $description =$_POST['description'];
    $price = $_POST['price'];
    $image = $_POST['image'];
     $sql ="SELECT id FROM products ORDER BY id ASC";

        $res = mysqli_query($con,$sql);

         $id = uniqid();

        while($row = mysqli_fetch_array($res)){
                $id = $row['id'];
        }

        $path = "images/".uniqid().".jpg";
                

        $actualpath  = "$path";
       
   
    $root.=$actualpath;
    $insert = "insert into products (title,description,price,image_url) values('$title','$description','$price','$root')";


    $run = mysqli_query($con,$insert);
    if($run){
        // if(isset($_FILES['image'])){
        // move_uploaded_file($_FILES['image']['tmp_name'], $path);
         if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){
            file_put_contents($path,base64_decode($image));
         	echo "success";
         	exit;
         }
     // }
         
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

<form action="insert_product1.php" method="post" enctype="multipart/form-data">
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
<input type="file" name="image">

<br>

<input type="submit" name="submit" value="submit">

</form>


	</body>
	</html>