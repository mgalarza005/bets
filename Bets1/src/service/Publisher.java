package service;
import javax.xml.ws.Endpoint;

import businessLogic.BLFacadeImplementation;
public class Publisher {
	public static void main (String args[]){
		Endpoint.publish("http://0.0.0.0:9999/ws",
				new BLFacadeImplementation());
		System.out.println("Service published");
	}
}