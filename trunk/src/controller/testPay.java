package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.services.AdaptivePaymentsService;
import com.paypal.svcs.types.ap.PayRequest;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.ap.ReceiverList;
import com.paypal.svcs.types.ap.Receiver;
import com.paypal.svcs.types.common.RequestEnvelope;
@ManagedBean(name= "payMBean")
@ViewScoped
public class testPay {
	public void pay(){
		PayRequest payRequest = new PayRequest();
		  
		List<Receiver> receivers = new ArrayList<Receiver>();
		Receiver receiver = new Receiver();
		receiver.setAmount(1.00);
		receiver.setEmail("merchant@QLXK.com");
		receivers.add(receiver);
		ReceiverList receiverList = new ReceiverList(receivers);

		payRequest.setReceiverList(receiverList);

		RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");
		payRequest.setRequestEnvelope(requestEnvelope); 
		payRequest.setActionType("PAY");
		payRequest.setCancelUrl("https://devtools-paypal.com/guide/ap_simple_payment?cancel=true");
		payRequest.setReturnUrl("https://devtools-paypal.com/guide/ap_simple_payment?success=true");
		payRequest.setCurrencyCode("USD");
		payRequest.setIpnNotificationUrl("http://replaceIpnUrl.com");

		Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", "sandbox");
		sdkConfig.put("acct1.UserName", "merchant_api1.QLXK.com");
		sdkConfig.put("acct1.Password", "1400294553");
		sdkConfig.put("acct1.Signature","ArX6vf1XJgnyPK3-dxT5Ar.JyJD8Ax59Vl6NMBKeAO3-wYJc127l3g0v");
		sdkConfig.put("acct1.AppId","APP-80W284485P519543T");

		AdaptivePaymentsService adaptivePaymentsService = new AdaptivePaymentsService(sdkConfig);
		try {
			PayResponse payResponse = adaptivePaymentsService.pay(payRequest);
		} catch (SSLConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidCredentialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidResponseDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientActionRequiredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MissingCredentialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
