import java.util.ArrayList;
import java.util.List;

class Sala {
    private String nome;
    private int lotacao;
    List<Pessoa> pessoasEtapa1 = new ArrayList<>();
    List<Pessoa> pessoasEtapa2 = new ArrayList<>();

    public Sala(String nome, int lotacao) {
        this.nome = nome;
        this.lotacao = lotacao;
    }

    public void esvaziaEtapas() {
        pessoasEtapa1.clear();
        pessoasEtapa2.clear();
    }

    public boolean adicionaPessoa(Pessoa pessoa, int lotacaoMaxima) {
        if (pessoasEtapa1.size() < lotacao && pessoasEtapa1.size() < lotacaoMaxima) {
            pessoasEtapa1.add(pessoa);
            return true;
        }
        return false;
    }

    public int getLotacao() {
        return lotacao;
    }

    public String getNome() {
        return nome;
    }
}