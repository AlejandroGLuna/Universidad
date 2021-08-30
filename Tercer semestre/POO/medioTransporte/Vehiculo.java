import java.util.Scanner;
/**
	*Esta es la clase abstracta vehiculo, la cual contiene las caracteristicas generales de un vehiculo de transporte.
	*Se comenta esta clase unicamente, ya que es la unica que es mas general y contiene a las demas.
	*@author Gomez Luna Alejandro, Katagiri Buentello Sergio Takao, Rodriguez Diaz David, Zamora Ramirez Oswaldo Rafael
	*@version 21.09.2019
*/
abstract public class Vehiculo{
	/**
		*El color que tendra el vehiculo.
	*/
	protected String color;

	/**
		*El material del que esta hecho el vehiculo.
	*/
	protected String material;

	/**
		*La capacidad de pasajeros que puede transportar el vehiculo.
	*/
	protected short capacidadPasajeros;

	/**
		*Si funciona o no el vehiculo.
	*/
	protected boolean funciona;

	/**
		*La velocidad actual del vehiculo.
	*/
	protected float velocidadActual;

	/**
		*Metodo abstracto que implementa cada clase que hereda de esta, dependiendo de que tipo de vehiculo es el que esta avanzando.
		*Sin embargo, se mantiene la caracteristica fundamental de que cualquier vehiculo puede avanzar.
	*/
	abstract protected void avanzar();

	/**
		*Este metodo no es abstracto, ya que todos los vehiculos se detienen de igual forma y su velocidad actual pasa a ser cero.
	*/
	protected void detenerse(){
		System.out.println("El vehiculo se ha detenido");
		this.velocidadActual=0f;
	}
	
	/**
		*Metodo abstracto que implementa cada clase que hereda de esta, dependiendo de que tipo de vehiculo es el que esta retrocediendo. 
		*Sin embargo, se mantiene la caracteristica fundamental de que cualquier vehiculo puede retroceder.
	*/
	abstract protected void retroceder();

	/**
		*Metodo abstracto que implementa cada clase que hereda de esta, dependiendo de que tipo de vehiculo es el que esta acelerando.
		*Sin embargo, se mantiene la caracteristica fundamental de que cualquier vehiculo puede acelerar.
	*/
	abstract protected void acelerar();

	/**
		*Este metodo no es abstracto, ya que todos los vehiculos se descomponen, provocando que su variable de si funciona sea falsa.
	*/
	protected void descomponerse(){
		System.out.println("El vehiculo se ha descompuesto");
		this.funciona=false;
	}
}

