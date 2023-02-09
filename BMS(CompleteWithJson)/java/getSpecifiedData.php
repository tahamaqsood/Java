<?php
include 'db_connect.php';

//Catch data from the url in alphebet manners.
$From_Id_Req = $_GET['From']??"";
$To_Id_Req = $_GET['To']??"";

//Replacing alphabetic town to numbers value
$queryForFrom = "SELECT * FROM places_and_busses WHERE Places_From = '$From_Id_Req'";
$resultForFrom = mysqli_query($conn,$queryForFrom);
$dataForFrom = mysqli_fetch_assoc($resultForFrom);
$ID_Places_From = $dataForFrom['Places_From_ID'];

//Replacing alphabetic town to numbers value
$queryForTo = "SELECT * FROM places_and_busses WHERE Places_To = '$To_Id_Req'";
$resultForTo = mysqli_query($conn,$queryForTo);
$dataForTo = mysqli_fetch_assoc($resultForTo);
$ID_Places_To = $dataForTo['Places_To_ID'];

// Creating array for the data
$heroes = array(); 
 
//Logic to find if the From value is greater than the To logic
if($ID_Places_From > $ID_Places_To){
    $sql = "SELECT * FROM `places_and_busses` WHERE Places_From_ID = $ID_Places_From OR Places_To_ID = $ID_Places_To ORDER BY Places_To_ID DESC";
}else{
    $sql = "SELECT * FROM `places_and_busses` WHERE Places_From_ID = $ID_Places_From OR Places_To_ID = $ID_Places_To ORDER BY Places_To_ID ASC";   
}
 
//creating an statment with the query
$stmt = $conn->prepare($sql);
 
//executing that statment
$stmt->execute();
 
//binding results for that statment 
$stmt->bind_result($id, $Place_From, $Place_from_ID, $Place_To, $Place_To_ID, $Busses);
 
//looping through all the records
while($stmt->fetch()){
 
 //pushing fetched data in an array 
 $temp = [
    'id'=>$id,
    'Place_From'=>$Place_From,
    'Place_To'=>$Place_To,
    'Busses'=>$Busses
 ];
 
 //pushing the array inside the hero array 
 array_push($heroes, $temp);
}
 
//displaying the data in json format 
echo json_encode($heroes);