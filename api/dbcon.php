<?php
	$connect=mysqli_connect("localhost","dbtest","alwls0100!","dbtest")or
	die("SQL sever에 연결할 수 없습니다.");

    mysqli_select_db($connect,"dbtest") or die("연결 안됨");
?>