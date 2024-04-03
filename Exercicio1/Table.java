import java.util.*;

public class Table {
    private int[][] table; //Matriz com números do tabuleiro
    private int row; // Linha do espaço vazio
    private int column; // Coluna do espaço vazio

    /*public Table() { // Construtor padrão
        over = false;
        table = new int[3][3];

        table[0][0] = 1;
        table[0][1] = 4;
        table[0][2] = 2;
        table[1][0] = 3;
        table[1][1] = 0;
        table[1][2] = 5;
        table[2][0] = 6;
        table[2][1] = 7;
        table[2][2] = 8;

        row = 1;
        column = 1;
    }*/

    public Table(ArrayList<Integer> numbers, int size) { //Construtor com posição predefinido 
        table = new int[size][size];
        int index = 0;

        for(int i = 0; i < table.length; i++) {
            for(int j = 0; j < table.length; j++) {
                table[i][j] = numbers.get(index++);
                if(table[i][j] == 0) {
                    row = i;
                    column = j;
                }
            }
        }
    }

    public void move(char operation) { //Realiza trocas na matriz conforme a operação
        switch (operation) {
            case 'u': //Troca entre 0 e seu valor abaixo
                if(row + 1 <= table.length - 1) {
                    int number = table[row+1][column];
                    table[row][column] = number;
                    table[row+1][column] = 0;
                    row++;
                }
                break;
            case 'l': //Troca entre 0 e seu valor à esquerda
                if(column + 1 <= table.length - 1) {
                    int number = table[row][column+1];
                    table[row][column] = number;
                    table[row][column+1] = 0;
                    column += 1;
                }
                break;
            case 'd': //Troca entre 0 e seu valor acima
                if(row - 1 >= 0) {
                    int number = table[row-1][column];
                    table[row][column] = number;
                    table[row-1][column] = 0;
                    row--;
                }
                break;
            case 'r': //Troca entre 0 e seu valor à direita
                if(column - 1 >= 0) {
                    int number = table[row][column-1];
                    table[row][column] = number;
                    table[row][column-1] = 0;
                    column -= 1;
                }
                break;
        }
    }

    public boolean complete() { //Verifica se os números estão em ordem crescente (completo)
        int number = 0;

        for(int i = 0; i < table.length; i++) {
            for(int j = 0; j < table.length; j++) {
                if(table[i][j] != number)
                    return false;
                number++;
            }
        }
        return true;
    }

    public void printTable() { //Imprime o tabuleiro conforme as formatações pedidas
        int index1, index2; //índices da matriz
        index1 = index2 = 0;

        for(int k = 0; k < table.length; k++) {
            for(int i = 0; i < table.length; i++) {
                System.out.print("+");
                for(int j = 0; j < 6; j++)
                    System.out.print("-");
            }
            System.out.println("+");
            
            for(int i = 0; i < table.length; i++) {
                if(table[index1][index2] >= 10)
                    System.out.print("|  ");
                else
                    System.out.print("|   ");
                System.out.print(table[index1][index2++] + "  ");
            }
            System.out.println("|");
            index1++;
            index2 = 0;
        }

        for(int k = 0; k < table.length; k++) {
            System.out.print("+");
            for(int i = 0; i < 6; i++)
                System.out.print("-");
        }
        System.out.println("+");
        System.out.println("");
    }
}