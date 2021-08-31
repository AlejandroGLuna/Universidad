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
            if ($_SESSION['previo'] != "tratamiento"){
                header("Location: inicioMedico.php");
                exit;
            }
            break;
        
        case '2':
            header("Location: ../paciente/inicioPaciente.php");
            exit;
            break;
    }

    if(isset($_POST['enter'])){
        include_once '../ajustes/validacion.php';
        $user = new User();
        $fechas = $_POST['citasH'];
        $consultorios = $_POST['consultoriosH'];
        $err = $user->insertarPacienteCita($_SESSION['curp_p'], $userSession->getCurrentUser(), $fechas, $consultorios);
        if(empty($err)){
            $_SESSION['previo'] = "";
            echo "<script>
                alert('CITAS PROGRAMADAS CORRECTAMENTE');
                window.location.href = 'inicioMedico.php';
            </script>";
        }else
            echo $err;
    }
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Citas</title>
    <link rel="stylesheet" href="../styles/menu.css">
    <link rel="stylesheet" href="../styles/boton.css">
    <link rel="stylesheet" href="../styles/tablas.css">


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
    <br><h2>Citas</h2>
    <form method = "POST">
        <center>
        <div class="forms">
            <p>Próxima cita:</p>
            <input type ="datetime-local" name="cita" id="ci">
        </div>
        <div class="forms">
            <p>Consultorio:</p>
            <input type ="text" name="consultorio" id="co" pattern = "[a-zA-Z\d\.\s,\-ÁáÉéÍíÓóÚúñÑ\\\_]+">
        </div>

        <div class="rows">
        <div class="forms">
            <button class="limpiar" type="reset">Limpiar</button>
        </div>
        <div class="forms">
            <button type = "button" class="validar" onclick = "agregarCita()">Siguiente</button>
        </div>
        </div><br>

        <table class = "default" id = "todasCitas">
            <tr>
                <th>Número</th>
                <th>Fecha de la cita</th>
                <th>Cosultorio</th>
            </tr>
        </table>

        <div class="rows">
            <div class="forms">
            <button type="button" class = "limpiar" name="borrar" onclick = "eliminarFila()">Borrar última cita</button>
            </div>
        </div><br>

        <div class="forms">
            <input type="submit" class = "validar" name="enter" value="Continuar" onclick = "guardarVariable()">
        </div>

        <input type="text" style="display:none" id="citaH" name = "citasH">
        <input type="text" style="display:none" id="consultorioH" name = "consultoriosH">
            </form>
        </center>
    </body>

    <footer>
            CRYSWER &copy; 2021
    </footer>

    <script>
        var citas = [];
        var consultorios = [];
        var numCitas = 0;

        function agregarCita(){
            var cita = document.getElementById("ci").value;
            var consultorio = document.getElementById("co").value;

            if(cita.length == 0 || consultorio.length == 0)
                return;

            var tabla = document.getElementById("todasCitas");
            var fila = tabla.insertRow();

            var celda1 = fila.insertCell();
            var celda2 = fila.insertCell();
            var celda3 = fila.insertCell();

            numCitas++;

            citas.push(cita);
            consultorios.push(consultorio);

            var numeroCita = document.createTextNode("Cita" + numCitas);
            var fechaCita = document.createTextNode(cita);
            var consultorioCita = document.createTextNode(consultorio);
            
            celda1.appendChild(numeroCita);
            celda2.appendChild(fechaCita);
            celda3.appendChild(consultorioCita);

            document.getElementById("ci").value = '';
            document.getElementById("co").value = '';
        }

        function eliminarFila(){
            if (numCitas > 0){
                citas.pop();
                consultorios.pop();
                numCitas--;
                document.getElementById("todasCitas").deleteRow(-1);
            }
        }

        function guardarVariable(){
            var x = citas.join('@');
            document.getElementById("citaH").value = x;

            var y = consultorios.join('@');
            document.getElementById("consultorioH").value = y;
        }
    </script>
</html>
