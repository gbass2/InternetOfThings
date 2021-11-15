<?php
require"database.php";

$username=$_POST["username"];
$password=$_POST["password"];
$emailid=$_POST["emailid"];
$phone=$_POST["phone"];
$address=$_POST["address"];

$pw= password_hash($password,PASSWORD_DEFAULT);
// Query
$query="UPDATE users SET password ='$pw',email ='$emailid',phone ='$phone',address ='$address' WHERE username ='$username'";

if(mysqli_query($conn,$query)){
    echo"Data inserted";
}
else{
    echo"error data not inserted";
}


?>
