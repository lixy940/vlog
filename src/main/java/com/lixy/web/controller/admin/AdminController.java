package com.lixy.web.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lixy.model.domain.ArticleCustom;
import com.lixy.model.domain.Link;
import com.lixy.model.domain.Log;
import com.lixy.model.dto.JsonResult;
import com.lixy.model.dto.LogConstant;
import com.lixy.model.dto.MaydayConst;
import com.lixy.model.enums.PostType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.lixy.service.ArticleService;
import com.lixy.service.AttachmentService;
import com.lixy.service.LinksService;
import com.lixy.service.UserService;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.servlet.ServletUtil;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author : 宋浩志
 * @createDate : 2018年8月27日
 */
@RequestMapping(value = "/admin")
@Controller
public class AdminController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private LinksService linksService;
	@Autowired
	private AttachmentService attachmentService;

	/**
	 * 后台首页
	 * 
	 * @return
	 */
	@RequestMapping(value = {"","index" })
	public String index(Model model) {
		// 查询已发布文章数
		Integer countPublish = articleService.countByStatus(null, PostType.POST_TYPE_POST.getValue(),null);
		model.addAttribute("countPublish", countPublish);
		// 友链总数
		List<Link> lists = linksService.findLinks();
		model.addAttribute("countLinks", lists.size());
		// 附件总数
		int countAttachment = attachmentService.countAttachment().size();
		model.addAttribute("countAttachment", countAttachment);
		// 成立天数
		Date blogStart=DateUtil.parse(MaydayConst.OPTIONS.get("blog_start").toString());
		model.addAttribute("establishDate", DateUtil.between(blogStart, DateUtil.date(), DateUnit.DAY));
		// 查询最新的文章
		ArticleCustom articleCustom = new ArticleCustom();
		articleCustom.setArticlePost(PostType.POST_TYPE_POST.getValue());
		PageInfo<ArticleCustom> pageInfo = articleService.findPageArticle(1, 5, articleCustom);
		model.addAttribute("articles", pageInfo.getList());
		// 查询最新的日志
		PageInfo<Log> info = logService.findLogs(1, 5);
		model.addAttribute("logs", info.getList());
		return "admin/admin_index";
	}

	/**
	 * 登录
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String login(Model model) {
//		User user = (User) session.getAttribute(MaydayConst.USER_SESSION_KEY);
//		if (user != null) {
//			return "redirect:/admin";
//		}
		return "admin/admin_login";
	}

	/**
	 * 验证
	 * 
	 * @param userName
	 *            用户名
	 * @param userPwd
	 *            用户密码
	 * @param session
	 * @return
	 */
	@PostMapping(value = "getLogin")
	@ResponseBody
	public JsonResult getLogin(@RequestParam(value = "userName") String userName,
							   @RequestParam(value = "userPwd") String userPwd, HttpSession session) {
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(userName, userPwd);
			//获取当前的Subject
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.login(token);
			// 添加登录日志
			logService.save(new Log(LogConstant.LOGIN, LogConstant.LOGIN_SUCCES, ServletUtil.getClientIP(request),
					DateUtil.date()));
			return new JsonResult(true, "登录成功");
		} catch (Exception e) {
			// 添加失败日志
			logService.save(new Log(LogConstant.LOGIN, LogConstant.LOGIN_ERROR, ServletUtil.getClientIP(request),
					DateUtil.date()));
			return new JsonResult(false, "未知错误！");
		}
/*			// 禁止时间10分钟
			int inhibitTime = 10;
			// 为true禁止登录
			String flag = "true";
			// 错误总次数5次
			int errorCount = 5;
			// 已注册用户
			User users = userService.findUser();
			// 判断账户是否被禁用十分钟
			Date date = DateUtil.date();
			if (users.getLoginLastTime() != null) {
				date = users.getLoginLastTime();
			}
			// 计算两个日期之间的时间差
			long between = DateUtil.between(date, DateUtil.date(), DateUnit.MINUTE);
			if (StrUtil.equals(users.getLoginEnable(), flag) && (between < inhibitTime)) {
				return new JsonResult(false, "账户被禁止登录10分钟，请稍后重试");
			}
			// 验证用户名密码
			User user = userService.getByNameAndPwd(userName, SecureUtil.md5(userPwd));*/

			// 修改最后登录时间
			/*userService.updateLoginLastTime(DateUtil.date(), users.getUserId());
			if (user != null) {
				session.setAttribute(MaydayConst.USER_SESSION_KEY, user);
				// 登录成功重置用户状态为正常
				userService.updateUserNormal(user.getUserId());
				// 添加登录日志
				logService.save(new Log(LogConstant.LOGIN, LogConstant.LOGIN_SUCCES, ServletUtil.getClientIP(request),
						DateUtil.date()));
				log.info(userName + "登录成功");
				return new JsonResult(true, "登录成功");
			} else {
				Integer error = userService.updateError();
				if (error == errorCount) {
					userService.updateLoginEnable("true",0);
				}else if(error==1) {
					userService.updateLoginEnable("false",1);
				}
				// 添加失败日志
				logService.save(new Log(LogConstant.LOGIN, LogConstant.LOGIN_ERROR, ServletUtil.getClientIP(request),
						DateUtil.date()));
				return new JsonResult(false, "用户名或密码错误！你还有" + (5 - error) + "次机会");
			}
		} catch (Exception e) {
			log.error("登录失败，系统错误！",e);
			return new JsonResult(false, "未知错误！");
		}*/
	}

//	/**
//	 * 注销登录
//	 *
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping("/exitLogon")
//	public String exitLogon(HttpSession session) {
//		session.invalidate();
//		return "admin/admin_login";
//	}

	/**
	 * 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
	 *
	 * @param redirectAttributes
	 * @return
	 */
	@GetMapping("/exitLogon")
	public String exitLogon(RedirectAttributes redirectAttributes) {
		// http://www.oschina.net/question/99751_91561
		// 此处有坑： 退出登录，其实不用实现任何东西，只需要保留这个接口即可，也不可能通过下方的代码进行退出
//         SecurityUtils.getSubject().logout();
		// 因为退出操作是由Shiro控制的       filterChainDefinitionMap.put("/logout", "logout");
		redirectAttributes.addFlashAttribute("message", "您已安全退出");
		return "admin/admin_login";
	}
}
