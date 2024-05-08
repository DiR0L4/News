package training.web.logic;

import training.web.bean.News;
import training.web.bean.User;
import training.web.bean.AuthInfo;

import java.util.ArrayList;
import java.util.List;

public class LogicStub {

	public User checkAuth(AuthInfo authInfo) {

		if("stas@mail.ru".equals(authInfo.getLogin())) {
			return new User(1,"Stas", "admin");
		}
		return null;
	}
	public List<News> lastNews(){
		List<News> news = new ArrayList<News>();

		news.add(new News("Базовый курс Java","Идеальный курс для начинающих изучать Java с нуля.","Дата начала: 1 июня | Длительность: 3 месяца | Стоимость: 30,000 руб.","images/path_to_java_basic_course_image.jpg"));
		news.add(new News("Продвинутый Java","Углубленные знания Java для опытных разработчиков.","Дата начала: 15 июля | Длительность: 2 месяца | Стоимость: 40,000 руб.","images/path_to_advanced_java_course_image.jpg"));
		news.add(new News("Java для веб-разработки","Курс охватывает серверную разработку на Java и фреймворки.","Дата начала: 20 августа | Длительность: 4 месяца | Стоимость: 45,000 руб.","images/path_to_web_java_course_image.jpg"));

		return news;

	}

}
