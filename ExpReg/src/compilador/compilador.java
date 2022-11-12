package compilador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class compilador {
    public
        // declaración de variables globales
        int i = 0;
        String Testo = "";
        int tamanio;
        boolean identiValido = false;
        boolean numValido = false;
        ArrayList<String> linea= new ArrayList<String>();
        ArrayList<String> valid = new ArrayList<String>();
               
        // Metodo para la lectura del txt
        public void leertesto() throws FileNotFoundException, IOException{
            File doc = new File("/Users/danieltovar/Downloads/Ejemplo.txt");
            Scanner obj = new Scanner(doc);
            while(obj.hasNextLine()){
                Testo = Testo + obj.nextLine();
            }
            tamanio = Testo.length();
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
            linea.add("<"); linea.add(">"); linea.add("="); linea.add(";");
            for(int i=0; i<30; i++){
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
                } else if((Integer.valueOf(Testo.charAt(i))>=59 && Integer.valueOf(Testo.charAt(i))<=62) || (Integer.valueOf(Testo.charAt(i))>=42 && Integer.valueOf(Testo.charAt(i))<=47) && (Integer.valueOf(Testo.charAt(i))!=44 && Integer.valueOf(Testo.charAt(i))!=46)){
                        Cad = Cad+Testo.charAt(i);
                        i++;
                        Evallave(Cad);
                }
            }
            return Cad;
        } 

        // Inicio de creacion de Metodos segun los diagramas 
        public void programa(){
            String token = Eval();
            if (token == "@INICIO"){
                Encabezado();
                Instrucciones();
                InstAux();
            } else {
                System.out.println("Error (1) se esperaba la palabra '@INICIO'.");
            }
        }

    // Constructor    
    public compilador(){
    }

    // Función Main
    public static void main(String[] args) throws Exception{
        // compilador metodo;
        // metodo = new compilador();
        // metodo.leertesto();
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
