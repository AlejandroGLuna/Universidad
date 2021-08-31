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
        header("Location: pacientes.php");
        exit;
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
        <br><h2>Consulta de Paciente</h2>
<center>
<table class = "default">
    <caption>DATOS DEL PACIENTE</caption>
    <tr>
        <th> Dato </th>
        <th> Información </th>
    </tr>

    <?php
    include_once "../ajustes/validacion.php"; 
    $user = new User();

    try {
        $paciente = $user->obtenerPaciente($curp_paciente);
        $edad = $user->obtenerEdad($paciente['f_nacimiento']);
        echo "<tr> <td> CURP </td> <td>{$paciente['curp_paciente']}</td> </tr>";
        echo "<tr> <td> Nombre </td> <td>{$paciente['nombre']} {$paciente['apellido_paterno']} {$paciente['apellido_materno']}</td> </tr>";
        echo "<tr> <td> E_mail </td> <td>{$paciente['e_mail']}</td> </tr>";
        echo "<tr> <td> Teléfono </td> <td>{$paciente['telefono']}</td> </tr>";
        echo "<tr> <td> Trabajo </td> <td>{$paciente['trabajo']}</td> </tr>";
        echo "<tr> <td> Edad </td> <td>{$edad}</td> </tr>";
        echo "<tr> <td> Género </td> <td>{$paciente['genero']}</td> </tr>";
        echo "<tr> <td> Estado civil </td> <td>{$paciente['edo_civil']}</td> </tr>";
        echo "<tr> <td> Dirección </td> <td>{$paciente['direccion']}</td> </tr>";
        echo "<tr> <td> Fecha de registro </td> <td>{$paciente['f_registro']}</td> </tr>";
    }catch (PDOException $errCon){
        echo 'Error de conexión a la base: ' . $errCon->getMessage();
    }catch (Exception $ex){
        echo 'Ocurrió un error inesperado: ' . $ex->getMessage();
    }
?>
</table><br><br>

<table class = "default">
    <caption>ENCUESTA DEL PACIENTE</caption>
    <tr>
        <th> Pregunta </th>
        <th> Respuesta </th>
    </tr>
    <?php
    function obtenerRespuesta($puntaje, $esFiebre, $esAlergia){
        $res = "";
        switch($puntaje){
            case '0':
                $res = "No";
                break;
            
            case '2':
                if($esAlergia)
                    $res = "A alimentos";
                else if($esFiebre)
                    $res = "Entre 37.5 y 39";
                else
                    $res = "Moderado";
                break;
            
            case '3':
                if($esAlergia)
                    $res = "A medicamentos";
                else if($esFiebre)
                    $res = "Mayor o igual a 39";
                else
                    $res = "Alto";
                break;
        }
        return $res;
    }

    function obtenerSector($total, $respiracion){
        return ($total > 10 | $respiracion == '2' | $respiracion == '3') ? "Urgencias" : "Consulta externa";
    }

    try{
        $encuesta = $user->obtenerEncuestaPaciente($curp_paciente);
        if(empty($encuesta)){
            echo "<tr> <td colspan ='2'> Encuesta no contestada </tr>";
        }else{
            echo "<tr> <td> Fiebre </td> <td> ". obtenerRespuesta($encuesta['res_fiebre'], true, false) ."</td> </tr>";
            echo "<tr> <td> Dolor de cabeza </td> <td> ". obtenerRespuesta($encuesta['res_dolcabeza'], false, false) ."</td> </tr>";
            echo "<tr> <td> Dificultad para respirar </td> <td> ". obtenerRespuesta($encuesta['res_respiracion'], false, false) ."</td> </tr>";
            echo "<tr> <td> Dolor de huesos </td> <td> ". obtenerRespuesta($encuesta['res_dolhuesos'], false, false) ."</td> </tr>";
            echo "<tr> <td> Cansancio </td> <td> ". obtenerRespuesta($encuesta['res_cansancio'], false, false) ."</td> </tr>";
            echo "<tr> <td> Flujo nasal </td> <td> ". obtenerRespuesta($encuesta['res_flujonasal'], false, false) ."</td> </tr>";
            echo "<tr> <td> Alergias </td> <td> ". obtenerRespuesta($encuesta['res_alergias'], false, true) ."</td> </tr>";
            echo "<tr> <td> Sector </td> <td> ". obtenerSector($encuesta['total_pts'], $encuesta['res_respiracion']) ."</td> </tr>";
        }
    }catch (PDOException $errCon){
        echo 'Error de conexión a la base: ' . $errCon->getMessage();
    }catch (Exception $ex){
        echo 'Ocurrió un error inesperado: ' . $ex->getMessage();
    }
    ?>
</table><br><br>

<table class = "default">
    <caption>TRATAMIENTO DEL PACIENTE</caption>
    <tr>
        <th> Medicamento </th>
        <th> Tratamiento/Indicaciones </th>
    </tr>
    <?php
    try{
        $receta = $user->obtenerTratamientoPaciente($curp_paciente);
        $hayMedicinas = false;
        $hayIndicaciones = false;

        $medicinas = $receta['medicamentos'];
        $dosis = $receta['dosis_tiempo'];
        $indicaciones = $receta['descripcion'];
        if(!is_null($medicinas)){
            $hayMedicinas = true;
            $medicinas = explode("@",$medicinas);
            $dosis = explode("@",$dosis);
            $i = 0;
            foreach($medicinas as $medicina){
                echo "<tr> <td> {$medicina} </td> <td> {$dosis[$i]} </td> </tr>";
                $i++;
            }
        }

        if(!is_null($indicaciones)){
            $hayIndicaciones = true;
            $indicaciones = explode("@",$indicaciones);
            foreach($indicaciones as $indicacion)
                echo "<tr> <td colspan = '2'> {$indicacion} </td></tr>";
        }

        if(!$hayMedicinas && !$hayIndicaciones){
            echo "<tr> <td colspan ='2'> Sin tratamiento recetado </td> </tr>";
        }
        
    }catch (PDOException $errCon){
        echo 'Error de conexión a la base: ' . $errCon->getMessage();
    }catch (Exception $ex){
        echo 'Ocurrió un error inesperado: ' . $ex->getMessage();
    }
    ?>
</table><br><br>

<table class = "default">
    <caption>CITAS DEL PACIENTE</caption>
    <tr>
        <th> Número de cita </th>
        <th> Horario </th>
        <th> Consultorio </th>
    </tr>
    <?php
    try{
        $citas = $user->obtenerCitasPaciente($curp_paciente);
        $fechas = explode("@", $citas['fecha']);
        $consultorios = explode("@", $citas['consultorios']);
        $i = 0;
        foreach($fechas as $fecha){
            if (empty($fecha)){
                echo "<tr> <td colspan ='3'> Sin citas programadas </td> </tr>";
                break;
            }
            $fecha = str_replace("T", " a las ", $fecha);
            echo "<tr> <td>". ($i + 1) ."</td> <td> {$fecha} </td> <td> {$consultorios[$i]} </td> </tr>";
            $i++;
        }
    }catch (PDOException $errCon){
        echo 'Error de conexión a la base: ' . $errCon->getMessage();
    }catch (Exception $ex){
        echo 'Ocurrió un error inesperado: ' . $ex->getMessage();
    }
    ?>
</table>
    <div class="rows">
    <div class="forms">
        <button type="button" class = "validar" onclick = "redireccionar('pacientes.php')">Entendido</button>
    </div>
    </div>  
</center>
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
