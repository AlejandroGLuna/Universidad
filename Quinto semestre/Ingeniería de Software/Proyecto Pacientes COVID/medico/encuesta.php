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
            if ($_SESSION['previo'] != "registrarPaciente" && $_SESSION['previo'] != "registrarPacienteConExpediente"){
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
        $r1 = intval($_POST['fiebre']);
        $r2 = intval($_POST['cabeza']);
        $r3 = intval($_POST['respirar']);
        $r4 = intval($_POST['corporal']);
        $r5 = intval($_POST['cansancio']);
        $r6 = intval($_POST['flujo']);
        $r7 = intval($_POST['alergia']);
        $total = $r1 + $r2 + $r3 + $r4 + $r5 + $r6 + $r7;
        if($r3 > 0 || $total > 10){
            $urgencias = true;
            $sector = "URGENCIAS";
        }
        else{
            $urgencias = false;
            $sector = "CONSULTA EXTERNA";
        }

        $hayExpediente = ($_SESSION['previo'] == "registrarPacienteConExpediente") ? true : false;
        $err = $user->insertarPacienteEncuesta($hayExpediente, $_SESSION['curp_p'], $userSession->getCurrentUser(), $r1, $r2, $r3, $r4, $r5, $r6, $r7, $total, $urgencias);
        if(empty($err)){
            $_SESSION['previo'] = "encuesta";
            echo "<script>
                alert('ENCUESTA CONTESTADA EXITOSAMENTE. PACIENTE CANALIZADO A ".$sector."');
                window.location.href = 'tratamiento.php';
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
    <title>Encuesta</title>
    <link rel="stylesheet" href="../styles/menu.css">
    <link rel="stylesheet" href="../styles/boton.css">
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
    <br><h2>Encuesta</h2>
    <form method = "POST">
        <center>
        <div class="forms">
            <p> ¿Presenta fiebre? </p>
            <input type = "radio" name = "fiebre" value = '1' onclick = "validate('fiebre21','fiebre22')" required>Sí
            <input type = "radio" name = "fiebre" value = '0' onclick = "cancel('fiebre21','fiebre22')">No
        </div>
        <div class="forms">
            <p>Indique el rango de temperatura</p>
            <input type = "radio" name = "fiebre" id = "fiebre21" value = '2' disabled>Entre 37.5 y 39
            <input type = "radio" name = "fiebre" id = "fiebre22" value = '3' disabled>Mayor o igual a 39
        </div><br>
        <div class="forms">
            <p> ¿Presenta dolor de cabeza </p>
            <input type = "radio" name = "cabeza" value = '1' onclick = "validate('cabeza21','cabeza22')" required>Sí
            <input type = "radio" name = "cabeza" value = '0' onclick = "cancel('cabeza21','cabeza22')">No
        </div>
        <div class="forms">
            <p>Indique la intensidad del dolor de cabeza</p>
            <input type = "radio" name = "cabeza" id = "cabeza21" value = '2' disabled>Moderada
            <input type = "radio" name = "cabeza" id = "cabeza22" value = '3' disabled>Alta</p>
        </div><br>
        <div class="forms">
            <p>¿Tiene dificultad para respirar?</p>
            <input type = "radio" name = "respirar" value = '1' onclick = "validate('respirar21','respirar22')" required>Sí
            <input type = "radio" name = "respirar" value = '0' onclick = "cancel('respirar21','respirar22')">No
        </div>
        <div class="forms">
            <p>Indique el nivel de dificultad para respirar</p>
            <input type = "radio" name = "respirar" id = "respirar21" value = '2' disabled>Moderada
            <input type = "radio" name = "respirar" id = "respirar22" value = '3' disabled>Alta
        </div><br>
        <div class="forms">
            <p>¿Tiene dolor coporal?</p>
            <input type = "radio" name = "corporal" value = '1' onclick = "validate('corporal21','corporal22')" required>Sí
            <input type = "radio" name = "corporal" value = '0' onclick = "cancel('corporal21','corporal22')">No
        </div>
        <div class="forms">
            <p>Indique la intensidad del dolor corporal</p>
            <input type = "radio" name = "corporal" id = "corporal21" value = '2' disabled>Moderada
            <input type = "radio" name = "corporal" id = "corporal22" value = '3' disabled>Alta
        </div><br>
        <div class="forms">
            <p>¿Siente cansancio?</p>
            <input type = "radio" name = "cansancio" value = '1' onclick = "validate('cansancio21','cansancio22')" required>Sí
            <input type = "radio" name = "cansancio" value = '0' onclick = "cancel('cansancio21','cansancio22')">No</p>
        </div>
        <div class="forms">
            <p>Indique el nivel de cansancio</p>
            <input type = "radio" name = "cansancio" id = "cansancio21" value = '2' disabled>Moderado
            <input type = "radio" name = "cansancio" id = "cansancio22" value = '3' disabled>Alto
        </div><br>
        <div class="forms">
            <p>¿Tiene flujo nasal?</p>
            <input type = "radio" name = "flujo" value = '1' onclick = "validate('flujo21','flujo22')" required>Sí
            <input type = "radio" name = "flujo" value = '0' onclick = "cancel('flujo21','flujo22')">No</p>
        </div>
        <div class="forms">
            <p>Indique el nivel de flujo nasal</p>
            <input type = "radio" name = "flujo"  id = "flujo21" value = '2' disabled>Moderado
            <input type = "radio" name = "flujo"  id = "flujo22" value = '3' disabled>Alto
        </div><br>
        <div class="forms">
            <p>¿Tiene alergías?</p>
            <input type = "radio" name = "alergia" value = '1' onclick = "validate('alergia21','alergia22')" required>Sí
            <input type = "radio" name = "alergia" value = '0' onclick = "cancel('alergia21','alergia22')">No
        </div>
        <div class="forms">
            <p>Indique el tipo de alergías</p>
            <input type = "radio" name = "alergia" id = "alergia21" value = '2' disabled>Hacia Alimentos
            <input type = "radio" name = "alergia" id = "alergia22" value = '3' disabled>Hacia Medicamenos
        </div><br>
        <div class="rows">
        <div class="forms">
            <input type="reset" class = "limpiar" name="cancelar" value = "Limpiar"></button>
        </div>
        <div class="forms">
            <input type="submit" class = "validar" name="enter" value="Continuar">
        </div>
        </div>
        </center>
    </form>
    <footer>
            CRYSWER &copy; 2021
    </footer>
</body>
    <script>
        function validate(element1,element2){
            document.getElementById(element1).disabled=false;
            document.getElementById(element2).disabled=false;
            document.getElementById(element1).checked=true;
        }

        function cancel(elemento1, elemento2){
            document.getElementById(elemento1).disabled=true;
            document.getElementById(elemento2).disabled=true;
        }
    </script>
</html>