package training.web.logic;

import training.web.bean.User;
import training.web.bean.AuthInfo;

public class LogicStub {

	public User checkAuth(AuthInfo authInfo) {

		if("stas@mail.ru".equals(authInfo.getLogin())) {
			return new User("Stas", "admin");
		}
		return null;
	}

}
