package com.bguf.netty.chatDemo.server;

import com.mysql.cj.xdevapi.SessionFactory;

/**
 * @author gufb
 * @date 2021-10-12 15:38
 */
public class LoginRequestMessageHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage loginRequestMessage) throws Exception {
        String userName = loginRequestMessage.getUsername();
        String password = loginRequestMessage.getPassword();
        boolean login = UserServiceFactory.getUserService().login(userName, password);
        LoginResponseMessage loginResponseMessage = null;
        if (login) {
            loginResponseMessage = new LoginResponseMessage(login, "登录成功");
            SessionFactory.getSession().bind(ctx.channel(), userName);
        } else {
            loginResponseMessage = new LoginResponseMessage(login, "用户名或密码不正确");
        }
        ctx.writeAndFlush(loginResponseMessage);
    }
}
