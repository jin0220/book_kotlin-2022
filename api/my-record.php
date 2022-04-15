<?php
    header('Content-Type:application/json; charset=utf-8');
    include 'dbcon.php';

    $id = $_POST['id'];
    $date = $_POST['date'];

    $sql = "select * from record where id='$id' and date='$date'";
    $result=mysqli_query($connect,$sql);
    $num_match = mysqli_num_rows($result); //데이터 레코드의 개수

    $response = array();

    if(!$num_match){ //등록되어 있지 않은 아이디
        // $response['success'] = false;
        // array_push($response,array());
    }
    else{ //등록되어 있는 아이디
        // $row=mysqli_fetch_array($result);
        // $db_pass=$row['password'];
        
        // if($db_pass != $password){ //비밀번호가 틀린 경우
        //     $response[]
        // }
        // $row=mysqli_fetch_array($result);
        // $response['success'] = true;
        // $response['id'] = $row['id'];
        // $response['title'] = $row['title'];
        // $response['image'] = $row['image'];
        // $response['author'] = $row['author'];
        // $response['publisher'] = $row['publisher'];
        // $response['rating'] = $row['rating'];
        // $response['memo'] = $row['memo'];
        // $response['date'] = $row['date'];

        if($result = mysqli_query($connect,$sql, MYSQLI_USE_RESULT)){
            while($row = mysqli_fetch_object($result)){
                array_push($response,$row);
                // $response['success'] = true;
                // $response['id'] = $row['id'];
                // $response['title'] = $row['title'];
                // $response['image'] = $row['image'];
                // $response['author'] = $row['author'];
                // $response['publisher'] = $row['publisher'];
                // $response['rating'] = $row['rating'];
                // $response['memo'] = $row['memo'];
                // $response['date'] = $row['date'];
            }
        }
    }

    echo json_encode($response, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>