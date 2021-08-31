<?php

require '../ajustes/conexion.php';

$mensaje = '';

$datos = [];
if (isset($_GET['curp'])) {
    $conexion = new Conexion();
    $datos = $conexion->db->query("SELECT * FROM medico WHERE curp_medico = '{$_GET['curp']}'")->fetch(PDO::FETCH_ASSOC);                                                                                
    if (empty($datos)) {
        header("Location: consultaMedicos.php");
        exit;
    }
}

if (isset($_POST['enter'])) {
    try {
        $conexion = new Conexion();

        if (isset($_GET['curp'])) {
            $query = "UPDATE medico SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, pass = ?, genero = ?, direccion = ?,f_nacimiento = ?,telefono = ?,edo_civil = ?,
                    e_mail = ?, hospital = ?, turno = ?, activo = ? WHERE curp_medico = ?";

            if(!empty($_POST['genero'])){
                $datos['genero'] = $_POST['genero'];
            } 
            if(!empty($_POST['turno'])){
                $datos['turno'] = $_POST['turno'];
            }
            if(!empty($_POST['sede'])){
                $datos['hospital'] = $_POST['sede'];
            }
            if(!empty($_POST['estado'])){
                $datos['edo_civil'] = $_POST['estado'];
            }
            if(!empty($_POST['activo'])){
                if($_POST['activo'] == 'activo'){
                    $datos['activo'] = 1;
                } else{
                    $datos['activo'] = 0;
                }
            }

            $generarContra = ($_POST['contra'] == 'S') ? true : false;
            if($generarContra){
                $nuevaContra = substr(MD5(rand()), 0, 8);
                $datos['pass'] = MD5($nuevaContra);
            }    

            $stmt = $conexion->db->prepare($query);
            $exito = $stmt->execute([$_POST['nombre'],$_POST['apellidoP'],$_POST['apellidoM'], $datos['pass'],$datos['genero'],$_POST['direccion'],$_POST['nacimiento'],$_POST['telefono'],
                $datos['edo_civil'],$_POST['correo'],$datos['hospital'],$datos['turno'],$datos['activo'], $_GET['curp']]);
        }
        if (!$exito) {
            echo "<script>
                alert('NO FUE POSIBLE ACTUALIZAR DATOS');
                </script>";
        } else {
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
            window.location.href = 'consultaMedicos.php';
            </script>";
        }
    } catch (Exception $ex) {
        $mensaje = "Error no esperado: " . $ex->getMessage();
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
                    <a href='inicioAdmin.php'>Inicio</a>
                </li>

                <li>
                    <a href="../ajustes/logout.php" class="boton" id="salir">Salir</a>
                </li>
            </ul>
        </div>

    <br><h2>Administrador</h2>
    <br><h2>Datos del médico</h2>
    
    <form method = "POST">

    <div class="rows">
        <div class="forms">
            <label for="">CURP:</label><input type="text" value = "<?php echo $datos['curp_medico']; ?>" <?php echo isset($_GET['curp']) ? 'disabled' : ''; ?> name="curp" id = "d" pattern = "[A-Z]{4}\d{6}(H|M)[A-Z]{5}([A-Z]|\d)\d" required>
        </div> 
    </div>    

    <div class="rows">
            <div class="forms">
                <label for="">Nombre(s):</label><input type="text" value = "<?php echo $datos['nombre']; ?>" placeholder="Nombre(s)" name="nombre" id = "a" pattern = "([a-zA-ZÁáÉéÍíÓóÚúñÑ]{2,}\s*){1,3}" required>
            </div>
            <div class="forms">
                <label for="">Apellido Paterno:</label><input type="text" value="<?php echo $datos['apellido_paterno']; ?>" placeholder="Apellido Paterno" id = "b" name="apellidoP" pattern = "([a-zA-ZÁáÉéÍíÓóÚúñÑ]{2,}\s*){1,3}" required>  
            </div>
            <div class="forms">
                <label for="">Apellido Materno:</label><input type="text" value="<?php echo $datos['apellido_materno']; ?>" placeholder="Apellido Materno" id = "c" name="apellidoM" pattern = "([a-zA-ZÁáÉéÍíÓóÚúñÑ]{2,}\s*){1,3}" required>
            </div><br>
        </div>
    </div>

        <div class="rows">
            <div class="forms">
                <label for="">Género:</label><input type="text" name="generoActual" value="<?php echo $datos['genero']; ?>" disabled >
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
                <label for="">Fecha de Nacimiento:</label><input type="date" value="<?php echo $datos['f_nacimiento']; ?>" placeholder="Fecha de nacimiento" id = "e" name="nacimiento">
            </div>
            <div class="forms">
            <label for="">Dirección:</label><input type="text" value="<?php echo $datos['direccion']; ?>" placeholder="Dirección" name="direccion" id = "f" pattern = "([a-zA-ZÁáÉéÍíÓóÚúñÑ,\d\.#]+\s*)+" required>
            </div>
            <div class="forms">
            <label for="">E-mail:</label><input type="email" value="<?php echo $datos['e_mail']; ?>" placeholder="Correo Electrónico" name="correo" id = "g" required>
            </div>
             <div class="forms">
                <label for="">Teléfono:</label><input type="text" value="<?php echo $datos['telefono']; ?>" placeholder="Teléfono" name ="telefono" id = "h" pattern = "(\d\d){5,6}" required>
            </div>

        </div><br>

        <div class="rows">
            <div class="forms">
                <label for="">Estado Civil:</label><input type ="text" name="estadoActual" value="<?php echo $datos['edo_civil']; ?>" disabled >
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
                <label for="">Turno:</label><input type ="text" name="turnoActual" value="<?php echo $datos['turno']; ?>" disabled >
            </div>
            <div class="forms">
                <label for="">Selecciona opción para cambiar:</label>
                <label for="">       </label>
                <label for="matutino">Matutino</label>
                <input type="radio" class="radiales" name="turno" value="MATUTINO" >
                <label for="">       </label>
                <br><label for="vespertino">Vespertino</label>
                <input type="radio" class="radiales" name="turno" value="VESPERTINO" >
            </div>
        </div>

        <div class="rows">
            <div class="forms">
                <label for="">Hospital de trabajo:</label><input type ="text" name="turnoActual" value="<?php echo $datos['hospital']; ?>" disabled >
            </div>
            <div class="forms">
                <label for="hospital">Seleccione para cambiar:</label>
                <select name = "sede" id="hospital">
                    <option value = ""></option>
                    <option value = "CENTRAL NORTE" >Central Norte</option>
                    <option value = "CENTRAL SUR">Central Sur</option>
                    <option value = "CENTRAL ESTE">Central Este</option>
                    <option value = "CENTRAL OESTE">Central Oeste</option>
                </select>
            </div>

            <div class="forms">
                <label for="">Estado:</label><input type ="text" name="estadoActual" value="<?php echo ($datos['activo'] == 1) ? 'ACTIVO' : 'NO ACTIVO'; ?>" disabled >
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
                    <input type="submit" class ="validar" value="Actualizar" name = "enter"> 
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

