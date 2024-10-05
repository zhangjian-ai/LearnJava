package com.example.jmeter_plugins;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class DemoSampler extends AbstractJavaSamplerClient {

    private static final String baseUrl = "http://www.httpbin.org/";
    private static final String urlPath = "post";


    @Override // 父类本方法返回null，表示默认是没有参数的。这里配置参数，可以在jmeter界面上传值进来
    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();

        // 添加参数及默认值
        arguments.addArgument("name", null);
        arguments.addArgument("age", null);

        return arguments;
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sampleResult = new SampleResult();

        // 获取请求参数，获取的参数只能是自定义的参数
        String name = javaSamplerContext.getParameter("name");
        String age = javaSamplerContext.getParameter("age");

        // 发起网络请求
        String url = baseUrl + urlPath;

        // 1. 设置请求数据，展示在jmeter结果树界面
        HashMap<String, String> body = new HashMap<>();
        body.put("name", name);
        body.put("age", age);

        HashMap<String, String> header = new HashMap<>();
        String key = "Content-Type";
        header.put(key, "application/json");

        // 2. 创建请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(new BasicHeader(key, header.get(key)));  // 设置请求头
        httpPost.setEntity(new StringEntity(JSON.toJSONString(body), "utf8"));  // 设置请求体

        // jmeter 结果树请求体信息
        sampleResult.setRequestHeaders(JSON.toJSONString(header));
        sampleResult.setSamplerData(JSON.toJSONString(body));

        // 3. 创建请求和响应实例
        CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(3000).setConnectTimeout(3000).setSocketTimeout(2000).build()).build();
        CloseableHttpResponse response;

        // 4. 发起请求调用
        try {
            // 开始计时
            sampleResult.sampleStart();
            // 发起请求
            response = client.execute(httpPost);
            // 结束计时
            sampleResult.sampleEnd();
            // 处理响应结果
            HttpEntity responseEntity = response.getEntity();
            JSONObject responseJsonObject = JSONObject.parseObject(EntityUtils.toString(responseEntity, "utf8"));

            // jmeter 结果树响应体信息
            sampleResult.setResponseHeaders(JSON.toJSONString(responseJsonObject.get("headers")));
            responseJsonObject.remove("headers");
            sampleResult.setResponseData(responseJsonObject.toJSONString(), "utf8");

            // 设置jmeter请求状态
            sampleResult.setSuccessful(true);
        } catch (IOException e) {
            e.printStackTrace();

            // jmeter 结果树响应体信息
            sampleResult.setResponseData(e.getMessage(), SampleResult.TEXT);
            // 设置请求状态
            sampleResult.setSuccessful(false);
        }

        return sampleResult;
    }
}
