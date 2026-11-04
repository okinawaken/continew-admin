package top.continew.admin.auth;

import top.continew.admin.auth.enums.AuthTypeEnum;
import top.continew.admin.auth.model.req.LoginReq;
import top.continew.admin.auth.model.resp.LoginResp;
import top.continew.admin.system.model.resp.ClientResp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 登录处理器
 *
 * @param <T> 登录请求参数类型
 * @author KAI
 * @author Charles7c
 * @since 3.5.0
 */
public interface LoginHandler<T extends LoginReq> {

    /**
     * 登录
     *
     * @param req     登录请求参数
     * @param client  客户端信息
     * @param request  请求对象
     * @param response 响应对象
     * @return 登录响应参数
     */
    LoginResp login(T req, ClientResp client, HttpServletRequest request,
        HttpServletResponse response);

    /**
     * 登录前置处理
     *
     * @param req     登录请求参数
     * @param client  客户端信息
     * @param request 请求对象
     */
    void preLogin(T req, ClientResp client, HttpServletRequest request);

    /**
     * 登录后置处理
     *
     * @param req     登录请求参数
     * @param client  客户端信息
     * @param request 请求对象
     */
    void postLogin(T req, ClientResp client, HttpServletRequest request);

    /**
     * 获取认证类型
     *
     * @return 认证类型
     */
    AuthTypeEnum getAuthType();
}
