<?php

$servername= "localhost";
$username="android";
$dbname="userdatabase";
$password="qlS8D3l@r(/Kg@Xz";
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
