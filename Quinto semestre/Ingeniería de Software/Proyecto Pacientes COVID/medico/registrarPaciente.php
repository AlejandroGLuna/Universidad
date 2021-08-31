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

    if (isset($_POST['enter'])){
        $email = trim($_POST['email']);
        if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
            $emailErr = "Dirección de correo inválida";
        }else{
            include_once '../ajustes/validacion.php';
            $user = new User();
            $pass = substr(MD5(rand()), 0, 8);
            //93fa5ea, 52edb9b, dfab00e, 5ad6573, 81513e3, 58369fc, 70a360c, cafe0c2, 9bcadafe, 163643d3, 7d5f00b1
            $err = $user->insertarPaciente($_POST['curp'], $userSession->getCurrentUser(), MD5($pass), strtoupper($_POST['nombre']), strtoupper($_POST['apellidoP']), strtoupper($_POST['apellidoM']), $_POST['genero'], strtoupper($_POST['direccion']), $_POST['nacimiento'], $_POST['telefono'], $_POST['estado'], $email, strtoupper($_POST['ocupacion']));
            if(empty($err)){
                $_SESSION['curp_p'] = $_POST['curp'];
                $_SESSION['previo'] = "registrarPaciente";
                echo "<script>
                    alert('PACIENTE INGRESADO CORRECTAMENTE. SU CONTRASEÑA ES: ".$pass."');
                    window.location.href = 'encuesta.php';
                </script>";
            }else
                echo $err;
        }
    }
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Datos demográficos</title>
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
    <br><h2>Registrar Paciente</h2>

    <form method="POST">
        <div class="rows">
            <div class="forms">
                <label for="">Nombre(s):</label><input type="text" placeholder="Nombre(s)" name="nombre" id = "a" pattern = "([a-zA-ZÁáÉéÍíÓóÚúñÑ]{2,}\s*){1,3}" required>
            </div>
            <div class="forms">
                <label for="">Apellido Paterno:</label><input type="text" placeholder="Apellido Paterno" id = "b" name="apellidoP" pattern = "([a-zA-ZÁáÉéÍíÓóÚúñÑ]{2,}\s*){1,3}" required>  
            </div>
            <div class="forms">
                <label for="">Apellido Materno:</label><input type="text" placeholder="Apellido Materno" id = "c" name="apellidoM" pattern = "([a-zA-ZÁáÉéÍíÓóÚúñÑ]{2,}\s*){1,3}" required>
            </div><br>
        </div>

            <div class="rows">
            <p>Seleccionar género:</p><br>
            <div class="forms">
                <label for="femenino">Femenino</label>
                <input type="radio" class="radiales" name="genero" value="FEMENINO" checked>
                <br><label for="masculino">Masculino</label>
                <input type="radio" class="radiales" name="genero" value="MASCULINO" >
            </div>
        </div>
        
        <div class="rows">
        <div class="forms">
            <label for="">CURP:</label><input type="text" placeholder="CURP" name="curp" id = "d" pattern = "[A-Z]{4}\d{6}(H|M)[A-Z]{5}([A-Z]|\d)\d" required>
        </div>
            <div class="forms">
            <label for="">Fecha de Nacimiento:</label><input type="date" placeholder="Fecha de nacimiento" id = "e" name="nacimiento">
        </div>
        <div class="forms">
            <label for="estadocivil">Seleccionar estado civil:</label>
            <select name = "estado" id="estadocivil">
                <option value = "SOLTERO(A)" selected>Soltero(a)</option>
                <option value = "CASADO(A)">Casado(a)</option>
                <option value = "DIVORCIADO(A)">Divorciado(a)</option>
                <option value = "VIUDO(A)">Viudo(a)</option>
            </select>
        </div>
        
        </div><br>

        <div class="rows">
        <div class="forms">
            <label for="">Dirección:</label><input type="text" placeholder="Dirección" name="direccion" id = "f" pattern = "([a-zA-ZÁáÉéÍíÓóÚúñÑ,\d\.#]+\s*)+" required>
        </div>
            <div class="forms">
            <label for="">E-mail:</label><input type="email" placeholder="Correo Electrónico" name="email" id = "g" required>
        </div>
        <div class="forms">
            <label for="">Teléfono:</label><input type="text" placeholder="Teléfono" name ="telefono" id = "h" pattern = "(\d\d){5,6}" required>
        </div>
        </div>

        <div class="rows">
        <div class="forms">
            <label for="">Ocupación:</label><input type="text" placeholder="Ocupación" name="ocupacion" id = "i" pattern = "([a-zA-ZÁáÉéÍíÓóÚúñÑ,]+\s*)+">
        </div>
        </div>

        <div class="rows">
        <div class="forms">
            <input type="reset" class = "limpiar" value="Limpiar" onclick = "reset()">
        </div>
        <div class="forms">
            <input type="submit" class ="validar" value="Enviar" name = "enter"> 
        </div>
        </div>
    </form>

    <script>
        function reset(){
            document.getElementById('a').value = '';
            document.getElementById('b').value = '';
            document.getElementById('c').value = '';
            document.getElementById('d').value = '';
            document.getElementById('e').value = '';
            document.getElementById('f').value = '';
            document.getElementById('g').value = '';
            document.getElementById('h').value = '';
            document.getElementById('i').value = '';
        }
    </script>

    <footer>
            CRYSWER &copy; 2021
    </footer>
</body>
</html>

