package com.generation.crudfarmacia.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.generation.crudfarmacia.model.CategoriaModel;
import com.generation.crudfarmacia.model.ProdutoModel;
import com.generation.crudfarmacia.model.UsuarioModel;
import com.generation.crudfarmacia.repository.CategoriaRepository;
import com.generation.crudfarmacia.repository.ProdutoRepository;
import com.generation.crudfarmacia.repository.UsuarioRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        
        // 1. Evita duplicar dados caso você mude o ddl-auto para 'update' no futuro
        if (categoriaRepository.count() == 0 && usuarioRepository.count() == 0) {

            System.out.println("====== INICIANDO CARGA PRÉ DEFINIDOS DE DADOS ======");

            // 🔑 CADASTRANDO UM USUÁRIO ADMINISTRADOR PADRÃO
            UsuarioModel admin = new UsuarioModel();
            admin.setNome("Administrador Farmácia");
            admin.setUsuario("admin@farmacia.com");
            admin.setSenha(passwordEncoder.encode("admin1234")); // Criptografia obrigatória
            usuarioRepository.save(admin);

            // 📁 CATEGORIA 1: ANTIBIÓTICOS
            CategoriaModel antibiotico = new CategoriaModel();
            antibiotico.setClasse("Antibiótico");
            antibiotico.setPrincipioAtivo("Amoxicilina");
            antibiotico.setLaboratorio("EMS");
            antibiotico.setTarja("Vermelha");
            antibiotico.setTipo("Genérico");
            antibiotico.setRequerReceita(true);
            categoriaRepository.save(antibiotico);

            // 📁 CATEGORIA 2: ANALGÉSICOS
            CategoriaModel analgesico = new CategoriaModel();
            analgesico.setClasse("Analgésico");
            analgesico.setPrincipioAtivo("Dipirona Monoidratada");
            analgesico.setLaboratorio("Medley");
            analgesico.setTarja("Isento");
            analgesico.setTipo("Referência");
            analgesico.setRequerReceita(false);
            categoriaRepository.save(analgesico);

            // 📦 PRODUTO 1 (Vinculado a Antibióticos)
            ProdutoModel p1 = new ProdutoModel();
            p1.setNome("Amoxicilina 500mg - 30 Comprimidos");
            p1.setPreco(new Double("45.90"));
            p1.setEstoque(50); // Alinhado com o nome do seu atributo de estoque
            p1.setCategoria(antibiotico); // Cria o vínculo de Chave Estrangeira
            produtoRepository.save(p1);

            // 📦 PRODUTO 2 (Vinculado a Analgésicos)
            ProdutoModel p2 = new ProdutoModel();
            p2.setNome("Dipirona 1g Novalgina - 10 Comprimidos");
            p2.setPreco(new Double("18.50"));
            p2.setEstoque(120);
            p2.setCategoria(analgesico);
            produtoRepository.save(p2);

            // 📦 PRODUTO 3 (Vinculado a Analgésicos)
            ProdutoModel p3 = new ProdutoModel();
            p3.setNome("Dipirona Gotas 500mg/mL Medley");
            p3.setPreco(new Double("12.30"));
            p3.setEstoque(85);
            p3.setCategoria(analgesico);
            produtoRepository.save(p3);

            System.out.println("====== CARGA DE DADOS PRÉ DEFINIDAS CONCLUÍDA COM SUCESSO ======");
        }
    }
}