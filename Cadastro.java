import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

class Pessoa {
    String nome;
    int idade;

    Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
}

public class Cadastro {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Pessoa> lista = new ArrayList<>();

        // 🔹 CARREGAR DADOS DO ARQUIVO
        try {
            BufferedReader br = new BufferedReader(new FileReader("dados.txt"));
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String nome = partes[0];
                int idade = Integer.parseInt(partes[1]);

                lista.add(new Pessoa(nome, idade));
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Arquivo ainda não existe.");
        }

        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE CADASTRO ===");
            System.out.println("1 - Cadastrar pessoa");
            System.out.println("2 - Listar pessoas");
            System.out.println("3 - Remover pessoa");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Idade: ");
                    int idade = sc.nextInt();
                    sc.nextLine();

                    lista.add(new Pessoa(nome, idade));

                    // 🔹 SALVAR NO ARQUIVO
                    try {
                        FileWriter fw = new FileWriter("dados.txt", true);
                        fw.write(nome + "," + idade + "\n");
                        fw.close();
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar.");
                    }

                    System.out.println("Pessoa cadastrada!");
                    break;

                case 2:
                    System.out.println("\nLista de pessoas:");

                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma pessoa cadastrada.");
                    } else {
                        for (Pessoa p : lista) {
                            System.out.println("Nome: " + p.nome + " | Idade: " + p.idade);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Digite o nome para remover: ");
                    String nomeRemover = sc.nextLine();

                    boolean removido = false;

                    for (int i = 0; i < lista.size(); i++) {
                        if (lista.get(i).nome.equalsIgnoreCase(nomeRemover)) {
                            lista.remove(i);
                            removido = true;
                            System.out.println("Pessoa removida!");
                            break;
                        }
                    }

                    if (!removido) {
                        System.out.println("Pessoa não encontrada!");
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}