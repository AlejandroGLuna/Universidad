#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Apr 29 07:46:51 2019

@author: edaI01alu15
"""
t=e=l=x=0
tareas=examenes=laboratorio={}
print("***TAREAS***")
for i in range(0,5):
    tareas[i]=float(input("\tIngrese la calificacion de la tarea {}: ".format(i+1)))
    t+=tareas[i]
print("***EXÁMENES***")
for i in range(0,4):
    examenes[i]=float(input("\tIngrese la calificacion del examen {}: ".format(i+1)))
    e+=examenes[i]
print("***PRÁCTICAS***")
for i in range(0,5):
    laboratorio[i]=float(input("\tIngrese la calificacion de la práctica {}: ".format(i+1)))
    l+=laboratorio[i]
t=t/5
e=e/4
l=l/5
print("Tu promedio de exámenes fue de: {}".format(e))
print("Tu promedio de laboratorio fue de: {}".format(l))
x=(e*.6)+(l*.4)
print("Tu calificacion inicial es: {}".format(x))
if t>8.5:
    x+=0.5
elif t<=7:
    x-=0.5
print("Tu calificacion de tareas es de {}, por lo tanto tu calificacion final es: {}".format(t,x))



