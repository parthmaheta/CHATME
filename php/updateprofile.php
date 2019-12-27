<?php include("db.php");

$uid=mysqli_real_escape_string($con,$_POST['ID']);
$name=mysqli_real_escape_string($con,$_POST['NAME']);
$status=mysqli_real_escape_string($con,$_POST['STATUS']);

$data = base64_decode($_POST['file']);

//genrate unique name
$file=round(microtime(true)).".jpeg";
file_put_contents('./img/'.$file, $data);
	
mysqli_query($con,"update user_details set NAME='".$name."',PICTURE='".$file."',STATUS='".$status."' where ID=$uid");

mysqli_close($con);

//send new file name
$object = (object) ['PICTURE' => $file];
echo json_encode($object); 


?>
