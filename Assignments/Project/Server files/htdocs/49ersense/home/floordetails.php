<?php
require "database.php";
//
$houseID=$_POST["houseID"];
$floorID=$_POST["floorID"];
$floorNO=$_POST["floorNO"];

$query = "SELECT
    t1.houseID,
    t1.tmodetf,
    t1.tfan,
    t1.tcurrent,
    t1.tcontrol,
    t1.status,
    GROUP_CONCAT(
        CONCAT_WS(',',
        t2.lightID, t2.access, t2.status)
        ORDER BY
        t2.lightID
        SEPARATOR ',')
        AS 'lights'
    	  FROM
        userdatabase.thermoschedule
    		AS t1
    		INNER JOIN userdatabase.lightdetails
    		AS t2
    		ON t1.floorID = t2.floorID
      	WHERE t1.floorID = {$floorID}
      	GROUP BY
        		t1.houseID,
        		t1.tmodetf,
        		t1.tfan,
        		t1.tcurrent,
        		t1.tcontrol,
        		t1.status;";

$result=mysqli_query($conn,$query);

if(mysqli_num_rows($result)>0){
    $row=mysqli_fetch_assoc($result);
    $mode = $row["tmodetf"];
    $fan = $row["tfan"];
    $tCurrent = $row["tcurrent"];
    $tControl = $row["tcontrol"];
    $tStatus = $row["status"];
    $lights = $row["lights"];

    echo "{$houseID},{$floorID},{$floorNO},{$mode},{$fan},{$tCurrent},{$tControl},{$tStatus},{$lights}";
}else{
    echo "0";
}

?>