/*abstract public class VehiculoAcuatico extends Vehiculo{
	protected String tipoEmbarcacion;
	protected byte numVelas;

	void arribar(){
		System.out.println("El vehiculo ha llegado a su destino");
		this.detenerse();
	}

	void hundirse(){
		System.out.println("El vehiculo acuatico se ha hundido");
		this.descomponerse();
	}
}

abstract public class VehiculoTerrestre extends Vehiculo{
	protected byte numLlantas;
	
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

abstract public class VehiculoAereo extends Vehiculo{
	protected String tipoVuelo;
	protected float alturaMaximaPosible;

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

///////////////////////////////////////////////////////
public class Barco extends VehiculoAcuatico{
	public short numBotesSalvaVidas;
	public String clase;
	public short vueltasTimon;

	Barco(String color, String material, short capacidadPasajeros, boolean funciona, float velocidadActual, String tipoEmbarcacion, byte numVelas,short numBotesSalvaVidas, String clase, short vueltasTimon){
		this.color=color;
		this.material=material;
		this.capacidadPasajeros=capacidadPasajeros;
		this.funciona=funciona;
		this.velocidadActual=velocidadActual;
		this.tipoEmbarcacion=tipoEmbarcacion;
		this.numVelas=numVelas;
		this.numBotesSalvaVidas=numBotesSalvaVidas;
		this.clase=clase;
		this.vueltasTimon=vueltasTimon;
	}

	public void virar(){
		int vuelta;
		float grados;
		Scanner sc = new Scanner(System.in);
		if(velocidadActual > 0 ){
			System.out.println("Si quieres virar hacia la derecha presiona 1, si quieres virar hacia la derecha presiona 2");
			vuelta = sc.nextInt();
			System.out.println("cuantos grados quieres virar?");
			grados = sc.nextFloat();
			if(vuelta == 1){
				System.out.println("Se esta virando hacia la derecha en "+grados+" grados");
				velocidadActual--;
			}
			else{
				System.out.println("Se esta virando hacia la izquierda en "+grados+" grados");
				velocidadActual--;
			}
		}
		else{
			System.out.println("Se necesita incrementar la velocidad");
		}
	}
	public void emergencia(){
		int capacidad = capacidadPasajeros/numBotesSalvaVidas;
		System.out.println("Mujeres y ninios primero, suban a los botes en grupos de "+capacidad);
	}
	public void avanzar(){
        System.out.println("El Barco esta Avanzando");
    }

    public void retroceder(){
        System.out.println("El Barco esta retrocediendo");
    }

    public void acelerar(){
        System.out.println("El Barco esta Acelerando");
    }
}

public class Submarino extends VehiculoAcuatico{
	public short numeroMisiles;
	public float profundidadMaxima;
	public float profundidadActual;

	Submarino(String color, String material, short capacidadPasajeros, boolean funciona, float velocidadActual, String tipoEmbarcacion, byte numVelas, short numeroMisiles, float profundidadMaxima, float profundidadActual){
		this.color=color;
		this.material=material;
		this.capacidadPasajeros=capacidadPasajeros;
		this.funciona=funciona;
		this.velocidadActual=velocidadActual;
		this.tipoEmbarcacion=tipoEmbarcacion;
		this.numVelas=numVelas;
		this.numeroMisiles=numeroMisiles;
		this.profundidadMaxima=profundidadMaxima;
		this.profundidadActual=profundidadActual;
	}

	public void disparar(int numeroMisiles){
		if(numeroMisiles>0){
			System.out.println("Disparando");
		}
	}
	public void sumerger(float profundidadMaxima, float profundidadActual){

		if(profundidadMaxima> profundidadActual){
			System.out.println("Sumergiendo");
			for( int i = (int)profundidadActual; i < profundidadMaxima; i--);
		}
	}
	public void avanzar(){
        System.out.println("El Submarino esta Avanzando");
    }

    public void retroceder(){
        System.out.println("El Submarino esta retrocediendo");
    }

    public void acelerar(){
        System.out.println("El Submarino esta Acelerando");
    }
}



//////////////////////////////////////////////
public class Helicoptero extends VehiculoAereo{
    public byte numHelices;

    Helicoptero(String color, String material, short capacidadPasajeros, boolean funciona, float velocidadActual, String tipoVuelo, float alturaMaximaPosible, byte numHelices){
		this.color=color;
		this.material=material;
		this.capacidadPasajeros=capacidadPasajeros;
		this.funciona=funciona;
		this.velocidadActual=velocidadActual;
		this.tipoVuelo=tipoVuelo;
		this.alturaMaximaPosible=alturaMaximaPosible;
		this.numHelices=numHelices;
	}

    public void avanzar(){
        System.out.println("El Helicoptero esta Avanzando");
    }

    public void retroceder(){
        System.out.println("El Helicoptero esta retrocediendo");
    }

    public void acelerar(){
        System.out.println("El Helicoptero esta Acelerando");
    }
}

public class Avion extends VehiculoAereo{
    public String aereolinea;
    public float costoBoleto;

    Avion(String color, String material, short capacidadPasajeros, boolean funciona, float velocidadActual, String tipoVuelo, float alturaMaximaPosible, String aereolinea, float costoBoleto){
		this.color=color;
		this.material=material;
		this.capacidadPasajeros=capacidadPasajeros;
		this.funciona=funciona;
		this.velocidadActual=velocidadActual;
		this.tipoVuelo=tipoVuelo;
		this.alturaMaximaPosible=alturaMaximaPosible;
		this.aereolinea=aereolinea;
		this.costoBoleto=costoBoleto;
	}

    public void avanzar(){
        System.out.println("El Avion esta Avanzando");
    }

    public void retroceder(){
        System.out.println("El Avion esta retrocediendo");
    }

    public void acelerar(){
        System.out.println("El Avion esta Acelerando");
    }

}

public class GloboAereostatico extends VehiculoAereo{
    public float pesoSoportado;

    GloboAereostatico(String color, String material, short capacidadPasajeros, boolean funciona, float velocidadActual, String tipoVuelo, float alturaMaximaPosible, float pesoSoportado){
		this.color=color;
		this.material=material;
		this.capacidadPasajeros=capacidadPasajeros;
		this.funciona=funciona;
		this.velocidadActual=velocidadActual;
		this.tipoVuelo=tipoVuelo;
		this.alturaMaximaPosible=alturaMaximaPosible;
		this.pesoSoportado=pesoSoportado;
	}

    public void avanzar(){
        System.out.println("El Globo Aereostatico esta Avanzando");
    }

    public void retroceder(){
        System.out.println("El Globo Aereostatico esta retrocediendo");
    }

    public void acelerar(){
        System.out.println("El Globo Aereostatico esta Acelerando");
    }
}

public class Bicicleta extends VehiculoTerrestre{
	Bicicleta(String color, String material, short capacidadPasajeros, boolean funciona, float velocidadActual, byte numLlantas){
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
		System.out.println("La bicla esta acelerado");
		while(this.velocidadActual<100){
			this.velocidadActual+=15;
		}
		System.out.println("La bicla ha llegado a su velocidad maxima");
	}
}

public class Patineta extends VehiculoTerrestre{
	Patineta(String color, String material, short capacidadPasajeros, boolean funciona, float velocidadActual, byte numLlantas){
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
		System.out.println("La patineta esta acelerado");
		while(this.velocidadActual<100){
			this.velocidadActual+=15;
		}
		System.out.println("La patineta ha llegado a su velocidad maxima");
	}
}

public class Principal{
	//Avion(String color, String material, short capacidadPasajeros, boolean funciona, float velocidadActual, String tipoVuelo, float alturaMaximaPosible, String aereolinea, float costoBoleto)
	Avion thomas = new Avion("Azul con blanco","Acero",(short)300,true,1500f,"Vuelo Comercial",10000f, "MagniCharters",2470.79f);
	//Helicoptero(String color, String material, short capacidadPasajeros, boolean funciona, float velocidadActual, String tipoVuelo, float alturaMaximaPosible, byte numHelices)
	Helicoptero condorito = new Helicoptero("Cromo","Titanio",(short)6,true,2300f,"Militar", 12500.9f,(byte)3);
	//Bicicleta(String color, String material, short capacidadPasajeros, boolean funciona, float velocidadActual, byte numLlantas)
	Bicicleta yamaha = new Bicicleta("Roja","Aluminio",(short)2,true,0f,(byte)2);
	//Patineta(String color, String material, short capacidadPasajeros, boolean funciona, float velocidadActual, byte numLlantas)
	Patineta braille = new Patineta("Claro","Roble",(short)1,true, 0f,(byte)4);
	//Barco(String color, String material, short capacidadPasajeros, boolean funciona, float velocidadActual, String tipoEmbarcacion, byte numVelas,short numBotesSalvaVidas, String clase, short vueltasTimon)
	Barco turiBoat = new Barco("Grisaceo","Plata y Madera",(short)500,true,120.35f,"Turistica",(byte)0,(short)46,"Premiere",(byte)10);
	//Submarino(String color, String material, short capacidadPasajeros, boolean funciona, float velocidadActual, String tipoEmbarcacion, byte numVelas, short numeroMisiles, float profundidadMaxima, float profundidadActual)
	Submarino yellowSubmarine = new Submarino("Amarillo","Material Indestructible",(short)300,true,200.222f,"Imaginativa",(byte)0,(byte)0,10000.4332f,100.23f);
}*/