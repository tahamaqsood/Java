<?php
include 'db_connect.php';

$heroes = array(); 
 
//this is our sql query 
$sql = "SELECT id, Places_From, Places_From_ID, Places_To, Places_To_ID, Busses FROM places_and_busses;";
 
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
 'Place_from_ID'=>$Place_from_ID,
 'Place_To'=>$Place_To,
 'Place_To_ID'=>$Place_To_ID,
 'Busses'=>$Busses

 ];
 
 //pushing the array inside the hero array 
 array_push($heroes, $temp);
}
 
//displaying the data in json format 
echo json_encode($heroes);