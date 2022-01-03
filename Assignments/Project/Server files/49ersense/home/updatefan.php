<?php
require "database.php";

$houseID=$_POST["houseID"];
$floorID=$_POST["floorID"];
$fan=$_POST["fan"];

$query="UPDATE thermoschedule SET tfan = '$fan' WHERE houseID ={$houseID} AND floorID = {$floorID}";

if(mysqli_query($conn,$query)){
    echo"Data updated";
}else{
    echo"0";
}


?>
