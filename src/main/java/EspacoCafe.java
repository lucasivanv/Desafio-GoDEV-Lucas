import java.util.ArrayList;
import java.util.List;

class EspacoCafe {
    private String nome;
    private int lotacao;
    private List<Pessoa> pessoas = new ArrayList<>();

    public EspacoCafe(String nome, int lotacao) {
        this.nome = nome;
        this.lotacao = lotacao;
    }

    public boolean adicionaPessoa(Pessoa pessoa) {
        if (pessoas.size() < lotacao) {
            pessoas.add(pessoa);
            return true;
        }
        return false;
    }

    public String getNome() {
        return nome;
    }

    public int getLotacao() {
        return lotacao;
    }

    public List<Pessoa> getPessoas() {
        return new ArrayList<>(pessoas);
    }

    public void esvaziaEspaco() {
        pessoas.clear();
    }

    public boolean contemPessoa(Pessoa pessoa) {
        return pessoas.contains(pessoa);
    }
}
