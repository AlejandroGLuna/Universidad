<?php

require_once '../fpdf/fpdf.php';

class PDF extends FPDF{
    public function header(){
        $this->SetFillColor(109, 202, 221);
        $this->Rect(0,1,36,2,'F');
        $this->image('../images/logo.jpeg', 0.5, 0.5, 3);
        $this->Setfont('Arial','B',15);
        $this->Cell(16);
        $this->Cell(1.3, 2,' REPORTE DE REGISTROS', 0, 0, 'C');
        $this->Ln(2);
    }

    public function Footer(){
        $this->SetY(-1);
        $this->SetFont('Arial','I', 8);
        $this->Cell(0, 0, 'Pagina '.$this->PageNo().'/{nb}', 0, 0,'R');

        $this->SetY(-1);
        $this->SetFont('Arial','I', 8);
        $this->Cell(0, 0, 'Generado el: '.date('j').'/'.date('m').'/'.date('Y'), 0, 0,'L');
    }


}