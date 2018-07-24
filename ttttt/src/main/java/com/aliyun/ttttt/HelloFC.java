package com.aliyun.ttttt;

import com.aliyun.fc.runtime.Context;
import com.aliyun.fc.runtime.Credentials;
import com.aliyun.fc.runtime.StreamRequestHandler;
import com.aliyun.oss.OSSClient;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.json.JSONException;
import org.json.JSONObject;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
public class HelloFC implements StreamRequestHandler {
   
    public void handleRequest(
        InputStream inputStream, OutputStream outputStream, Context context) throws IOException {   
    	 
    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
         StringBuffer stringBuffer = new StringBuffer();
         String string = "";
         while ((string = bufferedReader.readLine()) != null) {
             stringBuffer.append(string);
         }
         
         try {
			JSONObject input = new JSONObject(stringBuffer.toString());
		} catch (JSONException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}  
         
        IClientProfile profile = DefaultProfile.getProfile("ap-southeast-1", "LTAI96smm3oSrSMN", "C06yGJs8lupkNeqEHXtasT6tMy9tSP");
        // 如果是除杭州region外的其它region（如新加坡region）， 需要做如下处理
       
        try {
        DefaultProfile.addEndpoint("dm.ap-southeast-1.aliyuncs.com", "ap-southeast-1", "Dm",  "dm.ap-southeast-1.aliyuncs.com");
        } catch (ClientException e) {
        e.printStackTrace();
        }
        
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        
        try {
            request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName("estrella@howardhaha.men");
            request.setFromAlias("estrella");
            request.setAddressType(1);
            request.setReplyToAddress(true);
            request.setToAddress("estrellaouyang@gmail.com");
            request.setSubject("test");
            request.setHtmlBody(input.get());
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (ServerException e1) {
            e1.printStackTrace();
        }
        catch (ClientException e2) {
            e2.printStackTrace();
        }    
       }    
       
  }


