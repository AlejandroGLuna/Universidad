import java.util.LinkedList;

class Persona{
	private String nombre;
	private byte edad;
	private float peso;
	private float talla;
	private boolean vivo;

	private void morir(){
		this.vivo=false;
	}

	Persona(String nombre, byte edad, float peso, float talla, boolean vivo){
		this.nombre=nombre;
		this.edad=edad;
		this.peso=peso;
		this.talla=talla;
		this.vivo=vivo;
	}

	public String getNombre(){
		return this.nombre;
	}

	public void setVida(boolean vivo){
		this.vivo=vivo;
	}
}

class Doctor extends Persona{
	String especialidad;
	long cedulaProfesional;
	int aniosExperiencia;
	LinkedList<Paciente> listaPacientes=new LinkedList<Paciente>();

	Doctor(String nombre, byte edad, float peso, float talla, boolean vivo, String especialidad, long cedulaProfesional, int aniosExperiencia){
		super(nombre,edad,peso,talla,vivo);
		this.especialidad=especialidad;
		this.cedulaProfesional=cedulaProfesional;
		this.aniosExperiencia=aniosExperiencia;
	}

	private void operar(){
		System.out.println("Operando a un paciente");
	}

	public void nuevoPaciente(Paciente nuevo){
		System.out.println("Se agrega el paciente "+nuevo.getNombre()+" a la lista de pacientes");
		listaPacientes.add(nuevo);
		System.out.println("El paciente esta siendo operado por "+this.getNombre());
		operar();
		System.out.println("El paciente no sobrevivio la operacion");
		setVida(false);
	}
}

class Paciente extends Persona{
	String tipoSangre;
	String presionArterial;
	float temperatura;
	int frecuencia;
	String[] alergias;
	boolean enfermo;
	LinkedList<String> historialMedico=new LinkedList<String>();

	Paciente(String nombre, byte edad, float peso, float talla, boolean vivo, String tipoSangre, String presionArterial, float temperatura, int frecuencia, String[] alergias, boolean enfermo){
		super(nombre,edad,peso,talla,vivo);
		this.tipoSangre=tipoSangre;
		this.presionArterial=presionArterial;
		this.temperatura=temperatura;
		this.frecuencia=frecuencia;
		this.alergias=alergias;
		this.enfermo=enfermo;
	}

	private void enfermarse(){
		enfermo=true;
		presionArterial="150/100";
		this.temperatura+=5;
		this.frecuencia+=10;
	}

	void irAlMedico(){
		System.out.println("Asistiendo al hospital porque yo "+this.getNombre()+" me siento mal");
		enfermarse();
	}
}

class Principal{
	public static void main(String[] args) {
		String[] alergiasD={"Picante","Mani","Polen"};
		Paciente deivid=new Paciente("Deivid",(byte)19,65.78f,175.75f,true,"O+","90/100",37.3f,85,alergiasD,true);
		deivid.irAlMedico();
		Doctor profesorPatricio=new Doctor("Patricio Estrella", (byte)37,70.67f,62.3f, true,"Gastroenterologo",765364875l,8);
		profesorPatricio.nuevoPaciente(deivid);
	}
}
