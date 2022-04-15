<?php
	header('Content-type:text/html; charset=utf-8');
    include 'dbcon.php';

	$num = $_POST['num'];
    $title = $_POST['title'];
	$image = $_POST['image'];
	$author = $_POST['author'];
	$publisher = $_POST['publisher'];
    $rating = $_POST['rating'];
    $memo = $_POST['memo'];
	$date = $_POST['date'];
	
    $sql="update record set ";

    $sql.="title='$title', image='$image', author='$author', publisher='$publisher', rating='$rating', memo='$memo', date='$date'";

    $sql.="where num=$num";

	mysqli_query($connect,$sql);

	mysqli_close($connect);

    $response = array();
    $response['success'] = true;

    echo json_encode($response, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>