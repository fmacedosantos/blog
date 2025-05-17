package com.fmacedosantos.blog.repository;

import com.fmacedosantos.blog.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    /*
    Documentação a respeito dos Query Methods:
    https://docs.spring.io/spring-data/mongodb/reference/mongodb/repositories/query-methods.html

    findBy é a operação
    Title é o campo
    Containing é a key
    IgnoreCase modifica a busca para ignorar maiúsculas e minúsculas

    Sendo assim, os Query Method é a criação de queries avançadas que o mongoDb
    disponibiliza a partir de palavras-chave, sem precisar de implementação
     */
    List<Post> findByTitleContainingIgnoreCase(String text);

    /*
    O mongoDB também permite que criemos nossas próprias queries, com base
    em sua própria sintaxe, disponibilizada em:
    https://www.mongodb.com/pt-br/docs/manual/reference/operator/query/regex/

    ?0 define o primeiro parâmetro do método como valor para $regex
    i ignora maiúsculas e minúsculas
     */

    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Post> searchTitle(String text);
}
