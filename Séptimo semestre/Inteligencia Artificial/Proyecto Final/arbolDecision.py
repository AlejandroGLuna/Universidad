import pandas as pd               # Para la manipulación y análisis de datos
import numpy as np
from sklearn.tree import DecisionTreeRegressor
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import mean_squared_error, mean_absolute_error, r2_score
from sklearn import model_selection
import matplotlib.pyplot as plt   # Para la generación de gráficas a partir de los datos
from sklearn.tree import plot_tree
from sklearn.tree import export_text

def modeloAD(archivo, cabeza, x, y, r, mD, mS, mL, clasificar):
    datos = pd.read_csv(archivo,header=cabeza) if archivo.endswith(".csv") else pd.read_excel(archivo, header=cabeza)
    etiquetas = datos[y].unique()

    #Variables predictoras
    X = np.array(datos[x])
        
    global parametrosPredecir
    parametrosPredecir = x

    #Variable clase
    Y = np.array(datos[y])

    X_train, X_test, Y_train, Y_test = model_selection.train_test_split(X, Y, 
                                                                                random_state=0,
                                                                                test_size = (int(r)/100),
                                                                                shuffle = True)

    #Se entrena el modelo a partir de los datos de entrada
    if mD == 0:
        if clasificar:
            PronosticoAD = DecisionTreeClassifier(random_state=0, min_samples_split=int(mS), min_samples_leaf=int(mL))
        else:
            PronosticoAD = DecisionTreeRegressor(random_state=0, min_samples_split=int(mS), min_samples_leaf=int(mL))
    else:
        if clasificar:
            PronosticoAD = DecisionTreeClassifier(random_state=0, max_depth=int(mD), min_samples_split=int(mS), min_samples_leaf=int(mL))
        else:
            PronosticoAD = DecisionTreeRegressor(random_state=0, max_depth=int(mD), min_samples_split=int(mS), min_samples_leaf=int(mL))
        
    PronosticoAD.fit(X_train, Y_train)
    Y_Pronostico = PronosticoAD.predict(X_test)

    global arbolDecision
    arbolDecision = PronosticoAD

    plt.figure(figsize=(16,16))  
    plot_tree(PronosticoAD, feature_names = x)
    plt.savefig('web/graphs/arbol.png', bbox_inches='tight')
    plt.close()

    global bondadAjuste
    bondadAjuste = PronosticoAD.score(X_test, Y_test)

    if clasificar:
        Matriz_Clasificacion = pd.crosstab(Y_test.ravel(), Y_Pronostico)
        return Matriz_Clasificacion.to_json(orient="split")
    else:
        return ["MAE: " + str(mean_absolute_error(Y_test, Y_Pronostico)), "MSE: " + str(mean_squared_error(Y_test, Y_Pronostico)), "RMSE: " + str(mean_squared_error(Y_test, Y_Pronostico, squared=False)), 'Score: ' + str(r2_score(Y_test, Y_Pronostico))]

def mostrarImportancias():
    return pd.DataFrame({'Variable': parametrosPredecir,
                        'Importancia': arbolDecision.feature_importances_}).sort_values('Importancia', ascending=False).to_json(orient="split")

def descripcionArbol():
    return export_text(arbolDecision, 
                      feature_names = parametrosPredecir)

def mostrarParametros():
    return parametrosPredecir

def generarPronostico(parametros):
    dict = {}
    for k,v in zip(parametrosPredecir,parametros):
        dict[k] = [float(v)]

    etiqueta = pd.DataFrame(dict)
    return [str(arbolDecision.predict(etiqueta)[0]), "La prediccion tiene una exactitud de: " + str(bondadAjuste)]