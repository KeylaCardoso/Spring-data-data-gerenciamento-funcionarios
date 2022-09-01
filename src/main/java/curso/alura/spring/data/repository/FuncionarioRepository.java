package curso.alura.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import curso.alura.spring.data.orm.Funcionario;
import curso.alura.spring.data.orm.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository
		extends PagingAndSortingRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {
	// usando oPagingaAndSorting em vez do CrudRepositori se torna possivel fazer paginacao
	// JpaSpecificationExecutor - para criar Query dinamicas

	List<Funcionario> findByNome(String nome);

	// List<Funcionario> findByNomeAndSalarioGreaterThanAndDataContratacao(String
	// nome, Double salario, LocalDate data);

	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario <= :salario AND f.dataContratacao = :data")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate data);
	// esta segunda opção eh uma forma de criar uma Query usando estrutura SQL,
	// porem com os nomes das entidades javae não o
	// DerivatedQuery
	// possibilitando que criemos uma Query usando os nomes das nossas entidades em
	// Java, e dest aforma, podendo obter um nome menor do método

	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data", nativeQuery = true) // aqui esta
																											// usando
																											// Native
																											// Query, ou
																											// seja,
																											// esta
																											// usando os
																											// nomes
																											// conforme
																											// aparecem
																											// no
																											// banco de
																											// dados
																											// (como
																											// funcionarioS
																											// e
																											// data_contratacao)
	List<Funcionario> findDataContratacaoMaior(LocalDate data);

	@Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();

}
