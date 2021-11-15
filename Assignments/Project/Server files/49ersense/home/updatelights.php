<?php
require "database.php";

$houseID=$_POST["houseID"];
$floorID=$_POST["floorID"];
$lightStatus=$_POST["lightStatus"];
$lightID=$_POST["lightID"];
$dimmerLevel=$_POST["dimmerLevel"];

$query="UPDATE lightdetails SET status = $dimmerLevel, access = $lightStatus WHERE houseID ={$houseID} AND floorID = {$floorID} AND lightID = {$lightID}";

if(mysqli_query($conn,$query)){
    echo"Data updated";
}else{
    echo"0";
}


?>
