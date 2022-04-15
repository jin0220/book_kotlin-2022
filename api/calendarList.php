<?php
    header('Content-Type:application/json; charset=utf-8');
    include 'dbcon.php';

    $id = $_POST['id'];
    $date = $_POST['date'];

    $sql = "select distinct date from record where id='$id' and date like'$date%'";
    $result=mysqli_query($connect,$sql);
    $num_match = mysqli_num_rows($result); //데이터 레코드의 개수

    $response = array();

    if(!$num_match){

    }
    else{
        if($result = mysqli_query($connect,$sql, MYSQLI_USE_RESULT)){
            while($row = mysqli_fetch_object($result)){
                array_push($response,$row);
            }
        }
    }

    echo json_encode($response, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>