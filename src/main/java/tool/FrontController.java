package tool;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "*.action" })
public class FrontController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {

		try {
			// パスを取得
			String path = req.getServletPath().substring(1);

			// ファイル名を取得しクラス名に変換
			String name = path.replace(".a", "A").replace('/', '.');

			System.out.println("★ servlet path -> " + req.getServletPath());
			System.out.println("★ class name -> " + name);

			// Actionクラスのインスタンスを取得
			Action action = (Action) Class.forName(name)
					.getDeclaredConstructor().newInstance();

			// Action実行
			action.execute(req, res);

			// （※この課題仕様ではAction内でforwardする）
			// String url = action.execute(req, res);
			// req.getRequestDispatcher(url).forward(req, res);

		} catch (Exception e) {
			e.printStackTrace();

			// エラーページへ遷移
			req.getRequestDispatcher("/error.jsp").forward(req, res);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, java.io.IOException {

		doGet(req, res);
	}

}