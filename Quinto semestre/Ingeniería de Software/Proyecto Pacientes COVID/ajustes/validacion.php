<?php

    require 'conexion.php';

    class User{

        private $nombre;
        private $username;

        public function usuarioExiste($user, $pass, $tipoUsuario){
            $md5pass = MD5($pass);
            $datos = [];
            try{
                $con = new Conexion();
                switch($tipoUsuario){
                    case '0':
                        $query = "SELECT * FROM administrador WHERE nombre = '{$user}' AND pass = '{$md5pass}'";
                        $datos = $con->db->query($query)->fetch(PDO::FETCH_ASSOC);
                        break;
                    
                    case '1':
                        $query = "SELECT * FROM medico WHERE curp_medico = '{$user}' AND pass = '{$md5pass}'";
                        $datos = $con->db->query($query)->fetch(PDO::FETCH_ASSOC);
                        break;

                    case '2':
                        $query = "SELECT * FROM paciente WHERE curp_paciente = '{$user}' AND pass = '{$md5pass}'";
                        $datos = $con->db->query($query)->fetch(PDO::FETCH_ASSOC);
                        break;
                }
            }catch(Exception $ex) {
                echo "Error no esperado: " . $ex->getMessage();
            }
  
            if(empty($datos)){
                return False;
            }else{
                return True;
            }
        }

        public function ponerUsuario($user, $tipoUsuario){
            try{
                $con = new Conexion();
                switch($tipoUsuario){
                    case '0':
                        $this->nombre = 'Administrador';
                        $this->username = "admin";
                        break;

                    case '1':
                        $query = "SELECT * FROM medico WHERE curp_medico = '{$user}'";
                        $datos = $con->db->query($query)->fetchAll(PDO::FETCH_ASSOC);

                        foreach($datos as $dato){
                            $this->nombre = $dato['nombre'];
                            $this->username = $dato['curp_medico'];
                        }
                        break;

                    case '2':
                        $query = "SELECT * FROM paciente WHERE curp_paciente = '{$user}'";
                        $datos = $con->db->query($query)->fetchAll(PDO::FETCH_ASSOC);

                        foreach($datos as $dato){
                            $this->nombre = $dato['nombre'];
                            $this->username = $dato['curp_paciente'];
                        }
                        break;
                }
                return $this->nombre;
            }catch(Exception $ex) {
                echo "Error no esperado: " . $ex->getMessage();
            }
        }

        public function insertarMedico($curp,$pass,$nombre,$apellido_paterno,$apellido_materno,$genero,$direccion,$f_nacimiento,$telefono,$edo_civil,$e_mail,$hospital,$turno){
            try{
                $conexion = new Conexion();
                $query = "INSERT INTO medico (curp_medico, pass, nombre, apellido_paterno, apellido_materno, genero, direccion,f_nacimiento,telefono,edo_civil,e_mail, hospital, turno) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                $stmt = $conexion->db->prepare($query);
                $exito = $stmt->execute([$curp,$pass,$nombre,$apellido_paterno,$apellido_materno,$genero,$direccion,$f_nacimiento,$telefono,$edo_civil,$e_mail,$hospital,$turno]);
                return "";

            }catch(PDOException $e) {
                if ($e->errorInfo[1] == 1062)
                    return "Usuasrio ya existe";
                else
                    return "Error no esperado en la conexión a la base de datos"; 
            }catch(Exception $ex){
                return "Error inesperado " . $ex->getMessage();
            }
        }

        public function seleccionarPacientesPorGenero($genero){
            try{
                $conexion = new Conexion();
                $query = "SELECT * FROM paciente WHERE genero = '{$genero}' AND activo = 1";
                $pacientes = $conexion->db->query($query)->fetchAll(PDO::FETCH_ASSOC);
    
            }catch(Exception $ex) {
                echo "Error no esperado: " . $ex->getMessage();
            }
            return $pacientes;
        }

        public function obtenerMedico($curp){
            try {
                $conexion = new Conexion();
                $query = "SELECT * FROM medico WHERE curp_medico = '{$curp}' AND activo = 1";
                $medico = $conexion->db->query($query)->fetch(PDO::FETCH_ASSOC);
            
            } catch (PDOException $errCon) {
                echo 'Error de conexión a la base: ' . $errCon->getMessage();
            } catch (Exception $ex) {
                echo 'Ocurrió un error inesperado: ' . $ex->getMessage();
            }
            return $medico;
        }

        public function seleccionarPacientesPorConsulta($consulta){
            try{
                $conexion = new Conexion();
                if($consulta == 'URGENCIAS'){
                    $query = "SELECT * FROM urgencias ";
                    $pacientes = $conexion->db->query($query)->fetchAll(PDO::FETCH_ASSOC);

                } elseif($consulta == 'CONSULTAEXTERNA'){
                    $query = "SELECT * FROM consulta_externa ";
                    $pacientes = $conexion->db->query($query)->fetchAll(PDO::FETCH_ASSOC);

                }
                
            }catch(Exception $ex) {
                echo "Error no esperado: " . $ex->getMessage();
            }
            return $pacientes;
        }

        public function obtenerEdad($fecha_nacimiento) {
            $fecha = new DateTime($fecha_nacimiento);
            $ahora = new DateTime();
            $diferencia = $ahora->diff($fecha);
            return $diferencia->y;
        }

        public function insertarPaciente($curp_paciente, $curp_medico, $pass,$nombre,$apellido_paterno,$apellido_materno,$genero,$direccion,$f_nacimiento,$telefono,$edo_civil,$e_mail,$trabajo){
            try{
                $con = new Conexion();
                $query = "INSERT INTO paciente (curp_paciente, curp_medico, pass, nombre, apellido_paterno, apellido_materno, genero, direccion, f_nacimiento, telefono, edo_civil, e_mail, trabajo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                $stmt = $con->db->prepare($query);
                $stmt->execute([$curp_paciente, $curp_medico, $pass, $nombre, $apellido_paterno, $apellido_materno, $genero, $direccion, $f_nacimiento, $telefono, $edo_civil, $e_mail, $trabajo]);
                return "";
            }catch(PDOException $e) {
                if ($e->errorInfo[1] == 1062)
                    return "Usuasrio ya existe";
                else
                    return "Error no esperado en la conexión a la base de datos"; 
            }catch(Exception $ex){
                return "Error inesperado " . $ex->getMessage();
            }
        }

        public function insertarPacienteEncuesta($hayRegistro, $curp_paciente, $curp_medico, $res_1, $res_2, $res_3, $res_4, $res_5, $res_6, $res_7, $total, $urgencias){
            try{
                $con = new Conexion();
                $query = "INSERT INTO encuesta (curp_paciente, curp_medico, res_fiebre, res_dolcabeza, res_respiracion, res_dolhuesos, res_cansancio, res_flujonasal, res_alergias, total_pts) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                $stmt = $con->db->prepare($query);
                $stmt->execute([$curp_paciente, $curp_medico, $res_1, $res_2, $res_3, $res_4, $res_5, $res_6, $res_7, $total]);
                
                if($hayRegistro){
                    $actualiza = "UPDATE paciente SET activo = 1 WHERE curp_paciente = ?";
                    $stmt = $con->db->prepare($actualiza);
                    $stmt->execute([$curp_paciente]);
                }
                
                if($urgencias)
                    $query = "INSERT INTO urgencias (curp_paciente) VALUES (?)";
                else
                    $query = "INSERT INTO consulta_externa (curp_paciente) VALUES (?)";

                $stmt = $con->db->prepare($query);
                $stmt->execute([$curp_paciente]);
                return "";
                
            }catch(PDOException $e) {
                if ($e->errorInfo[1] == 1062)
                    return "Registro ya existe";
                else
                    return "Error no esperado en la conexión a la base de datos"; 
            }catch(Exception $ex){
                return "Error inesperado " . $ex->getMessage();
            }
        }

        public function insertarPacienteTratamiento($curp_paciente, $medicamento, $dosis_tiempo, $descripcion){
            try{
                $con = new Conexion();
                $query = "INSERT INTO medicamento (curp_paciente, medicamentos, dosis_tiempo, descripcion) VALUES (?, ?, ?, ?)";
                $stmt = $con->db->prepare($query);
                if(empty($medicamento)){
                    $medicamento = NULL;
                    $dosis_tiempo = NULL;
                }
                
                if(empty($descripcion))
                    $descripcion = NULL;

                $stmt->execute([$curp_paciente, $medicamento, $dosis_tiempo, $descripcion]);

                return "";
            }catch(PDOException $e) {
                if ($e->errorInfo[1] == 1062)
                    return "Registro ya existe";
                else
                    return "Error no esperado en la conexión a la base de datos" . $e->getMessage(); 
            }catch(Exception $ex){
                return "Error inesperado " . $ex->getMessage();
            }    
        }

        public function insertarPacienteCita($curp_paciente, $curp_medico, $fechas, $consultorio){
            try{
                $con = new Conexion();
                $query = "INSERT INTO cita (curp_paciente, curp_medico, fecha, consultorios) VALUES (?, ?, ?, ?)";
                $stmt = $con->db->prepare($query);
                $stmt->execute([$curp_paciente, $curp_medico, $fechas, $consultorio]);
                
                return "";
            }catch(PDOException $e) {
                if ($e->errorInfo[1] == 1062)
                    return "Registro ya existe";
                else
                    return "Error no esperado en la conexión a la base de datos" . $e->getMessage(); 
            }catch(Exception $ex){
                return "Error inesperado " . $ex->getMessage();
            }   
        }

        public function obtenerPacientes($curp_medico){
            try{
                $con = new Conexion();
                $query = "SELECT * FROM paciente WHERE curp_medico = '{$curp_medico}' AND activo = 1 ORDER BY apellido_paterno DESC, apellido_materno DESC, nombre DESC ";
                $pacientes = $con->db->query($query)->fetchAll(PDO::FETCH_ASSOC);
                return $pacientes;
            }catch(Exception $ex){
                throw $ex;
            }   
        }

        public function obtenerPaciente($curp_paciente){
            try{
                $con = new Conexion();
                $query = "SELECT * FROM paciente WHERE curp_paciente = '{$curp_paciente}'";
                $paciente = $con->db->query($query)->fetch();
                return $paciente;
            }catch(Exception $ex){
                throw $ex;
            }   
        }

        public function obtenerEncuestaPaciente($curp_paciente){
            try{
                $con = new Conexion();
                $query = "SELECT * FROM encuesta WHERE curp_paciente = '{$curp_paciente}' ORDER BY id_encuesta DESC LIMIT 1";
                $encuesta = $con->db->query($query)->fetch();
                return $encuesta;
            }catch(Exception $ex){
                throw $ex;
            }   
        }

        public function obtenerTratamientoPaciente($curp_paciente){
            try{
                $con = new Conexion();
                $query = "SELECT * FROM medicamento WHERE curp_paciente = '{$curp_paciente}'";
                $tratamiento = $con->db->query($query)->fetch();
                return $tratamiento;
            }catch(Exception $ex){
                throw $ex;
            }   
        }

        public function obtenerCitasPaciente($curp_paciente){
            try{
                $con = new Conexion();
                $query = "SELECT * FROM cita WHERE curp_paciente = '{$curp_paciente}'";
                $citas = $con->db->query($query)->fetch();
                return $citas;
            }catch(Exception $ex){
                throw $ex;
            }   
        }

        public function actualizarPaciente($curp_paciente, $pass, $nombre, $apellidoP, $apellidoM, $genero, $direccion, $f_nacimiento, $telefono, $edo_civil, $e_mail, $trabajo, $activo){
            try{
                $con = new Conexion();
                $query = "UPDATE paciente SET pass = ?, nombre = ?, apellido_paterno = ?, apellido_materno = ?, genero = ?, direccion = ?, f_nacimiento = ?, telefono = ?, edo_civil = ?,
                    e_mail = ?, trabajo = ?, activo = ? WHERE curp_paciente = ?";
                $stmt = $con->db->prepare($query);
                $stmt->execute([$pass, $nombre, $apellidoP, $apellidoM, $genero, $direccion, $f_nacimiento, $telefono, $edo_civil, $e_mail, $trabajo, $activo, $curp_paciente]);
                return "";
            }catch(PDOException $e) {
                return "Error no esperado en la conexión a la base de datos" . $e->getMessage(); 
            }catch(Exception $ex){
                return "Error inesperado " . $ex->getMessage();
            }   
        }

        public function existePaciente($curp_paciente, $curp_medico){
            try{
                $con = new Conexion();
                $query = "SELECT * FROM paciente WHERE curp_paciente = '{$curp_paciente}' AND curp_medico = '{$curp_medico}'";
                $registro = $con->db->query($query)->fetch();
                if(!empty($registro))
                    return true;
                else
                    return false;
            }catch(Exception $ex){
                throw $ex;
            }   
        }
    }
?>