package metodo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class metodo {
    public
        int i = 0;
        String Testo = "";
        int tamanio;
        String linea= "";
        boolean gatito=false;
        boolean arroba=false;
        boolean number_int=false;
        boolean number_dec=false;
        boolean errorDec=false;
        boolean comillacompleta=false;        
        
    public void leertesto() throws FileNotFoundException, IOException{
        File doc = new File("/Users/danieltovar/Downloads/Ejemplo.txt");
        Scanner obj = new Scanner(doc);
        Testo = obj.nextLine();
        tamanio = Testo.length();
        
    }

    public void primir(){
        System.out.print(linea);
        System.out.println(" ");
        linea=linea+" ";
    }
    public void Eval(){
        while(i!=tamanio){
            switch(Testo.charAt(i)){
                case '#':                                        // Identificador #[a-z]+        97-122
                    int ub = i;
                    for(int j=i+1; j<tamanio;j++){
                        char x = Testo.charAt(j);
                        int val = x;
                        if(val>=97 && val<=122){
                            gatito=true;
                        } else{
                            i=j;
                            j=tamanio;
                        }
                    }
                    if(gatito==true){
                        for(ub=ub; ub<i; ub++){
                            linea=linea+Testo.charAt(ub);
                        }
                        // System.out.println(linea);
                    }
                    gatito=false;
                    i++;
                break;


                case '@':                                       // Palabra reservada @[A-Z]+    65-90
                    int ubi = i;
                    for(int j=i+1; j<tamanio;j++){
                        char x = Testo.charAt(j);
                        int val = x;
                        if(val>=65 && val<=90){
                            arroba=true;
                        } else{
                            i=j;
                            j=tamanio;
                        }
                    }
                    if(arroba==true){
                        for(ubi=ubi; ubi<i; ubi++){
                            linea=linea+Testo.charAt(ubi);
                        }
                    } 
                    // System.out.println(linea);
                    arroba=false;
                    i++;
                break;


                                                                // lineaos [0-9]+ (.[0-9]*)?    46-57
                case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
                    int cont=0;
                    int ubIn = i;
                    int ubFin=0;
                    char x;
                    int val;
                    for(int j=i; j<tamanio;j++){
                        x = Testo.charAt(j);
                        val = x;
                        if(Testo.charAt(j)=='.'){
                            cont=cont+1;
                        }
                        if(val>57 || val<46){
                            ubFin=j;
                            break;
                        } else{
                            ubFin=tamanio;
                        }
                    }
                    
                    if(cont<=1){
                        for(int j=i; j<ubFin;j++){
                            x = Testo.charAt(j);
                            val = x;
    
                            if(val>57 || val<46 || val==47){
                                number_dec=false;
                                number_int=false;
                            } else{
                                if(cont==1){
                                    number_dec=true;
                                }else{
                                    number_int=true;
                                }
                            }
                        }
                    }else{
                        errorDec=true;
                        number_dec=false;
                        number_int=false;
                    }


                    if(number_dec==true) number_int=false;
                    if(errorDec==true){
                        number_dec=false;
                        number_int=false;
                    }   

                    if(number_dec==true){
                        for(ubIn=ubIn;ubIn<ubFin;ubIn++){
                            linea=linea+Testo.charAt(ubIn);
                        }
                    } 

                    if(number_int==true){
                        for(ubIn=ubIn;ubIn<ubFin;ubIn++){
                            linea=linea+Testo.charAt(ubIn);
                        }
                    }
                    // System.out.println(linea);
                    number_dec=false;
                    number_int=false;
                    errorDec=false;
                    i = tamanio;

                break;

                case '=':
                    if(Testo.charAt(i+1)=='='){
                        linea=linea+Testo.charAt(i)+Testo.charAt(i+1);
                        // System.out.print(" estamos comparando xD ");
                        i=i+2;
                    } else{
                        // System.out.print(" Estamos asignando ");
                        linea=linea+Testo.charAt(i);
                    }
                    i++;
                break;

                case '!':
                    if(Testo.charAt(i+1)=='='){
                        linea=linea+Testo.charAt(i)+Testo.charAt(i+1);
                        //System.out.print(" Es diferente de ");
                        i=i+2;
                    } else{
                        linea=linea+Testo.charAt(i);
                        //System.out.print(" Oshthia loco, que es esto?? ");
                    }
                    i++;
                break;

                case '<':
                    if(Testo.charAt(i+1)=='='){
                        linea=linea+Testo.charAt(i)+Testo.charAt(i+1);
                        //System.out.print(" Es menor o igual que ");
                        i=i+2;
                    } else{
                        linea=linea+Testo.charAt(i);
                        //System.out.print(" es menor que ");
                    }
                    i++;
                break;

                case '>':
                    if(Testo.charAt(i+1)=='='){
                        linea=linea+Testo.charAt(i)+Testo.charAt(i+1);
                        //System.out.print(" Es mayor o igual que ");
                        i=i+2;
                    } else{
                        linea=linea+Testo.charAt(i);
                        //System.out.print(" es mayor que ");
                    }
                    i++;
                break;

                case '&':
                    if(Testo.charAt(i+1)=='&'){
                        linea=linea+Testo.charAt(i)+Testo.charAt(i+1);
                        //System.out.print(" AND ");
                        i=i+2;
                    } else{
                        //linea=linea+Testo.charAt(i);
                        System.out.print(" Oshthia loco, que es esto?? ");
                    }
                    i++;
                break;

                case '|':
                    if(Testo.charAt(i+1)=='|'){
                        linea=linea+Testo.charAt(i)+Testo.charAt(i+1);
                        //System.out.print(" OR ");
                        i=i+2;
                    } else{
                        //linea=linea+Testo.charAt(i);
                        System.out.print(" Oshthia loco, que es esto?? ");
                    }
                    i++;
                break;
                
                case ',':
                    linea=linea+Testo.charAt(i);
                    i++;
                break;

                case ';':
                    linea=linea+Testo.charAt(i);
                    i++;
                break;

                case '{':
                    linea=linea+Testo.charAt(i);
                    i++;
                break;

                case '}':
                    linea=linea+Testo.charAt(i);
                    i++;
                break;

                case '(':
                    linea=linea+Testo.charAt(i);
                    i++;
                break;

                case ')':
                    linea=linea+Testo.charAt(i);
                    i++;
                break;

                case '+':
                    linea=linea+Testo.charAt(i);
                    i++;
                break;

                case '-':
                    linea=linea+Testo.charAt(i);
                    i++;
                break;

                case '*':
                    linea=linea+Testo.charAt(i);
                    i++;
                break;

                case '/':
                    linea=linea+Testo.charAt(i);
                    i++;
                break;

                

                default:
                char y = Testo.charAt(i);
                int valor = y;
                System.out.println(y);
                if(valor==39){
                    if(comillacompleta==false){
                        int j=i+1;
                        linea=linea+Testo.charAt(i);
                        do{
                            linea=linea+Testo.charAt(j);
                            char z = Testo.charAt(j);
                            int valo = z;
                            if(valo==39){
                                comillacompleta=true;
                                i=j;
                            }
                            j++;
                        }while(valor!=39);
                    }
                }
                i++;
                break;
            }  
        }

    }

public metodo(){
}
public static void main(String[] args) throws Exception{
    metodo fase2;
    fase2 = new metodo();
    fase2.leertesto();
    fase2.Eval();
    fase2.primir();
}
}