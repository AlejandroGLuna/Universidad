package vehiculos.terrestre;

import vehiculos.Vehiculo;

class Bicicleta extends VehiculoTerrestre{
	Bicicleta(String color, String material, byte capacidadPasajeros, boolean funciona, float velocidadActual, byte numLlantas){
		this.color=color;
		this.material=material;
		this.capacidadPasajeros=capacidadPasajeros;
		this.funciona=funciona;
		this.velocidadActual=velocidadActual;
		this.numLlantas=numLlantas;
	}

	void avanzar(){
		System.out.println("La bicla esta avanzando");
		this.velocidadActual+=20;
	}

	void retroceder(){
		System.out.println("La bicla esta retrocediendo");
		this.velocidadActual+=20;
	}

	void acelerar(){
		System.out.println("La bicla esta acelerado")
		while(this.velocidadActual<100){
			this.velocidadActual+=15;
		}
		System.out.println("La bicla ha llegado a su velocidad maxima");
	}
}

class Patineta extends VehiculoTerrestre{
	Patineta(String color, String material, byte capacidadPasajeros, boolean funciona, float velocidadActual, byte numLlantas){
		this.color=color;
		this.material=material;
		this.capacidadPasajeros=capacidadPasajeros;
		this.funciona=funciona;
		this.velocidadActual=velocidadActual;
		this.numLlantas=numLlantas;
	}

	void avanzar(){
		System.out.println("La patineta esta avanzando");
		this.velocidadActual+=20;
	}

	void retroceder(){
		System.out.println("La patineta esta retrocediendo");
		this.velocidadActual+=20;
	}

	void acelerar(){
		System.out.println("La patineta esta acelerado")
		while(this.velocidadActual<100){
			this.velocidadActual+=15;
		}
		System.out.println("La patineta ha llegado a su velocidad maxima");
	}
}

