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

    include_once "../ajustes/validacion.php"; 
    $user = new User();
    try {
        $paciente = $user->obtenerPaciente($curp_paciente);
    }catch (PDOException $errCon){
        echo 'Error de conexión a la base: ' . $errCon->getMessage();
    }catch (Exception $ex){
        echo 'Ocurrió un error inesperado: ' . $ex->getMessage();
    }

    if (isset($_POST['enviar'])) {
        if(!empty($_POST['genero'])){
            $paciente['genero'] = $_POST['genero'];
        } 

        if(!empty($_POST['estado'])){
            $paciente['edo_civil'] = $_POST['estado'];
        }

        if(!empty($_POST['activo'])){
            if($_POST['activo'] == 'ACTIVO'){
                $paciente['activo'] = 1;
            } else{
                $paciente['activo'] = 0;
            }
        }

        $generarContra = ($_POST['contra'] == 'S') ? true : false;
        if($generarContra){
            $nuevaContra = substr(MD5(rand()), 0, 8);
            $paciente['pass'] = MD5($nuevaContra);
        }

        $err = $user->actualizarPaciente($curp_paciente, $paciente['pass'], strtoupper($_POST['nombre']), strtoupper($_POST['apellidoP']), strtoupper($_POST['apellidoM']), $paciente['genero'], strtoupper($_POST['direccion']), $_POST['nacimiento'], strtoupper($_POST['telefono']), $paciente['edo_civil'] , $_POST['correo'], strtoupper($_POST['trabajo']), $paciente['activo']);

        if (empty($err)) {
            if($generarContra){
                echo "<script>
                    alert('PACIENTE ACTUALIZADO CORRECTAMENTE. SU NUEVA CONTRASEÑA ES: ".$nuevaContra."');
                </script>";
            }else{
                echo "<script>
                    alert('PACIENTE ACTUALIZADO CORRECTAMENTE.');
                </script>";
            }

            echo "<script>
            window.location.href = 'pacientes.php';
            </script>";

        }else
            echo "<script>
            alert('NO FUE POSIBLE ACTUALIZAR DATOS');
            window.location.href = 'pacientes.php';
            </script>";
    }

}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrador - Actualizar datos de médico</title>
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
    <br><h2>Actualizar Datos</h2>
    
    <form method = "POST">

    <div class="rows">
        <div class="forms">
            <label for="">CURP:</label><input type="text" value = "<?php echo $paciente['curp_paciente']; ?>" <?php echo isset($_GET['curp']) ? 'disabled' : ''; ?> name="curp" id = "d" pattern = "[A-Z]{4}\d{6}(H|M)[A-Z]{5}([A-Z]|\d)\d" required>
        </div> 
    </div>    

    <div class="rows">
            <div class="forms">
                <label for="">Nombre(s):</label><input type="text" value = "<?php echo $paciente['nombre']; ?>" placeholder="Nombre(s)" name="nombre" id = "a" pattern = "([a-zA-ZÁáÉéÍíÓóÚúñÑ]{2,}\s*){1,3}" required>
            </div>
            <div class="forms">
                <label for="">Apellido Paterno:</label><input type="text" value="<?php echo $paciente['apellido_paterno']; ?>" placeholder="Apellido Paterno" id = "b" name="apellidoP" pattern = "([a-zA-ZÁáÉéÍíÓóÚúñÑ]{2,}\s*){1,3}" required>  
            </div>
            <div class="forms">
                <label for="">Apellido Materno:</label><input type="text" value="<?php echo $paciente['apellido_materno']; ?>" placeholder="Apellido Materno" id = "c" name="apellidoM" pattern = "([a-zA-ZÁáÉéÍíÓóÚúñÑ]{2,}\s*){1,3}" required>
            </div><br>
        </div>
    </div>

        <div class="rows">
            <div class="forms">
                <label for="">Género:</label><input type="text" name="generoActual" value="<?php echo $paciente['genero']; ?>" disabled >
            </div>
            
            <div class="forms">
                <label for="">Selecciona opción para cambiar:</label>
                <label for="">       </label>
                <label for="femenino">Femenino</label>
                <input type="radio" class="radiales" name="genero" value="FEMENINO">
                <label for="">       </label>
                <br><label for="masculino">  Masculino</label>
                <input type="radio" class="radiales" name="genero" value="MASCULINO" >
            </div>
        </div> 

        <div class="rows">
            <div class="forms">
                <label for="">Fecha de Nacimiento:</label><input type="date" value="<?php echo $paciente['f_nacimiento']; ?>" placeholder="Fecha de nacimiento" id = "e" name="nacimiento">
            </div>
            <div class="forms">
            <label for="">Dirección:</label><input type="text" value="<?php echo $paciente['direccion']; ?>" placeholder="Dirección" name="direccion" id = "f" pattern = "([a-zA-ZÁáÉéÍíÓóÚúñÑ,\d\.#]+\s*)+" required>
            </div>
            <div class="forms">
            <label for="">E-mail:</label><input type="email" value="<?php echo $paciente['e_mail']; ?>" placeholder="Correo Electrónico" name="correo" id = "g" required>
            </div>
            <div class="forms">
                <label for="">Teléfono:</label><input type="text" value="<?php echo $paciente['telefono']; ?>" placeholder="Teléfono" name ="telefono" id = "h" pattern = "(\d\d){5,6}" required>
            </div>

        </div><br>

        <div class="rows">
            <div class="forms">
                <label for="">Estado Civil:</label><input type ="text" name="estadoActual" value="<?php echo $paciente['edo_civil']; ?>" disabled >
            </div>
            <div class="forms">
                <label for="estadocivil">Seleccionar para cambiar:</label>
                <select name = "estado" id="estadocivil">
                    <option value = ""></option>
                    <option value = "SOLTERO(A)">Soltero(a)</option>
                    <option value = "CASADO(A)">Casado(a)</option>
                    <option value = "DIVORCIADO(A)">Divorciado(a)</option>
                    <option value = "VIUDO(A)">Viudo(a)</option>
                </select>
            </div>
        </div> 
        

        <div class="rows">
            <div class="forms">
                <label for="">Ocupación:</label><input type ="text" required name="trabajo" value="<?php echo $paciente['trabajo']; ?>" pattern = "([a-zA-ZÁáÉéÍíÓóÚúñÑ,]+\s*)+">
            </div>
            <div class="forms">
                <label for="">Estado:</label><input type ="text" name="estadoActual" value="<?php echo ($paciente['activo'] == 1) ? 'ACTIVO' : 'NO ACTIVO'; ?>" disabled >
            </div>
            <div class="forms">
                <label for="estadoA">Seleccione para cambiar:</label>
                <select name = "activo" id="estadoA">
                    <option value = ""></option>
                    <option value = "ACTIVO" >Activo</option>
                    <option value = "NOACTIVO" >No activo</option>
                </select>
            </div>
        </div>

        <div class="rows">
            <div class="forms">
                <label for="">¿Desea cambiar la contraseña?</label>
            </div>
            <div class="forms">
                <input type = "radio" name = "contra" value = 'S' >Sí
                <input type = "radio" name = "contra" value = 'N' >No
            </div>
        </div>

            <div class="rows">
                <div class="forms">
                    <input type="submit" class ="validar" value="Actualizar" name = "enviar"> 
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

        </body>
    </body>
    <footer>
        CRYSWER &copy; 2021
    </footer>
</html>
