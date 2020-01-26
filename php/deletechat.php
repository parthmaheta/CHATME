<?php  include("db.php");
$uid=mysqli_real_escape_string($con,$_POST['ID']);
$fid=mysqli_real_escape_string($con,$_POST['FID']);
      
	$sql="delete from chat where _FROM=".$uid." and _TO=".$fid."";
	mysqli_query($con,$sql);
	
	$sql="delete from chat where _FROM=".$fid." and _TO=".$uid."";
	mysqli_query($con,$sql);
  			
        mysqli_close($con);

?>
