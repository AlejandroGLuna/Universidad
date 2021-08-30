package medioTransporte;


abstract class VehiculoAcuatico extends Vehiculo{
	private String tipoEmbarcacion;
	private byte numVelas;

	void arribar(){
		System.out.println("El vehiculo ha llegado a su destino");
		this.detenerse();
	}

	void hundirse(){
		System.out.println("El vehiculo acuatico se ha hundido");
		this.descomponerse();
	}
}

abstract class VehiculoTerrestre extends Vehiculo{
	private byte numLlantas;
	
	void chocar(){
		System.out.println("El vehiculo terrestre ha chocado");
		this.descomponerse();
	}

	void atascarse(){
		System.out.println("El vehiculo terrestre se ha atascado");
	}

	void llegarDestino(){
		System.out.println("El vehiculo terrestre ha llegado a su destino");
		this.detenerse();
	}
}

abstract class VehiculoAereo extends Vehiculo{
	private String tipoVuelo;
	private float alturaMaximaPosible;

	void derribarse(){
		System.out.println("El vehiculo aereo se ha derribado");
		this.descomponerse();
	}

	void elevarse(){
		System.out.println("El vehiculo aereo se esta elevando");
	}

	void despegar(){
		System.out.println("El vehiculo aereo esta despegando");
	}

	void aterrizar(){
		System.out.println("El vehiculo ha aterrizado");
		this.detenerse();
	}
}