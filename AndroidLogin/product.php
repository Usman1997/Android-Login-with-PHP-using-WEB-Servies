<?php

include 'connection.php';


$query = "select * from androidlogin.products";
$result = mysqli_query($con,$query);
while($row=mysqli_fetch_assoc($result)){
	$data[]=$row;
}
echo json_encode($data);

?>