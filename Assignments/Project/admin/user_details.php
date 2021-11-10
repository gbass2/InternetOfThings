<?php
require"database.php";

// Login Query
$query ="SELECT * FROM users where userid = 'user'";
$result=mysqli_query($conn,$query);

if(mysqli_num_rows($result)>0){
	while($row = mysqli_fetch_assoc($result)){
		$username=$row["username"];;
	    	// $emailid=$row["email"];
	    	// $phone=$row["phone"];
	    	// $address=$row["address"];
	    	// $userid=$row["userid"];

	      echo "{$username}";
		}
}
else{
	echo "false,0,0";
}

?>
