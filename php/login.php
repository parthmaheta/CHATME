<?php  include("db.php");
	$user =mysqli_real_escape_string($con,$_POST["l_user"]);
	$pass =mysqli_real_escape_String($con,$_POST["l_pass"]);
	$response = array();
	

	if($con){
		$sel = mysqli_query($con,"select * from user_details where EMAIL='$user' and PASSWORD='$pass'");
		if($rel = mysqli_fetch_array($sel)){
			array_push($response,array("login"=>"Login Success","ID"=>$rel["ID"],"NAME"=>$rel["NAME"],"EMAIL"=>$rel["EMAIL"],"PASSWORD"=>$rel["PASSWORD"],"PICTURE"=>$rel["PICTURE"],"STATUS"=>$rel["STATUS"]));
			echo json_encode($response);
		}
		else{
			array_push($response,array("login"=>"Login Fail"));
			echo json_encode($response);
		}
	}
?>
