import pandas as pd                         # Para la manipulación y análisis de datos
import numpy as np                          # Para crear vectores y matrices n dimensionales
from scipy.spatial.distance import cdist    # Para el cálculo de distancias
from scipy.spatial import distance

def calcularDistancias(archivo, cabeza, tipo, pLambda = None, rango = None):
    datos = pd.read_csv(archivo,header=cabeza) if archivo.endswith(".csv") else pd.read_excel(archivo, header=cabeza)
    
    #Determinando el subconjunto de datos en caso necesario
    df = None
    if rango is not None:
        rango = rango.split("-")
        df = datos.iloc[int(rango[0]):int(rango[1])+1]
    else:
        df = datos

    #Aplicando la respectiva metrica de distancia    
    dst = None
    if pLambda is not None: dst = cdist(df, df, metric='minkowski', p=float(pLambda))
    elif tipo == "1": dst = cdist(df, df, metric='euclidean')
    elif tipo == "2": dst = cdist(df, df, metric='chebyshev')
    else: dst = cdist(df, df, metric='cityblock')
    
    #Redondeando los datos a tres decimales y verificando si existe un rango de valores
    if rango is not None:
        MFinal = pd.DataFrame(dst, index =range(int(rango[0]),int(rango[1])+1), columns=range(int(rango[0]),int(rango[1])+1)).round(3)
    else:
        MFinal = pd.DataFrame(dst).round(3)

    #Se regresa el conjunto de datos en formato JSON para imprimirlos en pantalla
    return MFinal.to_json(orient="split")

def calcularParejaDistancias(archivo, cabeza, tipo, indices, pLambda = None):
    datos = pd.read_csv(archivo,header=cabeza) if archivo.endswith(".csv") else pd.read_excel(archivo, header=cabeza)
    
    indices = indices.split(",")

    #Obteniendo la pareja de datos
    Objeto1 = datos.iloc[int(indices[0])]
    Objeto2 = datos.iloc[int(indices[1])]

    #Aplicando la respectiva metrica de distancia y devolviendo el valor correpsondiente
    if tipo == "4": return distance.minkowski(Objeto1,Objeto2, float(pLambda))
    elif tipo == "1": return distance.euclidean(Objeto1,Objeto2)
    elif tipo == "2": return distance.chebyshev(Objeto1,Objeto2)
    else: return distance.cityblock(Objeto1,Objeto2)
        