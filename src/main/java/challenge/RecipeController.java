package challenge;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecipeController {

	@Autowired
	private RecipeService service;

	@PostMapping("/recipe")
	public Recipe save(@RequestBody Recipe recipe) {
		return service.save(recipe);
	}

	@PutMapping("/recipe/{id}")
	public void update(@PathVariable("id") String id, @RequestBody Recipe recipe) {
		service.update(id, recipe);
	}

	@DeleteMapping("/recipe/{id}")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

	@GetMapping("/recipe/{id}")
	public Recipe get(@PathVariable String id) {
		return service.get(id);
	}

	@GetMapping("/recipe/ingredient")
	public List<Recipe> listByIngredient(@RequestParam("ingredient") String ingredient) {
		return service.listByIngredient(ingredient);
	}

	@GetMapping("/recipe/search")
	public List<Recipe> search(@RequestParam("search") String search) {
		return service.search(search);
	}

	@PostMapping("/recipe/{id}/like/{userId}")
	public void like(@PathVariable("id") String id, @PathVariable("userId") String userId) {
		service.like(id, userId);
	}

	@DeleteMapping("/recipe/{id}/like/{userId}")
	public void unlike(@PathVariable("id") String id, @PathVariable("userId") String userId) {
		service.unlike(id, userId);
	}

	@PostMapping("/recipe/{id}/comment")
	public RecipeComment addComment(@PathVariable("id") String id, @RequestBody RecipeComment comment) {
		return service.addComment(id, comment);
	}

	@PutMapping("/recipe/{id}/comment/{commentId}")
	public void updateComment(@PathVariable("id") String id, @PathVariable("commentId") String commentId, @RequestBody RecipeComment comment ) {
		service.updateComment(id, commentId, comment);
	}

	@DeleteMapping("/recipe/{id}/comment/{commentId}")
	public void deleteComment(@PathVariable("id") String id, @PathVariable("commentId") String commentId) {
		service.deleteComment(id, commentId);
	}

}
