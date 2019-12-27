<?php include("db.php");
$id=mysqli_real_escape_string($con,$_POST['ID']);

//get last 24 hour statuses
$result=mysqli_query($con,"select FILEPATH from statuses where UID=".$id." and DATE_TIME > DATE_SUB(now(), INTERVAL 24 hour)");
		if(mysqli_num_rows($result)>0)
		  {
			  $string = array();
  			  while($row=mysqli_fetch_assoc($result))
				$string[]=$row;
			   echo json_encode($string);	
  	    	} 
	
	mysqli_close($con);		 

?>
