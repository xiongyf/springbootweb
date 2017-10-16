package com.springbootweb.common.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class ExceptionController implements ErrorController {

	@RequestMapping(value = "/error")
	public Map<String, Object> error(HttpServletRequest request) {
		DefaultErrorAttributes defaultErrorAttributes = new DefaultErrorAttributes();
		Map<String, Object> errorAttributes = defaultErrorAttributes
				.getErrorAttributes(new ServletRequestAttributes(request), false);
		if ("org.apache.shiro.authz.UnauthorizedException".equals(errorAttributes.get("exception"))) {
			errorAttributes.put("status", 403);
			errorAttributes.put("exception", "没有权限操作");
		}
		errorAttributes.put("success", false);
		return errorAttributes;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
