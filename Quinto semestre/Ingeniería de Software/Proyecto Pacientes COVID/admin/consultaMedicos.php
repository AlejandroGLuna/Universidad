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

require '../ajustes/conexion.php';

try {
    $conexion = new Conexion();

    $query = "SELECT * FROM medico ORDER BY apellido_paterno DESC, apellido_materno DESC, nombre DESC";

    $medicos = $conexion->db->query($query)->fetchAll(PDO::FETCH_ASSOC);

    $totalMedicos = sizeof($medicos);


?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrador - Listado de Medicos</title>
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
                    <a href="../ajustes/logout.php" class="boton" id="salir">Salir</a>
                </li>
            </ul>
        </div>

    <br><h2>Administrador</h2>
    <br><h2>REGISTRO DE MÉDICOS</h2>
    <br><h2>TOTAL DE MÉDICOS REGISTRADOS: <?php echo $totalMedicos ?></h2>


<table class = 'default'>
    <tr>
        <th> CURP </th>
        <th> Nombre </th>
        <th> E-mail </th>
        <th> Telefono </th>
        <th> Hospital </th>
        <th> Turno </th>
        <th> Fecha de nacimiento </th>
        <th> Género </th>
        <th> Estado Civil </th>
        <th> Dirección </th>
        <th> Estado </th>
        <th>  </th>
    </tr>

    <?php

    foreach ($medicos as $medico) {
    ?>
    <tr>
    <?php
        echo "<td>{$medico['curp_medico']} </td>]";
        echo "<td>{$medico['nombre']} {$medico['apellido_paterno']} {$medico['apellido_materno']} </td>";
        echo "<td>{$medico['e_mail']}</td>";
        echo "<td>{$medico['telefono']}</td>";
        echo "<td>{$medico['hospital']}</td>";
        echo "<td>{$medico['turno']}</td>";
        echo "<td>{$medico['f_nacimiento']}</td>";
        echo "<td>{$medico['genero']}</td>";
        echo "<td>{$medico['edo_civil']}</td>";
        echo "<td>{$medico['direccion']}</td>";
        if($medico['activo'] == 1){
            echo "<td> ACTIVO </td>";
        } else{
            echo "<td> NO ACTIVO </td>";
        }

        echo '<td><a href="actualizarDatosMedico.php?curp='.$medico['curp_medico'].'">' , '<button class ="consultarActualizar"> Actualizar </button> </a> </td>';
        
    }

    
    if (isset($_POST['generapdf'])){
        ob_end_clean(); //    the buffer and never prints or returns anything.
        ob_start(); // it starts buffering
        include_once 'plantillaPDF.php';
        $pdf = new PDF('L','cm','legal');
        $pdf->AliasNbPages();
        $pdf->AddPage();

        $pdf->SetFont('Arial','B',12);
        $pdf->Cell(15);
        $pdf->Cell(2,2,utf8_decode('MÉDICOS'),0,1,'L');

        $pdf->SetFont('Arial','B',10);
        $pdf->Cell(25);
        $pdf->Cell(2,1,utf8_decode('Total Médicos Registrados: ').$totalMedicos,0,1,'L');

        $pdf->SetFillColor(232,232,232);
        $pdf->SetFont('Arial','B',9);
        $pdf->Cell(3.4,0.5,'CURP',1,0,'C',1);
        $pdf->Cell(4,0.5,'NOMBRE',1,0,'C',1);
        $pdf->Cell(4,0.5,'E-MAIL',1,0,'C',1); 
        $pdf->Cell(2.3,0.5,'TELEFONO',1,0,'C',1);  
        $pdf->Cell(2.8,0.5,'F. NACIMIENTO',1,0,'C',1);
        $pdf->Cell(2.5,0.5,'EDO. CIVIL',1,0,'C',1);
        $pdf->Cell(3,0.5,utf8_decode('SEDE'),1,0,'C',1);
        $pdf->Cell(3,0.5,utf8_decode('TURNO'),1,0,'C',1);
        $pdf->Cell(6,0.5,utf8_decode('DIRECCIÓN'),1,1,'C',1);

        $pdf->SetFont('Arial','',7);

        foreach ($medicos as $row) {
            $pdf->Cell(3.4,2,$row['curp_medico'],1,0,'C',0);
            $pdf->Cell(4,2,utf8_decode($row['nombre']),1,0,'C',0);
            $pdf->Cell(4,2,$row['e_mail'],1,0,'C',0); 
            $pdf->Cell(2.3,2,$row['telefono'],1,0,'C',0);  
            $pdf->Cell(2.8,2,$row['f_nacimiento'],1,0,'C',0);
            $pdf->Cell(2.5,2,utf8_decode($row['edo_civil']),1,0,'C',0);
            $pdf->Cell(3,2,utf8_decode($row['hospital']),1,0,'C',0);
            $pdf->Cell(3,2,utf8_decode($row['turno']),1,0,'C',0);
            $pdf->Cell(6,2,utf8_decode($row['direccion']),1,1,'C',0);
            
        }
        
        $pdf->Output('D','consultas.pdf');
        ob_end_flush();
        
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
