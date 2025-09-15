package demo;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

import java.time.LocalDate;
import java.util.List;

public class AdministrativoApp {

    public static void main(String[] args) {
        ProdutoModel produtoModel = new ProdutoModel();
        PessoaModel pessoaModel = new PessoaModel();

        Produto p1 = new Produto();
        p1.setNome("TV");
        p1.setPreco(300.0);
        p1.setQuantidade(100);
        p1.setStatus(true);

        // 1) Criando um produto
        produtoModel.create(p1);

        // 2) Buscando todos os produtos na base de dados
        List<Produto> produtos = produtoModel.findAll();
            System.out.println("Qtde de produtos encontrados : " + produtos.size());

        // 3) Atualizando produto
        Produto produtoExistente = produtoModel.findById(p1);
        if (produtoExistente != null) {
            produtoExistente.setQuantidade(80);
            produtoModel.update(produtoExistente);
        }

        // 4) Criando uma pessoa
        Pessoa pe1 = new Pessoa();
        pe1.setNome("Maria");
        pe1.setEmail("maria@email.com");
        pe1.setIdade(30);
        pe1.setCpf("12345678900");
        pe1.setDataNascimento(LocalDate.of(1995, 5, 20));
        pessoaModel.create(pe1);

        // 5) Buscando todas as pessoas
        List<Pessoa> pessoas = pessoaModel.findAll();
        System.out.println("Qtde de pessoas encontradas: " + pessoas.size());

        // 6) Atualizando pessoa
        Pessoa pessoaExistente = pessoaModel.findById(pe1);
        if (pessoaExistente != null) {
            pessoaExistente.setEmail("maria.nova@email.com");
            pessoaModel.update(pessoaExistente);
        }

        // 7) Deletando produto e pessoa
        produtoModel.delete(p1);
        pessoaModel.delete(pe1);

        System.out.println("Operações CRUD concluídas com sucesso!!");
    }
}
