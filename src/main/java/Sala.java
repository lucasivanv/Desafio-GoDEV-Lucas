import java.util.ArrayList;
import java.util.List;

class Sala {
    private String nome;
    private int lotacao;
    private List<Pessoa> pessoasEtapa1 = new ArrayList<>();
    private List<Pessoa> pessoasEtapa2 = new ArrayList<>();

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

    public List<Pessoa> getPessoasEtapa1() {
        return new ArrayList<>(pessoasEtapa1);
    }

    public List<Pessoa> getPessoasEtapa2() {
        return new ArrayList<>(pessoasEtapa2);
    }

    public void trocaComSala(int indicePessoa, Sala outraSala) {
        pessoasEtapa2.set(indicePessoa, outraSala.pessoasEtapa1.get(indicePessoa));
    }

    public boolean contemPessoaEtapa1(Pessoa pessoa) {
        return pessoasEtapa1.contains(pessoa);
    }

    public boolean contemPessoaEtapa2(Pessoa pessoa) {
        return pessoasEtapa2.contains(pessoa);
    }

    public void moveTodasPessoasParaEtapa2() {
        pessoasEtapa2.addAll(pessoasEtapa1);
    }
}