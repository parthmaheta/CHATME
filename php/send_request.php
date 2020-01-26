<?php  include("db.php");
$uid=mysqli_real_escape_string($con,$_POST['UID']);
$fid=mysqli_real_escape_string($con,$_POST['FID']);
      
      if($uid!=$fid)
       {
        $sql="insert into request values(".$uid.",".$fid.")";
	mysqli_query($con,$sql);
       }			
        mysqli_close($con);

?>
