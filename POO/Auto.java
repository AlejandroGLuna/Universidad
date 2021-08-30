public class Auto{
	boolean funciona;
	private boolean encendido;
	private String marca;
	private String transmision;
	private byte capacidadPersonas;
	private float capacidadGasolina;
	private float velocidad;
	private byte nivelGasolina;

	Auto(String marca, String transmision, byte capacidadPersonas, float capacidadGasolina, byte nivelGasolina){
		this.funciona=true;
		this.encendido=false;
		this.marca=marca;
		this.transmision=transmision;
		this.capacidadPersonas=capacidadPersonas;
		this.capacidadGasolina=capacidadGasolina;
		this.nivelGasolina=nivelGasolina;
		this.velocidad=0f;
	}

	Auto(){}

	void encender(){
		if(!this.encendido && this.nivelGasolina>0){
			System.out.println("Se esta encendiendo el coche");
			this.encendido=true;
			System.out.println("Funcionamiento del motor: "+this.funciona+
								"\nNivel de gasolina: "+this.nivelGasolina);
		}else if(this.encendido)
			System.out.println("El coche ya estaba encendido");
		else
			System.out.println("");
	}

	void apagar(){
		if(this.encendido){
			System.out.println("Se esta apagando el coche");
			this.encendido=false;
			System.out.println("Funcionamiento del motor: "+this.funciona+
								"\nNivel de gasolina: "+this.nivelGasolina);
		}else
			System.out.println("El coche ya estaba apagado");
	}

	void acelerar(float velocidadMaxima){
		try{
			if(this.encendido && this.velocidad<velocidadMaxima && this.nivelGasolina>10){
					while(this.velocidad<velocidadMaxima){
						this.velocidad+=8;
						System.out.println("La velocidad actual es: "+this.velocidad);
						this.nivelGasolina-=2;
						if(this.nivelGasolina <= 10)
							throw new OutOfGasolineException("El coche se esta quedando sin gasolina");
					}
			}else if(this.nivelGasolina <= 10)
				throw new OutOfGasolineException("El coche no tiene gasolina suficiente para acelerar");
			}catch(OutOfGasolineException eOGE){
				System.out.println(eOGE.getMessage());
				this.apagar();
			}
	}
}

class OutOfGasolineException extends Throwable{
	OutOfGasolineException(String msj){
		super(msj);
	}
}

class Principal{
	public static void main(String[] args) {
		Auto magic = new Auto("Volkswagen","Buena",(byte)4,150f,(byte)50);
		magic.encender();
		magic.acelerar(200f);
		magic.apagar(); 
	}
}