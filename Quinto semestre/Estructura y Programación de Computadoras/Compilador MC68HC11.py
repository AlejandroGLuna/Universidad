#Modulo para utilizar expresiones regulares
import re

#Modulo para borrar archivo auxiliar
from os import remove as r

#Se importa ceil para cuando se requieran hacer redondeos
from math import ceil as c

#Diccionarios con las instrucciones del MS68HC11
IMM = {'adca': ('89', 2), 'adcb': ('C9', 2), 'adda': ('8B', 2), 'addb': ('CB', 2), 'addd': ('C3', 3), 'anda': ('84', 2), 'andb': ('C4', 2), 'bita': ('85', 2), 'bitb': ('C5', 2), 'cmpa': ('81', 2), 'cmpb': ('C1', 2), 'cpd': ('1A 83', 4), 'cpx': ('8C', 3), 'cpy': ('18 8C', 4), 'eora': ('88', 2), 'eorb': ('C8', 2), 'ldaa': ('86', 2), 'ldab': ('C6', 2), 'ldd': ('CC', 3), 'lds': ('8E', 3), 'ldx': ('CE', 3), 'ldy': ('18 CE', 4), 'oraa': ('8A', 2), 'orab': ('CA', 2), 'sbca': ('82', 2), 'sbcb': ('C2', 2), 'suba': ('80', 2), 'subb': ('C0', 2), 'subd': ('83', 3)}
DIR = {'adca': ('99', 2), 'adcb': ('D9', 2), 'adda': ('9B', 2), 'addb': ('DB', 2), 'addd': ('D3', 2), 'anda': ('94', 2), 'andb': ('D4', 2), 'bclr': ('15', 3), 'bita': ('95', 2), 'bitb': ('D5', 2), 'brclr': ('13', 4), 'brset': ('12', 4), 'bset': ('14', 3), 'cmpa': ('91', 2), 'cmpb': ('D1', 2), 'cpd': ('1A 93', 3), 'cpx': ('9C', 2), 'cpy': ('18 9C', 3), 'eora': ('98', 2), 'eorb': ('D8', 2), 'jsr': ('9D', 2), 'ldaa': ('96', 2), 'ldab': ('D6', 2), 'ldd': ('DC', 2), 'lds': ('9E', 2), 'ldx': ('DE', 2), 'ldy': ('18 DE', 3), 'oraa': ('9A', 2), 'orab': ('DA', 2), 'sbca': ('92', 2), 'sbcb': ('D2', 2), 'staa': ('97', 2), 'stab': ('D7', 2), 'std': ('DD', 2), 'sts': ('9F', 2), 'stx': ('DF', 2), 'sty': ('18 DF', 3), 'suba': ('90', 2), 'subb': ('D0', 2), 'subd': ('93', 2)}
INDX = {'adca': ('A9', 2), 'adcb': ('E9', 2), 'adda': ('AB', 2), 'addb': ('EB', 2), 'addd': ('E3', 2), 'anda': ('B4', 2), 'andb': ('E4', 2), 'asl': ('68', 2), 'asr': ('67', 2), 'bclr': ('1D', 3), 'bita': ('A5', 2), 'bitb': ('E5', 2), 'brclr': ('1F', 4), 'brset': ('1E', 4), 'bset': ('1C', 3), 'clr': ('6F', 2), 'cmpa': ('A1', 2), 'cmpb': ('E1', 2), 'com': ('63', 2), 'cpd': ('1A A3', 3), 'cpx': ('AC', 2), 'cpy': ('1A AC', 3), 'dec': ('6A', 2), 'eora': ('A8', 2), 'eorb': ('E8', 2), 'inc': ('6C', 2), 'jmp': ('6E', 2), 'jsr': ('AD', 2), 'ldaa': ('A6', 2), 'ldab': ('E6', 2), 'ldd': ('EC', 2), 'lds': ('AE', 2), 'ldx': ('EE', 2), 'ldy': ('1A EE', 3), 'lsl': ('68', 2), 'lsr': ('64', 2), 'neg': ('60', 2), 'oraa': ('AA', 2), 'orab': ('EA', 2), 'rol': ('69', 2), 'ror': ('66', 2), 'sbca': ('A2', 2), 'sbcb': ('E2', 2), 'staa': ('A7', 2), 'stab': ('E7', 2), 'std': ('ED', 2), 'sts': ('AF', 2), 'stx': ('EF', 2), 'sty': ('1A EF', 3), 'suba': ('A0', 2), 'subb': ('E0', 2), 'subd': ('A3', 2), 'tst': ('6D', 2)}
INDY = {'adca': ('18 A9', 3), 'adcb': ('18 E9', 3), 'adda': ('18 AB', 3), 'addb': ('18 EB', 3), 'addd': ('18 E3', 3), 'anda': ('18 A4', 3), 'andb': ('18 E4', 3), 'asl': ('18 68', 3), 'asr': ('18 67', 3), 'bclr': ('18 1D', 4), 'bita': ('18 A5', 3), 'bitb': ('18 E5', 3), 'brclr': ('18 1F', 5), 'brset': ('18 1E', 5), 'bset': ('18 1C', 4), 'clr': ('18 6F', 3), 'cmpa': ('18 A1', 3), 'cmpb': ('18 E1', 3), 'com': ('18 63', 3), 'cpd': ('CD A3', 3), 'cpx': ('CD AC', 3), 'cpy': ('18 AC', 3), 'dec': ('18 6A', 3), 'eora': ('18 A8', 3), 'eorb': ('18 E8', 3), 'inc': ('18 6C', 3), 'jmp': ('18 6E', 3), 'jsr': ('18 AD', 3), 'ldaa': ('18 A6', 3), 'ldab': ('18 E6', 3), 'ldd': ('18 EC', 3), 'lds': ('18 AE', 3), 'ldx': ('CD EE', 3), 'ldy': ('18 EE', 3), 'lsl': ('18 68', 3), 'lsr': ('18 64', 3), 'neg': ('18 60', 3), 'oraa': ('18 AA', 3), 'orab': ('18 EA', 3), 'rol': ('18 69', 3), 'ror': ('18 66', 3), 'sbca': ('18 A2', 3), 'sbcb': ('18 E2', 3), 'staa': ('18A 7', 3), 'stab': ('18 E7', 3), 'std': ('18 ED', 3), 'sts': ('18 AF', 3), 'stx': ('CD EF', 3), 'sty': ('18 EF', 3), 'suba': ('18 A0', 3), 'subb': ('18 E0', 3), 'subd': ('18 A3', 3), 'tst': ('18 6D', 3)}
EXT = {'adca': ('B9', 3), 'adcb': ('F9', 3), 'adda': ('BB', 3), 'addb': ('FB', 3), 'addd': ('F3', 3), 'anda': ('B4', 3), 'andb': ('F4', 3), 'asl': ('78', 3), 'asr': ('77', 3), 'bita': ('B5', 3), 'bitb': ('F5', 3), 'clr': ('7F', 3), 'cmpa': ('B1', 3), 'cmpb': ('F1', 3), 'com': ('73', 3), 'cpd': ('1A B3', 4), 'cpx': ('BC', 3), 'cpy': ('18 BC', 4), 'dec': ('7A', 3), 'eora': ('B8', 3), 'eorb': ('F8', 3), 'inc': ('7C', 3), 'jmp': ('7E', 3), 'jsr': ('BD', 3), 'ldaa': ('B6', 3), 'ldab': ('F6', 3), 'ldd': ('FC', 3), 'lds': ('BE', 3), 'ldx': ('FE', 3), 'ldy': ('18 FE', 4), 'lsl': ('78', 3), 'lsr': ('74', 3), 'neg': ('70', 3), 'oraa': ('BA', 3), 'orab': ('FA', 3), 'rol': ('79', 3), 'ror': ('76', 3), 'sbca': ('B2', 3), 'sbcb': ('F2', 3), 'staa': ('B7', 3), 'stab': ('F7', 3), 'std': ('FD', 3), 'sts': ('BF', 3), 'stx': ('FF', 3), 'sty': ('18 FF', 4), 'suba': ('B0', 3), 'subb': ('F0', 3), 'subd': ('B3', 3), 'tst': ('7D', 3)}
INH = {'aba': ('1B', 1), 'abx': ('3A', 1), 'aby': ('18 3A', 2), 'asla': ('48', 1), 'aslb': ('58', 1), 'asld': ('5', 1), 'asra': ('47', 1), 'asrb': ('57', 1), 'cba': ('11', 1), 'clc': ('0C', 1), 'cli': ('0E', 1), 'clra': ('4F', 1), 'clrb': ('5F', 1), 'clv': ('0A', 1), 'coma': ('43', 1), 'comb': ('53', 1), 'daa': ('19', 1), 'deca': ('4A', 1), 'decb': ('5A', 1), 'des': ('34', 1), 'dex': ('0 9', 1), 'dey': ('18 09', 2), 'fdiv': ('0 3', 1), 'idiv': ('0 2', 1), 'inca': ('4C', 1), 'incb': ('5C', 1), 'ins': ('31', 1), 'inx': ('0 8', 1), 'iny': ('18 08', 2), 'lsla': ('48', 1), 'lslb': ('58', 1), 'lsld': ('0 5', 1), 'lsra': ('44', 1), 'lsrb': ('54', 1), 'lsrd': ('0 4', 1), 'mul': ('3D', 1), 'nega': ('40', 1), 'negb': ('50', 1), 'nop': ('0 1', 1), 'psha': ('36', 1), 'pshb': ('37', 1), 'pshx': ('3C', 1), 'pshy': ('18 3C', 2), 'pula': ('32', 1), 'pulb': ('33', 1), 'pulx': ('38', 1), 'puly': ('18 38', 2), 'rola': ('49', 1), 'rolb': ('59', 1), 'rora': ('46', 1), 'rorb': ('56', 1), 'rti': ('3B', 1), 'rts': ('39', 1), 'sba': ('10', 1), 'sec': ('OD', 1), 'sei': ('OF', 1), 'sev': ('OB', 1), 'stop': ('CF', 1), 'swi': ('3F', 1), 'tab': ('16', 1), 'tap': ('0 6', 1), 'tba': ('17', 1), 'tets': ('0 0', '**'), 'tpa': ('0 7', 1), 'tsta': ('4D', 1), 'tstb': ('5D', 1), 'tsx': ('30', 1), 'tsy': ('18 30', 2), 'txs': ('35', 1), 'tys': ('18 35', 2), 'wai': ('3E', 1), 'xgdx': ('8F', 1), 'xgdy': ('18 8F', 2)}
REL = {'bcc': ('24', 2), 'bcs': ('25', 2), 'beq': ('27', 2), 'bge': ('2C', 2), 'bgt': ('2E', 2), 'bhi': ('22', 2), 'bhs': ('24', 2), 'ble': ('2F', 2), 'blo': ('25', 2), 'bls': ('23', 2), 'blt': ('2D', 2), 'bmi': ('2B', 2), 'bne': ('26', 2), 'bpl': ('2A', 2), 'bra': ('20', 2), 'brn': ('21', 2), 'bsr': ('8D', 2), 'bvc': ('28', 2), 'bvs': ('29', 2)}

