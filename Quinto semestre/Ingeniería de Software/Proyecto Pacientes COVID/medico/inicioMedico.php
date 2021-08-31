<?php
if(!isset($_SESSION['user'])){
    header("Location: ../index.php");
    exit;
}else{
    $tipoUsuario = $userSession->getCurrentUserType();
    switch ($tipoUsuario){
        case '0':
            header("Location: admin/inicioAdmin.php");
            exit;
            break;
            
        case '1':
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
    <title>Medico</title>
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
    
    <br><h2>Médico: <?php echo $userSession->getCurrentUserName();?> </h2>
    <br><h2>Seleccionar una opción</h2>

    <div class="rows">
    <div class="formsInicios">
        <button class="btn-pqq" type="button" onclick = "redireccionar('medico/registrarPaciente.php')">Registrar Nuevo Paciente</button>
    </div>

    <div class="formsInicios">
        <button class="btn-pqq" type="button" onclick = "solicitarCURP()">Canalizar Paciente Existente</button>
    </div>

    <div class="formsInicios">
        <button class="btn-pqq" type="button" onclick = "redireccionar('medico/consultarDatosPaciente.php')">Consultar Registros de Pacientes</button>
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

        function solicitarCURP(){
            var curp =  prompt("INTRODUZCA EL CURP DEL PACIENTE", "");
            if(curp!=null){
                var link = "medico/verificarRegistroPaciente.php?curp="+curp;
                window.location.href = link;
            }
        }
    </script>
</html>
