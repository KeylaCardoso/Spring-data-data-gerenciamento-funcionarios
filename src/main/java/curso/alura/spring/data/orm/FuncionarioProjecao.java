package curso.alura.spring.data.orm;

public interface FuncionarioProjecao { // Subentidade ou entidade projetada com os registros/atributos que se deseja
										// apresentar (id,
										// nome e salario), para deixar a Query mais leve, dinâmica, nao ter que
										// carregar uma query muito grande com registros que nao se deseja analisar no
										// momento
	Integer getId();

	String getNome();

	Double getSalario();

}
