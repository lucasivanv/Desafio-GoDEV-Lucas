import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int converteInt(String integer) {
        try {
            return Integer.parseInt(integer);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int opcao1;
        int opcao2;
        int itemEscolhido;

        Curso curso = new Curso();

        do {
            System.out.println("\nMENU\n Selecione uma opção:\n 1)Cadastro de Pessoa/Sala/Espaço de café\n 2)Consulta de Pessoa/Sala/Espaço de café\n 3)Sair");

            opcao1 = converteInt(reader.readLine());

            if (opcao1 == 1) {

                do {
                    System.out.println("\nMENU\n Selecione uma opção:\n 1)Cadastrar nome\n 2)Cadastrar sala\n 3)Cadastrar espaço\n 4)Retornar");

                    opcao2 = converteInt(reader.readLine());

                    if (opcao2 == 1) {

                        System.out.println("Primeiro nome da pessoa:");
                        String nome = reader.readLine();

                        System.out.println("Sobrenome da pessoa:");
                        String sobrenome = reader.readLine();

                        curso.cadastraPessoa(nome, sobrenome);

                    } else if (opcao2 == 2) {

                        System.out.println("Nome da sala:");
                        String nome = reader.readLine();

                        int lotacao;

                        do {
                            System.out.println("Lotação máxima:");
                            lotacao = converteInt(reader.readLine());

                            if (lotacao <= 0) {
                                System.out.println("Lotação inválida, por favor tente novamente");
                            }
                        } while (lotacao <= 0);

                        curso.cadastraSala(nome, lotacao);

                    } else if (opcao2 == 3) {

                        if (curso.espacosCadastrados.size() < 2) {

                            System.out.println("Nome do espaço de café:");
                            String nome = reader.readLine();

                            int lotacao;

                            do {
                                System.out.println("Lotação máxima:");
                                lotacao = converteInt(reader.readLine());

                                if (lotacao <= 0) {
                                    System.out.println("Lotação inválida, por favor tente novamente");
                                }
                            } while (lotacao <= 0);

                            curso.cadastraEspaco(nome, lotacao);

                        } else {
                            System.out.println("O número máximo de espaços de café (2) foi atingido");
                        }

                    } else if (opcao2 != 4) {
                        System.out.println("Opção inválida, por favor tente novamente");
                    }

                } while (opcao2 != 4);

                curso.quantidadePessoasPorLotacao();

                curso.distribuiPessoas();

            } else if (opcao1 == 2) {

                do {
                    System.out.println("\nMENU\n Selecione uma opção:\n 1)Consultar nome\n 2)Consultar sala\n 3)Consultar espaço\n 4)Retornar");

                    opcao2 = converteInt(reader.readLine());

                    if (opcao2 == 1) {
                        if (curso.pessoasCadastradas.size() != 0) {

                            System.out.println("\nMENU\n Selecione o(a) aluno(a):");

                            do {
                                int indice = 0;
                                for (Pessoa pessoa : curso.pessoasCadastradas) {
                                    indice = indice + 1;
                                    System.out.println(" " + indice + ")" + pessoa.getNomeCompleto());
                                }

                                itemEscolhido = converteInt(reader.readLine());
                                if (indice < itemEscolhido || 0 >= itemEscolhido) {
                                    System.out.println("Opção inválida, por favor tente novamente");
                                } else {
                                    break;
                                }
                            } while (itemEscolhido != Integer.MAX_VALUE);

                            Pessoa pessoa = curso.pessoasCadastradas.get(itemEscolhido - 1);

                            System.out.println("Aluno(a) escolhido(a): " + pessoa.getNomeCompleto());

                            for (Sala sala : curso.salasCadastradas) {
                                if (sala.contemPessoaEtapa1(pessoa)) {
                                    System.out.println(" Sala presente (1 Etapa): " + sala.getNome());
                                }

                                if (sala.contemPessoaEtapa2(pessoa)) {
                                    System.out.println(" Sala presente (2 Etapa): " + sala.getNome());
                                }
                            }

                            for (EspacoCafe espacoCafe : curso.espacosCadastrados) {
                                if (espacoCafe.contemPessoa(pessoa)) {
                                    System.out.println(" Espaço de café presente: " + espacoCafe.getNome());
                                }
                            }

                        } else {
                            System.out.println("Não há pessoas cadastradas");
                        }
                    } else if (opcao2 == 2) {

                        if (curso.salasCadastradas.size() != 0) {
                            System.out.println("\nMENU\n Selecione a sala:");
                            do {
                                int indice = 0;
                                for (Sala sala : curso.salasCadastradas) {
                                    indice = indice + 1;
                                    System.out.println(" " + indice + ")" + sala.getNome());
                                }

                                itemEscolhido = converteInt(reader.readLine());
                                if (indice < itemEscolhido || 0 >= itemEscolhido) {
                                    System.out.println("Opção inválida, por favor tente novamente");
                                } else {
                                    break;
                                }
                            } while (itemEscolhido != Integer.MAX_VALUE);

                            Sala sala = curso.salasCadastradas.get(itemEscolhido - 1);

                            System.out.println("Sala escolhida: " + sala.getNome() + "\nAluno(s) nesta sala (1 Etapa):");

                            for (Pessoa pessoa : sala.getPessoasEtapa1()) {
                                System.out.println(" " + pessoa.getNomeCompleto());
                            }

                            System.out.println("Sala escolhida: " + sala.getNome() + "\nAluno(s) nesta sala (2 Etapa):");

                            for (Pessoa pessoa : sala.getPessoasEtapa2()) {
                                System.out.println(" " + pessoa.getNomeCompleto());
                            }
                        } else {
                            System.out.println("Não há salas cadastradas");
                        }

                    } else if (opcao2 == 3) {

                        if (curso.espacosCadastrados.size() != 0) {
                            System.out.println("\nMENU\n Selecione o espaço de café:");
                            do {
                                int indice = 0;
                                for (EspacoCafe espacoCafe : curso.espacosCadastrados) {
                                    indice = indice + 1;
                                    System.out.println(" " + indice + ")" + espacoCafe.getNome());
                                }

                                itemEscolhido = converteInt(reader.readLine());

                                if (indice < itemEscolhido || 0 >= itemEscolhido) {
                                    System.out.println("Opção inválida, por favor tente novamente");
                                } else {
                                    break;
                                }
                            } while (itemEscolhido != Integer.MAX_VALUE);

                            EspacoCafe espacoCafe = curso.espacosCadastrados.get(itemEscolhido - 1);

                            System.out.println("Espaço de café escolhido: " + espacoCafe.getNome() + "\nAluno(s) neste espaço:");

                            for (Pessoa pessoa : espacoCafe.getPessoas()) {
                                System.out.println(" " + pessoa.getNomeCompleto());
                            }
                        } else {
                            System.out.println("Não há espaços de café cadastrados");
                        }
                    } else if (opcao2 != 4) {
                        System.out.println("Opção inválida, por favor tente novamente");
                    }

                } while (opcao2 != 4);
            }

        } while (opcao1 != 3);

        System.out.println("Fim do programa");
    }
}