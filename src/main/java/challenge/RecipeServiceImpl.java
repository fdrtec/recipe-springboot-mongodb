package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeCommentRepository commentRepository;

    @Override
    public Recipe save(Recipe recipe) {

        if (recipe.getComments().size() > 0)
            commentRepository.insert(recipe.getComments());

        return recipeRepository.insert(recipe);
    }

    @Override
    public void update(String id, Recipe recipe) {

        Optional<Recipe> recipeAlreadyExists = recipeRepository.findById(id);

        if (recipeAlreadyExists.isPresent()) {

            Recipe updatedRecipe = recipeAlreadyExists.get();
            updatedRecipe.setTitle(recipe.getTitle());
            updatedRecipe.setDescription(recipe.getDescription());
            updatedRecipe.setIngredients(recipe.getIngredients());

            recipeRepository.save(updatedRecipe);
        }
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
        return sortedSearch(search);
    }

    private List<Recipe> sortedSearch(String search) {
        return recipeRepository.findAllByTitleOrDescriptionLike(search).stream()
                .sorted(Comparator.comparing(Recipe::getTitle))
                .collect(Collectors.toList());
    }

    @Override
    public void like(String id, String userId) {
        Optional<Recipe> recipeAlreadyExists = recipeRepository.findById(id);

        if (recipeAlreadyExists.isPresent()) {

            Recipe recipe = recipeAlreadyExists.get();
            recipe.addIngredient(userId);

            recipeRepository.save(recipeAlreadyExists.get());
        }
    }

    @Override
    public void unlike(String id, String userId) {
        Optional<Recipe> recipeAlreadyExists = recipeRepository.findById(id);

        if (recipeAlreadyExists.isPresent()) {

            Recipe recipe = recipeAlreadyExists.get();
            recipe.deleteIngredient(userId);

            recipeRepository.save(recipeAlreadyExists.get());
        }
    }

    @Override
    public RecipeComment addComment(String id, RecipeComment comment) {
        Optional<Recipe> recipeAlreadyExists = recipeRepository.findById(id);

        if (!recipeAlreadyExists.isPresent())
            return null;

        RecipeComment savedComment = commentRepository.insert(comment);

        recipeAlreadyExists.get().addComment(savedComment);

        recipeRepository.save(recipeAlreadyExists.get());

        return savedComment;
    }

    @Override
    public void updateComment(String id, String commentId, RecipeComment comment) {
        Optional<Recipe> recipeAlreadyExists = recipeRepository.findById(id);

        //refatorar
        if (recipeAlreadyExists.isPresent()) {

            comment.setId(commentId);

            recipeAlreadyExists.get().updateComment(comment);
            commentRepository.save(comment);
            recipeRepository.save(recipeAlreadyExists.get());
        }
    }

    @Override
    public void deleteComment(String id, String commentId) {
        Optional<Recipe> recipeAlreadyExists = recipeRepository.findById(id);

        //melhorar exclusão, ver forma de não precisar excluir nas duas collections
        if (recipeAlreadyExists.isPresent()) {
            recipeAlreadyExists.get().deleteCommentById(commentId);
            commentRepository.deleteById(commentId);
            recipeRepository.save(recipeAlreadyExists.get());
        }
    }

}
