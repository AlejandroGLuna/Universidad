<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Grupo Hospitalario de México</title>
    <link rel="stylesheet" href="styles/menu.css">
    <link rel="stylesheet" href="styles/estilos.css">

</head>

<body class>
    
    <div id='barra-superior'>
        <ul class='menu'>
            <li>   
                <a href='index.php'>Inicio</a>
            </li>
            <li>   
                <a href="#miModal">Información</a>
                    <div id="miModal" class="modal">
                        <div class="modal-contenido">
                            <a href="#">X</a>
                            <h2>Información</h2>
                            <br>
                            <p>Este sistema fue realizado por el equipo
                                de Cryswer, conformado por:
                            </p>
                            <br>
                            <p>Gómez Luna Alejandro</p><br>
                            <p>Ortíz Camacho Jessica Elizabeth</p><br>
                            <p>Pardo Reyna Anelissa Allizon</p><br>
                            <p>Rodríguez Díaz David</p><br>
                            <p>Velarde Valencia Paulina</p>
                        </div>  
                    </div>
            </li>
            <li>
                <a href="#pop" class="boton" id=ingresar>Iniciar sesión</a>
                    <div class="modal" id="pop">
                        <div class="popup">
                            <a href="#" id="x" onclick = "return resetError()">X</a>
                            <div class="login">
                                <h2>Inicia sesión</h2>
                                <form method="POST" id="log">
                                    <p id = 'error'><?php if (isset($errorLogin) && !empty($errorLogin))echo $errorLogin; ?></p>
                                    <input type="text" placeholder="Nombre de usuario" name = "usuario">
                                    <input type="password" placeholder="Contraseña" name = "contra">
                                    <p><input type = "radio" id = "admin" name = "tipoUsuario" value = '0'> Administrador </p>
                                    <p><input type = "radio" id = "medico" name = "tipoUsuario" value = '1'> Médico </p>
                                    <p><input type = "radio" id = "paciente" name = "tipoUsuario" value = '2' checked> Paciente </p>
                                    <input type="submit" value="Aceptar" name = "enter"> 
                                </form>
                            </div>
                        </div>
                        
                    </div>
            </li>
            <script>
                function resetError(){
                    document.getElementById('error').innerHTML = ''
                    return true;
                }
            </script>
            <li>
            <a href="#contacto">Contacto</a>
                <div id="contacto" class="modal">
                    <div class="modal-contenido">
                        <a href="#">X</a>
                        <h2>Más información</h2>
                        <br>
                        <p>Para mayor información escribr al siguiente correo:</p>
                        <br>
                        <p>contato@ghdemexico.com.mx</p>
                        <br>
                        <p>Gracias por su atención.</p>
                    </div>  
                </div>
            </li>
        </ul>
    </div >
    <br><br><br>
    <h1>
        Objetivo
    </h1>

    <div id="bloque1">
        <br>
        <p>El objetivo del Grupo Hospitalario de México es brindar 
        el mejor servicio posible hacia todas aquellas personas 
        que requieran de servicios como
        </p>
        <br>
        <ul id="lis">
            <p>Atención médica urgente</p>  
            <p>Prueba de COVID-19</p>
            <p>Consulta médica</p>
        </ul>
        <br>
        <p>
        Estamos comprometidos contigo para brindarte el mayor 
        apoyo posible desde donde quiera que te encuentres, por 
        lo que hemos creado este sistema que nos permitirá 
        llevar una mejor gestión de los pacientes, para que 
        estos puedan consultar sus tratamientos y citas de una 
        manera eficaz. Asimismo, los médicos tendrán la capacidad 
        de registrar a los pacientes y, mediante una encuesta, 
        optimizar el lugar a donde se canalizarán los pacientes.
        </p>

    </div>
    <footer>
            CRYSWER &copy; 2021
    </footer>
</body>
</html>

