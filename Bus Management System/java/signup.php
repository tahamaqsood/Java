<?php 


if($_SERVER["REQUEST_METHOD"]=='POST'){
    include 'db_connect.php';

    createuser();
}

function createuser(){

    global $conn;

    $name = $_POST['username'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $confirm_password = $_POST['confirm_password'];

    $query = "INSERT INTO `users`(`username`, `email`, `password`, `confirm_password`) VALUES ('$name','$email','$password','$confirm_password')";

    $exec = mysqli_query($conn, $query);

     if($exec == true ){
    echo "ok";
    }else{
        echo "error";
    }

}


?>