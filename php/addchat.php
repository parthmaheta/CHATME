<?php include("db.php");

$from=$_POST['ID'];
$to=$_POST['FID'];
$message=$_POST['MSG'];

mysqli_query($con,"insert into chat(_FROM,_TO,MESSAGE) value(".$from.",".$to.",'".$message."')");

mysqli_close($con); 

?>
