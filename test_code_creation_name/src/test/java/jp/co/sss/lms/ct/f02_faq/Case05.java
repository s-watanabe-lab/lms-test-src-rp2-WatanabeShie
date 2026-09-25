package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;

/**
 * 結合テスト よくある質問機能
 * ケース05
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース05 キーワード検索 正常系")
public class Case05 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		//URLにアクセスする
		goTo("http://localhost:8080/lms/");

		//画面遷移が成功したかを確かめるため、タイトルタグを取得・検証
		assertEquals("ログイン | LMS", webDriver.getTitle());

		//エビデンスを取得（テスト01）
		getEvidence(new Object() {

		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		//入力するログインID
		webDriver.findElement(By.id("loginId")).clear();
		webDriver.findElement(By.id("loginId")).sendKeys("StudentAA01");

		//入力するパスワード
		webDriver.findElement(By.id("password")).clear();
		webDriver.findElement(By.id("password")).sendKeys("StudentAA011");

		//ログインボタンをクリック
		webDriver.findElement(By.cssSelector("input[type='submit']")).click();

		//Utilの機能より、コース詳細画面の「h2」が表示されるまで最大5秒待機する
		visibilityTimeout(By.tagName("h2"), 5);

		//画面遷移が成功したかを確かめるため、タイトルタグを取得・検証
		assertEquals("コース詳細 | LMS", webDriver.getTitle());

		//エビデンスを取得（テスト02）
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		//上部メニューの「機能」ボタン("dropdown-toggle)1が表示されるまで最大5秒待機する
		visibilityTimeout(By.className("dropdown-toggle"), 5);

		//「機能」リンクをクリックしてメニューを開く
		webDriver.findElement(By.className("dropdown-toggle")).click();

		//「ヘルプ」リンクが表示されるまで最大5秒待機する
		visibilityTimeout(By.linkText("ヘルプ"), 5);

		//画面上の「ヘルプ」リンクをクリック
		webDriver.findElement(By.linkText("ヘルプ")).click();

		//ヘルプ画面が表示されるまで待機
		visibilityTimeout(By.tagName("h2"), 5);

		//画面遷移が成功したかを確かめるため、タイトルタグを取得・検証
		assertEquals("ヘルプ | LMS", webDriver.getTitle());

		//エビデンスを取得（テスト03）
		getEvidence(new Object() {

		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// 遷移前のウィンドウハンドルを取得
		String currentWindow = webDriver.getWindowHandle();

		//画面上の「よくある質問」リンクをクリック
		webDriver.findElement(By.linkText("よくある質問")).click();

		// 新しく開いたタブ（別ウィンドウ）に操作対象を切り替える
		for (String windowHandle : webDriver.getWindowHandles()) {

			if (!windowHandle.equals(currentWindow)) {
				webDriver.switchTo().window(windowHandle);
				break;
			}
		}

		//よくある質問画面が表示されるまで待機
		visibilityTimeout(By.tagName("h2"), 5);

		//画面遷移が成功したかを確かめるため、タイトルタグを取得・検証
		assertEquals("よくある質問 | LMS", webDriver.getTitle());

		//エビデンスを取得（テスト04）
		getEvidence(new Object() {

		});

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 キーワード検索で該当キーワードを含む検索結果だけ表示")
	void test05() {

		//「キーワード」欄に「あ」と入力する
		webDriver.findElement(By.id("form")).clear();
		webDriver.findElement(By.id("form")).sendKeys("あ");

		//「検索」ボタンをクリック
		webDriver.findElement(By.cssSelector("input[value='検索']")).click();

		//検索結果テーブルが表示されるまで待機
		visibilityTimeout(By.className("sortabletable"), 5);

		//正しい画面で結果が表示されていることを検証
		assertEquals("よくある質問 | LMS", webDriver.getTitle());

		//エビデンスを取得（テスト05）
		getEvidence(new Object() {

		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 「クリア」ボタン押下で入力したキーワードを消去")
	void test06() {

		//「クリア」ボタンをクリック
		webDriver.findElement(By.cssSelector("input[value='クリア']")).click();

		//「キーワード」欄の値が空欄になっていることを検証
		assertEquals("", webDriver.findElement(By.id("form")).getAttribute("value"));

		//エビデンスを取得（テスト06）
		getEvidence(new Object() {

		});
	}

}
