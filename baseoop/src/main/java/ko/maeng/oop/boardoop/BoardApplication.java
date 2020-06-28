package ko.maeng.oop.boardoop;

import ko.maeng.oop.boardoop.account.Account;
import ko.maeng.oop.boardoop.account.Email;
import ko.maeng.oop.boardoop.account.Name;
import ko.maeng.oop.boardoop.account.Password;
import ko.maeng.oop.boardoop.activity.Activities;
import ko.maeng.oop.boardoop.activity.Activity;
import ko.maeng.oop.boardoop.activity.CommentScoreCondition;
import ko.maeng.oop.boardoop.activity.PostScoreCondition;
import ko.maeng.oop.boardoop.board.Board;
import ko.maeng.oop.boardoop.board.Category;
import ko.maeng.oop.boardoop.comment.Comment;
import ko.maeng.oop.boardoop.common.Content;
import ko.maeng.oop.boardoop.post.Post;
import ko.maeng.oop.boardoop.post.Title;

public class BoardApplication {
	public static void main(String[] args) {
		Activities activities = new Activities();
		Account account = Account.register(new Email("chulsu@naver.com"), new Password("1234567"),
			new Name("Rebwon"));
		Account account1 = Account.register(new Email("kitty@naver.com"), new Password("1234567"),
			new Name("kitty"));

		Board board = new Board(new Category("객체지향 프로그래밍"));
		Post post = Post.create(new Title("게시글 제목"), new Content("내용"), account);
		board.write(post);

		activities.addActivity(Activity.writePost(account, post, new PostScoreCondition()));

		Post selectPost = board.findPost(post.getId());

		Comment comment = Comment.create(account1, new Content("정말 좋은 글입니다."));
		selectPost.write(comment);
		activities.addActivity(Activity.writeComment(account1, comment, new CommentScoreCondition()));

		Integer totalActivityScore = activities.calculateTotalScore(account.getId());
		System.out.println(totalActivityScore);
	}
}
