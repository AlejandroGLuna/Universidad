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
            if ($_SESSION['previo'] != "encuesta"){
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
        $medicamentos = $_POST['varM'];
        $tratamientos = $_POST['varT'];
        $descripcion = $_POST['varD'];
        
        $err = $user->insertarPacienteTratamiento($_SESSION['curp_p'], $medicamentos, $tratamientos, $descripcion);

        if(empty($err)){
            $_SESSION['previo'] = "tratamiento";
            echo "<script>
                alert('TRATAMIENTO REGISTRADO CON EXITO');
                window.location.href = 'citas.php';
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
    <title>Tratamiento</title>
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
    <br><h2>Tratamiento</h2>
    <form method = "POST">
    <center>
        <div class="forms">
            <p>Recetar medicamento:</p>
            <input type = "radio" name = "opcion" value = '0' onclick = "validate()" required>Sí
            <input type = "radio" name = "opcion" value = '1' onclick = "cancel()">No
        </div>
        <div class="forms">
            <p>Medicamento: </p>
            <input type ="text" name="medicamento" id="m" pattern = "[a-zA-Z\d\.\s,\-ÁáÉéÍíÓóÚúñÑ\\]+" disabled>
        </div>
        <div class="forms">
            <p>Dosis/Tratamiento:</p>
            <textarea name = "tratamiento" id="t" rows="4" cols="50" pattern = "[a-zA-Z\d\.\s,\-ÁáÉéÍíÓóÚúñÑ\\]+"></textarea>
        </div>

        <div class="rows">
        <div class="forms">
            <button class="limpiar" type="reset">Limpiar</button>
        </div>
        <div class="forms">
            <button type = "button" class="validar" onclick = "cambiarReceta()">Siguiente</button>
        </div>
        </div><br>
        
        <div class="forms">
        <table class = "default" id = "receta">
            <tr>
                <th>Medicamento</th>
                <th>Tratamiento</th>
            </tr>
        </table>
        </div>

        <div class="rows">
            <div class="forms">
                <button type = "button" class = "limpiar" onclick = "eliminarFila('receta')">Borrar último medicamento</button>
            </div>
        </div>

        <table class = "default" id = "receta2">
            <tr>
                <th>Indicaciones</th>
            </tr>
        </table>

        <div class="rows">
            <div class="forms">
                <button type = "button"  class = "limpiar" name="borrar" onclick = "eliminarFila('receta2')">Borrar última indicación</button>
            </div>
        </div><br>

        <div class="forms">
            <input type="submit" class = "validar" name="enter" value="Continuar" onclick = "guardarVariables()">
        </div>

        <input type="text" style="display:none" id="medicinasH" name = "varM">
        <input type="text" style="display:none" id="tratamientosH" name = "varT">
        <input type="text" style="display:none" id="descripcionH" name = "varD">
    </center>
    </form>
</body>

    <footer>
            CRYSWER &copy; 2021
    </footer>

    <script>
        var medicinas = [];
        var tratamientos = [];
        var descripcion = []; 

        function validate(){
            document.getElementById("m").disabled=false;
        }

        function cancel(){
            document.getElementById("m").disabled=true;
            document.getElementById("m").value = '';
        }

        function cambiarReceta(){
            var medicina = document.getElementById("m").value;
            var tratamiento = document.getElementById("t").value;

            if(tratamiento.length == 0)
                return;

            if(medicina.length > 0){
                var tabla = document.getElementById("receta");
                var fila = tabla.insertRow();

                var celda1 = fila.insertCell();
                var celda2 = fila.insertCell();

                var medicamento = document.createTextNode(medicina);
                var dosis = document.createTextNode(tratamiento);

                celda1.appendChild(medicamento);
                celda2.appendChild(dosis);

                medicinas.push(medicina);
                tratamientos.push(tratamiento);
            }else{
                var tabla = document.getElementById("receta2");
                var fila = tabla.insertRow();

                var celda1 = fila.insertCell();

                var indicaciones = document.createTextNode(tratamiento);

                celda1.appendChild(indicaciones);

                descripcion.push(tratamiento);
            }

            document.getElementById("m").value = '';
            document.getElementById("t").value = '';
        }

        function eliminarFila(tabla){
            if (descripcion.length > 0){
                descripcion.pop();
                document.getElementById(tabla).deleteRow(-1);
            }else if(medicinas.length > 0){
                medicinas.pop();
                tratamientos.pop();
                document.getElementById(tabla).deleteRow(-1);
            }
        }

        function guardarVariables(){
            var x = medicinas.join('@');
            document.getElementById("medicinasH").value = x;

            var y = tratamientos.join('@');
            document.getElementById("tratamientosH").value = y;

            var z = descripcion.join('@');
            document.getElementById("descripcionH").value = z;
        }
    </script>

</html>
