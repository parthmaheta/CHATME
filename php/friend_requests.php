<?php  include("db.php");
$id=mysqli_real_escape_string($con,$_POST['ID']);




$sql="select ID,NAME,PICTURE,STATUS,LAST_SEEN from user_details where ID IN(select UID from request where FID=".$id.")";

$result=mysqli_query($con,$sql);

			//if no status found
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
  
  
  ?>
