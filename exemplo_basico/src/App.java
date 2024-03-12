import java.io.IOException;
import java.util.Scanner;



// public class App {

//     public static void clearScreen() throws IOException, InterruptedException {
//         new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//     }
/*Descobrindo a área de um retàngulo */
//     public static void main(String[] args) throws Exception {
//         clearScreen();
//         Scanner scan = new Scanner(System.in);
//         System.out.print("Digite o valor do primeiro lado: ");
//         double l1 = scan.nextDouble();
//         System.out.print("Digite o valor do segundo lado: ");
//         double l2 = scan.nextDouble();
//         double area = l1 * l2;
//         System.out.format("A área do retângulo de lados %f  e %f é %f.%n",
//             l1, l2, area);
//         scan.close();
//     }
// }

//     public static void main(String[] args) throws Exception {
//         clearScreen();
//         Scanner scan = new Scanner(System.in);
//         System.out.print("Digite o primeiro valor: ");
//         double p1 = scan.nextDouble();
//         System.out.print("Digite o segundo valor: ");
//         double p2 = scan.nextDouble();
//         System.out.format("A soma dos valores são: %f.%n", p1 + p2);
//         System.out.format("A subtração dos valores são: %f.%n", p1 - p2);
//         System.out.format("A multiplicação dos valores são: %f.%n", p1 * p2);
//         System.out.format("A divisão dos valores são: %f.%n", p1 / p2);
//         scan.close();
//     }
// }


/*Quantidade de n primos */
//     public static void main(String[] args) throws Exception {
//         clearScreen();
//         Scanner scan = new Scanner(System.in);
//         System.out.print("Digite o valor inteiro: ");
//         int l1 = scan.nextInt();
//         int primos = 0;
//         int valorPrimo = 2;
//         while (valorPrimo <= l1) {
//             boolean ehPrimo = true;
//             for (int i = 2; i < valorPrimo; i++){
//                 if (valorPrimo % i == 0) {
//                     ehPrimo = false;
//                     break;
//                 }
//             }
//             if (ehPrimo) {
//                 primos++;
//         }
//         valorPrimo++;
//         }
//         System.out.format("A quantidade de primos até %d é de %d.%n", l1, primos);
//         scan.close();
//     }
// }


        /*Multiplicação dos índices de um vetor*/
        // public class App {
           
        
            
        //     private static int[] lerVetor() {
        //         Scanner scan = new Scanner(System.in);
        //         int[] vetor = new int[3];
        //         for (int i = 0; i < vetor.length; i++){   
        //             System.out.print("Digite o valor do indice: " + i + ": ");
        //             vetor[i] = scan.nextInt();
        //         }
        //     return vetor;
        //     }

        //     private static int[] multiplicar(int[] v1, int[] v2) {
        //         int[] resultado = new int[v1.length];
        //         for (int i = 0; i < v1.length; i++){
        //             resultado[i] = v1[i] * v2[i];
        //         }
        //     return resultado;
        //     }

        //     private static int[] mostrarVetor(int[] vetor){
        //         System.out.print("Vetor: [");
        //         for (int i = 0; i < vetor.length; i++) {
        //             System.out.print(vetor[i]);
        //             if (i < vetor.length - 1) {
        //                 System.out.print(", ");
        //             }
        //         }
        //         System.out.println("]");
        //         return vetor;
        //     }
        

        //     public static void main(String[] args) {

        //         System.out.println("Digite os valores do vetor A:");
        //         int[] vetorA = lerVetor();
        
        //         System.out.println("Digite os valores do vetor B:");
        //         int[] vetorB = lerVetor();
        
        //         int[] resultado = multiplicar(vetorA, vetorB);
        //         System.out.println("Vetor A:");
        //         mostrarVetor(vetorA);
        
        //         System.out.println("Vetor B:");
        //         mostrarVetor(vetorB);
        
        //         System.out.println("Resultado da multiplicação:");
        //         mostrarVetor(resultado);

        //     }


        // }

            


        public class App {
           
            
            private static int[] opcoes() {
                Scanner scan = new Scanner(System.in);

                System.out.println("Digite o tamanho do vetor: ");
                int entrada = scan.nextInt();
                int[] vetor = new int [entrada];

                for (int i = 0; i < vetor.length; i++){
                    System.out.format("Digite o valor do índice %d:", i);
                    int valor = scan.nextInt();
                    vetor[i] = valor;
                }
                System.out.println("Digite qual opção você deseja: ");
                int opcao = scan.nextInt();
                switch (opcao) {
                    case 1:
                    int menor = Integer.MAX_VALUE;
                    for (int elemento : vetor) {
                        if (elemento < menor) {
                            menor = elemento;
                        }
                    }
                    System.out.format("O menor valor é: %d%n", menor);
                    break;

                    case 2:
                    int maior = Integer.MIN_VALUE;
                    for (int elemento : vetor) {
                        if (elemento > maior) {
                            maior = elemento;
                        }
                    }
                    System.out.format("O maior valor é: %d%n", maior);
                    break;

                    case 3:
                    int soma = 0;
                    for (int elemento : vetor){
                        soma += elemento;
                    }
                    System.out.format("A soma dos índices é: %d", soma); 
                    break;

                    default:
                    if (opcao < 1 || opcao > 3) {
                        System.out.println("Opção não existente.");    
                    }
                        break;
                }
                scan.close();
                return vetor;
            }

            public static void main(String[] args) {
                opcoes();
                
            }
        }
    