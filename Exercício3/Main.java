import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArvBin arvBin = new ArvBin(201);
        ArvBal arvBal  = new ArvBal(201);
        ArvAvl arvAvl = new ArvAvl(201);

        while(scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if(linha.equals(""))
                continue;
            char operacao = linha.charAt(0);
            String nome = linha.substring(2);

            switch (operacao) {
                case 'i':
                    arvBin.insert(nome);
                    arvBal.insert(nome);
                    arvAvl.insert(nome);
                    break;
            
                case 'd':
                    arvBin.remove(nome);
                    if(arvBal.remove(nome))
                        arvBal.balance();
                    if(arvAvl.remove(nome))
                        arvAvl.balance();
                    break;
            }
        }

        scanner.close();

        System.out.println(arvBin);
        System.out.println(arvBal);
        System.out.println(arvAvl);

    }
}