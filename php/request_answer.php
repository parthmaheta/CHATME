<?php  include("db.php");
$uid=mysqli_real_escape_string($con,$_POST['UID']);
$fid=mysqli_real_escape_string($con,$_POST['FID']);
$answer=$_POST['ANSWER'];//y for accept and n for reject




         //if accepts request add to friend table
	if($answer=="y"){
		$sql="insert into friend values(".$fid.",".$uid.")";
		mysqli_query($con,$sql);
	}
	
        //remove from request table  
	$sql="delete from request where UID=".$fid." and FID=".$uid."";
	mysqli_query($con,$sql);

			
   mysqli_close($con);

  ?>
