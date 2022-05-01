import pandas as pd                 # Para la manipulación y análisis de los datos
import numpy as np                  # Para crear vectores y matrices n dimensionales
from apyori import apriori

def generarGrafico(archivo, cabeza):
    datos = pd.read_csv(archivo,header=cabeza) if archivo.endswith(".csv") else pd.read_excel(archivo, header=cabeza)

    #Se incluyen todas las transacciones en una sola lista
    Transacciones = datos.values.reshape(-1).tolist()

    #Se crea una matriz (dataframe) usando la lista
    Lista = pd.DataFrame(Transacciones)

    #Se incluye la columna frecuencia con un valor por defecto, el cual se modificara posteriormente
    Lista['Frecuencia'] = 1

    #Se agrupa los elementos
    Lista = Lista.groupby(by=[0], as_index=False).count().sort_values(by=['Frecuencia'], ascending=True) #Conteo
    Lista['Porcentaje'] = (Lista['Frecuencia'] / Lista['Frecuencia'].sum()) #Porcentaje
    Lista = Lista.rename(columns={0 : 'Item'})

    #Se regresa el conjunto de datos en formato JSON para graficarlos
    return Lista.to_json(orient="split", index=False)

def generarReglas(archivo, cabeza, soporte, confianza, lift):
    datos = pd.read_csv(archivo, header=cabeza) if archivo.endswith(".csv") else pd.read_excel(archivo, header=cabeza)

    #Se crea una lista de listas a partir del dataframe y se remueven los 'NaN'
    #level=0 especifica desde el primer índice
    ListaDatos = datos.stack().groupby(level=0).apply(list).tolist()
    Reglas = apriori(ListaDatos, min_support = soporte, min_confidence = confianza, min_lift = lift)
    datos = []
    for item in Reglas:
        dict = {"Regla":"","Soporte":"","Confianza":"","Lift":""}
        #El primer índice de la lista
        Emparejar = item[0]
        items = [x for x in Emparejar]
        dict["Regla"] = str(items)

        #El segundo índice de la lista
        dict["Soporte"] = str(item[1])

        #El tercer índice de la lista
        dict["Confianza"] = str(item[2][0][2])
        dict["Lift"] = str(item[2][0][3])
        datos.append(dict)

    #Se regresa el conjunto de datos en formato JSON para imprimirlos en pantalla
    return pd.DataFrame(datos).to_json(orient="split", index=False)