<?php
require"database.php";

$houseID=$_POST["houseID"];
$securityStatus=$_POST["security"];

$query="UPDATE housestatus SET securityStatus ='$securityStatus' WHERE houseID =$houseID";

if(mysqli_query($conn,$query)){
    echo"Data updated";
}else{
    echo"0";
}


?>