#Expresiones regulares para identificar las componentes del programa
#--- Expresion para identificar que se trata de una instruccion sin margen
instruccion = re.compile(r"(^\w([\w\d$,*\.\-#\_]+\s*)+)+")

#--- Expresion para identificar si la directiva EQU fue malescrita
equ = re.compile(r"(?i)(\w+\s+[EQU]+\s+.+)+")

#--- Expresiones para identificar las directivas de procesamiento
org = re.compile(r"(?i)(\s+ORG\s+[\$\w\d]+(\s*\*.+)*)+")
end = re.compile(r"(?i)(\s+END\s+[\$\w\d]+(\s*\*.+)*)+")
fcb = re.compile(r"(?i)(\w*\s+FCB\s+[\$\w\d]+,[\$\w\d]+(\s*\*.+)*)+")

#--- Expresiones para identificar el modo de direccionamiento
modoINH = re.compile(r"((^\s+\w+)(\s*\*.+)*)+")
modoIMM = re.compile(r"((^\s+\w+)\s+#([\d\w\$]|'.)+(\s*\*.+)*)+")
modoDIEXRE = re.compile(r"((^\s+\w+)\s+([\d\w\$]|'.)+(\s*\*.+)*)+")
modoINDXeINDY = re.compile(r"((^\s+\w+)\s+([\d\w\$]|'.)+,(X|Y)*(,#([\d\w\$]|'.)+)*(\s[\w\d]+)*(\s*\*.+)*)+")

