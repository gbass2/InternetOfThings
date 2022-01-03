<?php
require "database.php";

$houseID=$_POST["houseID"];
$floorID=$_POST["floorID"];
$controlTemp=$_POST["controlTemp"];

$query="UPDATE thermoschedule SET tcontrol = $controlTemp WHERE houseID ={$houseID} AND floorID = {$floorID}";

if(mysqli_query($conn,$query)){
    echo"Data updated";
}else{
    echo"0";
}


?>
