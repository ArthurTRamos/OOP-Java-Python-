/*
 * Arthur Trottmann Ramos - 14681052
 * Movimentos do jogo: u (up), d (down), l (left), r (right)
 * Objetivo Final: Movimentar as peças até que o espaço vazio esteja no canto superior
 * esquerdo e os números que seguem estejam em ordem crescente
 */

import java.util.*; //Importa todas as classes do pacote java.util (como Scanner e Array)

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Objeto da classe Scanner para leitura de inputs
        ArrayList<Integer> numbers = new ArrayList<>(); //Objeto da classe do Array para armazenamento da posição do tabuleiro

        while (scanner.hasNextInt()) //Enquanto existir um int para ser lido
            numbers.add(scanner.nextInt()); //Armazena os valores do tabuleiro no array

        int size = (int) Math.sqrt(numbers.size()); //Determina as dimensões da matriz
        Table game = new Table(numbers, size);

        scanner.nextLine(); //Consome o caractere "\n"

        String operations = scanner.nextLine(); //Lê e armazena a string na variável operations

        game.printTable();
        
        for(int i = 0; i < operations.length(); i++) { //Itera pela quantidade de caracteres da string
            char operation = operations.charAt(i); //Lê e armazena cada caractere da string
            game.move(operation);
            game.printTable();
        }
        if(game.complete())
            System.out.println("Posicao final: true");
        else
            System.out.println("Posicao final: false");

        scanner.close(); //Finaliza a variável de leitura
    }
}
