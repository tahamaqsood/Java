<?php

    // Define method of request
    // On true. Create function to show users and give access to the database
if($_SERVER["REQUEST_METHOD"]=='POST'){
    include 'db_connect.php';
    showUsers();
}

    // Create function to show/ take users in an array
function showUsers(){
    
    // Get database connection from the server
    global $conn;

    // Select all data from the database
    $query = "Select * from users";
    $result = mysqli_query($conn, $query);
    $number_of_rows = mysqli_num_rows($result);

    // Create temporary array
    $temp_array = array();

    // if statement to check rows are not empty
    if($number_of_rows > 0){

        // On true. Run while loop to insert data in an array
        while($row = mysqli_fetch_assoc($result)){

            // Use of "[]" as short array
            // temp_array = Temporary array
            $temp_array[] = $row;

        }

    }

    // Define header type - Important because the request will be sending through the server.
    header('Content-Type: application/json');
    // print data we have in temporary array
    echo json_encode(array("users"=>$temp_array));
}

?>