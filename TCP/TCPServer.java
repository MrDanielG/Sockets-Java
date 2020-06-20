package app;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class TCPServer {
    public static void main(String argv[]) throws Exception {
        String fraseCliente = " ";
        String frase;
        double resultado;
        String operacion = null;
        String operandoA = null;
        String operandoB = null;
        ServerSocket welcomeSocket = new ServerSocket(6789);

        System.out.println("ECO-Server started... ");
        Socket connectionSocket = welcomeSocket.accept();

        InputStream is = connectionSocket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader inFromClient = new BufferedReader(isr);

        OutputStream os = connectionSocket.getOutputStream();
        DataOutputStream outToClient = new DataOutputStream(os);

        while (!fraseCliente.isEmpty()) {
            fraseCliente = inFromClient.readLine();
            System.out.println("Mensaje recibido: " + fraseCliente);

            if (!fraseCliente.isEmpty() && Character.isDigit(fraseCliente.charAt(0))) {
                StringTokenizer st = new StringTokenizer(fraseCliente, " ");

                while (st.hasMoreTokens()) {
                    String token = st.nextToken();
                    switch (token) {
                        case "+":
                            operacion = token;
                            break;

                        case "-":
                            operacion = token;
                            break;

                        case "*":
                            operacion = token;
                            break;

                        case "/":
                            operacion = token;
                            break;

                        case "^":
                            operacion = token;
                            break;

                        default:
                            if (operacion == null) {
                                operandoA = token;
                            } else {
                                operandoB = token;
                            }
                            break;
                    }
                }

                System.out.println("Operando A: " + operandoA);
                System.out.println("Operacion: " + operacion);
                System.out.println("Operando B: " + operandoB);

                resultado = operacion(operacion, operandoA, operandoB);
                System.out.println("Resultado: " + resultado);

                operacion = null;
                fraseCliente = String.valueOf(resultado);
            }

            frase = fraseCliente.toUpperCase() + '\n';
            outToClient.writeBytes(frase);
            welcomeSocket.close();
        }
        System.out.print("Mensaje vacio.. Conexion terminada.");
    }

    public static double operacion(String op, String _a, String _b) {
        double a = Double.parseDouble(_a);
        double b = Double.parseDouble(_b);
        double res = 0;

        switch (op) {
            case "+":
                res = a + b;
                break;

            case "-":
                res = a - b;
                break;

            case "*":
                res = a * b;
                break;

            case "/":
                res = a / b;
                break;

            case "^":
                res = Math.pow(a, b);
                break;

            default:
                System.out.println("Falla en operaciones");
                break;
        }
        return res;
    }
}
