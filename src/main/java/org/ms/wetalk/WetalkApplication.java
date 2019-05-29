package org.ms.wetalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ServletComponentScan
// @RequestMapping("/app")
public class WetalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(WetalkApplication.class, args);
	}

	// @Bean
	// public ServletRegistrationBean servletRegistrationBean() {
	// 	ServletRegistrationBean servlet = new ServletRegistrationBean(new ServerStarterServlet(), "/servlet/test");
	// 	servlet.setLoadOnStartup(1);
	// 	return servlet;
	// }

	// //在控制器类中，你可以有一个默认的处理方法，它可以在有一个向默认 URI 发起的请求时被执行
	// @RequestMapping
	// public String defaultHandler(){
	// 	return "default handle";
	// }
    //
    //
    // @RequestMapping("/hello")
    // public String hello() {
		// return "hello world";
    // }
    //
	// @RequestMapping("/list")
	// public List<String> list() {
	// 	List<String> list = new ArrayList<>();
	// 	list.add("1");
	// 	list.add("2");
	// 	list.add("3");
	// 	list.add("4");
	// 	return list;
	// }
    //
	// @RequestMapping("none")
	// public String none() {
	// 	return "none";
	// }
    //
	// @RequestMapping("/id")
	// // @RequestMapping(value = "id", params = {"id=10"})  //限制只处理参数id为10的请求
	// public String getById(@RequestParam("id") String anyId) {
	// 	return "your id is " + anyId;
	// }

}
