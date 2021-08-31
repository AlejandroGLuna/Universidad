<?php
class SesionUsuario{

    public function __construct(){
        session_start();
    }

    public function setCurrentUser($user, $nombre, $opcion){
        $_SESSION['user'] = $user;
        $_SESSION['nombre'] = $nombre;
        $_SESSION['type'] = $opcion;
    }

    public function getCurrentUser(){
        return $_SESSION['user'];
    }

    public function getCurrentUserType(){
        return $_SESSION['type'];
    }

    public function getCurrentUserName(){
        return $_SESSION['nombre'];
    }
    public function closeSession(){
        session_unset();
        session_destroy();
    }
}
?>