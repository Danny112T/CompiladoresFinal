package compilador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.print.attribute.standard.PrinterInfo;

public class compilador {
    public
        // declaración de variables globales
        int i = 0;
        String Testo = "";
        int tamanio;
        int tamCad;
        boolean identiValido = false;
        boolean numValido = false;
        ArrayList<String> linea = new ArrayList<String>();
        ArrayList<String> valid = new ArrayList<String>();
        ArrayList<String> errores = new ArrayList<String>();
               
        public void printError(int num){
            errores.add("Error (0) se esperaba la palabra '@INICIO'.");
            errores.add("Error (1) se esperaba un identificador valido o la palabra '@FINAL'");
            errores.add("Error (2) se esperaba un ';'");
            errores.add("Error (3) se esperaba un identificador valido");
            errores.add("Error (4) se esperaba la palabra '@CONST'");
            errores.add("Error (5) se esperaba un '='");
            errores.add("Error (6) se esperaba un '('");
            errores.add("Error (7) se esperaba un ')'");
            errores.add("Error (8) se esperaba la palabra '@ENTONCES'");
            errores.add("Error (9) se esperaba un '{'");
            errores.add("Error (10) se esperaba un '}'");
            errores.add("Error (11) se esperaba un ':'");
            errores.add("Error (12) se esperaba un numero valido");
            errores.add("Error (13) se esperaba un ','");
            errores.add("Error (14) se esperaba un '@'");
            errores.add("Error (15) el tipo de dato no es valido");
            errores.add("Error (16) se esperaba la palabra '@RET'");
            errores.add("Error (17) se esperaba la palabra '@FUNC'");
            errores.add("Error (18) se esperaba un '++' o un '--'");
            errores.add("Error (19) se esperaba un numero o un identificador valido");
            errores.add("Error (20) se esperaba un operador logico valido");
            errores.add("Error (21) se esperaba un simbolo logico valido");
            errores.add("Error (22) se esperaba un operador aritmetico valido");
            System.out.println(errores.get(num));
        }

        // Metodo para la lectura del txt
        public void leertesto() throws FileNotFoundException, IOException{
            File doc = new File("/Users/danieltovar/Downloads/Ejemplo.txt");
            Scanner obj = new Scanner(doc);
            while(obj.hasNextLine()){
                Testo = Testo + obj.nextLine();
            }
            tamanio = Testo.length();
        }

        public void devolver(){
            i = tamCad;
        }

        // Metodo para la evaluacion de las palabras reservadas
        public void Evallave(String E){      // Palabras reservadas
            boolean bandera = false;    
            linea.add("@CONS"); linea.add("@VAR"); linea.add("@ENT");
            linea.add("@DEC"); linea.add("@CAR"); linea.add("@CAD");
            linea.add("@BIN"); linea.add("@VOID"); linea.add("@LLAMAR");
            linea.add("@SI"); linea.add("@SINO"); linea.add("@FOR");
            linea.add("@AND"); linea.add("@OR"); linea.add("@DIF");
            linea.add("@IGUAL"); linea.add("@FLOTANTE"); linea.add("@ENTONCES");
            linea.add("@RETORNO"); linea.add("@MENORIGUAL"); linea.add("@MAYORIGUAL"); 
            linea.add("+"); linea.add("*"); linea.add("-"); linea.add("/"); 
            linea.add("<"); linea.add(">"); linea.add("="); linea.add(";"); //29
            linea.add("@INICIO");
            for(int i=0; i<=30; i++){
                if(E.equals(linea.get(i))){                    
                    bandera = true;
                    break;
                }                       
            }            
            if(bandera == true){
                valid.add(E);
                    //System.out.println(E+" Es valido y es una palabra reservada");
                }else{
                    //System.out.println(E+" No es valido pero tiene nombre de palabra reservada");
            }
        }

