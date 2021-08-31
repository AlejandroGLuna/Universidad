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
            break;
            
        case '1':
            header("Location: ../medico/inicioMedico.php");
            exit;
            break;
        
        case '2':
            header("Location: ../paciente/inicioPaciente.php");
            exit;
            break;
    }
}

if(isset($_POST['opcionT'])){
    header("Location: ../admin/registrosPacientes.php");
    exit();
}
if(isset($_POST['opcionC'])){
    header("Location: ../admin/registrosTipoDeConsulta.php?consulta={$_POST['opcionC']}");
    exit();
}
if(isset($_POST['opcionG'])){
    header("Location: ../admin/registrosPorGenero.php?genero={$_POST['opcionG']}");
    exit();
}

if(isset($_POST['opcionE'])){
    header("Location: ../admin/registrosPorEdad.php?edad={$_POST['edad']}");
    exit();
}

if(isset($_POST['opcionER'])){
    header("Location: ../admin/registrosPorEdad.php?edadDe={$_POST['edad1']}&a={$_POST['edad2']}");
    exit();
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrador - Consulta de registros</title>
    <link rel="stylesheet" href="../styles/menu.css">
    <link rel="stylesheet" href="../styles/boton.css">
</head>

    <body class>
        <div id='barra-superior'>
            <ul class='menu'>
                <li>   
                    <a href='inicioAdmin.php'>Inicio</a>
                </li>

                <li>
                    <a href="../ajustes/logout.php" class="boton" id="salir">Salir</a>
                </li>
            </ul>
        </div>

    <br><h2>CONSULTAS</h2>
    <h3>Seleccione el tipo de consulta para visualizar a los pacientes registrados:</h3>

    <form method = "POST">

    <div class="rows">
        <div class="forms">
            <input type="submit" class ="btn-pqq" name="opcionT" value="Todos los pacientes">
        </div>
    </div>

    <div class="forms">
    <h3>Por tipo de consulta: </h3>
    </div>
    <div class="rows">
        <div class="forms">
            <input type="submit" class ="btn-pqq" name="opcionC" value="URGENCIAS">
        </div>
        <div class="forms">
            <input type="submit" class ="btn-pqq" name="opcionC" value="CONSULTAEXTERNA">
        </div>
    </div>

    <div class="forms">
    <h3>Por género: </h3>
    </div>
    <div class="rows">
        <div class="forms">
            <input type="submit" class ="btn-pqq" name="opcionG" value="MASCULINO">
        </div>
        <div class="forms">
            <input type="submit" class ="btn-pqq" name="opcionG" value="FEMENINO">
        </div>
    </div>

    <div class="forms">
        <h3>Por edad: </h3>
    </div>

    <div class="rows">
            <div class="forms">
                <label for="" class = "forms">Introduzca la edad:</label> <input type="text" name = 'edad' placeholder="Edad" ><input type="submit" class = "btn-pqq-small" name="opcionE" value="Ver registros">
            </div>
    </div>

    <div class="rows">
            <div class="forms">
                <label for="" class = "forms">Ó introduzca un rango de edad: </label>  <input type ="text"  name="edad1" placeholder="Edad de" > <input type ="text" class = "forms" name="edad2" placeholder="a" > <input type="submit" class = "btn-pqq-small" name="opcionER" value="Ver registros"> 
            </div>
    </div>
            

            <footer>
                    CRYSWER &copy; 2021
            </footer>

        </body>
        </html>
