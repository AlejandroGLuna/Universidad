<?php
include_once '../ajustes/sesion_usuario.php';
require '../fpdf/fpdf.php';

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

include_once '../ajustes/validacion.php'; 

   
try {
    $con = new Conexion();
    $user = new User();
    $query = "SELECT * FROM paciente ORDER BY apellido_paterno DESC, apellido_materno DESC, nombre DESC";
    $pacientes = $con->db->query($query)->fetchAll(PDO::FETCH_ASSOC);

    $totalPacientes = sizeof($pacientes);
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrador - Consulta total de pacientes registrados</title>
    <link rel="stylesheet" href="../styles/menu.css">
    <link rel="stylesheet" href="../styles/boton.css">
    <link rel="stylesheet" href="../styles/tablas.css">
</head>

    <body class>
        <div id='barra-superior'>
            <ul class='menu'>
                <li>   
                    <a href='inicioAdmin.php'>Inicio</a>
                </li>

                <li>
                    <a href='consultaRegistros.php'>Regresar</a>
                </li>

                <li>
                    <a href="../ajustes/logout.php" class="boton" id="salir">Salir</a>
                </li>
            </ul>
        </div>

    <br><h2>CONSULTA TOTAL DE PACIENTES</h2>
    <br><h2>PACIENTES REGISTRADOS: <?php echo $totalPacientes ?></h2>

    <table class = "default">
        <tr>
            <th> CURP </th>
            <th> Nombre </th>
            <th> E-mail </th>
            <th> Telefono </th>
            <th> Fecha de nacimiento </th>
            <th> Estado Civil </th>
            <th> Ocupación </th>
            <th> Dirección </th>
            <th> Médico de registro </th>
            <th> Fecha de registro </th>
        </tr>

    <?php
    foreach ($pacientes as $paciente) {
    ?>
    
    <tr>
<?php
    echo "<td>{$paciente['curp_paciente']}</td>";
    echo "<td>{$paciente['nombre']} {$paciente['apellido_paterno']} {$paciente['apellido_materno']} </td>";
    echo "<td>{$paciente['e_mail']}</td>";
    echo "<td>{$paciente['telefono']}</td>";
    echo "<td>{$paciente['f_nacimiento']}</td>";
    echo "<td>{$paciente['edo_civil']}</td>";
    echo "<td>{$paciente['trabajo']}</td>";
    echo "<td>{$paciente['direccion']}</td>";
    $medico = $user->obtenerMedico($paciente['curp_medico']);
    echo "<td>{$medico['nombre']} {$medico['apellido_paterno']}</td>";
    echo "<td>{$paciente['f_registro']}</td>";

    ?>
    </tr>
<?php
}
    if (isset($_POST['generapdf'])){
        $pacientes = serialize($pacientes);
        $pacientes = urlencode($pacientes);
        header("Location: ../admin/pdfRegistrosPacientes.php?pacientes=".$pacientes);
        exit();
    }

} catch (PDOException $errCon) {
    echo 'Error de conexión a la base: ' . $errCon->getMessage();
} catch (Exception $ex) {
    echo 'Ocurrió un error inesperado: ' . $ex->getMessage();
}

?>
</table>
<form method="post"> 
    <input type = "submit" class = "btn-pqq-small" name = "generapdf" value = "Generar PDF">
</form> 

<footer>
                    CRYSWER &copy; 2021
            </footer>

</body>
</html>

