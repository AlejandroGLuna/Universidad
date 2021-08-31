<?php

require 'conexion.php';
echo "XD";
try{
    $conexion = new Conexion();
    $query = "INSERT INTO administrador (nombre, pass) VALUES ('prueba', MD5('prueba'))";

    $stmt = $conexion->db->query($query);
    echo "Si llego";
}catch (Exception $ex) {
    $mensaje = "Error no esperado: " . $ex->getMessage();
}


?>