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

if (isset($_GET['edadDe']) && isset($_GET['a']) && !empty($_GET['edadDe']) && !empty($_GET['a'])) {
    $tipo = "rango";
} elseif(isset($_GET['edad']) && !empty($_GET['edad'])){
    $tipo = "edad";
} else{
    echo "<script>
        alert('EDAD O RANGO DE EDAD INVÁLIDO');
        window.location.href = 'consultaRegistros.php';
        </script>";
}      

$cadena = ($tipo == "rango") ? "ENTRE " . $_GET['edadDe'] . " Y " . $_GET['a'] . " AÑOS ": $_GET['edad'] . " AÑOS ";

?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrador - Total de pacientes por edad</title>
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
    <br><h2>TOTAL DE PACIENTES  DE <?php echo ($tipo == "rango") ? "ENTRE " . $_GET['edadDe'] . " Y " . $_GET['a'] : $_GET['edad']; ?> AÑOS </h2>
    
    <form method = "POST">
    <table class = "default">
   <tr>
            <th> CURP </th>
            <th> Nombre </th>
            <th> Edad </th>
            <th> E-mail </th>
            <th> Telefono </th>
            <th> Fecha de nacimiento </th>
            <th> Estado Civil </th>
            <th> Ocupación </th>
            <th> Dirección </th>
            <th> Médico de registro </th>
            <th> Fecha de registro </th>
        </tr>
                                <tr>
                                <?php
                                
                include_once '../ajustes/validacion.php'; 
                $user = new User();
                $totalPacientes = 0;
                try {
                    $conexion = new Conexion();
                    $query = "SELECT * FROM paciente ORDER BY apellido_paterno DESC, apellido_materno DESC, nombre DESC";
                    $pacientes = $conexion->db->query($query)->fetchAll(PDO::FETCH_ASSOC);
                    if(!empty($pacientes)){
                        if ($tipo == "rango") {
                            foreach($pacientes as $paciente){
                                $edad = $user->obtenerEdad($paciente['f_nacimiento']);
                                if($edad >= $_GET['edadDe'] && $edad <= $_GET['a']){
                                    $totalPacientes = $totalPacientes + 1;
                 
                                    echo "<td>{$paciente['curp_paciente']}</td>";
                                    echo "<td>{$paciente['nombre']} {$paciente['apellido_paterno']} {$paciente['apellido_materno']} </td>";
                                    echo "<td>{$edad}</td>";
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
                        } 
                        if($totalPacientes == 0){
                            echo "<script>
                                alert('NO EXISTEN PACIENTES DE ENTRE ".$_GET['edadDe']." A ".$_GET['a']." AÑOS REGISTRADOS');
                                window.location.href = 'consultaRegistros.php';
                                </script>";
                        }      
                    } elseif($tipo == "edad"){
                            foreach($pacientes as $paciente){
                                $edad = $user->obtenerEdad($paciente['f_nacimiento']);
                                if($edad == $_GET['edad']){
                                    $totalPacientes = $totalPacientes + 1;
                                    ?>
                                    <tr>
                                    <?php
                                        echo "<td>{$paciente['curp_paciente']}</td>";
                                        echo "<td>{$paciente['nombre']} {$paciente['apellido_paterno']} {$paciente['apellido_materno']} </td>";
                                        echo "<td>{$edad}</td>";
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
                                if($totalPacientes == 0){
                                        echo "<script>
                                            alert('NO EXISTEN PACIENTES DE ".$_GET['edad']." AÑOS REGISTRADOS');
                                            window.location.href = 'consultaRegistros.php';
                                            </script>";
                                    }
                                
                            }
                    }
                } else{
                    echo "<script>
                    alert('ERROR EN LA BASE DE DATOS. NO HAY PACIENTES REGISTRADOS');
                    window.location.href = 'consultaRegistros.php';
                    </script>";
                }

                if (isset($_POST['generapdf'])){
                    include_once 'plantillaPDF.php';
                    $pdf = new PDF('L','cm','legal');
                    $pdf->AliasNbPages();
                    $pdf->AddPage();

                    $pdf->SetFont('Arial','B',12);
                    $pdf->Cell(12);
                    $pdf->Cell(2,2,utf8_decode('TOTAL DE PACIENTES  DE '.$cadena),0,1,'L');
            
                    $pdf->SetFont('Arial','B',10);
                    $pdf->Cell(25);
                    $pdf->Cell(2,1,'Total Pacientes Registrados: '.$totalPacientes,0,1,'L');
            
                    $pdf->SetFillColor(232,232,232);
                    $pdf->SetFont('Arial','B',9);
                    $pdf->Cell(3,0.5,'CURP',1,0,'C',1);
                    $pdf->Cell(4,0.5,'NOMBRE',1,0,'C',1);
                    $pdf->Cell(4,0.5,'E-MAIL',1,0,'C',1); 
                    $pdf->Cell(2,0.5,'TELEFONO',1,0,'C',1);  
                    $pdf->Cell(2.8,0.5,'F. NACIMIENTO',1,0,'C',1);
                    $pdf->Cell(2,0.5,'EDO. CIVIL',1,0,'C',1);
                    $pdf->Cell(1.5,0.5,utf8_decode('GÉNERO'),1,0,'C',1);
                    $pdf->Cell(3,0.5,utf8_decode('OCUPACIÓN'),1,0,'C',1);
                    $pdf->Cell(6,0.5,utf8_decode('DIRECCIÓN'),1,0,'C',1);
                    $pdf->Cell(3.5,0.5,utf8_decode('MÉDICO REGISTRO'),1,0,'C',1);
                    $pdf->Cell(2.5,0.5,'F. REGISTRO',1,1,'C',1); //SALTO DE LINIA
            
                    $pdf->SetFont('Arial','',7);
            
                    foreach ($pacientes as $paciente) {
                        $query = "SELECT * FROM paciente WHERE curp_paciente = '{$paciente['curp_paciente']}' ";
                        $row = $conexion->db->query($query)->fetch(PDO::FETCH_ASSOC);
                        $pdf->Cell(3,2,$row['curp_paciente'],1,0,'C',0);
                        $pdf->Cell(4,2,utf8_decode($row['nombre']),1,0,'C',0);
                        $pdf->Cell(4,2,$row['e_mail'],1,0,'C',0); 
                        $pdf->Cell(2,2,$row['telefono'],1,0,'C',0);  
                        $pdf->Cell(2.8,2,$row['f_nacimiento'],1,0,'C',0);
                        $pdf->Cell(2,2,utf8_decode($row['edo_civil']),1,0,'C',0);
                        $pdf->Cell(1.5,2,utf8_decode($row['genero']),1,0,'C',0);
                        $pdf->Cell(3,2,utf8_decode($row['trabajo']),1,0,'C',0);
                        $pdf->Cell(6,2,utf8_decode($row['direccion']),1,0,'C',0);
                        $medico = $user->obtenerMedico($row['curp_medico']);
                        $pdf->Cell(3.5,2,utf8_decode($medico['nombre']." ". $medico['apellido_paterno']),1,0,'C',0);
                        $pdf->Cell(2.5,2,$row['f_registro'],1,1,'C',0);
                    }
            
                    $pdf->Output();
                
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