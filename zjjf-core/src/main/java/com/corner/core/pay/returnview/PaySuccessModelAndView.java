package com.corner.core.pay.returnview;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;

public class PaySuccessModelAndView {
	
	private State state;
	private String miboServiceType ;
	private Map<String,String> map = new HashMap<String,String>();
	private Model model ;
	private String  view ="" ;
	
	public PaySuccessModelAndView(Map<String,String> map ,Model model,String miboServiceType,State state){
		this.setMap(map);
		this.state=state;
		this.setModel(model);
		this.miboServiceType=miboServiceType;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	//触发器
	public  void request(){
		 state.handle(this);
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Map<String,String> getMap() {
		return map;
	}

	public void setMap(Map<String,String> map) {
		this.map = map;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getMiboServiceType() {
		return miboServiceType;
	}

	public void setMiboServiceType(String miboServiceType) {
		this.miboServiceType = miboServiceType;
	}
}