        // Metodo para evalular las expresiones regulares
        public String Eval(){
            String Cad ="";
            identiValido =false ;
            numValido = false ;
            int contpt = 0;
            if(i < tamanio){
                int pos = Testo.charAt(i);
                // elimina o pasa por alto los espacios y saltos de linea
                if(pos == 32 || pos == 10 || pos == 13){
                    i++;
                    return Eval();
                }


                if(Testo.charAt(i)=='#'){     // Identificadores 
                    Cad="";
                    Cad = Cad+Testo.charAt(i);
                    tamCad=i-1;
                    i++;
                    while(i < tamanio){
                        if(Testo.charAt(i)>=97 && Testo.charAt(i)<=122){
                            Cad= Cad+Testo.charAt(i);
                            i++;
                        }else {
                            valid.add(Cad);
                            break;
                        }
                    }
                    identiValido = true;
                } else if(Testo.charAt(i)=='@'){    // Palabra reservada
                        Cad="";
                        Cad = Cad+Testo.charAt(i);
                        tamCad=i-1;
                        i++;
                        
                        while(i < tamanio){
                            pos = Testo.charAt(i);
                            if(Testo.charAt(i)>=65 && Testo.charAt(i)<=90){
                                Cad= Cad+Testo.charAt(i);
                                i++;
                            }else { 
                                valid.add(Cad);
                                break;
                            }
                        }
                        Evallave(Cad);
                } else if(Testo.charAt(i)=='.' || (Integer.valueOf(Testo.charAt(i))>=48 && Integer.valueOf(Testo.charAt(i))<=57)){   // Numeros
                        Cad="";
                        tamCad=i-1;
                        while(i<tamanio){
                            if(Testo.charAt(i)=='.') contpt++;
                            if((Testo.charAt(i)=='.' || (Testo.charAt(i)>=48 && Testo.charAt(i)<=57)) && contpt<2){
                                Cad=Cad+Testo.charAt(i);
                                i++;
                            }else{
                                valid.add(Cad);
                                break;
                            }
                        }
                        numValido = true;
                } else if((Integer.valueOf(Testo.charAt(i))>=59 /*;<>= */ && Integer.valueOf(Testo.charAt(i))<=62) || (Integer.valueOf(Testo.charAt(i))>=42 && /*aritmeticos */ Integer.valueOf(Testo.charAt(i))<=47) && (Integer.valueOf(Testo.charAt(i))!=46)){
                        Cad = Cad+Testo.charAt(i);
                        tamCad=i-1;
                        i++;
                        Evallave(Cad);
                }
            }
            return Cad;
        } 

        // Inicio de creacion de Metodos segun los diagramas 
        public void programa(){             //Metodo programa
            String token = Eval();
            if (token.equals("@INICIO")){
                Encabezado();
                Instrucciones();
                token = Eval();
                if(token.equals(";")){
                    InstAux();
                } else{
                    printError(2);
                }
            } else {
                printError(0);
            }
        }

        public void InstAux(){              //Metodo InstAux
            //Leen cadena 
            //Validar los first de Instrucciones
            //Regresan a la palabra anterior 
            Instrucciones();
            String token = Eval();
            if(token.equals("@FINAL")){
                // NADA
            } else if(token.equals(";")){
                InstAux();
            } else{
                printError(1);
            }
        }

        public void Encabezado(){           //Metodo Encabezado
            consAux();
            varAux();
            FuncAux();
        }

        public void consAux(){             //Metodo AuxConst
            String token = Eval();
            if(!token.equals("@") && !token.equals("@FUNC") && !token.equals("@LLAMA") && !token.equals("@LEER") && !token.equals("@ESCRIBIR") && !token.equals("@SI") && !token.equals("@POR")){
                if(token.equals("@CONS")){
                    token = Eval();
                    if(identiValido==true){
                        token = Eval();
                        if(token.equals("=")){
                            //validar si es Num
                            token = Eval();
                            if(numValido == true){
                                aux1();
                                token = Eval();
                                if(token.equals(";")){
                                    consAux();
                                } else {
                                printError(2);
                                }
                            } else{
                                printError(5);
                            } 
                        } else{
                            printError(5);
                        }
                    } else{
                        printError(3);
                    }
                } else{
                    printError(4);
                }
            } else{
                devolver();
                //regresar cadena, una anterior
            }
        }

        public void aux1(){                // Metodo aux1
            String token = Eval();
            if(token.equals(",")){
                token = Eval();
                if(identiValido == true){
                    token = Eval();
                    if(token.equals("=")){
                        token = Eval();
                        if(numValido==true){
                            aux1();
                        } else{
                            printError(12);
                        }
                    } else{
                        printError(5);
                    }
                } else{
                    printError(3);
                }
            } else{
                printError(13);
            }
        }

        public void varAux(){              // metodo varAux
            String token = Eval();
            if(token.equals("@")){
                tipo();
                token = Eval();
                if(identiValido == true){
                    aux2();
                    token = Eval();
                    if(token.equals(";")){
                        varAux();
                    } else{
                        printError(2);
                    }
                } else{
                    printError(3);
                }
            }else{
                printError(14);
            }
        }
    
        public void tipo(){             // Metodo tipo
            String token = Eval();
            switch (token) {
                case "@ENT": case "@DEC": case "@CAR": case "@CAD": case "BIN":

                    break;
                default:
                    printError(15);
                    break;
            }
        }

