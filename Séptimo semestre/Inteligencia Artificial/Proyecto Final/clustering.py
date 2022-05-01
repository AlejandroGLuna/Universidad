import pandas as pd               # Para la manipulación y análisis de datos
import numpy as np                # Para crear vectores y matrices n dimensionales
import matplotlib.pyplot as plt   # Para la generación de gráficas a partir de los datos
import seaborn as sns             # Para la visualización de datos basado en matplotlib
from sklearn.preprocessing import StandardScaler
import scipy.cluster.hierarchy as shc 
from sklearn.cluster import AgglomerativeClustering
from sklearn.cluster import KMeans
from kneed import KneeLocator

def matrizCorrelaciones(archivo, cabeza):
    datos = pd.read_csv(archivo,header=cabeza) if archivo.endswith(".csv") else pd.read_excel(archivo, header=cabeza)
    Corrdatos = datos.corr(method='pearson')

    plt.figure(figsize=(14,7))
    MatrizInf = np.triu(Corrdatos)
    sns.heatmap(Corrdatos, cmap='RdBu_r', annot=True, mask=MatrizInf)
    plt.savefig('web/graphs/mapaCalor.png', bbox_inches='tight')
    plt.close()

    return Corrdatos.to_json(orient="split")

def cabecera(archivo, cabeza):
    datos = pd.read_csv(archivo,header=cabeza) if archivo.endswith(".csv") else pd.read_excel(archivo, header=cabeza)
    return sorted(datos)

def dendogram(archivo, cabeza, v, n):
    datos = pd.read_csv(archivo,header=cabeza) if archivo.endswith(".csv") else pd.read_excel(archivo, header=cabeza)
    Matriz = np.array(datos[v])

    estandarizar = StandardScaler()                     # Se instancia el objeto StandardScaler o MinMaxScaler 
    MEstandarizada = estandarizar.fit_transform(Matriz) # Se calculan la media y desviación y se escalan los datos

    plt.figure(figsize=(10, 7))
    plt.title("Clusters formados")
    plt.xlabel('Variables')
    plt.ylabel('Distancia')

    shc.dendrogram(shc.linkage(MEstandarizada, method='complete', metric='euclidean')) #Aqui se encuentar el funcionamiento del arbol

    n = float(n)
    if n > 0:
        plt.axhline(y=n, color='orange', linestyle='--')
    
    plt.savefig('web/graphs/dendograma.png', bbox_inches='tight')
    plt.close()

    return True

def numClustersJerarquico(archivo, cabeza, v, n):
    datos = pd.read_csv(archivo,header=cabeza) if archivo.endswith(".csv") else pd.read_excel(archivo, header=cabeza)
    Matriz = np.array(datos[v])

    estandarizar = StandardScaler()                     # Se instancia el objeto StandardScaler o MinMaxScaler 
    MEstandarizada = estandarizar.fit_transform(Matriz) # Se calculan la media y desviación y se escalan los datos

    MJerarquico = AgglomerativeClustering(n_clusters=int(n), linkage='complete', affinity='euclidean')
    MJerarquico.fit_predict(MEstandarizada)

    datos = datos[v]
    datos['clusterH'] = MJerarquico.labels_

    global MatrizClusters
    MatrizClusters = datos

    return datos.groupby(['clusterH'])['clusterH'].count().to_json(orient="split")

def getClustersJerarquico():
    return MatrizClusters.groupby(['clusterH']).mean().to_json(orient="split")

def metodoCodo(archivo, cabeza, v, r):
    datos = pd.read_csv(archivo,header=cabeza) if archivo.endswith(".csv") else pd.read_excel(archivo, header=cabeza)
    Matriz = np.array(datos[v])

    estandarizar = StandardScaler()                     # Se instancia el objeto StandardScaler o MinMaxScaler 
    MEstandarizada = estandarizar.fit_transform(Matriz) # Se calculan la media y desviación y se escalan los datos

    SSE = []
    for i in range(2, int(r)+1):
        km = KMeans(n_clusters=i, random_state=0) #random_state toma una semilla para generar una posicion pseudoaleatoria de los centroides
        km.fit(MEstandarizada)
        SSE.append(km.inertia_)
    
    return SSE

def localizarCodo(SSE, r):
    kl = KneeLocator(range(2, int(r)+1), SSE, curve="convex", direction="decreasing")
    return kl.elbow

def numClustersParticional(archivo, cabeza, v, n):
    datos = pd.read_csv(archivo,header=cabeza) if archivo.endswith(".csv") else pd.read_excel(archivo, header=cabeza)
    Matriz = np.array(datos[v])

    estandarizar = StandardScaler()                     # Se instancia el objeto StandardScaler o MinMaxScaler 
    MEstandarizada = estandarizar.fit_transform(Matriz) # Se calculan la media y desviación y se escalan los datos

    MParticional = KMeans(n_clusters=int(n), random_state=0).fit(MEstandarizada)
    MParticional.predict(MEstandarizada)

    datos = datos[v]
    datos['clusterP'] = MParticional.labels_

    global MatrizClusters
    MatrizClusters = datos

    return datos.groupby(['clusterP'])['clusterP'].count().to_json(orient="split")

def getClustersParticional():
    return MatrizClusters.groupby(['clusterP']).mean().to_json(orient="split")