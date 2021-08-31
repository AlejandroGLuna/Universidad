<?php

include 'ajustes/validacion.php';
include 'ajustes/sesion_usuario.php';

$userSession = new SesionUsuario();
$user = new User();
$errorLogin = "";

if(isset($_SESSION['user'])){
    $tipoUsuario = $userSession->getCurrentUserType();

    switch($tipoUsuario){
        case '0':
            include_once 'admin/inicioAdmin.php';
            break;
        
        case '1':
            include_once 'medico/inicioMedico.php';
            break;
        
        case '2':
            include_once 'paciente/inicioPaciente.php';
            break;
    }
    
}else if(isset($_POST['usuario']) && isset($_POST['contra'])){
    $userForm = $_POST['usuario'];
    $passForm = $_POST['contra'];
    $opcion = $_POST['tipoUsuario'];

    if($user->usuarioExiste($userForm,$passForm,$opcion)){
        $nombreUsuario = $user->ponerUsuario($userForm,$opcion);
        $userSession->setCurrentUser($userForm,$nombreUsuario,$opcion);
        switch($opcion){
            case '0':
                include_once 'admin/inicioAdmin.php';
                break;
            
            case '1':
                include_once 'medico/inicioMedico.php';
                break;
            
            case '2':
                include_once 'paciente/inicioPaciente.php';
                break;
        }
    }else{
        $errorLogin = "Usuario y/o contraseña incorrectos";
        include_once 'login.php';
    }
}else{
    include_once 'login.php';
}
?>