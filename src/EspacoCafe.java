import java.util.ArrayList;
import java.util.List;

class EspacoCafe {
    String nome;
    int lotacao;
    List<Pessoa> pessoas = new ArrayList<>();

    public boolean adicionaPessoa(Pessoa pessoa) {
        if (pessoas.size() < lotacao) {
            pessoas.add(pessoa);
            return true;
        }
        return false;
    }
}
