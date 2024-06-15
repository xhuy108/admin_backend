//package dev.dxhuy.admin_backend.repository;
//
//import dev.dxhuy.admin_backend.domain.Movie;
//import org.springframework.data.elasticsearch.annotations.Query;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//import java.util.List;
//
//public interface MovieElasticSearchRepository extends ElasticsearchRepository<Movie, Long> {
//    @Query("{\"bool\": {\"must\": {\"match\": {\"name\": \"?0\"}}}}")
//    List<Movie> findByName(String value);
//
//
//}
