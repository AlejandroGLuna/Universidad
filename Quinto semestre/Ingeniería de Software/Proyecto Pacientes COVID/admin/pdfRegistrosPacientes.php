<?php

class PDF_registroPacientes{

    public function PDF($pacientes, $totalPacientes,$paciente){
        include_once 'plantillaPDF.php';

        $pdf = new PDF('L','cm','legal');
        $pdf->AliasNbPages();
        $pdf->AddPage();

        $pdf->SetFont('Arial','B',10);
        $pdf->Cell(25);
        $pdf->Cell(3,2,'Total Pacientes Registrados: '.$totalPacientes,0,1,'L');

        $pdf->SetFillColor(232,232,232);
        $pdf->SetFont('Arial','B',9);
        $pdf->Cell(3.4,0.5,'CURP',1,0,'C',1);
        $pdf->Cell(4,0.5,'NOMBRE',1,0,'C',1);
        $pdf->Cell(4,0.5,'E-MAIL',1,0,'C',1); 
        $pdf->Cell(2.3,0.5,'TELEFONO',1,0,'C',1);  
        $pdf->Cell(2.8,0.5,'F. NACIMIENTO',1,0,'C',1);
        $pdf->Cell(2.5,0.5,'EDO. CIVIL',1,0,'C',1);
        $pdf->Cell(3,0.5,utf8_decode('OCUPACIÓN'),1,0,'C',1);
        $pdf->Cell(6,0.5,utf8_decode('DIRECCIÓN'),1,0,'C',1);
        $pdf->Cell(3.5,0.5,utf8_decode('MÉDICO REGISTRO'),1,0,'C',1);
        $pdf->Cell(2.5,0.5,'F. REGISTRO',1,1,'C',1); //SALTO DE LINIA

        $pdf->SetFont('Arial','',7);

        include_once '../ajustes/validacion.php';
        $user = new User();

        foreach ($pacientes as $row) {
             
            $pdf->Cell(3.4,2,$row['curp_paciente'],1,0,'C',0);
            $pdf->Cell(4,2,utf8_decode($row['nombre']),1,0,'C',0);
            $pdf->Cell(4,2,$row['e_mail'],1,0,'C',0); 
            $pdf->Cell(2.3,2,$row['telefono'],1,0,'C',0);  
            $pdf->Cell(2.8,2,$row['f_nacimiento'],1,0,'C',0);
            $pdf->Cell(2.5,2,utf8_decode($row['edo_civil']),1,0,'C',0);
            $pdf->Cell(3,2,utf8_decode($row['trabajo']),1,0,'C',0);
            $pdf->Cell(6,2,utf8_decode($row['direccion']),1,0,'C',0);
            $medico = $user->obtenerMedico($paciente['curp_medico']);
            $pdf->Cell(3.5,2,utf8_decode($medico['nombre']." ". $medico['apellido_paterno']),1,0,'C',0);
            $pdf->Cell(2.5,2,$row['f_registro'],1,1,'C',0);
        }
        $pdf->Output();
    }
}
?>