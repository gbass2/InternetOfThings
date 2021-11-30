<?php
require"database.php";
$houseID=$_POST["houseID"];

// House status query
$query ="SELECT * FROM housestatus WHERE houseID ='$houseID'";
$result=mysqli_query($conn,$query);
$row = mysqli_fetch_assoc($result);

$houseID1=$row["houseID"];
$securityStatus=$row["securityStatus"];

$query = "SELECT
    GROUP_CONCAT(
        CONCAT_WS(',',
        t1.status)
        ORDER BY
        t1.doorID
        SEPARATOR ',')
        AS 'doors'
    	  FROM
        userdatabase.dooractuator
				AS t1";

$result=mysqli_query($conn,$query);
$locks = "";

if(mysqli_num_rows($result)>0){
    $row=mysqli_fetch_assoc($result);
		$locks = $row["doors"];
}

$query = "SELECT
    GROUP_CONCAT(
        CONCAT_WS(',',
        t1.garageID, t1.doorStatus,t1.lockStatus,t1.doorNo)
        ORDER BY
        t1.doorNo
        SEPARATOR ',')
        AS 'garage'
    	  FROM
        userdatabase.garage
				AS t1";

$result=mysqli_query($conn,$query);
$garage = "";

if(mysqli_num_rows($result)>0){
    $row=mysqli_fetch_assoc($result);
		$garage = $row["garage"];
}

$query = "SELECT
    GROUP_CONCAT(
        CONCAT_WS(',',
        t1.floorID, t1.floorNo)
        ORDER BY
        t1.floorID
        SEPARATOR ',')
        AS 'floors'
    	  FROM
        userdatabase.floors
				AS t1";

$result=mysqli_query($conn,$query);
$floors = "";

if(mysqli_num_rows($result)>0){
    $row=mysqli_fetch_assoc($result);
		$floors = $row["floors"];
}

$query ="SELECT * FROM garage where houseID ='$houseID'";
$result=mysqli_query($conn,$query);
$numGarage = mysqli_num_rows($result);

$query ="SELECT * FROM floors where houseID ='$houseID'";
$result=mysqli_query($conn,$query);
$numFloors = mysqli_num_rows($result);

if(!empty($garage) && !empty($locks)&& !empty($floors)){
		echo "{$houseID},{$securityStatus},{$locks},{$numGarage},{$numFloors},{$garage},{$floors}";
}else{
		echo "0";
}

?>
