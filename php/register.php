<?php  include("db.php");
	$username =mysqli_real_escape_string($con,$_POST["user_name"]);
	$useremail =mysqli_real_escape_string($con,$_POST["user_email"]);
	$userpass = mysqli_real_escape_string($con,$_POST["user_password"]);
	

	if($con){			
mysqli_query($con,"insert into user_details(NAME,EMAIL,PASSWORD) values('".$username."','".$useremail."','".$userpass."')");
	//get id of inserted row	
	$result=mysqli_query($con,"select ID from user_details where EMAIL='".$useremail."'");
	$row=mysqli_fetch_assoc($result);
        echo json_encode($row);		
	}
?>
