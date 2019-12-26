<?php    include("db.php");
	$mail = mysqli_real_escape_string($con,$_POST["mail"]);
	$pass = mysqli_real_escape_String($con,$_POST["pass"]);

	if($con){
		mysqli_query($con,"update user_details set PASSWORD='$pass' where EMAIL='$mail'");

	}
?>
