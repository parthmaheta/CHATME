<?php  include("db.php");

    $id  =mysqli_real_escape_string($con,$_POST["ID"]);
    $fid =mysqli_real_escape_string($con,$_POST["FID"]);
    

	
    $sql="select MESSAGE from chat where _FROM=$fid and _TO=$id and STATUS=0";     	
    $result=mysqli_query($con,$sql);          
    
    //mark as read
    $sql="update chat set STATUS=1 where _FROM=$fid and _TO=$id"; 	
    mysqli_query($con,$sql);	      		 
     
     
     $string = array();
    while($row=mysqli_fetch_assoc($result))
	$string[]=$row;
    echo json_encode($string);    
    

    mysqli_close($con);
?>
