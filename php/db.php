<?php
$servername = "localhost";
$username = "root";
$password = "";
$database ="chat_me";

// Create connection
$con = mysqli_connect($servername, $username, $password);

mysqli_select_db($con,$database);
?>
