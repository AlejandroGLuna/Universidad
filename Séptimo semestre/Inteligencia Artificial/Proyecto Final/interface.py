import os 
import eel
import pandas as pd
import reglaAsociacion as ra
import metricasDistancia as md
import clustering as cl
import regresionLogistica as rl
import arbolDecision as ad

#-----------------------------------------------------------------------------
nombreArchivo = ""

#-----------------------------------------------------------------------------
#Metodos para la visualizacion de datos en la pestaña de datos
archivoCargado = '0'
modeloGenerado = '0'

@eel.expose
def leerArchivo(archivo, encabezado):
    cabecera = 0 if encabezado else None

    global cabeza
    cabeza = cabecera

    #Almacenando el nombre del archivo que contendra los datos a analizar
    global nombreArchivo
    nombreArchivo = archivo

    df = pd.read_csv(archivo, header = cabecera) if archivo.endswith(".csv") else pd.read_excel(archivo, header = cabecera)

    global archivoCargado 
    archivoCargado = '1'

    global archivoJSON
    #archivoJSON = pd.concat([df.head(50), df.tail(50)]).to_json(orient = "split") if(len(df) > 100) else df.to_json(orient = "split")
    archivoJSON = df.to_json(orient = "split")

@eel.expose
def hayArchivo():
    return archivoCargado

@eel.expose
def datosArchivo():
    return archivoJSON

@eel.expose
def hayCabecera():
    return cabeza
    
@eel.expose
def hayModelo():
    return modeloGenerado
#-----------------------------------------------------------------------------
#Metodos para el primer algoritmo
@eel.expose
def grafico():
    return ra.generarGrafico(nombreArchivo, cabeza)

@eel.expose
def reglas(support, confidence, lift):
    return ra.generarReglas(nombreArchivo, cabeza, float(support), float(confidence), float(lift))

#-----------------------------------------------------------------------------
#Metodos para las metricas de distancia
@eel.expose
def distancias(tipoDistancia, param, rango, tipoRango):
    if tipoRango == "opcion3":
        return str(md.calcularParejaDistancias(nombreArchivo, cabeza, tipoDistancia, rango, param))
    elif tipoRango == "opcion1":
        return md.calcularDistancias(nombreArchivo, cabeza, tipoDistancia, param)
    else:
        return md.calcularDistancias(nombreArchivo, cabeza, tipoDistancia, param, rango)

#-----------------------------------------------------------------------------
#Metodos para la matriz de correlaciones
@eel.expose
def correlaciones():
    return cl.matrizCorrelaciones(nombreArchivo, cabeza)

#-----------------------------------------------------------------------------
#Metodos para los algoritmos de clusterizacion
@eel.expose
def cabeceraDatos():
    return cl.cabecera(nombreArchivo, cabeza)

@eel.expose
def dendograma(v, n):
    return cl.dendogram(nombreArchivo, cabeza, v, n)

@eel.expose
def clustersJerarquico(v, n):
    return cl.numClustersJerarquico(nombreArchivo, cabeza, v, n)

@eel.expose
def clustersFormadosJerarquico():
    return cl.getClustersJerarquico()

@eel.expose
def elbowMethod(v, r):
    return cl.metodoCodo(nombreArchivo, cabeza, v, r)

@eel.expose
def codo(SSE, r):
    return int(cl.localizarCodo(SSE, r))

@eel.expose
def clustersParticional(v, n):
    return cl.numClustersParticional(nombreArchivo, cabeza, v, n)

@eel.expose
def clustersFormadosParticional():
    return cl.getClustersParticional()

#-----------------------------------------------------------------------------
#Metodos para los algoritmos de regresion logistica
@eel.expose
def obtenerReporteRL(x,y,r):
    return rl.modeloRL(nombreArchivo, cabeza, x, y, r)

@eel.expose
def obtenerRL():
    global modeloGenerado 
    modeloGenerado = '1'
    return rl.mostrarModelo()

#-----------------------------------------------------------------------------
#Metodos para los algoritmos de arboles de decision
@eel.expose
def obtenerReporteAD(x,y,r,mD,mS,mL,c):
    return ad.modeloAD(nombreArchivo, cabeza, x, y, r, mD, mS, mL,c)

@eel.expose
def obtenerAD():
    global modeloGenerado 
    modeloGenerado = '2'
    return ad.descripcionArbol()

@eel.expose
def obtenerImportancias():
    return ad.mostrarImportancias()

#-----------------------------------------------------------------------------
#Metodo para usar el modelo generado
@eel.expose
def obtenerParametros():
    if modeloGenerado == '1':
        return rl.mostrarParametros()
    else:
        return ad.mostrarParametros()

@eel.expose
def usarModelo(p):
    if modeloGenerado == '1':
        return rl.generarPronostico(p)
    else:
        return ad.generarPronostico(p)

#-----------------------------------------------------------------------------
#Iniciar el Front-end
if not os.path.exists('web/graphs'):
    os.makedirs('web/graphs')
    
eel.init("web")
eel.start("index.html")