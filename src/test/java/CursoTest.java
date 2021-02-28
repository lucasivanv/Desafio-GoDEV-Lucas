import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CursoTest {

    @Test
    public void testDiferencaEntreSalasEhNoMaximo1Pessoa(){

        Curso curso = new Curso();

        for (int i=0; i < 5; i++) {
            curso.cadastraPessoa(String.format("nome %d",i), String.format("sobrenome %d",i));
        }

        curso.cadastraSala("Primeira", 2);
        curso.cadastraSala("Segunda", 100);

        curso.distribuiPessoas();

        Sala sala1 = curso.salasCadastradas.get(0);
        Sala sala2 = curso.salasCadastradas.get(1);

        assertEquals(2,sala1.getPessoasEtapa1().size());
        assertEquals(3,sala2.getPessoasEtapa1().size());
    }

    @ParameterizedTest
    @ValueSource(ints = {3,10,21,22,24})
    public void testMetadeDasPessoasTrocamDeSalaEntreEtapas(int quantidaDePessoas) {
        Curso curso = new Curso();

        for (int i = 0; i < quantidaDePessoas; i++) {
            curso.cadastraPessoa(String.format("nome %d", i), String.format("sobrenome %d", i));
        }

        curso.cadastraSala("Primeira", 8);
        curso.cadastraSala("Segunda", 100);
        curso.cadastraSala("Terceira", 50);

        curso.distribuiPessoas();

        for (Sala sala : curso.salasCadastradas) {

            int pessoasNaMesmaSalaEntreEtapas = 0;

            for (Pessoa pessoa : sala.getPessoasEtapa1()) {

                if (sala.contemPessoaEtapa2(pessoa)) {
                    pessoasNaMesmaSalaEntreEtapas += 1;
                }
            }
            assertEquals(sala.getPessoasEtapa1().size() - Math.ceil(sala.getPessoasEtapa1().size() / 2.0), pessoasNaMesmaSalaEntreEtapas);
        }
    }

    @Test
    public void testPessoaNaoRepeteEntreSalas(){
        Curso curso = new Curso();

        for (int i=0; i < 11; i++) {
            curso.cadastraPessoa(String.format("nome %d",i), String.format("sobrenome %d",i));
        }

        curso.cadastraSala("Primeira", 10);
        curso.cadastraSala("Segunda", 100);

        curso.distribuiPessoas();

        Sala sala1 = curso.salasCadastradas.get(0);
        Sala sala2 = curso.salasCadastradas.get(1);

        for (Pessoa pessoa : curso.pessoasCadastradas) {
            assertNotEquals(sala1.contemPessoaEtapa1(pessoa), sala2.contemPessoaEtapa1(pessoa));
            assertNotEquals(sala1.contemPessoaEtapa2(pessoa), sala2.contemPessoaEtapa2(pessoa));
        }
    }

    @Test
    public void testPessoaNaoRepeteEntreEspacos(){
        Curso curso = new Curso();

        for (int i=0; i < 11; i++) {
            curso.cadastraPessoa(String.format("nome %d",i), String.format("sobrenome %d",i));
        }

        curso.cadastraEspaco("Primeiro", 10);
        curso.cadastraEspaco("Segundo", 100);

        curso.distribuiPessoas();

        EspacoCafe espacoCafe1 = curso.espacosCadastrados.get(0);
        EspacoCafe espacoCafe2 = curso.espacosCadastrados.get(1);

        for (Pessoa pessoa : curso.pessoasCadastradas) {
            assertNotEquals(espacoCafe1.contemPessoa(pessoa), espacoCafe2.contemPessoa(pessoa));
        }
    }
}