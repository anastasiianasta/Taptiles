package sk.tuke.gamestudio.server.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentDto {
    @NotBlank(message = "MESSAGE PAYLOAD IS EMPTY")
    @Size(max = 500, message = "MESSAGE PAYLOAD LIMIT IS 500 SYMBOLS")
    private String comment;

    @Min(value = 1, message = "RATING MUST BE BETWEEN 1 AND 5")
    @Max(value = 5, message = "RATING MUST BE BETWEEN 1 AND 5")
    private int rating = 5;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