        public void aux2(){             // Metodo aux2
            String token = Eval();
            if(!token.equals(";")){
                if(token.equals(",")){
                    token = Eval();
                    if(identiValido == true){
                        aux2();
                    } else{
                        printError(3);
                    }
                } else{
                    printError(13);
                }
            } else{
                devolver();
            }
        }

        public void FuncAux(){          // Metodo FuncAux
            String token = Eval();
            if(!token.equals("@LLAMA") && !token.equals("@LEER") && !token.equals("@ESCRIBIR") && !token.equals("@SI") && !token.equals("@FOR")){
                if(token.equals("@FUNC")){
                    token = Eval();
                    if(identiValido==true){
                        token = Eval();
                        if(token.equals("(")){
                            token = Eval();
                            if(token.equals(")")){
                                token=Eval();
                                if(token.equals("{")){
                                    Encabezado();
                                    Instrucciones();
                                    token = Eval();
                                    if(token.equals(";")){
                                        InstAux2();
                                        token = Eval();
                                        if(token.equals("@RET")){
                                            AsigAux();
                                            token = Eval();
                                            if(token.equals("}")){
                                                token = Eval();
                                                if(token.equals(";")){
                                                    FuncAux();
                                                } else{
                                                    printError(2);
                                                }
                                            } else{
                                                printError(10);
                                            } 
                                        } else{
                                            printError(16);
                                        }
                                    } else{
                                        printError(2);
                                    }
                                } else{
                                    printError(9);
                                }
                            } else{
                                printError(7);
                            }
                        } else{
                            printError(6);
                        }
                    } else{
                        printError(3);
                    }
                } else{
                    printError(i);
                }
            } else{
                devolver();
                //Regresar cadena
            }
        }

        public void InstAux2(){         // Metodo InstAux2
            String token = Eval();
            if(!token.equals("@RET")){
                token = Eval();
                if(token.equals(";")){
                    Instrucciones();
                    InstAux2();
                } else{
                    printError(2);
                }
            }else{
                devolver();
                //regresar cadena
            }
        }

        public void AsigAux(){          // Metodo AsigAux
            String token = Eval();      // Hasta que funcione el Expre
            if(identiValido==true){
                Expre();
            } else if(numValido==true){   
                Expre();
            } else{
                printError(19);
            }
            
        }

        public void Instrucciones(){       // metodo instrucciones
            String token = Eval();
            switch (token) {
                case "@SI":                 // metodo "@SI"
                    token = Eval();
                    if(token.equals("(")){
                        condicion();
                        token = Eval();
                        if(token.equals(")")){
                            token = Eval();
                            if(token.equals("{")){
                                Instrucciones();
                                token = Eval();
                                if(token.equals(";")){
                                    InstAux2();
                                    token = Eval();
                                    if(token.equals("}")){
                                        Sino();
                                    } else{
                                        printError(10);
                                    }
                                }else {
                                    printError(2);
                                }
                            } else{
                                printError(9);
                            }
                        } else{
                            printError(7);
                        }
                    } else{
                        printError(6);
                    }
                    break;
            
                case "@FOR":                // metodo "@FOR"
                    token = Eval();
                    if(token.equals("(")){
                        tipoFor();
                        token = Eval();
                        if(identiValido==true){
                            token = Eval();
                            if(token.equals("=")){
                                AsigAux();
                                token = Eval();
                                if(token.equals(":")){
                                    condicion();
                                    token = Eval();
                                    if(token.equals(":")){
                                        token = Eval();
                                        if(identiValido==true){
                                            forAux();
                                            token = Eval();
                                            if(token.equals("{")){
                                                Instrucciones();
                                                token = Eval();
                                                if(token.equals(";")){
                                                    InstAux2();
                                                    token = Eval();
                                                    if(token.equals("}")){
                                                    } else{
                                                        printError(10);
                                                    }
                                                } else{
                                                    printError(2);
                                                }
                                            } else{
                                                printError(9);
                                            }
                                        } else{
                                            printError(3);
                                        }
                                    } else{
                                        printError(11);
                                    }
                                }else{
                                    printError(11);
                                }
                            }else{
                                printError(5);
                            }
                        } else{
                            printError(3);
                        }
                    } else{
                        printError(6);
                    }
                    break;

                case "@LLAMA":  //Metodo @LLAMA  
                    token= Eval();
                    if(identiValido==true){
                        token = Eval();//vgubh
                    } else {
                        printError(3);
                    }
                    break;

                case "@LEER":  // METODO @LEER
                    token= Eval();
                    if(identiValido==true){
                        token = Eval();//vgybhnj
                    } else {
                        printError(3);
                    }
                    break;

                case "@ESCRIBIR":  // METODO @ESCRIBIR
                    token= Eval();
                    if(identiValido==true){
                        token = Eval(); // dasda
                    } else {
                        printError(3);
                    }
                    break;
                
                default:
                    if(identiValido==true){
                        token = Eval();
                        if(token.equals("=")){
                            AsigAux();
                        } else{
                            printError(5);
                        }
                    } else{
                        printError(3);
                    }
                    break;
            }
        }

