package mybatis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserController {
	public static ApplicationContext context;

	static {
		context = new ClassPathXmlApplicationContext("mybatis/principle/ApplicationContext.xml");
	}

	UserDao userDao;

	User user;

	UserController(UserDao userDao) {

		this.userDao = userDao;
		user = new User();
	}

	public String addData() {
		user.setId(1);
		user.setDept("天一阁主");
		user.setName("灵魂收割者");
		user.setPhone("123");
		user.setWebsite("阿卡丽");
		userDao.addUser(user);
		user.setId(2);
		user.setDept("地一宗主");
		user.setName("仙灵女巫");
		user.setPhone("123");
		user.setWebsite("璐璐");
		userDao.addUser(user);
		return "添加成功";
	}

	public String queryData() {
		String result;
		user.setName("灵魂收割者");
		result = userDao.getUser(user).getName();
		user.setName("仙灵女巫");
		result = result + userDao.getUser(user).getName();
		return result;
	}

	public String Update() {
		user.setId(2);
		user.setPhone("1111111");
		userDao.updateUser(user);
		return "修改成功";
	}

	public String DeleteDate() {
		userDao.deleteUser(1);
		userDao.deleteUser(2);
		return "删除成功";
	}

	public static void main(String[] args) {
		UserDao userDao = (UserDao) UserController.context.getBean("userDao");
		UserController userController = new UserController(userDao);
		userController.testSql();
	}

	public void testSql() {
		System.out.println(DeleteDate());
		System.out.println(addData());
		System.out.println(queryData());
		System.out.println(Update());
	}

}
