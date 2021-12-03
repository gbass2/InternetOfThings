<?php
require"database.php";

$name=$_POST["name"];
$username=$_POST["username"];
$password=$_POST["password"];
$emailid=$_POST["emailid"];
$phone=$_POST["phone"];
$address=$_POST["address"];
$userid=$_POST["userid"];

$pw= password_hash($password,PASSWORD_DEFAULT);

$query="INSERT INTO users(name,username,password,email,phone,address,userid) VALUES('$name','$username','$pw','$emailid',$phone,$address,'$userid')";
mysqli_query($conn,$query);

// if(mysqli_query($conn,$query)){
//    echo"Data inserted";
// }
// else{
//     echo"error data not inserted";
// }

// Adding the user to the other tables
$query="SELECT * FROM users where username ='$username'";
$result=mysqli_query($conn,$query);
if(mysqli_num_rows($result)>0){
    $row = mysqli_fetch_assoc($result);
    $houseID = $row["houseID"];
    // Adding entry to housestatus
    $query="INSERT INTO housestatus(houseID, securityStatus) VALUES($houseID,'Unarmed')";
    mysqli_query($conn,$query);
    
    // Adding entries to floors
    $query="INSERT INTO floors(houseID,floorID,floorNo) VALUES($houseID,1,1)";
    mysqli_query($conn,$query);
    $query="INSERT INTO floors(houseID,floorID,floorNo) VALUES($houseID,2,1)";
    mysqli_query($conn,$query);
    
    // Adding entries to dooractuator
    $query="INSERT INTO dooractuator(doorID,houseID,floorID,status) VALUES(1,$houseID,1,0)";
    mysqli_query($conn,$query);
    $query="INSERT INTO dooractuator(doorID,houseID,floorID,status) VALUES(2,$houseID,1,0)";
    mysqli_query($conn,$query);
    $query="INSERT INTO dooractuator(doorID,houseID,floorID,status) VALUES(3,$houseID,1,0)";
    mysqli_query($conn,$query);
    
    // Adding entries to garage
    $query="INSERT INTO garage(garageID,doorStatus,lockStatus,doorNo,houseID) VALUES(1,'Closed',0,1,$houseID)";
    mysqli_query($conn,$query);
    $query="INSERT INTO garage(garageID,doorStatus,lockStatus,doorNo,houseID) VALUES(2,'Closed',0,2,$houseID)";
    mysqli_query($conn,$query);
    
    // Adding entries to lightdetails
    $query="INSERT INTO lightdetails(lightID,houseID,floorID,access,status) VALUES(1,$houseID,1,0,1)";
    mysqli_query($conn,$query);
    $query="INSERT INTO lightdetails(lightID,houseID,floorID,access,status) VALUES(2,$houseID,1,0,1)";
    mysqli_query($conn,$query);
    $query="INSERT INTO lightdetails(lightID,houseID,floorID,access,status) VALUES(3,$houseID,2,0,1)";
    mysqli_query($conn,$query);
    $query="INSERT INTO lightdetails(lightID,houseID,floorID,access,status) VALUES(4,$houseID,2,0,1)";
    mysqli_query($conn,$query);
    
    // Adding entries to thermoschedule
    $query="INSERT INTO thermoschedule(houseID,floorID,tmodetf,tfan,tcurrent,tcontrol,startTime,endTime,status) VALUES($houseID,1,'Off','Off',68,68,0,0,0)";
    mysqli_query($conn,$query);
    $query="INSERT INTO thermoschedule(houseID,floorID,tmodetf,tfan,tcurrent,tcontrol,startTime,endTime,status) VALUES($houseID,2,'Off','Off',68,68,0,0,0)";
    mysqli_query($conn,$query);
    
}

?>
