<?php  include("db.php");
$uid=mysqli_real_escape_string($con,$_POST['UID']);
$fid=mysqli_real_escape_string($con,$_POST['FID']);
      
	$sql="delete from friend where UID=".$uid." and FID=".$fid."";
	mysqli_query($con,$sql);
	
	$sql="delete from friend where UID=".$fid." and FID=".$uid."";
	mysqli_query($con,$sql);
  			
        mysqli_close($con);

?>
