<?php
require"database.php";
// $houseID=$_POST["houseID"];
$houseID=1;
// House status query
$query ="SELECT * FROM housestatus where houseID ='$houseID'";
$result=mysqli_query($conn,$query);
$row = mysqli_fetch_assoc($result);

$houseID1=$row["houseID"];
$securityStatus=$row["securityStatus"];


$query ="SELECT * FROM dooractuator where houseid ='$houseID'";
$result=mysqli_query($conn,$query);

$locks = array();
if($result){
		while($row = mysqli_fetch_assoc($result)){
				 array_push($locks,$row['status']);
		}
}


$query ="SELECT * FROM garage where houseID ='$houseID'";
$result=mysqli_query($conn,$query);

$garage = array();
$numGarage = mysqli_num_rows($result);

if($result){
		while($row = mysqli_fetch_assoc($result)){
				array_push($garage,$row['garageID'],$row['doorStatus'],$row['lockStatus'],$row['doorNo']);
		}
}

$query ="SELECT * FROM floors where houseID ='$houseID'";
$result=mysqli_query($conn,$query);

$floors = array();
$numFloors = mysqli_num_rows($result);

if($result){
		while($row = mysqli_fetch_assoc($result)){
				array_push($floors,$row['floorID'],$row['floorNo']);
		}
}


if(!empty($garage) && !empty($locks)&& !empty($floors)){
		echo "{$houseID},{$securityStatus},{$locks[0]},{$locks[1]},{$locks[2]},{$numFloors},{$numGarage},{$garage[0][0]},{$garage[1][0]},{$garage[2][0]},{$garage[3][0]},{$garage[4][0]},{$garage[5][0]},{$garage[6][0]},{$garage[7][0]}";
	} else {
		echo "0";
	}


?>
