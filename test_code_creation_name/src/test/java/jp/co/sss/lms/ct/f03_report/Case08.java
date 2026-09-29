package jp.co.sss.lms.ct.f03_report;

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
 * 結合テスト レポート機能
 * ケース08
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース08 受講生 レポート修正(週報) 正常系")
public class Case08 {

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
		// TODO ここに追加//入力するログインID
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
	@DisplayName("テスト03 提出済の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		//「提出済み」が含まれる行の詳細ボタンを選択できるように探し出す
		By targetBtn = By.xpath("//tr[contains(., '提出済み')]//input[@value='詳細']");

		//「詳細」ボタンが表示されるまで最大5秒待機する
		visibilityTimeout(targetBtn, 5);

		//「詳細」ボタンをクリック
		webDriver.findElement(targetBtn).click();

		//セクション詳細画面の「h2」が表示されるまで最大5秒待機する
		visibilityTimeout(By.tagName("h2"), 5);

		//画面遷移が成功したかを確かめるため、タイトルタグを取得・検証
		assertEquals("セクション詳細 | LMS", webDriver.getTitle());

		//エビデンスを取得（テスト03）
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「確認する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		//「日報を確認する」ボタンが表示されるまで最大5秒待機する
		visibilityTimeout(By.cssSelector("input[value*='を確認する']"), 5);

		//「日報を確認する」ボタンをクリック
		webDriver.findElement(By.cssSelector("input[value*='を確認する']")).click();

		//レポート登録画面の「legend」が表示されるまで最大5秒待機する
		visibilityTimeout(By.tagName("legend"), 5);

		//画面遷移が成功したかを確かめるため、タイトルタグを取得・検証
		assertEquals("レポート登録 | LMS", webDriver.getTitle());

		//エビデンスを取得（テスト04）
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しセクション詳細画面に遷移")
	void test05() {
		//「報告レポート」の入力欄（textarea)が表示されるまで最大5秒待機する
		visibilityTimeout(By.tagName("textarea"), 5);

		//「報告レポート」欄に「本日の学習内容：継承」と入力する
		webDriver.findElement(By.tagName("textarea")).clear();
		webDriver.findElement(By.tagName("textarea")).sendKeys("本日の学習内容：継承");

		//「提出する」ボタンをクリック
		webDriver.findElement(By.xpath("//button[contains(text(),'提出する')]")).click();

		//セクション詳細画面の「h2」が表示されるまで最大5秒待機する
		visibilityTimeout(By.tagName("h2"), 5);

		//画面遷移が成功したかを確かめるため、タイトルタグを取得・検証
		assertEquals("セクション詳細 | LMS", webDriver.getTitle());

		//ボタン名が「提出済～を確認する」に更新されて画面に表示されていることを検証
		assertTrue(webDriver.findElement(By.cssSelector("input[value*='提出済み']")).isDisplayed());

		//エビデンスを取得（テスト05）
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test06() {

		//セクション詳細画面の「h2」が表示されるまで最大5秒待機する
		visibilityTimeout(By.tagName("h2"), 5);

		//画面上の「ようこそ○○さん」のリンクをクリック
		webDriver.findElement(By.partialLinkText("ようこそ")).click();

		//ユーザー詳細画面が表示されるまで待機
		visibilityTimeout(By.tagName("h2"), 5);

		//画面遷移が成功したかを確かめるため、タイトルタグを取得・検証
		assertEquals("ユーザー詳細", webDriver.getTitle());

		//画面上のh2タグのテキストが「ユーザー詳細」であることを検証
		assertEquals("ユーザー詳細", webDriver.findElement(By.tagName("h2")).getText());

		//エビデンスを取得（テスト06）
		getEvidence(new Object() {

		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 該当レポートの「詳細」ボタンを押下しレポート詳細画面で修正内容が反映される")
	void test07() {
		//画面を下方向にスクロールしてレポート欄が見える位置にする
		scrollBy("500");

		//修正した日付を指定して、「詳細」ボタンのセレクタを指定
		By detailBtn = By.xpath("//tr[contains(., '10月1日')]//input[@value='詳細']");

		//「詳細」ボタンが表示されるまで最大5秒待機する
		visibilityTimeout(detailBtn, 5);

		//「詳細」ボタンをクリック
		webDriver.findElement(detailBtn).click();

		//レポート詳細画面の「h2」が表示されるまで最大5秒待機する
		visibilityTimeout(By.tagName("h2"), 5);

		//画面遷移が成功したかを確かめるため、タイトルタグを取得・検証
		assertEquals("レポート詳細 | LMS", webDriver.getTitle());

		//画面上テキストを取得し、テスト05で修正した内容が反映されていることを検証
		String reportText = webDriver.findElement(By.tagName("body")).getText();
		assertTrue(reportText.contains("継承"));

		//エビデンスを取得（テスト07）
		getEvidence(new Object() {

		});
	}

}
