<?php
require "database.php";

$houseID=$_POST["houseID"];
$alarmStatus=$_POST["alarm];

$query="UPDATE housestatus SET alarmStatus ='$alarmStatus' WHERE houseID ={$houseID}";

if(mysqli_query($conn,$query)){
    echo"Data updated";
}else{
    echo"0";
}
?>
