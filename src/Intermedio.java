import java.util.ArrayList;
import java.util.Vector;

public class Intermedio {
    public static class TokInt{
        public String Token,valor;
        public int pos;
        TokInt(int pos ,String Token,String valor){
            this.Token = Token;
            this.pos  = pos;
            this.valor=valor;   
        }
    }
    private String SS="",DS="",CS=""; //Segmento de Pila, Datos y Codigo
    private int Temporal = 1;
    private ArrayList<TokInt> listaTokens = new ArrayList<TokInt>();
    private String [][] CodeSegment;
    Intermedio(Vector v){
    	
    	
        CodeSegment = getTokens(v); //Se toma lo importante de la lista de Tokens...
     
        System.out.println("\t--------------INTERMEDIO--------------");
        for (int i = 0;i < CodeSegment.length; i++) {
            String Tipo = CodeSegment[i][0];
            String Token = CodeSegment[i][1];
            
            if(CodeSegment[i][1]=="=") {
            	TokInt token = new TokInt(i,CodeSegment[i-1][1],CodeSegment[i+1][1]);
            	listaTokens.add(token);
            }
            if(CodeSegment[i][0]=="IDENTIFIER" || CodeSegment[i][0]=="INTEGER") {         	
            	if(CodeSegment[i-1][0]=="PLUS" || CodeSegment[i-1][0]=="MULTIPLY") {    
            		if(CodeSegment[i-2][0]=="IDENTIFIER" || CodeSegment[i-2][0]=="INTEGER") {
            			if(CodeSegment[i-3][0]=="PLUS" || CodeSegment[i-3][0]=="MULTIPLY") {
            				if(CodeSegment[i-4][0]=="IDENTIFIER" || CodeSegment[i-4][0]=="INTEGER") {
            					System.out.println("-------------------------Cuadruplo-------------------------");
            						System.out.println("                          "+CodeSegment[i-4][1]+CodeSegment[i-3][1]+CodeSegment[i-2][1]+CodeSegment[i-1][1]+CodeSegment[i][1]);
            							System.out.println("Operador            Operando1            Operando2            Res");
            					        int valOp1=0;
            					        int valOp2=0;
            					        int valOp3=0;
            							for (int k = 0;k < listaTokens.size(); k++) {
            			
            								if( listaTokens.get(k).Token.equals(CodeSegment[i][1]) && isNumeric(listaTokens.get(k).valor)) {
            									
												valOp1=Integer.parseInt(listaTokens.get(k).valor);	
											}
			        						if( listaTokens.get(k).Token.equals(CodeSegment[i-2][1]) && isNumeric(listaTokens.get(k).valor)) {
			        							valOp2=Integer.parseInt(listaTokens.get(k).valor);	
			        							
            					        	}
            					        	if(listaTokens.get(k).Token.equals(CodeSegment[i-4][1] )&& isNumeric(listaTokens.get(k).valor)) {
            					        		valOp3=Integer.parseInt(listaTokens.get(k).valor);
            					        		
            					        	}
            					        }
            							
            							if(isNumeric(CodeSegment[i][1])) {
            								valOp1=Integer.parseInt(CodeSegment[i][1]);
            							}
										if(isNumeric(CodeSegment[i-2][1])) {
											valOp2=Integer.parseInt(CodeSegment[i-2][1]);
										}
										if(isNumeric(CodeSegment[i-4][1])) {
											valOp3=Integer.parseInt(CodeSegment[i-4][1]);
            							}
            							int res1=0;
            							int res2=0;
            							
            							if(CodeSegment[i-3][1].equals("*") ) {
            								res1=valOp1*valOp2;
            							}
            							if(CodeSegment[i-3][1].equals("+")) {
            								res1=valOp1+valOp2;
            							}
            							if(CodeSegment[i-1][1].equals("*")) {
            								res2=res1*valOp3;
            							}
            							if(CodeSegment[i-1][1].equals("+")) {
            								res2=res1+valOp3;
            							}
            							
            							
            							System.out.println("    "+CodeSegment[i-3][1]+"                " +"   "+CodeSegment[i-4][1]+"                " +"   "+CodeSegment[i-2][1]+"   "+"              T1"   );
            							System.out.println("    "+CodeSegment[i-1][1]+"                " +"   "+"T1"+"               " +"   "+CodeSegment[i][1]+"   "+"               "+CodeSegment[i-6][1]   );
            							System.out.println("                                 "+CodeSegment[i-6][1]+":=" + res2);
            							
            							
            							for (int k = 0;k < listaTokens.size(); k++) {
            								if( listaTokens.get(k).Token.equals(CodeSegment[i-6][1]) && isNumeric(listaTokens.get(k).valor)) {
													listaTokens.get(k).valor= Integer.toString(res2);
											}
			    
            					        }
            							
            				}
            				
            			}
            		}
            		
            	}    	
            }
        }  
            
    }

    private String[][] getTokens(Vector v){//Vector de Vectores... [[CLASS,class],[IDENTIFICADOR, haha], ... ]
        String[][]mat = new String[v.size()-16][2];
        for (int i = 0;i < v.size() - 2; i++) {
            if(i > 13) {
                Vector temp = (Vector) v.elementAt(i);
                mat[i-14][0] = temp.elementAt(0).toString();
                mat[i-14][1] = temp.elementAt(1).toString();
                //System.out.println(mat[i-14][0]+"\t"+mat[i-14][1]);
            }
        }
        return mat;
    }
    
    private static boolean isNumeric(String cadena){
    	try {
    		Integer.parseInt(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }

}

