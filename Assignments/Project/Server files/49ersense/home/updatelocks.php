<?php
require "database.php";

$houseID=$_POST["houseID"];
$doorID=json_decode($_POST["doorID"]);
$status=json_decode($_POST["lockStatus"]);

for ($x=0; $x <sizeof($doorID); $x++){
    $query="UPDATE dooractuator SET status = $status[$x] WHERE houseID ={$houseID} AND doorID = {$doorID[$x]}";

    if(mysqli_query($conn,$query)){
        echo"Data updated ";
    }else{
        echo"0 ";
    }
}


?>
