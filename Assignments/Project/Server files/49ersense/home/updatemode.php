<?php
require "database.php";

$houseID=$_POST["houseID"];
$floorID=$_POST["floorID"];
$mode=$_POST["mode"];

$query="UPDATE thermoschedule SET tmodetf = '$mode' WHERE houseID ={$houseID} AND floorID = {$floorID}";

if(mysqli_query($conn,$query)){
    echo"Data updated";
}else{
    echo"0";
}


?>
