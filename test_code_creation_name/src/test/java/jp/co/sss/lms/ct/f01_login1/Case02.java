package jp.co.sss.lms.ct.f01_login1;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト ログイン機能①
 * ケース02
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース02 受講生 ログイン 認証失敗")
public class Case02 {

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
	@DisplayName("テスト01 ログインID未入力")
	void test01() {
		//URLにアクセスする
		goTo("http://localhost:8080/lms/");

		//入力するパスワード
		webDriver.findElement(By.id("password")).sendKeys("StudentAA001");

		//ログインボタンをクリック
		webDriver.findElement(By.cssSelector("input[type='submit']")).click();

		//検証：エラーメッセージが表示されているか
		WebElement errorMsg = webDriver.findElement(By.className("error"));

		// メッセージが画面に表示されているか（CSS等で隠れていないか）
		assertTrue(errorMsg.isDisplayed());

		// メッセージの内容が正しいか
		assertEquals("ログインIDは必須です。", errorMsg.getText());

		//エビデンスを取得（テスト01）
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 パスワード未入力")
	void test02() {
		//URLにアクセスする
		goTo("http://localhost:8080/lms/");

		//入力するログインID
		webDriver.findElement(By.id("loginId")).sendKeys("StudentAA001");

		//ログインボタンをクリック
		webDriver.findElement(By.cssSelector("input[type='submit']")).click();

		//検証：エラーメッセージが表示されているか
		WebElement errorMsg = webDriver.findElement(By.className("error"));

		// メッセージが画面に表示されているか（CSS等で隠れていないか）
		assertTrue(errorMsg.isDisplayed());

		// メッセージの内容が正しいか
		assertEquals("パスワードは必須です。", errorMsg.getText());

		//エビデンスを取得（テスト01）
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 ログインID・パスワード未入力")
	void test03() {
		//URLにアクセスする
		goTo("http://localhost:8080/lms/");

		//未入力の状態でログインボタンをクリック
		webDriver.findElement(By.cssSelector("input[type='submit']")).click();

		//検証：エラーメッセージが表示されているか
		List<WebElement> errorMsg = webDriver.findElements(By.className("error"));

		// メッセージが画面に表示されているか（CSS等で隠れていないか）
		assertTrue(errorMsg.get(0).getText().contains("ログインIDは必須です。"));
		assertTrue(errorMsg.get(1).getText().contains("パスワードは必須です。"));

		//エビデンスを取得（テスト01）
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(4)
	@DisplayName("テスト04 誤ったログインID・パスワードを入力")
	void test04() {
		//URLにアクセスする
		goTo("http://localhost:8080/lms/");

		//入力するログインID
		webDriver.findElement(By.id("loginId")).sendKeys("StudentA300");

		//入力するパスワード
		webDriver.findElement(By.id("password")).sendKeys("StudentA300");

		//ログインボタンをクリック
		webDriver.findElement(By.cssSelector("input[type='submit']")).click();

		//検証：エラーメッセージが表示されているか
		WebElement errorMsg = webDriver.findElement(By.className("error"));

		// メッセージの内容が正しいか
		assertTrue(errorMsg.isDisplayed());
		assertTrue(errorMsg.getText().contains("ログインに失敗しました。"));

		//エビデンスを取得（テスト01）
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 境界値：ロック直前（2回連続失敗）")
	void test05() {
		//URLにアクセスする
		goTo("http://localhost:8080/lms/");

		//繰り返し処理
		for (int i = 0; i < 2; i++) {
			webDriver.findElement(By.id("loginId")).clear();
			webDriver.findElement(By.id("loginId")).sendKeys("StudentA300");
			webDriver.findElement(By.cssSelector("input[type='submit']")).click();
		}

		//検証：エラーメッセージが表示されているか
		WebElement errorMsg = webDriver.findElement(By.className("error"));

		// メッセージの内容が正しいか
		assertTrue(errorMsg.getText().contains("ログインに失敗しました。"));

		//エビデンスを取得（テスト01）
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 境界値：ロック発生（3回連続失敗）")
	void test06() {
		//URLにアクセスする
		goTo("http://localhost:8080/lms/");

		//繰り返し処理
		for (int i = 0; i < 3; i++) {
			webDriver.findElement(By.id("loginId")).clear();
			webDriver.findElement(By.id("loginId")).sendKeys("StudentA300");
			webDriver.findElement(By.cssSelector("input[type='submit']")).click();
		}

		//検証：エラーメッセージが表示されているか
		WebElement errorMsg = webDriver.findElement(By.className("error"));

		// メッセージの内容が正しいか
		assertTrue(errorMsg.getText().contains("規定の回数を超えたため、アカウントにロックがかかりました。"));

		//エビデンスを取得（テスト01）
		getEvidence(new Object() {
		});
	}

}
