<?php
    header('Content-type:text/html; charset=utf-8');
    include 'dbcon.php';

    $num = $_POST['num']; 

    $sql="delete from record where num=$num";

    mysqli_query($connect, $sql);

    mysqli_close($connect);

?>