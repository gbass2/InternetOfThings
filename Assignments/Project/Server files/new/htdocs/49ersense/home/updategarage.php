<?php
require "database.php";

$houseID=$_POST["houseID"];
$garageID=$_POST["garageID"];
$garageStatus=$_POST["garageStatus"];

$query="UPDATE garage SET doorStatus ='$garageStatus' WHERE houseID ={$houseID} AND garageID = {$garageID}";

if(mysqli_query($conn,$query)){
    echo"Data updated";
}else{
    echo"0";
}


?>
