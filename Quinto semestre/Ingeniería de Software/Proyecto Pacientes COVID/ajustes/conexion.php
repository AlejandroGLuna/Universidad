<?php

class Conexion {

    public $db;

    public function __construct() {
        try {
            $this->db = new PDO('mysql:host=localhost;port=3306;dbname=ghdemexi_pacientes_covid;charset=utf8', 'ghdemexi_admin', '4sdQ4JJ2aTYXg9X');
        }catch (PDOException $errCon) {
            echo 'Error de conexión a la base: ' . $errCon->getMessage();
        }
    }
}
?>