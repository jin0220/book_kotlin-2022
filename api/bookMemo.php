<?php
    header('Content-type:text/html; charset=utf-8');
    include 'dbcon.php';

    $id = $_POST['id'];
    $bookTitle = $_POST['title'];
    $bookImage = $_POST['image'];
    $bookAuthor = $_POST['author'];
    $bookPublisher = $_POST['publisher'];
    $rating = $_POST['rating'];
    $usermemo = $_POST['memo'];
    $date = date("Y-m-d");

    $sql = "insert into record (id, title, image, author, publisher, rating, memo, date) ";     
    $sql.= "values('$id','$bookTitle','$bookImage','$bookAuthor','$bookPublisher','$rating','$usermemo','$date')"; 

    mysqli_query($connect, $sql);

    mysqli_close($connect);

    $response = array();
    

    if($id == '' || $bookTitle == '' || $bookImage == '' || $bookAuthor == ''|| 
    $bookPublisher == '' || $rating == '' || $usermemo == ''){
        $response['success'] = false;
    }
    else{
        // $response['success'] = true;
        $response['id'] = $id;
        $response['title'] = $bookTitle;
        $response['image'] = $bookImage;
        $response['author'] = $bookAuthor;
        $response['publisher'] = $bookPublisher;
        $response['date'] = $date;
        $response['memo'] = $usermemo;
        $response['rating'] = $rating;
    }

    

    echo json_encode($response, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);

?>