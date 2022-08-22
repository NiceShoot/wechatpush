package cn.xibei.wechat.utils;

import cn.xibei.wechat.config.rest.HttpInterceptor;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: intelligent_scheduling
 * @description:
 * @author: jiabing
 * @create: 2020-04-29 10:23
 **/
@Component
public class RestTemplateUtil {

    // 注入实例
    private static RestTemplate restTemplate;
    static {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(60000);// 超时时间
        requestFactory.setReadTimeout(60000);
        restTemplate = new RestTemplate(requestFactory);
        restTemplate.getInterceptors().add(new HttpInterceptor());
    }

    /**
     * post请求---表单提交
     * @param url
     * @param paramMap
     * @param paramHeader
     * @return
     * @throws Exception
     */
    public static String doPostForm(String appName,String url, Map<String,String> paramMap, Map<String,String> paramHeader) throws Exception {
        // header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Accept-Encoding", "UTF-8");
        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        if (!paramHeader.isEmpty()){
            paramHeader.keySet().forEach(key -> headers.add(key,paramHeader.get(key)));
        }
        // 参数
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (!paramMap.isEmpty()){
            paramMap.keySet().forEach(key -> params.add(key,paramMap.get(key)));
        }
        HttpEntity<LinkedMultiValueMap> formEntity = new HttpEntity<>(params,headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, formEntity, String.class);
        return exchange.getBody();
    }

    /**
     * post请求---payload提交，@requestBody  类型
     * @param url
     * @param paramBody
     * @param paramHeader
     * @return
     * @throws Exception
     */
    public static String doPostPayLoad(String url, Map<String,Object> paramBody, Map<String,String> paramHeader) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Accept-Encoding", "UTF-8");
        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json; charset=UTF-8");
        if (!paramHeader.isEmpty()){
            paramHeader.keySet().forEach(key -> headers.add(key,paramHeader.get(key)));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(paramBody);
        HttpEntity<String> formEntity = new HttpEntity<>(string,headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, formEntity, String.class);
        return exchange.getBody();
    }

    public static String doPostPayLoadStr(String url, Map<String,String> paramBody, Map<String,String> paramHeader) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Accept-Encoding", "UTF-8");
        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json; charset=UTF-8");
        if (!paramHeader.isEmpty()){
            paramHeader.keySet().forEach(key -> headers.add(key,paramHeader.get(key)));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(paramBody);
        HttpEntity<String> formEntity = new HttpEntity<>(string,headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, formEntity, String.class);
        return exchange.getBody();
    }

    /**
     * get请求
     * @throws Exception
     */
    public static String doGet(  String url, Map<String,Object> paramMap, Map<String,String> paramHeader)  {
        // header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Accept-Encoding", "UTF-8");
        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json; charset=UTF-8");
        if (!paramHeader.isEmpty()){
            paramHeader.keySet().forEach(key -> headers.add(key,paramHeader.get(key)));
        }
        // url
        StringBuilder builder = new StringBuilder("?");
        if (!paramMap.isEmpty()){
            paramMap.keySet().forEach(key -> builder.append(key).append("=").append(paramMap.get(key).toString()).append("&"));
        }
        HttpEntity<String> formEntity = new HttpEntity<>(null,headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url + builder, HttpMethod.GET, formEntity, String.class);
        return exchange.getBody();
    }

    /**
     * 截取参数
     * @param url
     * @return
     */
    public static String getParameter(String url) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            final String charset = "utf-8";
            url = URLDecoder.decode(url, charset);
            if (url.indexOf('?') != -1) {
                final String contents = url.substring(url.indexOf('?') + 1);
                String[] keyValues = contents.split("&");
                for (int i = 0; i < keyValues.length; i++) {
                    String key = keyValues[i].substring(0, keyValues[i].indexOf("="));
                    String value = keyValues[i].substring(keyValues[i].indexOf("=") + 1);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }



//    private static String getUrlWithParams(String url, Map<String, Object> params) {
//        if (MapUtils.isNotEmpty(params)) {
//            StringBuilder paramsSb = new StringBuilder();
//            paramsSb.append("?");
//
//            params.keySet().forEach(k -> paramsSb.append(String.format("%s={%s}", k, k)).append('&'));
//            url = url + paramsSb;
//        }
//
//        return url;
//    }
//
//    private static HttpHeaders getHeaders(Map<String, String> headerMap) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept", "application/json");
//        headers.add("Accept-Encoding", "UTF-8");
//        headers.add("Content-Encoding", "UTF-8");
//        headers.add("Content-Type", "application/json; charset=UTF-8");
//        if (MapUtils.isNotEmpty(headerMap)) {
//            headerMap.forEach(headers::add);
//        }
//
//        return headers;
//    }



    /**
     * get请求
     */
//    public static <T> T getForObject(String url, Map<String, Object> params, Map<String, String> headerMap, Class<T> responeType) {
//        HttpHeaders headers = getHeaders(headerMap);
//        url = getUrlWithParams(url, params);
//
//        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
//        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, responeType, params);
//        return responseEntity.getBody();
//    }

    /**
     * get请求
     */
//    public static <T> T getForObject(String url, Map<String, Object> params, Map<String, String> headerMap, ParameterizedTypeReference<T> responeType) {
//        HttpHeaders headers = getHeaders(headerMap);
//        url = getUrlWithParams(url, params);
//
//        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
//        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, responeType, params);
//        return responseEntity.getBody();
//    }

    /**
     * get请求
     */
//    public static <T> T getForObject(String url, Map<String, Object> params, Class<T> responeType) {
//        return getForObject(url, params, null, responeType);
//    }

    /**
     * get请求
     */
//    public static <T> T getForObject(String url, Map<String, Object> params, ParameterizedTypeReference<T> responeType) {
//        return getForObject(url, params, null, responeType);
//    }
}
