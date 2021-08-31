<?php
include_once '../ajustes/sesion_usuario.php';

$userSession = new SesionUsuario();

if(!isset($_SESSION['user'])){
    header("Location: ../index.php");
    exit;
}else{
    $tipoUsuario = $userSession->getCurrentUserType();
    switch ($tipoUsuario){
        case '0':
            header("Location: ../admin/inicioAdmin.php");
            exit;
            break;
            
        case '1':
            break;
        
        case '2':
            header("Location: ../paciente/inicioPaciente.php");
            exit;
            break;
    }

    if (isset($_GET['curp'])) {
        $curp_paciente = $_GET['curp'];
    }else{
        header("Location: inicioMedico.php");
        exit;
    }
    include_once '../ajustes/validacion.php';

    $user = new User();
    try{
        $existe = $user->existePaciente($curp_paciente, $userSession->getCurrentUser());
        if($existe){
            $_SESSION['curp_p'] = $curp_paciente;
            $_SESSION['previo'] = "registrarPacienteConExpediente";
            echo "<script>
                alert('PACIENTE ENCONTRADO');
                window.location.href = 'encuesta.php';
            </script>";
        }else{
            echo "<script>
                alert('PACIENTE NO ENCONTRADO. VERIFICAR ENTRADA');
                window.location.href = 'inicioMedico.php';
            </script>";
        }
    }catch(Exception $ex){
        echo "<script>
                alert('ERROR AL CONECTARSE A LA BASE DE DATOS');
                window.location.href = 'inicioMedico.php';
            </script>";
    }
}

?>