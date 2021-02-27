import org.junit.jupiter.api.Test;

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

        assertEquals(2,sala1.pessoasEtapa1.size());
        assertEquals(3,sala2.pessoasEtapa1.size());
    }
}