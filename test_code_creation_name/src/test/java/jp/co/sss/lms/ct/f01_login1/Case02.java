package jp.co.sss.lms.ct.f01_login1;

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
	@DisplayName("テスト04 DBに存在しないログインID・パスワードを入力")
	void test02() {

		// ブラウザをリセット
		webDriver.manage().deleteAllCookies();

		//URLにアクセスする
		goTo("http://localhost:8080/lms/");

		//入力するログインID
		webDriver.findElement(By.id("loginId")).clear();
		webDriver.findElement(By.id("loginId")).sendKeys("StudentA300");

		//入力するパスワード
		webDriver.findElement(By.id("password")).clear();
		webDriver.findElement(By.id("password")).sendKeys("StudentA300");

		//ログインボタンをクリック
		webDriver.findElement(By.cssSelector("input[type='submit']")).click();

		//検証：エラーメッセージが表示されているか
		WebElement errorMsg = webDriver.findElement(By.className("error"));

		// メッセージの内容が正しいか
		assertTrue(errorMsg.isDisplayed());
		assertTrue(errorMsg.getText().contains("ログインに失敗しました。"));

		//エビデンスを取得（テスト02）
		getEvidence(new Object() {
		});
	}

}