        public void Sino(){
            String token = Eval();
            if(!token.equals("@LLAMA") && !token.equals("@LEER") && !token.equals("@ESCRIBIR") && !token.equals("@SI") && !token.equals("@FOR")){
                if(token.equals("@SINO")){
                    if(token.equals("{")){
                        Instrucciones();
                        token = Eval();
                        if(token.equals(";")){
                            InstAux2();
                            token = Eval();
                            if(token.equals("}")){
                            } else{
                                printError(10);
                            }
                        } else{
                            printError(2);
                        }
                    } else{
                        printError(9);
                    }
                }
            } else{
                devolver();
                //regresar cadena
            }
        }

        public void tipoFor(){                  // Metodo tipoFor
            String token = Eval();
            if(token.equals("@ENT") || token.equals("@DEC")){

            } else{
                printError(15);
            }
        }

        public void forAux(){               //metodo forAux
            String token = Eval();
            token = token + Eval();
            if(token.equals("++") || token.equals("--")){

            } else{
                printError(18);
            }
        }

        public void condicion(){            // metodo condicion
            String token = Eval();
            if(!token.equals(")")){
                if(identiValido==true || numValido==true){
                    SimRel();
                    condAux();
                    auxLog();
                } else{
                    printError(19);
                }
            } else{
                devolver();
            }
            
        }

        public void condAux(){                  // metodo condAux
            String token = Eval();
            if(numValido == true || identiValido==true){

            }else{
                printError(19);
            }
        }

        public void auxLog(){               // metodo auxLog
            /*
             * si tiene epsilon, se pone token = eval()
             * if() con los follows
             *  regresar en el if y en else
             *  if reglas
             */
            String token = Eval();
            if(!token.equals(")")){
                devolver();
                simLog();
                condicion();
            } else{
                devolver();
            }
        }

        public void Expre(){                // metodo Expre
            simArit();
            AsigAux();
        }

        public void SimRel(){           // metodo SimRel
            String token = Eval();
            switch (token) {
                case "@IGUAL":
                    
                    break;

                case "@MENORIGUAL":
                    
                    break;

                case "@MAYORIGUAL":
                    
                    break;

                case "@DIF":
                    
                    break;

                case "<":
                    
                    break;

                case ">":
                    
                    break;
                default:
                    printError(20);
                    break;
            }
        }

        public void simLog(){       // Metodo simLog
            String token = Eval();
            if(token.equals("@AND") || token.equals("@OR")){

            } else{
                printError(i);
            }
        }
        
        public void simArit(){
            String token = Eval();
            switch (token) {
                case "+":
                    
                    break;
            
                case "-":
                    
                    break;

                case "*":
                    
                    break;

                case "/":
                    
                    break;
                
                default:
                    printError(22);
                    break;
            }
        }


    // Constructor    
    public compilador(){
    }

    // Función Main
    public static void main(String[] args) throws Exception{
        compilador metodo;
        metodo = new compilador();
        metodo.leertesto();
        metodo.programa();
        // System.out.println(metodo.Eval());
        // System.out.println(metodo.Eval());
        // System.out.println(metodo.Eval());
        // System.out.println(metodo.Eval());
        // System.out.println(metodo.Eval());
        // System.out.println(metodo.Eval());
        // System.out.println(metodo.Eval());
        // System.out.println(metodo.Eval());
        // System.out.println(metodo.Eval());
        // System.out.println(metodo.Eval());
        // System.out.println(metodo.Eval());
        // System.out.println(metodo.Eval());
        // System.out.println(metodo.Eval());
        // System.out.println(metodo.Eval());
        // System.out.println(metodo.Eval());
        // System.out.println(metodo.Eval());


    }
}
/* ---------------------------------------------- NOTAS ---------------------------------------------------- 
 *      - Programa, dentro de un eval genera la cadena que va a un if, sino va a un error
 *          (numero de error(?)) sino quiere un metodo de error un cout "Se esperaba la palabra programa"
 *      
 *      - eval solo se llama cuando se utiliza un terminal osease los parentesis, menormayorque
 * 
 *      + Recomendacion del Yisus: metodo que guarde todo lo que encuentre
 */
