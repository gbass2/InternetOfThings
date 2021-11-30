<?php
require"database.php";

$name=$_POST["name"];
$username=$_POST["username"];
$password=$_POST["password"];
$emailid=$_POST["emailid"];
$phone=$_POST["phone"];
$address=$_POST["address"];
$userid=$_POST["userid"];

$pw= password_hash($password,PASSWORD_DEFAULT);
// Query
$query="INSERT INTO users(name,username,password,email,phone,address,userid) VALUES('$name','$username','$pw','$emailid',$phone,$address,'$userid')";

if(mysqli_query($conn,$query)){
    echo"Data inserted";
}
else{
    echo"error data not inserted";
}


?>
