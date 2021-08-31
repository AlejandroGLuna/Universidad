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

include_once '../ajustes/validacion.php'; 
$user = new User();

if (isset($_GET['genero'])) {
    $pacientes = $user->seleccionarPacientesPorGenero($_GET['genero']);
} else{
    echo 'Error inseperado';
}
    if(empty($pacientes)){
        echo "<script>
            alert('NO EXISTEN PACIENTES ".$_GET['genero']."S REGISTRADOS');
            window.location.href = 'consultaRegistros.php';
            </script>";
    }

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
    <br><h2>REGISTROS POR GÉNERO: <?php echo $_GET['genero'];?> </h2>
    <br><h2>PACIENTES REGISTRADOS: <?php echo $totalPacientes ?></h2>
    
    <form method = "POST">
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
                include_once 'plantillaPDF.php';
                $pdf = new PDF('L','cm','legal');
                $pdf->AliasNbPages();
                $pdf->AddPage();
        
                $pdf->SetFont('Arial','B',12);
                $pdf->Cell(12);
                $pdf->Cell(2,2,utf8_decode('PACIENTES POR GÉNERO: '.$_GET['genero']),0,1,'L');
        

                
                $pdf->SetFont('Arial','B',10);
                $pdf->Cell(25);
                $pdf->Cell(2,1,'Total Pacientes Registrados: '.$totalPacientes,0,1,'L');
        
                $pdf->SetFillColor(232,232,232);
                $pdf->SetFont('Arial','B',9);
                $pdf->Cell(3.4,0.5,'CURP',1,0,'C',1);
                $pdf->Cell(4,0.5,'NOMBRE',1,0,'C',1);
                $pdf->Cell(4,0.5,'E-MAIL',1,0,'C',1); 
                $pdf->Cell(2.3,0.5,'TELEFONO',1,0,'C',1);  
                $pdf->Cell(2.8,0.5,'F. NACIMIENTO',1,0,'C',1);
                $pdf->Cell(2.5,0.5,'EDO. CIVIL',1,0,'C',1);
                $pdf->Cell(3,0.5,utf8_decode('OCUPACIÓN'),1,0,'C',1);
                $pdf->Cell(6,0.5,utf8_decode('DIRECCIÓN'),1,0,'C',1);
                $pdf->Cell(3.5,0.5,utf8_decode('MÉDICO REGISTRO'),1,0,'C',1);
                $pdf->Cell(2.5,0.5,'F. REGISTRO',1,1,'C',1); //SALTO DE LINIA
        
                $pdf->SetFont('Arial','',7);
        
                foreach ($pacientes as $row) {
                    $pdf->Cell(3.4,2,$row['curp_paciente'],1,0,'C',0);
                    $pdf->Cell(4,2,utf8_decode($row['nombre']),1,0,'C',0);
                    $pdf->Cell(4,2,$row['e_mail'],1,0,'C',0); 
                    $pdf->Cell(2.3,2,$row['telefono'],1,0,'C',0);  
                    $pdf->Cell(2.8,2,$row['f_nacimiento'],1,0,'C',0);
                    $pdf->Cell(2.5,2,utf8_decode($row['edo_civil']),1,0,'C',0);
                    $pdf->Cell(3,2,utf8_decode($row['trabajo']),1,0,'C',0);
                    $pdf->Cell(6,2,utf8_decode($row['direccion']),1,0,'C',0);
                    $medico = $user->obtenerMedico($paciente['curp_medico']);
                    $pdf->Cell(3.5,2,utf8_decode($medico['nombre']." ". $medico['apellido_paterno']),1,0,'C',0);
                    $pdf->Cell(2.5,2,$row['f_registro'],1,1,'C',0);
                }
        
                $pdf->Output();
            
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
