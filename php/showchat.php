<?php  include("db.php");
    $id  =mysqli_real_escape_string($con,$_POST["ID"]);
    $fid =mysqli_real_escape_string($con,$_POST["FID"]);
    
    //mark as read
    $sql="update chat set STATUS=1 where _FROM=$id and _TO=$fid or _FROM=$fid and _TO=$id"; 	
    $result=mysqli_query($con,$sql);
	
    $sql="select MESSAGE,_FROM  from chat where _FROM=$id and _TO=$fid or _FROM=$fid and _TO=$id";     	
    $result=mysqli_query($con,$sql);          
    
     $string = array();
    while($row=mysqli_fetch_assoc($result))
	$string[]=$row;
    echo json_encode($string);	      		

    mysqli_close($con);
?>
