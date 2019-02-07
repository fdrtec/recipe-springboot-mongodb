package challenge;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Classe para mapear a receita no MongoDB
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "recipe")
public class Recipe {

	@Id
	private String id;
	private String title;
	private String description;
	private List<String> likes;
	private List<String> ingredients;
	private List<RecipeComment> comments;

	public Recipe() {
	}

	public Recipe(String id, String title, String description, List<String> likes, List<String> ingredients, List<RecipeComment> comments) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.likes = likes;
		this.ingredients = ingredients;
		this.comments = comments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getLikes() {
		return likes;
	}

	public void setLikes(List<String> likes) {
		this.likes = likes;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public List<RecipeComment> getComments() {
		return comments;
	}

	public void setComments(List<RecipeComment> comments) {
		this.comments = comments;
	}

	public void addIngredient(String userId) {
		if (Strings.isNotEmpty(userId))
			this.likes.add(userId);
	}

	public void deleteIngredient(String userId) {
		if (Strings.isNotEmpty(userId))
			this.likes.remove(userId);
	}

	public void addComment(RecipeComment comment) {
		if (comment != null)
			this.comments.add(comment);
	}

	public void deleteCommentById(String commentId) {
		this.comments.removeIf(c -> c.getId().equals(commentId));
	}

	public void updateComment(RecipeComment comment) {
		this.comments.stream()
				.filter(c -> c.getId().equals(comment.getId()))
				.forEach(cc -> cc.setComment(comment.getComment()));

	}
}
