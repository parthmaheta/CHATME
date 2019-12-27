<?php  include("db.php");
$id=mysqli_real_escape_string($con,$_POST['ID']);


//get all friends
$sql="select FID as f from friend where UID=".$id." union select UID as f from friend where FID=".$id."";

$result=mysqli_query($con,$sql);
$f_list=array();


//if no friend found
if(mysqli_num_rows($result)==0)
  {
   $obj=(object)["ID"=>"0"];
   echo json_encode($obj);
  }
  
  else{
  	  while($row=mysqli_fetch_array($result))
	    array_push($f_list,$row[0]);
   
   //getting details of friend 
   $sql="select ID,NAME,PICTURE,STATUS,LAST_SEEN from user_details where ID in(".implode(',',$f_list).")";
   $result=mysqli_query($con,$sql);
                      		
			  $string = array();
  			  while($row=mysqli_fetch_assoc($result))
				$string[]=$row;
			   echo json_encode($string);	      		
	  	    		 
		 
   }
 
 mysqli_close($con);
