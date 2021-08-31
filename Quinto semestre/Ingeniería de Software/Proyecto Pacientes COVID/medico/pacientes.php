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
}

?>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Medico</title>
    <link rel="stylesheet" href="../styles/menu.css">
    <link rel="stylesheet" href="../styles/boton.css">
    <link rel="stylesheet" href="../styles/tablas.css">
    <link rel="stylesheet" href="../styles/estilos.css">

</head>
    <body class>
        <div id='barra-superior'>
            <ul class='menu'>
                <li>   
                    <a href='inicioMedico.php'>Inicio</a>
                </li>

                <li>
                    <a href="../ajustes/logout.php" class="boton" id="salir">Salir</a>
                </li>
            </ul>
        </div>
    
    <br><h2>Médico: <?php echo $userSession->getCurrentUserName();?> </h2>
    <br><h2>Pacientes Registrados</h2>

<table class = "default">
    <tr>
        <th> CURP Paciente</th>
        <th> Nombre </th>
        <th> Apellido Paterno </th>
        <th> Apellido Materno </th>
        <th> Actualizar </th>
        <th> Consultar </th>
    </tr>

    <?php

    function obtenerEdad ($fecha_nacimiento) {
        $fecha = new DateTime($fecha_nacimiento);
        $ahora = new DateTime();
        $diferencia = $ahora->diff($fecha);
        return $diferencia->y;
    }

    include_once "../ajustes/validacion.php"; 
    $user = new User();
    try {
        $pacientes = $user->obtenerPacientes($userSession->getCurrentUser());
        foreach ($pacientes as $paciente) {
            echo "<tr>";
                echo "<td>{$paciente['curp_paciente']}</td>";
                echo "<td>{$paciente['nombre']}</td>";
                echo "<td>{$paciente['apellido_paterno']} </td>";
                echo "<td>{$paciente['apellido_materno']} </td>";
                echo '<td> <a href ="actualizarDatosPaciente.php?curp=' . $paciente['curp_paciente'] .'"><button class ="consultarActualizar">Actualizar</button></a></td>';
                echo '<td> <a href ="consultarDatosPaciente.php?curp=' . $paciente['curp_paciente'] .'"><button class ="consultarActualizar">Consultar</button></a></td>';
            echo "</tr>";
        }
    }catch (PDOException $errCon){
        echo 'Error de conexión a la base: ' . $errCon->getMessage();
    }catch (Exception $ex){
        echo 'Ocurrió un error inesperado: ' . $ex->getMessage();
    }

?>
</table>
</body>

<footer>
        CRYSWER &copy; 2021
</footer>

</html>