#--- Expresiones para identificar constantes y variables
cyv = re.compile(r"((^\w+\s+)EQU\s+[\$\w\d]+(\s*\*.+)*)+")

#--- Expresion para identificar etiqueta
etiqueta = re.compile(r"(^\w+\s*(\s*\*.+)*)+")

#--- Expresion para identificar comentario
comentario = re.compile(r"((^\s*\*.+)+)+")

#Funcion para convertir los operandos
def convertirOperando(inicial,dic):
    #Bandera para saber si existe o no la variable/constante 
    inexistente = False
    
    #Verificando si el operando es una variable/constante
    try:
        final = dic[inicial]
    except:
        #En caso de que no sea una constante, se verifica si el valor es hexadecimal
        if inicial.startswith("$"):
            final = inicial[1:].upper()
        elif inicial.startswith('\''):
            #Si la cadena es un caracter, se transforma a hexadecimal
            final = hex(ord(inicial[1:]))[2:].upper()
        else:
            #Si la cadena esta en decimal, se transforma a hexadecimal
            try:
                final = hex(int(inicial))[2:].upper()
            #Se trata de una variable inexistente
            except:
                inexistente = True
     
    if inexistente:
        raise Exception("No se pudo convertir el operando")
    else:
        #En caso de requerirse, se adicionan ceros hasta un maximo de 2
        return final.zfill(2)

#Funcion para verificar que la magnitud del operando sea correcta
def verificarOperando(disponible,opcode,operando):
    return True if (disponible - (c(len(opcode)/2) + c(len(operando)/2))) >= 0 else False

