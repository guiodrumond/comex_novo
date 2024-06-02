package br.com.alura.comex_nova_versao.repository;

import br.com.alura.comex_nova_versao.model.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

}
