<?php

//function isNull($nombre, $apellidoP, $apellidoM, $curp, $)

function generaContrasena(){
    $token = substr(MD5(rand()), 0, 7);
    return $token;
}


function obtenerEdad ($fecha_nacimiento) {
    $fecha = new DateTime($fecha_nacimiento);
    $ahora = new DateTime();
    $diferencia = $ahora->diff($fecha);
    return $diferencia->y;
}

?>