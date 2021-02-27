import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Curso {

    List<Sala> salasCadastradas = new ArrayList<>();
    List<Pessoa> pessoasCadastradas = new ArrayList<>();
    List<EspacoCafe> espacosCadastrados = new ArrayList<>();

    public void cadastraEspaco(String nome, Integer lotacao) {

        EspacoCafe espacoCafe = new EspacoCafe(nome, lotacao);
        espacosCadastrados.add(espacoCafe);
    }

    public void cadastraSala(String nome, Integer lotacao) {

        Sala sala = new Sala(nome, lotacao);
        salasCadastradas.add(sala);
    }

    public void cadastraPessoa(String nome, String sobrenome) {

        Pessoa pessoa = new Pessoa(nome, sobrenome);
        pessoasCadastradas.add(pessoa);
    }

    private int getLotacaoMaxima() {
        List<Integer> list = new ArrayList<>();

        if (salasCadastradas.size() == 1) {

            Sala sala = salasCadastradas.get(0);

            return sala.lotacao;

        } else {
            for (Sala sala : salasCadastradas) {
                list.add(sala.lotacao);
            }

            return Collections.min(list) + 1;    // E se todas as salas tiverem a mesma lotação (por causa do +1)?
        }
    }

    public void distribuiPessoas() {
        distribuiPessoasEspacos();
        distribuiPessoasSalas();
    }

    private void distribuiPessoasSalas() {
        int proximaSala = -1;
        int lotacaoOcupadaSalas = 0;

        for (Sala sala : salasCadastradas) {
            sala.esvaziaEtapas();
        }


        for (int i = 0; i < pessoasCadastradas.size(); ++i) {

            Pessoa pessoa = pessoasCadastradas.get(i);

            proximaSala = proximaSala + 1;

            if (salasCadastradas.size() != 0) {

                int lotacaoMaximaSalas = getLotacaoMaxima();

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
        }
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

            int metadeSala = (int) Math.floor(lotacaoOcupadaSalas / 2.0);

            do {
                contadorPessoas = contadorPessoas + 1;

                salaA.pessoasEtapa2.set(contadorPessoas, salaB.pessoasEtapa1.get(contadorPessoas));

            } while (metadeSala > contadorPessoas + 1);

        } while (contadorSalas != salasCadastradas.size() - 1);

    }

    private void distribuiPessoasEspacos() {

        for (EspacoCafe espacoCafe : espacosCadastrados) {
            espacoCafe.esvaziaEspaco();
        }

        for (int i = 0; i < pessoasCadastradas.size(); ++i) {

            Pessoa pessoa = pessoasCadastradas.get(i);

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
            }
        }
    }
}

