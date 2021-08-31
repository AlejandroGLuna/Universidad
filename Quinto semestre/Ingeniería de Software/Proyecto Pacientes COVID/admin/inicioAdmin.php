<?php
if(!isset($_SESSION['user'])){
    header("Location: ../index.php");
    exit;
}else{
    include_once "ajustes/sesion_usuario.php";
    $tipoUsuario = $userSession->getCurrentUserType();
    switch ($tipoUsuario){
        case '0':
            break;
            
        case '1':
            header("Location: medico/inicioMedico.php");
            exit;
            break;
        
        case '2':
            header("Location: paciente/inicioPaciente.php");
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
    <title>Administrador</title>
    <link rel="stylesheet" href="styles/menu.css">
    <link rel="stylesheet" href="styles/boton.css">
    <link rel="stylesheet" href="styles/admin.css">
    <link rel="stylesheet" href="styles/estilos.css">

</head>
<body class>
    
    <div id='barra-superior'>
        <ul class='menu'>
            <li>   
                <a href='medico/inicioMedico.php'>Inicio</a>
            </li>

            <li>
                <a href="ajustes/logout.php" class="boton" id="salir">Salir</a>
            </li>

        </ul>
    </div>

    <br><h2>Administrador</h2>
    <br><h2>Seleccionar una opción</h2><br>

<div class="rows">
    <div class="formsInicios">
        <button class="btn-pqq" type="button" onclick = "redireccionar('admin/registrarMedico.php')">Registrar Nuevo Médico</button>
    </div>

    <div class="formsInicios">
        <button class="btn-pqq" type="button" onclick = "redireccionar('admin/consultaRegistros.php')">Consultar registros de pacientes</button>
    </div>

    <div class="formsInicios">
        <button class="btn-pqq" type="button" onclick = "redireccionar('admin/consultaMedicos.php')">Actualizar registro de un médico</button>
    </div>
    </div>

    </body>

    <footer>
        CRYSWER &copy; 2021
    </footer>

    <script>
        function redireccionar(pagina){
            window.location.href = pagina;
            
        }

    </script>
</html>
