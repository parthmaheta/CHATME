<?php  include("db.php");
$id=mysqli_real_escape_string($con,$_POST['ID']);


//get all friends
$sql="select FID as f from friend where UID=".$id." union select UID as f from friend where FID=".$id."";

$result=mysqli_query($con,$sql);
$f_list=array();
$status_list=array();
//if no status found
if(mysqli_num_rows($result)==0)
  {
   $obj=(object)["ID"=>"0"];
   echo json_encode($obj);
  }
  
  else{
  	  while($row=mysqli_fetch_array($result))
	    array_push($f_list,$row[0]);
   
   //getting path for status of last 24 hour 
   $sql="select user_details.NAME,statuses.FILEPATH from statuses INNER JOIN user_details on statuses.UID=user_details.ID where statuses.UID in(".implode(',',$f_list).") and statuses.DATE_TIME > DATE_SUB(now(), INTERVAL 24 hour)";
           $result=mysqli_query($con,$sql);
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
		         		
		   //while($row=mysqli_fetch_array($result))
  	  	    //array_push($status_list,$row[0]);  
  	  	    //print_r($status_list);	  	    		 
		 }
   }
 
 mysqli_close($con);
