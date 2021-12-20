package program;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class JUnitTest {

	@Test
	void checkNameTest() {
		Database db = Database.getInstance();
		boolean ret = db.checkName("1");
		assertEquals(true, ret);
	}
	
	@Test
	void checkNameInvalidTest() {
		Database db = Database.getInstance();
		boolean ret = db.checkName("abcx");
		assertEquals(false, ret);
	}

	@Test
	void checkNameAndPwdTest() {
		Database db = Database.getInstance();
		boolean ret = db.checkNameAndPwd("1", "1");
		assertEquals(true, ret);
	}
	
	@Test
	void checkNameAndPwdInvalidTest() {
		Database db = Database.getInstance();
		boolean ret = db.checkNameAndPwd("abc", "1231");
		assertEquals(false, ret);
	}
	
	@Test
	void checkExistTableTest() {
		Database db = Database.getInstance();
		boolean ret = db.checkExistTable("member");
		assertEquals(true, ret);
	}
}