#Ciclo infinito que se repetira hasta que el usuario desee salir
while(True):
    print("\t--------BIENVENIDO AL COMPILADOR DEL MS68HC11--------")
    
    #Leyendo el archivo
    nombreArchivo = input("Ingrese el nombre del archivo a compilar: ")
                 
    #Verificando si el archivo existe    
    try:
        archivo = open(nombreArchivo + ".asc", "r")
    except:
        print("No existe el archivo ingresado. Intente nuevamente")
        continue
                 
    #Diccionario para guardar las etiquetas que se encuentren
    etiquetas = {}
    
    #Diccionario para guardar las constantes y variables que se encuentren
    covs = {}
                 
    #Inicializando a la memoria
    memoria = "-1"
    
    #Creando el archivo con la compilacion en formato de facil lectura
    aux = open("Auxiliar.lst","w")
    
    #Obteniendo la informacion del archivo
    textoArchivo = archivo.readlines()
    
    #Bandera para saber si la compilacion encontro el end
    existeEnd = False
    
    #Leyendo linea por linea el archivo
    for i,linea in enumerate(textoArchivo,1):
        #Eliminando cualquier caracter especial al final de la linea
        linea = linea.rstrip()

        #Cadena auxiliar
        cadena = ""
        
        #Identificando si es una instruccion sin espacio al margen
        if re.fullmatch(instruccion,linea) != None:
            
            #Declaracion de constantes y variables
            if re.fullmatch(cyv,linea) != None:
                partes = linea.split()

                #Revisando si la cadena esta en hexadecimal
                if partes[2].startswith("$"):
                    memoriaActual = partes[2][1:]
                else:
                    #Si la cadena esta en decimal, se transforma a hexadecimal
                    num = hex(int(partes[2]))[2:]
                    memoriaActual = num

                #Guardando el valor de la variable/constante en un diccionario
                nombre = partes[0]
                covs[nombre] = memoriaActual.lstrip("0")

                #Dandole formato a la cadena
                cadena = ((" "*6) + memoriaActual + (" "*7) + nombre 
                          + (" "*(10-len(partes[0]))) + partes[1] + (" "*7) + partes[2])

                #Comprobando si existe un comentario
                if len(partes) > 3:
                    cadena += ((" "*17) + ' '.join([x for x in partes[3:]]))
            
            #Declaracion de etiqueta
            elif re.fullmatch(etiqueta,linea) != None:         
                nombre = linea.strip()

                #Almacenando el valor en memoria de la etiqueta
                etiquetas[nombre.upper()] = memoria
                cadena = (" " + memoria + (" "*12) + nombre)
            
            #Directiva de procesamiento del MS68HC11 tipo FCB con RESET
            elif re.fullmatch(fcb,linea) != None:
                partes = linea.split()

                #Variable que almacenara el compilado
                compilado = ""

                #Obteniendo los operandos
                operandos = partes[2].split(",")
                for operando in operandos:
                    try:
                        compilado += convertirOperando(operando,covs)
                    except:
                        cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 002 VARIABLE INEXISTENTE\n")
                        aux.write(cadena)
                        continue
                        
                #Dandole formato a la cadena
                cadena = (" " + memoria + " " + compilado)
                cadena += ((" "*7) + partes[0] + (" "*(10 - len(partes[0]))) + partes[1] 
                           + (" "*7) + partes[2])

                #Comprobando si existe un comentario
                if len(partes) > 3:
                    cadena += ((" "*(22-len(partes[2]))) + ' '.join([x for x in partes[3:]]))
                else:
                    cadena += ((" "*17) + partes[0] + (" "*7) + partes[1])

            else:
                if re.fullmatch(equ,linea) != None:
                    cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 004 MNEMONICO INEXISTENTE\n")
                    aux.write(cadena)
                    continue
                else:
                    cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 009 INSTRUCCION CARECE DE ALMENOS"
                          + " UN ESPACIO RELATIVO AL MARGEN\n")
                    aux.write(cadena)
                    continue

        else:
            #Identificando a que pertenece
            #Directiva de procesamiento del MS68HC11 tipo ORG
            if re.fullmatch(org,linea) != None:
                partes = linea.split()

                #Revisando si la cadena esta en hexadecimal
                if partes[1].startswith("$"):
                    memoria = partes[1][1:]
                else:
                    #Si la cadena esta en decimal, se transforma a hexadecimal
                    num = hex(int(partes[2]))[2:].upper()
                    num = num.zfill(4)
                    memoria = num

                #Dandole formato a la cadena
                cadena = ((" "*6) + memoria + (" "*17) + partes[0] + (" "*7) + partes[1])

                #Comprobando si existe un comentario
                if len(partes) > 2:
                    cadena += ((" "*17) + ' '.join([x for x in partes[2:]]))

            #Directiva de procesamiento del MS68HC11 tipo END
            elif re.fullmatch(end,linea) != None:
                partes = linea.split()

                #Dandole formato a la cadena
                cadena = ((" "*27) + partes[0] + (" "*7) + partes[1])

                #Comprobando si existe un comentario
                if len(partes) > 2:
                    cadena += ((" "*17) + ' '.join([x for x in partes[2:]]))

                #Indicando que el programa contiene un END
                existeEnd = True

                #Escribiendo la cadena generada en el archivo auxiliar
                aux.write(cadena + "\n")

                #Terminando la lectura del programa
                break

            #Directiva de procesamiento del MS68HC11 tipo FCB sin RESET
            elif re.fullmatch(fcb,linea) != None:
                partes = linea.split()

                #Variable que almacenara el compilado
                compilado = ""

                #Obteniendo los operandos
                operandos = partes[1].split(",")
                for operando in operandos:
                    try:
                        compilado += convertirOperando(operando,covs)
                    except:
                        cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 002 VARIABLE INEXISTENTE\n")
                        aux.write(cadena)
                        continue
                                
                #Dandole formato a la cadena
                cadena = (" " + memoria + " " + compilado)
                cadena += ((" "*17) + partes[0] + (" "*7) + partes[1])

                #Comprobando si existe un comentario
                if len(partes) > 2:
                    cadena += ((" "*(22-len(partes[2]))) + ' '.join([x for x in partes[2:]]))

            #Direccionamiento Inherente (INH)
            elif re.fullmatch(modoINH,linea) != None:
                partes = linea.split()

                #Encontrando el mnemonico
                try:
                    clave = INH[partes[0].lower()]
                except:
                    #Verificando si el mnemonico pertenece a algun otro modo de direccionamiento
                    clave = partes[0].lower()
                    if ((clave in IMM) or (clave in DIR) or (clave in INDX) or (clave in INDY) 
                        or (clave in EXT) or (clave in REL)):
                        cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 005 INSTRUCCION CARECE DE OPERANDO(S)\n")
                    else:
                        cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 004 MNEMONICO INEXISTENTE\n")
                    aux.write(cadena)
                    continue

                #Obteniendo el espacio que ocupa la instruccion
                espacio = clave[1]

                #Calculando el espacio de memoria ocupado
                memoriaActual = hex(int(memoria,16) + espacio)[2:]

                #Obteniendo el opcode sin espacios y la compilacion de la instruccion
                opcode = clave[0].replace(" ","")

                #Dandole formato a la cadena
                cadena = (" " + memoria + " " + opcode + (" "*(21-len(opcode))) 
                          + partes[0])

                #Comprobando si existe un comentario
                if len(partes) > 1:
                    if partes[1].startswith("*"):
                        cadena += ((" "*(29-len(partes[0]))) + ' '.join([x for x in partes[2:]]))
                    else:
                        cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 006 INSTRUCCION NO LLEVA OPERANDO(S)\n")
                        aux.write(cadena)
                        continue

                #Actualizando la ultima direccion de memoria
                #Adicionando los ceros necesarios al inicio para asegurar que se mantenga el formato
                memoria = memoriaActual.zfill(4).upper()

            #Direccionamiento Inmediato (IMM)
            elif re.fullmatch(modoIMM,linea) != None:
                partes = linea.split()

                #Encontrando el mnemonico
                try:
                    clave = IMM[partes[0].lower()]
                except:
                    cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 004 MNEMONICO INEXISTENTE\n")
                    aux.write(cadena)
                    continue

                #Obteniendo el opcode sin espacios
                opcode = clave[0].replace(" ","")

                #Obteniendo el espacio que ocupa la instruccion
                espacio = clave[1]

                #Obteniendo el operando sin el #
                operandoInicial = partes[1][1:]

                #Verificando que solo haya un operando
                if len(operandoInicial.split(",")) > 1:
                    cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 006 INSTRUCCION NO LLEVA OPERANDO(S)\n")
                    aux.write(cadena)
                    continue

                try:
                    operando = convertirOperando(operandoInicial,covs)
                except:
                    cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 001 CONSTANTE INEXISTENTE\n")
                    aux.write(cadena)
                    continue
                            
                #Verificando que la magnitud del operando sea correcta
                if verificarOperando(espacio,opcode,operando) == False:
                    cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 007 MAGNITUD DE OPERANDO ERRONEA\n")
                    aux.write(cadena)
                    continue 
                
                #Verificando si se deben adicionar mas ceros al inicio del operando
                if (espacio - c(len(opcode)/2)) > 1:
                    operando = operando.zfill(4)
    
                #Obteniendo la compilacion de la instruccion
                compilado = opcode + operando

                #Dandole formato a la cadena
                cadena = (" " + memoria + " " + compilado + (" "*(21-len(compilado))) 
                          + partes[0] + (" "*(10-len(partes[0]))) + partes[1])

                #Comprobando si existe un comentario
                if len(partes) > 2:
                    cadena += ((" "*(22-len(partes[1]))) + ' '.join([x for x in partes[2:]]))

                #Actualizando la ultima direccion de memoria
                memoriaActual = hex(int(memoria,16) + espacio)[2:]

                #Actualizando la ultima direccion de memoria
                #Adicionando los ceros necesarios al inicio para asegurar que se mantenga el formato
                memoria = memoriaActual.zfill(4).upper()

            #Direccionamiento Directo (DIR), Extendido (EXT) y Relativo (REL)
            elif re.fullmatch(modoDIEXRE,linea) != None:
                partes = linea.split()

                #Obteniendo el operando
                operandoInicial = partes[1]

                #Verificando que solo haya un operando
                if len(operandoInicial.split(",")) > 1:
                    cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 006 INSTRUCCION NO LLEVA OPERANDO(S)\n")
                    aux.write(cadena)
                    continue
                
                #Bandera para saber si se tiene una etiqueta de operando
                esEtiqueta = False
                
                #Verificando si el operando es una etiqueta
                try:
                    operando = convertirOperando(operandoInicial,covs)
                except:
                    #Si hay una excepcion, el operando es una posible etiqueta
                    esEtiqueta = True

                if esEtiqueta:
                    #Bandera para saber si tiene salto relativo
                    relativo = False

                    #Encontrando el mnemonico
                    try:
                        clave = REL[partes[0].lower()]
                        relativo = True
                    except:
                        try:
                            clave = EXT[partes[0].lower()]
                        except:
                            cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 004 MNEMONICO INEXISTENTE\n")
                            aux.write(cadena)
                            continue

                    #Obteniendo el opcode sin espacios
                    opcode = clave[0].replace(" ","")

                    #Obteniendo el espacio que ocupa la instruccion
                    espacio = clave[1]
                    
                    #Actualizando la ultima direccion de memoria
                    memoriaActual = hex(int(memoria,16) + espacio)[2:]

                    if relativo:
                        #Simbolo que indica que existe una instruccion relativa para una posterior revision
                        cadena = "%"
                    else:
                        #Verificando si el mnemonico tambien puede estar en direccionamiento directo
                        cadena = "&&" if partes[0].lower == "jsr" else "&"
                             
                    cadena += partes[1] + "%" + memoriaActual + "%" + memoria + "%" + opcode + "%" + ' '.join(partes)

                else:
                    #Adicionando posibles ceros al inicio del operando
                    operando = operando.zfill(2)

                    #Diferenciando entre modo de direccionamiento directo y extendido
                    if len(operando) <= 2:
                        #Encontrando el mnemonico
                        try:
                            clave = DIR[partes[0].lower()]
                        except:
                            try:
                                clave = EXT[partes[0].lower()]
                            except:
                                cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 004 MNEMONICO INEXISTENTE\n")
                                aux.write(cadena)
                                continue
                    else:
                        #Encontrando el mnemonico
                        try:
                            clave = EXT[partes[0].lower()]
                        except:
                            cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 004 MNEMONICO INEXISTENTE\n")
                            aux.write(cadena)
                            continue

                    #Obteniendo el opcode sin espacios
                    opcode = clave[0].replace(" ","")

                    #Obteniendo el espacio que ocupa la instruccion
                    espacio = clave[1]
                    
                    #Verificando que la magnitud del operando sea correcta
                    if verificarOperando(espacio,opcode,operando) == False:
                        cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 007 MAGNITUD DE OPERANDO ERRONEA\n")
                        aux.write(cadena)
                        continue
                        
                    #Verificando si se deben adicionar mas ceros al inicio del operando
                    if (espacio - c(len(opcode)/2)) > 1:
                        operando = operando.zfill(4)

                    #Obteniendo la compilacion de la instruccion
                    compilado = opcode + operando

                    #Dandole formato a la cadena
                    cadena = (" " + memoria + " " + compilado + (" "*(21-len(compilado))) 
                              + partes[0] + (" "*(10-len(partes[0]))) + partes[1])

                    #Comprobando si existe un comentario
                    if len(partes) > 2:
                        cadena += ((" "*(22-len(partes[1]))) + ' '.join([x for x in partes[2:]]))

                    #Actualizando la ultima direccion de memoria
                    memoriaActual = hex(int(memoria,16) + espacio)[2:]

                #Actualizando la ultima direccion de memoria
                #Adicionando los ceros necesarios al inicio para asegurar que se mantenga el formato
                memoria = memoriaActual.zfill(4).upper()

            #Direccionamiento Indexado (IND,X) o (IND,Y)
            elif re.fullmatch(modoINDXeINDY,linea) != None:
                partes = linea.split()

                #Obteniendo el operando
                operandoInicial = partes[1]

                '''Obteniendo si pertenece al modo de direccionamiento de x o y o a las excepciones,
                   las cuales son las instrucciones con mas de un operando'''
                indexacion = operandoInicial.split(",")

                #Verificando que haya operandos suficientes (minimo)
                if len(indexacion) < 2:
                    cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 005 INSTRUCCION CARECE DE OPERANDO(S)\n")
                    aux.write(cadena)
                    continue
                
                #Verificando que no haya mas de tres operandos (maximo)
                if len(indexacion) > 3:
                    cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 006 INSTRUCCION NO LLEVA OPERANDO(S)\n")
                    aux.write(cadena)
                    continue
                    
                if indexacion[1].lower() == "x":
                    #Encontrando el mnemonico
                    try:
                        clave = INDX[partes[0].lower()]
                    except:
                        cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 004 MNEMONICO INEXISTENTE\n")
                        aux.write(cadena)
                        continue
                        
                elif indexacion[1].lower() == "y":
                    #Encontrando el mnemonico
                    try:
                        clave = INDY[partes[0].lower()]
                    except:
                        cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 004 MNEMONICO INEXISTENTE\n")
                        aux.write(cadena)
                        continue

                else:
                    #Encontrando el mnemonico
                    try:
                        clave = DIR[partes[0].lower()]
                    except:
                        cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 004 MNEMONICO INEXISTENTE\n")
                        aux.write(cadena)
                        continue

                    #Verificando que no haya mas de tres operandos (maximo)
                    if len(indexacion) > 2:
                        cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 006 INSTRUCCION NO LLEVA OPERANDO(S)\n")
                        aux.write(cadena)
                        continue

                #Obteniendo el opcode sin espacios
                opcode = clave[0].replace(" ","")

                #Obteniendo el espacio que ocupa la instruccion
                espacio = clave[1]

                #Obteniendo el primer o unico operando
                try:
                    operando = convertirOperando(indexacion[0],covs)
                except:
                    cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 002 VARIABLE INEXISTENTE\n")
                    aux.write(cadena)
                    continue

                #Bandera para saber si hay mas operandos
                masOperandos = False

                #Verificando y obteniendo el segundo operando y omitiendo el #
                if len(indexacion) <= 2:
                    if indexacion[1].lower() not in ["x","y"]:
                        operando2 = indexacion[1][1:]
                        masOperandos = True
                else:
                    operando2 = indexacion[2][1:]
                    masOperandos = True

                if masOperandos:    
                    #Verificando si el segundo operando es una constante
                    try:
                        operando += convertirOperando(operando2,covs)
                    except:
                        cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 002 VARIABLE INEXISTENTE\n")
                        aux.write(cadena)
                        continue
                        
                #Verificando que la magnitud del operando sea correcta
                if verificarOperando(espacio,opcode,operando) == False:
                    cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 007 MAGNITUD DE OPERANDO ERRONEA\n")
                    aux.write(cadena)
                    continue
                        
                #Bandera para saber si se tiene salto relativo
                relativo = False

                #Verificando si se tiene un salto relativo:
                if len(partes) > 2 and not partes[2].startswith("*"):
                    relativo = True

                #Obteniendo la compilacion de la instruccion
                compilado = opcode + operando

                #Actualizando la ultima direccion de memoria
                memoriaActual = hex(int(memoria,16) + espacio)[2:]

                #Verificando si hubo un direccionamiento relativo
                if not relativo:
                    #Verificando que no falten operandos
                    disponible = espacio - (c(len(compilado)/2))
                    if disponible > 0:
                        cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 005 INSTRUCCION CARECE DE OPERANDO(S)\n")
                        aux.write(cadena)
                        continue

                    #Dandole formato a la cadena
                    cadena = (" " + memoria + " " + compilado + (" "*(21-len(compilado))) 
                              + partes[0] + (" "*(10-len(partes[0]))) + partes[1])

                    #Comprobando si existe un comentario
                    if len(partes) > 2:
                        cadena += ((" "*(22-len(partes[1]))) + ' '.join([x for x in partes[2:]]))
                else:
                    #Verificando que no falten operandos, contemplando el salto relativo
                    disponible = espacio - (c(len(compilado)/2) + 1)
                    if disponible > 0:
                        cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 005 INSTRUCCION CARECE DE OPERANDO(S)\n")
                        aux.write(cadena)
                        continue

                    #Indicando que existe una instruccion relativa para una posterior revision
                    cadena = "%" + partes[2] + "%" + memoriaActual + "%" + memoria + "%" + compilado + "%" + ' '.join(partes)

                #Actualizando la ultima direccion de memoria
                #Adicionando los ceros necesarios al inicio para asegurar que se mantenga el formato
                memoria = memoriaActual.zfill(4).upper()

            #Declaracion de comentario enteramente
            elif re.fullmatch(comentario,linea) != None:
                cadena = ((" "*17) + linea)

            #Cualquier otro tipo de linea
            elif linea == "":
                if memoria == "-1":
                    cadena = " 0000 "
                else:
                    cadena = " "

            #Linea no reconocida
            else:
                cadena = ((" "*27) + linea + "\n" + (" "*27) + "^ ERROR 009 INSTRUCCION CARECE DE ALMENOS"
                          + " UN ESPACIO RELATIVO AL MARGEN\n")
                aux.write(cadena)
                continue
        
        #Escribiendo la cadena generada en el archivo auxiliar
        aux.write(cadena + "\n")
    
    #Comprobando si el programa contenia una etiqueta END
    if not existeEnd:
        aux.write("\n" + (" "*27) + "^ ERROR 010 NO SE ENCUENTRA END")
        
    #Cerrando los archivos ocupados
    aux.close()
    archivo.close()
    
    #Obteniendo la informacion del archivo auxiliar
    aux = open("Auxiliar.lst","r")
    textoArchivo = aux.readlines()
    n = str(len(textoArchivo))

    #Creando el archivo final
    lst = open(nombreArchivo + ".lst","w")

    #Leyendo el archivo auxiliar
    for i,linea in enumerate(textoArchivo,1):
        linea = linea.rstrip()
        
        #Bandera para saber si la memoria debe modificarse
        modificarMemoria = 0
        
        if linea.startswith("%"):
            linea = linea[1:].strip()
            
            #Obteniendo la informacion almacenada en la linea de texto
            info = linea.split("%")
            
            #Buscando la etiqueta del salto relativo y obteniendo su valor en memoria
            try:
                final = int(etiquetas[info[0]].upper(),16)
            #No existe la etiqueta
            except:
                cadena = ((" "*27) + info[4] + "\n" + (" "*27) + "^ ERROR 003 ETIQUETA INEXISTENTE\n")
                lst.write(cadena)
                continue
            
            #Obteniendo el valor de inicio del salto
            inicio = int(info[1],16)
            
            #Calculando el salto
            if final >= inicio:
                resta = final - inicio
                 #Verificando que este en un rango valido
                if resta <= 128:
                    #De ser necesario, se adicionan ceros para mantener el formato
                    complemento = hex(resta)[2:].zfill(2)
                else:
                    cadena = ((" "*27) + info[4] + "\n" + (" "*27) + "^ ERROR 008 SALTO RELATIVO MUY LEJANO\n")
                    lst.write(cadena)
                    continue
            else:
                #Obteniendo la resta
                resta = inicio - final
                
                #Verificando que este en un rango valido
                if resta <= 127:
                    #Obteniendo el numero en binario
                    binario = bin(resta)[2:].zfill(8)
                    #Obteniendo el negado del numero en binario
                    negado = "0b" + ''.join(["1" if bit is "0" else "0" for bit in binario])
                    #Obteniendo el complemento a 2
                    complemento = hex(int(negado,2) + 1)[2:].zfill(2)
                else:
                    cadena = ((" "*27) + info[4] + "\n" + (" "*27) + "^ ERROR 008 SALTO RELATIVO MUY LEJANO\n")
                    lst.write(cadena)
                    continue
            
            #Obteniendo el resto de la cadena
            partes = info[4].split()
            
            #Generando el compilado
            compilado = info[3] + complemento.upper()
            
            #Dandole formato a la cadena
            cadena = (" " + info[2] + " " + compilado + (" "*(21-len(compilado))) 
                        + partes[0] + (" "*(10-len(partes[0]))) + partes[1] + " ")
            
            #Verificando si hay algo mas por escribir en la linea
            if len(partes) > 2:
                if partes[2].startswith("*"):
                    cadena += ((" "*(21-len(partes[1]))) + ' '.join([x for x in partes[2:]]))
                else:
                    cadena += partes[2] + ((" "*(22-len(partes[1])-len(partes[2]))))
                    if len(partes) > 3:
                        cadena += ' '.join([x for x in partes[3:]])
        
        elif linea.startswith("&"):
            linea = linea[1:].strip()
            
            #Obteniendo la informacion almacenada en la linea de texto
            info = linea.split("%")
            
            #Verificando si en vez de etiqueta era una variable mal escrita
            if info[3] not in ["BD","7E"]:
                cadena = ((" "*27) + info[4] + "\n" + (" "*27) + "^ ERROR 002 VARIABLE INEXISTENTE\n")
                lst.write(cadena)
                continue
            
            #Obteniendo la etiqueta
            etiqueta = info[0][1:] if info[0].startswith("&") else info[0]
            try:
                operando = etiquetas[etiqueta].upper()
            except:
                cadena = ((" "*27) + info[4] + "\n" + (" "*27) + "^ ERROR 003 ETIQUETA INEXISTENTE\n")
                lst.write(cadena)
                continue
                
            #Verificando si la etiqueta puede utilizarse con direccionamiento directo
            if info[0].startswith("&") and len(etiqueta) <= 2:
                modificarMemoria -= 1
                compilado = DIR[eq].upper() + operando.zfill(2)
            else:
                compilado = info[3] + operando.zfill(4)
        
            #Obteniendo el resto de la cadena
            partes = info[4].split()
            
            #Dandole formato a la cadena
            cadena = (" " + info[2] + " " + compilado + (" "*(21-len(compilado))) 
                        + partes[0] + (" "*(10-len(partes[0]))) + partes[1])
            
            #Verificando si hay un comentario
            if len(partes) > 2:
                cadena += ((" "*(22-len(partes[1]))) + ' '.join([x for x in partes[2:]]))
        else:
            if modificarMemoria != 0:
                memoria = int(linea[:5],16) + modificarMemoria
                cadena = " " + hex(memoria)[2:] + linea[5:]
            else:
                cadena = linea
        
        #Ajustando los espacios para el numero de linea en el archivo
        i = str(i)
        a =  (len(n)-len(i)+1)*" " + i + " A"
        lst.write(a + cadena + "\n")
    
    #Cerrando los archivos
    lst.close()
    aux.close()
    
    #Eliminando el archivo auxiliar
    r("Auxiliar.lst")
    
    #Preguntando al usuario si desea compilar otro archivo
    opcion = input("Si desea terminar la ejecucion del programa escriba N: ")
    if opcion.upper() == "N":
        break