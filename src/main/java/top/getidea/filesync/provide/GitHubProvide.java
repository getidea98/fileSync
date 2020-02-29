package top.getidea.filesync.provide;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import top.getidea.filesync.DTO.GitHubAccess;
import top.getidea.filesync.DTO.GitHubAccessTokenDTO;

import java.io.IOException;

/**
 * @Description :
 * @Author : Getidea
 * @Date: 2020-02-29 10:56
 */
public class GitHubProvide {
    /**
     * 获取结果码
     * 1 通过OkHttp向服务器发送post请求，将本次请求的信息通过JSON传递
     * 2 将结果码返回上层
     */

    MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    public String getGitHubAccessToken(GitHubAccessTokenDTO gitHubAccessTokenDTO){
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(gitHubAccessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            return result.split("&")[0].split("=")[1];
        } catch (IOException e) {

        }
        return null;
    }

    /**
     *	通过结果码访问该账户的信息
     */
    public GitHubAccess getGitHubAccessInfo(String Access_token){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+Access_token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GitHubAccess gitHubAccess = JSON.parseObject(string, GitHubAccess.class);
            return gitHubAccess;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
