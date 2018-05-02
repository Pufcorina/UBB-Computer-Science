<?php
/**
 * Created by PhpStorm.
 * User: todor
 * Date: 5/1/2018
 * Time: 12:34 PM
 */

    $connect= new mysqli('localhost', 'root', 'password', 'lab7');

    if($connect->connect_error){
        die('connection failed brush');
    } else
        echo 'connection worked';
?>