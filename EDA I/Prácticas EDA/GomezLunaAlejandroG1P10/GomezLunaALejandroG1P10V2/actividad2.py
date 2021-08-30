# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""
a=0
x=5
contrasenia="only1future123"

while a==0:
    usuario=input("Introduzca la contraseña: ")
    if usuario==contrasenia:
        print("Bienvenido al programa, espere un momento...")
        while 0==0:
            print(".",end='')
        a+=1
    else:
        print("\nContraseña incorrecta")
        x-=1
        print("Te quedan {} intentos".format(x))
        if x==0:
            print("No te quedan mas intentos, saliendo del programa")
            a+=1