<?php  include("db.php");
$id=mysqli_real_escape_string($con,$_POST['ID']);
$fid=mysqli_real_escape_string($con,$_POST['FID']);

//getting total new message
$sql="select count(MESSAGE) as row from chat where STATUS=0 and _FROM=".$fid." and _TO=".$id."";
$result=mysqli_query($con,$sql);

$row=mysqli_fetch_assoc($result);

 echo json_encode($row);

?>
