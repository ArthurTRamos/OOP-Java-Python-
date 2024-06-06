import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArvBin<Integer> arvBin = new ArvBin<Integer>(201);

        while(scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if(linha.equals(""))
                continue;
            char operacao = linha.charAt(0);
            Integer nome = Integer.parseInt(linha.substring(2));

            switch (operacao) {
                case 'i':
                    arvBin.insert(nome);
                    break;
            
                case 'd':
                    arvBin.remove(nome);
                    break;
            }
        }

        scanner.close();

        System.out.println(arvBin);
    }
}