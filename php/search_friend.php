<?php  include("db.php");
$name=mysqli_real_escape_string($con,$_POST['NAME']);

$sql="select ID,NAME,PICTURE from user_details where NAME LIKE '".$name."%' or EMAIL LIKE '".$name."%'";

$result=mysqli_query($con,$sql);

			//if no friend found
			if(mysqli_num_rows($result)==0)
			  {
			   $obj=(object)["ID"=>"0"];
			   echo json_encode($obj);
			  }
			  
	     	     else{
	     	          $string = array();
  			  while($row=mysqli_fetch_assoc($result))
				$string[]=$row;
			   echo json_encode($string);	          		
	  		}
	  		mysqli_close($con);
  
  
  ?>
