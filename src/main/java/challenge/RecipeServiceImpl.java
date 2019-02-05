package challenge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public Recipe save(Recipe recipe) {

		if (recipe.getComments().size() > 0)
			commentRepository.saveAll(recipe.getComments());

		return recipeRepository.save(recipe);
	}

	@Override
	public void update(String id, Recipe recipe) {
		
	}

	@Override
	public void delete(String id) {
		recipeRepository.deleteById(id);
	}

	@Override
	public Recipe get(String id) {
		return recipeRepository.findById(id).get();
	}

	@Override
	public List<Recipe> listByIngredient(String ingredient) {
		return recipeRepository.findAllByIngredientsEqualsOrderByTitleAsc(ingredient);
	}

	@Override
	public List<Recipe> search(String search) {
		return null;
	}

	@Override
	public void like(String id, String userId) {

	}

	@Override
	public void unlike(String id, String userId) {

	}

	@Override
	public RecipeComment addComment(String id, RecipeComment comment) {
		return null;
	}

	@Override
	public void updateComment(String id, String commentId, RecipeComment comment) {

	}

	@Override
	public void deleteComment(String id, String commentId) {

	}

}
