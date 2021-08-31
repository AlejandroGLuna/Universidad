<?php
if(!isset($_SESSION['user'])){
    header("Location: index.php");
    exit;
}else{
    $tipoUsuario = $userSession->getCurrentUserType();
    switch ($tipoUsuario){
        case '0':
            header("Location: admin/inicioAdmin.php");
            exit;
            break;
            
        case '1':
            header("Location: medico/inicioMedico.php");
            exit;
            break;
        
        case '2':
            break;
    }
}
?>
<html>
    <head>
        <title> Paciente </title>
    </head>
    <body>
        <div>
            <p style="text-align:right"> <?php echo $userSession->getCurrentUserName();?> 
            <a href = "ajustes/logout.php"> Cerrar sesión </a> </p>
        </div>
        <center>
            <p>Paciente </p>
            <p>Selecciona la opción a realizar</p>
            <button>Consultar Tratamiento</button></a>
            <button>Consultar Próximas Citas</button></a>
        </center>
    </body>
</html>