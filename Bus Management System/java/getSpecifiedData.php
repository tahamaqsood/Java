<?php
include 'db_connect.php';


//Catch data from the url in alphebet manners.
$From_Id_Req = $_GET['From']??"";
$To_Id_Req = $_GET['To']??"";

$queryForFrom = "SELECT * FROM places_and_busses WHERE Places_From = '$From_Id_Req'";
$resultForFrom = mysqli_query($conn,$queryForFrom);
$dataForFrom = mysqli_fetch_assoc($resultForFrom);
$ID_Places_From = $dataForFrom['Places_From_ID'];

$queryForTo = "SELECT * FROM places_and_busses WHERE Places_To = '$To_Id_Req'";
$resultForTo = mysqli_query($conn,$queryForTo);
$dataForTo = mysqli_fetch_assoc($resultForTo);
$ID_Places_To = $dataForTo['Places_To_ID'];


$heroes = array(); 
 
//this is our sql query 
$sql = "SELECT * FROM places_and_busses WHERE Places_From_ID BETWEEN $ID_Places_From AND $ID_Places_To";
 
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

 'Place_From'=>$Place_From,
 'Place_To'=>$Place_To,
 'Busses'=>$Busses

 ];
 
 //pushing the array inside the hero array 
 array_push($heroes, $temp);
}
 
//displaying the data in json format 
echo json_encode($heroes);