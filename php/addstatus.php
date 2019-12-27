<?php include("db.php");

$uid=$_POST['ID'];

$data = base64_decode($_POST['file']);


$file=round(microtime(true)).".jpeg";
mysqli_query($con,"insert into statuses(UID,FILEPATH) value(".$uid.",'".$file."')");
mysqli_close($con); 
file_put_contents('./img/'.$file, $data);

?>
