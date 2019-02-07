package challenge;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {

	List<Recipe> findAllByIngredientsEqualsOrderByTitleAsc(String ingredients);

	@Query("{'$or': [{'title': {$regex: ?0, $options: 'i'}}, {'description': {$regex: ?0, $options: 'i'} }]}")
	List<Recipe> findAllByTitleOrDescriptionLike(String search);
}
