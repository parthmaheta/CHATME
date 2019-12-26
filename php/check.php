<?php   include("db.php");
	$email = mysqli_real_escape_string($con,$_POST["check_mail"]);
	$response = array();

	
	if($con){
		$sel = mysqli_query($con,"select ID from user_details where EMAIL='".$email."'");
		if($rel = mysqli_fetch_array($sel)){
			array_push($response,array("msg"=>"Register User"));
			echo json_encode($response);
		}
		else{
			array_push($response,array("msg"=>"UnRegister User"));
			echo json_encode($response);
		}
	}
	mysqli_close($con);
?>
