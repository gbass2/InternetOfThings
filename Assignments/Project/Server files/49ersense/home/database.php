<?php

$servername= "localhost";
$username="android2";
$dbname="userdatabase";
$password="nv(YHoGF45dFz!@-";
// Create connection
$conn = mysqli_connect($servername,$username,$password,$dbname);
// check connection
if(!$conn){
    die("Connection Failed!".mysqli_connect_error());
}
else{
    //echo"Connection Successful ";
}


?>
