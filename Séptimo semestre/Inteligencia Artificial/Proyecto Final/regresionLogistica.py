import pandas as pd               # Para la manipulación y análisis de datos
import numpy as np                # Para crear vectores y matrices n dimensionales
from sklearn import linear_model
from sklearn import model_selection

def modeloRL(archivo, cabeza, x, y, r):
    datos = pd.read_csv(archivo,header=cabeza) if archivo.endswith(".csv") else pd.read_excel(archivo, header=cabeza)
    etiquetas = datos[y].unique()

    global etiquetasDatos
    etiquetasDatos = etiquetas

    datos = datos.replace({etiquetas[0]:0, etiquetas[1]:1})

    #Variables predictoras
    X = np.array(datos[x])

    global parametrosPredecir
    parametrosPredecir = x

    #Variable clase
    Y = np.array(datos[y])

    X_train, X_validation, Y_train, Y_validation = model_selection.train_test_split(X, Y, 
                                                                                random_state=0,
                                                                                test_size = (int(r)/100),
                                                                                shuffle = True)

    #Se entrena el modelo a partir de los datos de entrada
    Clasificacion = linear_model.LogisticRegression(random_state=0)
    Clasificacion.fit(X_train, Y_train)

    global bondadAjuste
    bondadAjuste = Clasificacion.score(X_validation, Y_validation)

    global modeloRLCalculos
    modeloRLCalculos = Clasificacion

    datosModelo = Clasificacion.intercept_.tolist() + np.squeeze(Clasificacion.coef_).tolist()
    texto = str(datosModelo[0]) + " "
    for nombre,valor in zip(x, datosModelo[1:]):
        if valor >= 0:
            valor = "+" + str(valor)
        texto += str(valor) + "(" + nombre + ")" + " "
    
    global modeloRLTexto
    modeloRLTexto = texto

    Y_Clasificacion = Clasificacion.predict(X_validation)

    Matriz_Clasificacion = pd.crosstab(Y_validation.ravel(), Y_Clasificacion)
    Matriz_Clasificacion = Matriz_Clasificacion.rename({0:etiquetas[0], 1:etiquetas[1]},axis="index")

    return Matriz_Clasificacion.to_json(orient="split")

def mostrarModelo():
    return modeloRLTexto

def mostrarParametros():
    return parametrosPredecir

def generarPronostico(parametros):
    dict = {}
    for k,v in zip(parametrosPredecir,parametros):
        dict[k] = [float(v)]

    etiqueta = pd.DataFrame(dict)
    return [etiquetasDatos[int(modeloRLCalculos.predict(etiqueta)[0])], "La prediccion tiene una exactitud de: " + str(bondadAjuste)]