<?php

$servername= "localhost";
$username="android";
$dbname="userdatabase";
$password="fzAbgzqqHruD4V5H";
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
