package challenge;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Classe para mapear a receita no MongoDB
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "recipe")
public class Recipe {

	@Id
	private String id;
	private String title;
	private String description;
	private List<Integer> likes;
	private List<String> ingredients;
	private List<Comment> comments;

	public Recipe() {
	}

	public Recipe(String id, String title, String description, List<Integer> likes, List<String> ingredients, List<Comment> comments) {
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

	public List<Integer> getLikes() {
		return likes;
	}

	public void setLikes(List<Integer> likes) {
		this.likes = likes;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
