import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Programa {

    public static int converteInt(String integer) {
        try {
            return Integer.parseInt(integer);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE; //ATENCAO AQUI PARA LOTACAO DA SALA
        }
    }

    public static int getLotacaoMaxima(List<Sala> salasCadastradas) {
        List<Integer> list = new ArrayList<>();

        if (salasCadastradas.size() == 1) {

            Sala sala = salasCadastradas.get(0);

            return sala.lotacao;

        } else {
            for (Sala sala : salasCadastradas) {
                list.add(sala.lotacao);
            }

            int i = Collections.min(list) + 1;
            return i;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int opcao1;
        int opcao2;
        int opcao3;

        List<Pessoa> pessoasCadastradas = new ArrayList<>();

        List<Sala> salasCadastradas = new ArrayList<>();

        List<EspacoCafe> espacosCadastrados = new ArrayList<>();

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

                        Pessoa pessoa = new Pessoa(nome, sobrenome);

                        pessoasCadastradas.add(pessoa);

                    } else if (opcao2 == 2) {

                        Sala sala = new Sala();

                        System.out.println("Nome da sala:");
                        sala.nome = reader.readLine();

                        System.out.println("Lotação máxima:");
                        sala.lotacao = converteInt(reader.readLine());

                        salasCadastradas.add(sala);

                    } else if (opcao2 == 3) {

                        if (espacosCadastrados.size() < 2) {

                            System.out.println("Nome do espaço de café:");
                            String nome = reader.readLine();

                            System.out.println("Lotação máxima:");
                            int lotacao = converteInt(reader.readLine());

                            EspacoCafe espacoCafe = new EspacoCafe(nome, lotacao);

                            espacosCadastrados.add(espacoCafe);
                        } else {
                            System.out.println("O número máximo de espaços de café (2) foi atingido");
                        }

                    } else if (opcao2 != 4) {
                        System.out.println("Opção inválida, por favor tente novamente");
                    }

                } while (opcao2 != 4);


                int lotacaoMaximaSalas = getLotacaoMaxima(salasCadastradas);

                for (Sala sala : salasCadastradas) {
                    sala.pessoasEtapa1.clear();
                    sala.pessoasEtapa2.clear();
                }

                for (EspacoCafe espacoCafe : espacosCadastrados) {
                    espacoCafe.esvaziaEspaco();
                }

                int proximaSala = -1;
                int lotacaoOcupadaSalas = 0;

                for (int i = 0; i < pessoasCadastradas.size(); ++i) {

                    Pessoa pessoa = pessoasCadastradas.get(i);

                    proximaSala = proximaSala + 1;

                    if (salasCadastradas.size() != 0) {

                        if (proximaSala < salasCadastradas.size()) {

                            Sala sala = salasCadastradas.get(proximaSala);

                            sala.adicionaPessoa(pessoa, lotacaoMaximaSalas);

                        } else {
                            lotacaoOcupadaSalas = lotacaoOcupadaSalas + 1;

                            if (lotacaoOcupadaSalas < lotacaoMaximaSalas - 1) {

                                proximaSala = 0;

                                Sala sala = salasCadastradas.get(proximaSala);

                                sala.adicionaPessoa(pessoa, lotacaoMaximaSalas);

                            } else {
                                for (Sala sala : salasCadastradas) {

                                    boolean adicionouNaSala = sala.adicionaPessoa(pessoa, lotacaoMaximaSalas);

                                    if (adicionouNaSala) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (espacosCadastrados.size() == 1) {

                        EspacoCafe espacoCafe = espacosCadastrados.get(0);

                        espacoCafe.adicionaPessoa(pessoa);


                    } else if (espacosCadastrados.size() == 2) {


                        EspacoCafe espacoCafe1 = espacosCadastrados.get(0);
                        EspacoCafe espacoCafe2 = espacosCadastrados.get(1);

                        int indicePessoa = pessoasCadastradas.indexOf(pessoa);

                        if (indicePessoa % 2 == 0) {
                            boolean adicionouNoEspaco1 = espacoCafe1.adicionaPessoa(pessoa);

                            if (!adicionouNoEspaco1) {
                                espacoCafe2.adicionaPessoa(pessoa);
                            }

                        } else {
                            boolean adicionouNoEspaco2 = espacoCafe2.adicionaPessoa(pessoa);

                            if (!adicionouNoEspaco2) {
                                espacoCafe1.adicionaPessoa(pessoa);
                            }
                        }

                        //
                        //
                        //   DOIS INTERVALOS DE CAFÉ, TROCAR PESSOAS?
                        //
                        //

                    }
                }

                if (salasCadastradas.size() != 0) {
                    for (Sala sala : salasCadastradas) {

                        sala.pessoasEtapa2.addAll(sala.pessoasEtapa1);
                    }

                    int contadorSalas = -1;
                    int contadorSalas2;
                    int contadorPessoas;

                    do {
                        contadorSalas = contadorSalas + 1;

                        if (contadorSalas == salasCadastradas.size() - 1) {
                            contadorSalas2 = 0;
                        } else {
                            contadorSalas2 = contadorSalas + 1;
                        }

                        Sala salaA = salasCadastradas.get(contadorSalas);
                        Sala salaB = salasCadastradas.get(contadorSalas2);

                        contadorPessoas = -1;

                        int metadeSala = (int) Math.floor(lotacaoOcupadaSalas / 2);
                        System.out.println("metade sala = " + metadeSala);

                        do {
                            contadorPessoas = contadorPessoas + 1;

                            salaA.pessoasEtapa2.set(contadorPessoas, salaB.pessoasEtapa1.get(contadorPessoas));

                        } while (metadeSala > contadorPessoas + 1);

                        //
                        // APAGAR ABAIXO
                        //
                        //

                        System.out.println(salaA.nome + " Etapa 1");
                        for (Pessoa pessoa : salaA.pessoasEtapa1) {
                            System.out.println(pessoa.getNomeCompleto());
                        }

                        System.out.println(salaA.nome + " Etapa 2");
                        for (Pessoa pessoa : salaA.pessoasEtapa2) {
                            System.out.println(pessoa.getNomeCompleto());
                        }

                        //
                        // APAGAR ACIMA
                        //
                        //
                    } while (contadorSalas != salasCadastradas.size() - 1);
                }


            } else if (opcao1 == 2) {

                do {
                    System.out.println("\nMENU\n Selecione uma opção:\n 1)Consultar nome\n 2)Consultar sala\n 3)Consultar espaço\n 4)Retornar");

                    opcao2 = converteInt(reader.readLine());

                    if (opcao2 == 1) {
                        if (pessoasCadastradas.size() != 0) {
                            System.out.println("\nMENU\n Selecione o(a) aluno(a):");

                            int indice = 0;
                            for (Pessoa pessoa : pessoasCadastradas) {
                                indice = indice + 1;
                                System.out.println(" " + indice + ")" + pessoa.getNomeCompleto());
                            }

                            opcao3 = converteInt(reader.readLine());

                            Pessoa pessoa = pessoasCadastradas.get(opcao3 - 1);

                            System.out.println("Aluno(a) escolhido(a): " + pessoa.getNomeCompleto());

                            for (Sala sala : salasCadastradas) {
                                if (sala.pessoasEtapa1.contains(pessoa)) {
                                    System.out.println(" Sala presente (1 Etapa): " + sala.nome);
                                }

                                if (sala.pessoasEtapa2.contains(pessoa)) {
                                    System.out.println(" Sala presente (2 Etapa): " + sala.nome);
                                }
                            }

                            for (EspacoCafe espacoCafe : espacosCadastrados) {
                                if (espacoCafe.contemPessoa(pessoa)) {
                                    System.out.println(" Espaço de café presente: " + espacoCafe.getNome());
                                }
                            }

                        } else {
                            System.out.println("Não há pessoas cadastradas");
                        }
                    } else if (opcao2 == 2) {

                        if (salasCadastradas.size() != 0) {
                            System.out.println("\nMENU\n Selecione a sala:");

                            int indice = 0;
                            for (Sala sala : salasCadastradas) {
                                indice = indice + 1;
                                System.out.println(" " + indice + ")" + sala.nome);
                            }

                            opcao3 = converteInt(reader.readLine());

                            Sala sala = salasCadastradas.get(opcao3 - 1);

                            System.out.println("Sala escolhida: " + sala.nome + "\nAluno(s) nesta sala (1 Etapa):");

                            for (Pessoa pessoa : sala.pessoasEtapa1) {
                                System.out.println(" " + pessoa.getNomeCompleto());
                            }

                            System.out.println("Sala escolhida: " + sala.nome + "\nAluno(s) nesta sala (2 Etapa):");

                            for (Pessoa pessoa : sala.pessoasEtapa2) {
                                System.out.println(" " + pessoa.getNomeCompleto());
                            }
                        } else {
                            System.out.println("Não há salas cadastradas");
                        }

                    } else if (opcao2 == 3) {

                        if (espacosCadastrados.size() != 0) {
                            System.out.println("\nMENU\n Selecione o espaço de café:");

                            int indice = 0;
                            for (EspacoCafe espacoCafe : espacosCadastrados) {
                                indice = indice + 1;
                                System.out.println(" " + indice + ")" + espacoCafe.getNome());
                            }

                            opcao3 = converteInt(reader.readLine());

                            EspacoCafe espacoCafe = espacosCadastrados.get(opcao3 - 1);

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