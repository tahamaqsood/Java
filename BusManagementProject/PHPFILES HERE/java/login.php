<?php 


if($_SERVER["REQUEST_METHOD"]=='POST'){
    include 'db_connect.php';

    loginuser();
}

function loginuser(){

    global $conn;

    $name = $_POST['username'];
    $password = $_POST['password'];

    $query = "select * from users where username='$name' and password='$password'";
    $exec = mysqli_query($conn, $query);
    $result = mysqli_fetch_array($exec);

    if($result >0 ){
    echo "ok";
    }

}


?>